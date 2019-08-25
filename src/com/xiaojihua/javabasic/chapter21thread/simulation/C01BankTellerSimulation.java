package com.xiaojihua.javabasic.chapter21thread.simulation;

/**
 * 知识点：
 * 模拟银行出纳服务客户，根据客户的排队数量动态的调整出纳人数，客户的到达时间以及
 * 客户的服务时间均随机。
 *
 * 这个模拟可以代表这样一种情形：
 * 多个对象随机的出现并且需要限定数量的服务者对其进行随机时间的处理。
 */

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 总控类
 */
public class C01BankTellerSimulation {
    private static final int SIZE = 50;
    private static final int PRIOD = 1000;
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(SIZE);
        service.execute(new CustomerGen(customers));
        service.execute(new TellerManager(customers,PRIOD,service));
        System.out.println("press enter to shutdown");
        System.in.read();
        service.shutdownNow();
    }
}

/**
 * 顾客类。
 * 此类是一个只读对象，因为只包含一个final字段，永远不会更改，
 * 因此不需要synchronized或者volatile来控制，
 * 并且Teller每次只能获取一个Customer并且对其进行服务，因为
 * 是从ArrayBlockingQueue中取，服务完成后就从队列中删除，因此
 * Customer只能同时被一个线程访问
 */
class Customer{
    private final int serviceTime;

    public Customer(){
        this.serviceTime = 1000;
    }
    public Customer(int serviceTime){
        this.serviceTime = serviceTime;
    }

    public int getServiceTime(){
        return serviceTime;
    }

    public String toString(){
        return "[" + serviceTime + "]";
    }
}

/**
 * 客户队列，继承自ArrayBlockingQUeue
 *
 */
class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int size){
        super(size);
    }

    public String toString(){
        if(this.size() == 0){
            return "[empty]";
        }
        StringBuilder strBuilder = new StringBuilder();
        for(Customer c : this){
            strBuilder.append(c);
        }
        return strBuilder.toString();
    }
}

/**
 * Customer的生产类，并将生产出来的Customer加入到队列中
 */
class CustomerGen implements Runnable{
    private CustomerLine customers;
    private static Random random = new Random(47);
    public CustomerGen(CustomerLine customers){
        this.customers = customers;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                /*
                    这里使用put，而并非offer是因为，ArrayBlockingQueue的put
                    方法是指将元素插入到队列的末尾，如果队列满了那么就阻塞直到队列
                    有可用位置来存放，offer也是插入元素到队列的末尾，但是如果队列
                    满了的话就返回false，代表插入失败，offer也提供了一个重载方法
                    可以指定等待的时间，如果超时后还是没有可用的位置使用，那么仍然
                    返回false
                 */
                customers.put(new Customer(random.nextInt(1000)));
            }
        }catch(InterruptedException e){
            System.out.println("Customer Generator interrupted!");
        }
        System.out.println("Customer Generator Completed!");
    }
}

/**
 * Tller（出纳员类）
 * 从Customers中获取一个Customer并且对其进行服务，记录在一段时间内
 * 服务的Customer数量，同时提供doSomeThingElse方法来处理当customer
 * 比较少的时候让此Teller去做其他事情，以及serving方法来处理当有足够
 * 多的Customer的时候让此Teller从空闲事情转而处理Customer服务。
 *
 * 同时实现了Comparable接口，通过服务过得Customer数量来进行优先级
 * 排序，是的服务最少数量的Teller排在最前面，进而可以让这些Teller
 * 去做其他事情，然后有足够Customer的时候在回来
 */
class Teller implements Runnable, Comparable<Teller>{
    private CustomerLine customers;
    private static int count = 0;
    private final int id = count++;
    private int servicedCustomers;
    private boolean isServicing = true;

    public Teller(CustomerLine customers){
        this.customers = customers;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){

                /*
                    这里使用了take，下面注释的一段使用了poll
                    这两个都是ArrayBlockingQueue的方法，区别在于：
                    take返回队头元素，如果当前没有可用元素则等待知道有可用
                    元素为止。
                    poll也是返回队头元素，但是如果当前没有可用元素那么
                    将返回null，poll提供了一个重载的方法可以指定当队列
                    为空的时候等待的时间，超时仍然返回null

                    如果使用了下面的那段注释的方式来操作的话，实际上当
                    队列中没有值的时候是在不断的循环取值
                 */
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized(this){
                    servicedCustomers++;
                    while(!isServicing){
                        wait();
                    }
                }

                /*Customer comstomer = customers.poll();
                if(comstomer != null){
                    TimeUnit.MILLISECONDS.sleep(comstomer.getServiceTime());
                    synchronized(this){
                        servicedCustomers++;
                        while(!isServicing){
                            //wait()必须放到synchronized中？
                            wait();
                        }
                    }
                }*/


            }
        }catch(InterruptedException e){
            System.out.println(this + " interrupted!");
        }
        System.out.println(this + " completed!");
    }

    public synchronized void doSomeThingElse(){
        servicedCustomers = 0;
        isServicing = false;
    }

    public synchronized void serving(){
        //注意用法
        assert !isServicing : "already serving " + this;
        isServicing = true;
        notifyAll();
    }

    @Override
    public String toString(){
        return "Teller:" + id;
    }

    /**
     * 这个方法也应该是synchronized因为牵扯到共享资源的关键读取
     * 读取的时候加锁不让其他线程对资源进行更改，保证了资源的同步。
     * @param other
     * @return
     */
    @Override
    public synchronized int compareTo(Teller other){
        return servicedCustomers < other.servicedCustomers ? -1 :(servicedCustomers == other.servicedCustomers ? 0 : 1);
    }
}

/**
 * 中心控制器，用来根据顾客数量动态调整Teller的数量，并且每次调增完成以后将
 * 结果输出出来。
 */
class TellerManager implements Runnable{
    private CustomerLine customers;//客户队列
    //工作中的Teller队列，是一个优先队列，服务人数最少的Teller在最前面，然后会被空闲化
    private PriorityBlockingQueue<Teller> workingQueue  = new PriorityBlockingQueue<>();
    //做其他工作的Teller队列，是一个按照插入顺序的队列，用于将服务人数最少的Teller恢复
    private LinkedBlockingQueue<Teller> doOtherThingQueue = new LinkedBlockingQueue<>();
    private int period;//调整的时间间隔
    private ExecutorService service;

    public TellerManager(CustomerLine customers, int period,ExecutorService service){
        this.customers = customers;
        this.period = period;
        this.service = service;

        //保证有一个teller初始化运行
        Teller teller = new Teller(customers);
        service.execute(teller);
        workingQueue.offer(teller);
    }

    /**
     * 调整方法
     */
    public void adjustMethod(){
        Teller teller = null;
        //当Customer数量够多的时候增加Teller
        if(customers.size() / workingQueue.size() > 2){
            //从doOtherThingQueue队列中恢复
            if(doOtherThingQueue.size() > 0){
                teller = doOtherThingQueue.remove();
                teller.serving();
                workingQueue.offer(teller);
            }else{
                //新建
                teller = new Teller(customers);
                service.execute(teller);
                workingQueue.offer(teller);
            }
            //返回，不做下面的判断了
            return;
        }

        //Customer数量少的时候减少Teller
        if((customers.size() / workingQueue.size() <= 2) && workingQueue.size() > 1){
            reassignOneTeller();
        }

        //没有客户的时候保证只有一个Teller
        if(customers.size() == 0){
            while(workingQueue.size() > 1){
                reassignOneTeller();
            }
        }
    }

    /**
     * 公用方法：减少一个服务中的Teller
     */
    public void reassignOneTeller(){
        Teller teller = workingQueue.poll();
        teller.doSomeThingElse();
        doOtherThingQueue.offer(teller);
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                //先调整，然后打印调整后的队列以及Teller列表
                TimeUnit.MILLISECONDS.sleep(period);
                adjustMethod();
                System.out.println(customers + " {" + workingQueue + "}");
            }
        }catch(InterruptedException e){
            System.out.println("Teller Manager interrupted!");
        }
        System.out.println("Teller Manager completed!");
    }
}



package com.xiaojihua.javabasic.chapter21thread.simulation;

/**
 * 知识点：
 * 模拟银行出纳服务客户，根据客户的排队数量动态的调整出纳人数，客户的到达时间以及
 * 客户的服务时间均随机。
 */

import java.util.Random;
import java.util.concurrent.*;

/**
 * 总控类
 */
public class C01BankTellerSimulation {
}

/**
 * 顾客类
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
 * 客户队列
 */
class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int size){
        super(size);
    }

    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        for(Customer c : this){
            strBuilder.append(c);
        }
        return strBuilder.toString();
    }
}

class CustomerGen implements Runnable{
    private CustomerLine customers;
    private Random random = new Random(47);
    public CustomerGen(CustomerLine customers){
        this.customers = customers;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
                customers.offer(new Customer(random.nextInt(1000)));
            }
        }catch(InterruptedException e){
            System.out.println("Customer Generator interrupted!");
        }
        System.out.println("Customer Generator Completed!");
    }
}

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
                TimeUnit.MILLISECONDS.sleep(customers.remove().getServiceTime());
                synchronized(this){
                    servicedCustomers++;
                    while(!isServicing){
                        //wait()必须放到synchronized中？
                        wait();
                    }
                }

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

    @Override
    public int compareTo(Teller other){
        return servicedCustomers < other.servicedCustomers ? -1 :(servicedCustomers == other.servicedCustomers ? 0 : 1);
    }
}

class TellerManager implements Runnable{
    private CustomerLine customers;
    private PriorityBlockingQueue<Teller> workingQueue;
    private LinkedBlockingQueue<Teller> doOtherThingQueue;
    private int period;
    private ExecutorService service;

    public TellerManager(CustomerLine customers, PriorityBlockingQueue<Teller> workingQueue,
                         LinkedBlockingQueue<Teller> doOtherThingQueue,int period,ExecutorService service){
        this.customers = customers;
        this.workingQueue = workingQueue;
        this.doOtherThingQueue = doOtherThingQueue;
        this.period = period;
        this.service = service;

        //保证有一个teller
        Teller teller = new Teller(customers);
        service.execute(teller);
        workingQueue.offer(teller);
    }

    @Override
    public void run(){

    }
}



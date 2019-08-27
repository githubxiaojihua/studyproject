package com.xiaojihua.javabasic.chapter21thread.simulation;

import com.xiaojihua.javabasic.chapter19enum.C12Meal2;
import com.xiaojihua.javabasic.chapter19enum.C12Meal2.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 分析：
 * 1、需求：
 *    模拟一个饭店，顾客到达后向服务员点餐，服务员下菜单，厨师根据菜单做菜，做好菜以后
 *    反馈给相关服务员，由服务员分发给顾客，顾客用餐。顾客只有吃完一个菜以后才能接收下一道菜。
 *    厨师和服务员有多个。
 *
 * 使用同步队列进行模拟
 */
public class C02RestaurantWithQueues {
    public static void main(String[] args) throws IOException,InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Restruant restruant = new Restruant(2,5,service);
        service.execute(restruant);
        System.out.println("press enter to quit!");
        System.in.read();
        service.shutdownNow();

    }
}

/**
 * 订单类，由waiter传递给restuarant在传递给chief
 */
class Order{
    private static int count = 0;
    private final int id = count++;
    private final Food food;
    private final RWaiter waiter;
    private final RCustomer customer;

    public Order(Food food, RWaiter waiter, RCustomer customer){
        this.food = food;
        this.waiter = waiter;
        this.customer = customer;
    }

    public Food getFood(){
        return this.food;
    }

    public RWaiter getWaiter(){
        return this.waiter;
    }

    public RCustomer getCustomer(){
        return this.customer;
    }


    @Override
    public String toString(){
        return "order:" + id + " food:" + food + " for customer:" + customer + " service by:" +
                waiter;
    }
}

/**
 * 顾客类，通过synchronousQueue来控制一次只能消费一道菜。
 * synchronousQueue相当于没有容量的同步队列，当调用put()的时候
 * 必须等待tack的调用，也就是说，只能管理一个元素put后才能tack，
 * tack后才能put
 */
class RCustomer implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private final RWaiter waiter;
    private SynchronousQueue<Plate> foods = new SynchronousQueue<>();

    public RCustomer(RWaiter waiter){
        this.waiter = waiter;
    }

    public void deliver(Plate plate) throws InterruptedException{
        foods.put(plate);
    }

    @Override
    public void run(){


        for(C12Meal2 meal2 : C12Meal2.values()){
            Food food = meal2.randomNext();
            try{
                //创建菜单并传递给服务员下菜单
                waiter.sendOrder(this, food);
                //吃菜，当没有菜的时候阻塞
                Plate plate  = foods.take();
                System.out.println(this + " is eating " + plate.getFood());
            }catch(InterruptedException e){
                System.out.println(this + " is Interrupted!");
                //e.printStackTrace();
                //这里的break是用来跳出循环的
                //因为当出现异常后没如果没有break将会继续执行for循环
                break;
            }
        }

        System.out.println(this + " completed!");
    }

    @Override
    public String toString(){
        return "customer:" + id;
    }


}

/**
 * waiter类
 */
class RWaiter implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private final Restruant restruant;
    public BlockingQueue<Plate> filledQueue = new LinkedBlockingQueue<>();

    public RWaiter(Restruant restruant){
        this.restruant = restruant;
    }

    public void sendOrder(RCustomer customer, Food food){
        try{
            //构建order对象，并向饭店发送订单
            Order order = new Order(food,this,customer);
            restruant.orders.put(order);
        }catch(InterruptedException e){
            System.out.println(this + " is interrupted");
        }

    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                Plate plate = filledQueue.take();
                RCustomer customer = plate.getOrder().getCustomer();
                System.out.println(this + " deliver " + customer + " ," + plate);
                customer.deliver(plate);

            }
        }catch(InterruptedException e){
            System.out.println("RWaiter is interrupted!");
        }
        System.out.println(this + " completed!");
    }

    @Override
    public String toString(){
        return " Waiter:" + id;
    }


}

class RChif implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private final Restruant restruant;
    private static Random random = new Random(47);
    public RChif(Restruant restruant){
        this.restruant = restruant;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                Order order = restruant.orders.take();
                Food requestFood = order.getFood();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, requestFood);
                order.getWaiter().filledQueue.put(plate);
            }
        }catch(InterruptedException e){
            System.out.println(this + " is Interrupted!");
        }
        System.out.println(this + " completed!");


    }

    @Override
    public String toString(){
        return " Chif:" + id;
    }
}

/**
 * 厨师做完菜以后返回的对象
 */
class Plate{
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food){
        this.order = order;
        this.food = food;
    }

    public Order getOrder(){
        return order;
    }

    public Food getFood(){
        return food;
    }

    @Override
    public String toString(){
        //注意观察这里的输出，枚举类可以直接toString，打印的就是枚举实例名称
        return food.toString();
    }
}

class Restruant implements Runnable{
    private static int count = 0;
    private final int id = count++;
    public BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    private List<RChif> chifList = new ArrayList<>();
    private List<RWaiter> waiters = new ArrayList<>();
    private ExecutorService service;
    private static Random random = new Random(47);

    public Restruant(int chifNum, int waiterNum, ExecutorService service){
        this.service = service;
        for(int i=0; i<chifNum; i++){
            RChif chif = new RChif(this);
            chifList.add(chif);
            service.execute(chif);
        }

        for(int i=0; i<waiterNum; i++){
            RWaiter waiter= new RWaiter(this);
            waiters.add(waiter);
            service.execute(waiter);
        }
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                RWaiter waiter = waiters.get(random.nextInt(waiters.size()));
                RCustomer customer = new RCustomer(waiter);
                service.execute(customer);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(InterruptedException e){
            System.out.println("restaurant interrupted!");
        }
        System.out.println("Restaurant closing");

    }

}



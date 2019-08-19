package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import com.xiaojihua.javabasic.chapter15generic.C04Generate;
import com.xiaojihua.javabasic.chapter15generic.generalPurposeGenerator.C01BasicGenerator;
import com.xiaojihua.javabasic.chapter21thread.newLibaryComponents.semaphore.C02Fat;
import java.util.List;
import java.util.concurrent.*;

/**
 * 知识点：
 * Exchanger的使用。
 * 使用Exchanger可以实现在两个进程之间交换数据。Exchanger就像是一个交换区域，
 * 当一个持有Exchanger的线程调用exchange(object)方法后，就携带着object
 * 进入到这个交换区域，等另一个进入到这个交换区域的线程（同样需要持有相同Exchanger
 * 并调用exchange(Object)方法的线程），然后相互之间交换持有的object对象。
 *
 *
 */
public class C06ExchangerDemo {
    protected static final int SIZE = 20;
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        List<C02Fat> productList = new CopyOnWriteArrayList<>(),
                customerList = new CopyOnWriteArrayList<>();
        Exchanger<List<C02Fat>> ex = new Exchanger<>();
        service.execute(new ExchangerProducer<>(productList,C01BasicGenerator.create(C02Fat.class),ex));
        //service.execute(new ExchangerCustomer<>(customerList,ex));
        service.execute(new ExchangerCustomer<>(customerList,ex));

        TimeUnit.MILLISECONDS.sleep(2000);
        service.shutdownNow();

    }
}

class ExchangerProducer<T> implements Runnable{
    private List<T> productList;
    private C04Generate<T> generator;
    private Exchanger<List<T>> exchanger;

    public ExchangerProducer(List<T> productList,C04Generate<T> generator,Exchanger<List<T>> exchanger){
        this.productList = productList;
        this.generator = generator;
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                for(int i=0; i<C06ExchangerDemo.SIZE; i++){
                    productList.add(generator.next());
                }
                productList = exchanger.exchange(productList);
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}

class ExchangerCustomer<T> implements Runnable{
    private Exchanger<List<T>> exchanger;
    private List<T> customerList;
    private volatile T value;

    public ExchangerCustomer(List<T> customerList, Exchanger<List<T>> exchanger){
        this.customerList = customerList;
        this.exchanger =exchanger;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                exchanger.exchange(customerList);

                for(T x : customerList){
                    value = x;
                    customerList.remove(x);
                }



            }
        }catch(InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Final value:" + value);
    }
}
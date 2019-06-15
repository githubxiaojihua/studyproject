package com.xiaojihua.javabasic.chapter21thread;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 知识点：
 * 1、从任务中获取返回值，需要任务继承Callable而并非Runnable，泛型为返回值的类型，必须实现call方法，必须使用ExecutorService.submit方法调用。
 * 2、
 */
public class C02TaskWithResult implements Callable<String> {
    private int id;
    C02TaskWithResult(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        Thread.yield();
        return "result of TaskWithResult " + id;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            //submit产生Future对象，可以使用isDone来检查Future是否完成，当完成后会有一个结果可以使用get来获取该结果。当Future还没有处理完的时候
            //调用get，那么get会堵塞，直到结果返回
            results.add(service.submit(new C02TaskWithResult(i)));
        }
        System.out.println("in the main Thread");
        for(Future<String> fs : results){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                service.shutdown();
            }
        }
    }
}

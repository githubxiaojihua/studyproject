package com.xiaojihua.javabasic.chapter21.shareResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *对于IntGenerator产生的int进行数值检查是否为偶数
 * 知识点：
 * 1、通过对IntGenerator的使用，是的本类与具体的IntGenerator解耦。
 * 2、通过实现Runnable接口，来使用不同的线程共同访问同一个IntGenerator对象，考察在多线程下共享资源的访问情况。
 */
public class EvenChecker implements Runnable{
    private IntGenerator intGenerator;//共享资源
    private final int id;//每个进程的id
    EvenChecker(IntGenerator it, int id){
        this.intGenerator = it;
        this.id = id;
    }
    @Override
    public void run() {
        while(!intGenerator.isCanceled()){
            int val = intGenerator.next();
            if(val % 2 != 0){
                System.out.println(val + " is not even. ");
                intGenerator.cancel();
            }
        }
    }

    /**
     * 生成多个EvenChecker任务，并同时对传入的IntGenerator进行操作
     * @param it
     * @param count
     */
    public static void test(IntGenerator it, int count){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++){
            service.execute(new EvenChecker(it,i));
        }
        service.shutdown();
    }

    public static void test(IntGenerator it){
        test(it,10);
    }
}

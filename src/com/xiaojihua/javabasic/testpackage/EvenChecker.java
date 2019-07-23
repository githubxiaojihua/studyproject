package com.xiaojihua.javabasic.testpackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    public EvenChecker(IntGenerator generator){
        this.generator = generator;
    }
    @Override
    public void run(){
        while(!generator.isCancle()){
            int num = generator.next();
            if(num % 2 != 0){
                System.out.println(num + " is not even!");
                generator.cancle();
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        EvenGenerator evenGen = new EvenGenerator();
        for(int i=0; i<10; i++){
            service.execute(new EvenChecker(evenGen));
        }
        service.shutdown();

    }
}

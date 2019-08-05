package com.xiaojihua.javabasic.chapter21thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 线程之间通过IO流实现相互之间交流。
 * 通过PipedWriter和PipedReader来配合使用，前者允许线程往管道中写数据
 * 后者允许线程从此管道中读取数据。管道的底层是基于blocking queue。
 *
 * Sender通过PipedWriter创建管道的输入端，Resever通过PipedReader创建管道的
 * 接收端，Resever的PipedReader必须通过Sender的PipedWriter来建立这里才能建立
 * 完整的管道，Resever的PipedReader在读取的时候如果管道中没有数据那么线程会阻塞。
 *
 * 注意管道在使用前（写入数据或者读取数据前）必须正确建立，即有明确的输入方和输出方。否则
 * 不能正常使用（blocking queue更方便和安全)。
 *
 * 使用PipedWriter和PipedReader是可以被interrupted的，这与普通的IO操作是不一样的
 * 正如以前的例子，IO和synchronized阻塞是不能无法使用interrupte进行中断的。
 *
 *
 */
public class C15PipedIO {
    public static void main(String[] args) throws Exception{
        Sender sender = new Sender();
        Resever resever = new Resever(sender);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(sender);
        service.execute(resever);

        TimeUnit.SECONDS.sleep(4);
        service.shutdownNow();
    }
}

class Sender implements Runnable{
    private static Random random = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getOut(){ return out; }

    @Override
    public void run(){
        try{
            while(true){
                for(char c = 'A'; c <= 'z'; c++){
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }

        }catch(IOException e){
            System.out.println(e + " Sender write exception.");
        }catch(InterruptedException e){
            System.out.println(e + " Sender sleep interrupted.");
        }

    }
}

class Resever implements Runnable{
    private PipedReader reader;
    public Resever(Sender sender) throws IOException{

        /*
            这里有个小的知识点：
            下面的这一句要放到Writer调用write方法之前
            PipedReader的建立必须先于Writer的写入操作，否则会报错：java.io.IOException: Pipe not connected
            这是因为在写入writer调用write方法往管道中写数据的前提是pipe被建立起来，
            但是如果放到run中那么当write方法被调用的时候管道只有输入方没有接收方，没有建立起来。
         */
        reader = new PipedReader(sender.getOut());
    }

    @Override
    public void run(){

        try{

            while(true){
                char c = (char)reader.read();
                System.out.println("Read:" + c + ",");
            }
        }catch(IOException e){
            System.out.println(e + " Resever read exception。");
        }

    }
}
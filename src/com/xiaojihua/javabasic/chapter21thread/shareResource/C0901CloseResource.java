package com.xiaojihua.javabasic.chapter21thread.shareResource;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点:
 * 中断ioblock，看可以采用关闭底层引起阻塞的资源来实现。
 * 但是Sytem.in关闭好像不行，与教程中给出的输出是不一样的
 */
public class C0901CloseResource {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        service.execute(new IOBlocked(socketInput));
        service.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("shuting down all threads");
        service.shutdownNow();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("closing " + socketInput.getClass().getName());
        socketInput.close();//底层资源关闭后，线程的isInterrupted复位返回true

        TimeUnit.SECONDS.sleep(1);
        System.out.println("closing " + System.in.getClass().getName());
        System.in.close();

    }
}

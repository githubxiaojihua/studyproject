package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 通过使用NIO可以使用中断sleepblock阻塞一样的方式中断IO堵塞
 */
class NIOBlocked implements Runnable{
    private final SocketChannel sc;
    public NIOBlocked(SocketChannel sc){
        this.sc = sc;
    }
    public void run(){
        System.out.println("Waiting for read() in " + this);
        try {
            sc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("CloseByInterruptException");
        }catch (AsynchronousCloseException e){
            System.out.println("AsynchronousCloseException");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("Exiting NIOBlocked.run() " + this);
    }
}

public class C0902NIOInterruption {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        Future<?> f = service.submit(new NIOBlocked(sc1));
        service.execute(new NIOBlocked(sc2));
        service.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }


}

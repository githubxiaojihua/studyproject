package com.xiaojihua.enterpriseJ2EE.network.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * With a selector, we can use one thread instead of several to manage
 * multiple channels. Context-switching between threads is expensive for the
 * operating system, and additionally, each thread takes up memory.
 * 使用selector我们可以使用一个线程而不是多个线程来管理多个channels。因为在线程中进行上下文切换
 * 也是很耗费资源的。
 *
 * 本例只是演示了如何使用selector和socketChannel，连接的目标是C04，所以可以先启动C04，但是本例只是做
 * 为演示运行过程中是有问题的。
 */
public class C07NonBlockingIO {
    public static void main(String[] args) throws IOException {
        int cPort = 8081;//客户端 端口
        int sPort = 8080;//服务端 端口
        //1、初始化SocketChannel和Selector
        Selector sel = sel = Selector.open();
        SocketChannel ch = SocketChannel.open();

        try{
            //2、将与socketchannal关联的socket绑定到cport
            ch.bind(new InetSocketAddress(cPort));
            //3、设置channel为非阻塞
            ch.configureBlocking(false);
            //4、向selector注册，并声明感兴趣的事件
            //SelectionKey.OP_CONNECT:连接就绪事件，表示客户端与服务器完成了连接
            //SelectionKey.OP_READ：读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
            //SelectionKey.OP_WRITE: 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
            //SelectionKey.OP_ACCEPT: 接收连接就绪事件，表示服务器监听到了客户连接，那么服务器可以接收这个连接了
            ch.register(sel,SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            //5、连接服务器，这里与教程中的不一样必须先connect才能监听到，否则select将一直阻塞。
            InetAddress ad = InetAddress.getLocalHost();
            //运行完这一步服务器端就已经接收到了连接。
            /*If this channel is in non-blocking mode then an invocation of this method initiates
              a non-blocking connection operation. If the connection is established immediately,
              as can happen with a local connection, then this method returns true. Otherwise this
              method returns false and the connection operation must later be completed by invoking
              the finishConnect method.
              如果channel是一个非阻塞的模式那些方法将调用一个非阻塞的连接方法。如果连接当场就建立那么方法返回true，否则
              方法返回false并且需要调用 finishConnection 方法来结束连接操作。
              If this channel is in blocking mode then an invocation of this method will block until
              the connection is established or an I/O error occurs.
              如果channel是一个阻塞模式那么此操作将阻塞直到连接建立或者抛出异常。
              */
            ch.connect(new InetSocketAddress(ad, sPort));
            //6、调用select方法监控注册到selector上的channel。在注册的channel准备好之前是阻塞的，当
            //channel准备进行read\write\connet之后解除阻塞。
            sel.select();
            //6、返回底层注册channel中的已经准备好的事件
            Iterator<SelectionKey> iterator = sel.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isConnectable()){
                    System.out.println("Connect will not block");
                    //完成连接操作
                    /*
                      A non-blocking connection operation is initiated by placing a socket
                      channel in non-blocking mode and then invoking its connect method.
                      Once the connection is established, or the attempt has failed, the socket
                      channel will become connectable and this method may be invoked to complete the
                      connection sequence. If the connection operation failed then invoking this method
                      will cause an appropriate IOException to be thrown.
                      一个非阻塞的连接通过设置一个socket为非阻塞模式而调用，当连接操作建立完成或者连接失败，socket
                      channel将变为connectable，然后需要调用这个方法来结束连接操作。如果连接失败了那么调用这个方法
                      会抛出一个错误。

                      If this channel is already connected then this method will not block and will
                      immediately return true. If this channel is in non-blocking mode then this method
                      will return false if the connection process is not yet complete. If this channel
                      is in blocking mode then this method will block until the connection either
                      completes or fails, and will always either return true or throw a checked
                      exception describing the failure.
                      如果channel已经连接上了那么这个方法会立即返回true。如果channel是非阻塞的那么当连接进行还没有完成
                      的话会返回false。如果channel是阻塞模式那么这个方法将阻塞进到连接成功或者是失败。
                     */
                    ch.finishConnect();
                }
                //下面的状态是不会满足的
                if(key.isReadable())
                    System.out.println("Read will not block");
                if(key.isWritable())
                    System.out.println("Write will not block");
            }
        }finally {
            ch.shutdownOutput();//优雅的结束连接。
            ch.close();
            sel.close();
        }

    }
}

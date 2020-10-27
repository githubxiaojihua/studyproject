package com.xiaojihua.enterpriseJ2EE.network.nio.bealdung;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端
 */
public class EchoServer {
    private static final String POISON_PILL = "POISON_PILL";

    public static void main(String[] args) throws IOException {
        //1.构建Selector
        Selector selector = Selector.open();
        //2.构建ServerSocketChannel，不再从sockect获取阻塞IO流，而是获到一个channel
        //这里可以将socket与channel看作一个对象
        //ServerSocketChannel只是一个listening sockets主要是用来处理ACCEPT操作
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        //3.向serverSocket绑定端口
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        //4.设置为非阻塞模式
        serverSocket.configureBlocking(false);
        //5.注册channel，并对accept事件感兴趣。
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //6.通过ByteBuffer来对channel进行读写。这是NIO的新特性
        ByteBuffer buffer = ByteBuffer.allocate(256);
        //7.开始监控，通过一个无限循环来模拟服务器的不间断监控，也就是不断的调用select()
        //来对channels进行监控，监控到了就进行处理，处理完了再监控
        while (true) {
            //8.在有任何channel事件触发前阻塞。（注意阻塞是当前线程停止，并让出CPU）
            //执行select操作来监控ready channels(有相关事件触发的channel)
            //此方法还有一个重载的方法：select(long timeout)用于设置阻塞等待时间
            //timeout为正值则等待指定时间或直到有事件触发，为0则一直等待或有事件触发，不能为负值
            selector.select();
            //9.获得监听到的有相关事件触发的channels的SelectionKey集合(具体内容看总结中的SelectionKey组成部分)
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            //10.遍历selectedKeys
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                //11.获得SelectionKey对象，并判断是哪一类事件。
                SelectionKey key = iter.next();
                //12.accept事件触发，说明服务器接收了客户端的连接（真下的连接需要使用serverSocket.accept()建立），
                //则创建一个socketchannel（一个connecting sockets，可以进行read和write），
                // 并注册read事件到selector
                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }
                //13.read事件
                if (key.isReadable()) {
                    answerWithEcho(buffer, key);
                }
                //处理完事件后，需要移除
                iter.remove();
            }
        }
    }

    /**
     * 可读事件的处理器
     * 接收客户端的信息并输出回客户端
     * @param buffer
     * @param key
     * @throws IOException
     */
    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key)
            throws IOException {

        //通过SelectionKey来获取channel
        SocketChannel client = (SocketChannel) key.channel();
        //读取数据
        client.read(buffer);
        //判断收到的是还是终止命令，如是则关闭channel.
        if (new String(buffer.array()).trim().equals(POISON_PILL)) {
            client.close();
            System.out.println("Not accepting client messages anymore");
        }
        //将收到的信息写出到channel。客户端
        //在将数据写入buffer后，并且在读之前需要调用flip具体含义看mybase相关IO笔记中的重点
        buffer.flip();
        client.write(buffer);
        buffer.clear();
    }

    /**
     * 建立连接的事件处理器
     * selector监测到accept事件后，与客户端建立连接，获取channel，然后继续注册到selector中
     * 的read事件。
     * @param selector
     * @param serverSocket
     * @throws IOException
     */
    private static void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {
        //接收来自客户端的连接，并获取服务端socketChannel
        SocketChannel client = serverSocket.accept();
        //向selector中注册channel，感兴趣的事件为read
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 使用java编程的方式调用 系统指令（命令），相关的类还有com.xiaojihua.javabasic.util.OSExecution
     * 写本方的是为了在单元测试的时候做为独立的进程
     * 当然也可以直接运行main方法测试，而不使用单元测试
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static Process start() throws IOException, InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = EchoServer.class.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        return builder.start();
    }
}

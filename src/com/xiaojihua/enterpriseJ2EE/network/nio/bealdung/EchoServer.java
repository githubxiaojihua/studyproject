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
        //2.构建ServerSocketChannel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        //3.绑定端口
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        //4.设置为非阻塞模式
        serverSocket.configureBlocking(false);
        //5.注册channel，并对accept事件感兴趣。
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //6.通过ByteBuffer来对channel进行读写。
        ByteBuffer buffer = ByteBuffer.allocate(256);
        //7.开始监控
        while (true) {
            //8.在有任何channel事件触发前阻塞。（注意阻塞是当前线程停止，并让出CPU）
            selector.select();
            //9.获得监听到的SelectionKey(具体内容看总结中的SelectionKey组成部分)
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            //10.遍历selectedKeys
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                //11.获得SelectionKey对象，并判断是哪一类事件。
                SelectionKey key = iter.next();
                //12.accept事件，创建socketchannel，并注册read事件到selector
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

        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);
        //判断收到的是还是终止命令，如是则关闭channel.
        if (new String(buffer.array()).trim().equals(POISON_PILL)) {
            client.close();
            System.out.println("Not accepting client messages anymore");
        }
        //将收到的信息写出到channel。客户端
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
        //接收来自客户端的连接，并创建服务端socketChannel
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

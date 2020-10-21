package com.xiaojihua.enterpriseJ2EE.network.nio.bealdung;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 客户端，未使用selector相关nio selector知识。
 * 只使用了SocketChannel
 *
 */
public class EchoClient {
    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static EchoClient instance;

    /**
     * 单例模式，实例化EchoClient
     * @return
     */
    public static EchoClient start() {
        if (instance == null)
            instance = new EchoClient();
        return instance;
    }

    /**
     * 关闭连接
     * @throws IOException
     */
    public static void stop() throws IOException {
        client.close();
        buffer = null;
    }

    /**
     * 私有构造方法
     * 打开客户端socket连接，连接到服务器相关端口
     * 初始化ByteBuffer
     */
    private EchoClient() {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 5454));
            buffer = ByteBuffer.allocate(256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向客户端发送消息
     * 并接收服务器发回的信息
     * 其中write和read是阻塞的
     * @param msg
     * @return
     */
    public String sendMessage(String msg) {
        //根据实际字符串大小构建ByteBuffer
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            System.out.println("response=" + response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}

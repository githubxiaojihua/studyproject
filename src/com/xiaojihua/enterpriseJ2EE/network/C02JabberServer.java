package com.xiaojihua.enterpriseJ2EE.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socke编程中的server端。
 * 说明：
 * 1、对于socket的理解：socket是在程序中代表两台电脑之间连接的两个端点，是一个抽象概念。当有一个连接被建立后在连接的
 * 每个端都有一个socket对象，可以将这个连接比作一条电缆，而电缆则分别插入一两个socket中。
 * 2、可以从一个soket中获取InputStream和OutputStream(Rreader和Writer)，来像操作IO流一样的操作网络连接对象。
 * 3、ServerSocket用于建立服务端的Socket对象，客户端可直接建立Socket，一旦连接建立后双方就都是通过socket来进行
 * 通信。
 * 4、使用socket建立的连接是专用的并且是持久性的，一直到显式的进行中断操作，连接才关闭。
 * 5、注意ServerSocket和Socket的关闭，都是在各自初始化后使用try,finally语句来进行初始化。
 *
 * 本例实现：
 * 服务端接收客户端信息然后写回，并在控制台打印。"END"作为终止命令
 */
public class C02JabberServer {
    //指定服务器端口号，不要使用1-1024，这些是系统内部端口。
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        //1、通过指定端口号来实例化serversocket
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started:" + s);
        try{
            //2、通过accept方法来创建一个Socket。再接收到来自客户端的请求之前是阻塞的
            Socket socket = s.accept();
            try{
                //收到客户发来的连接请求后会，可打印出服务端和客户端的相关信息
                //一个连接有四个属性，服务器端ip和端口，客户机端ip和端口
                //其中服务器端端口为8080，这个在使用serversocket的时候指定了PORT
                //客户端端口是客户机上可以端口的下一个端口
                System.out.println("connection accepted :" + socket);
                //3、初始化输入流，一般通过socket获取的原始inputStream需要使用buffered装饰。
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //4、初始化输了流
                //注意初始化PrintWriter的时候设置了第二个参数为true，这代表在调用每一个println()方法的时候，
                //都会自动调用flush()方法将buffer中的信息写出到网络中。
                //在这里使用自动的flush方法很重要，因为，server端和client端都在等待着对方的一行信息，如果这里
                //在写出信息的时候不flush那么信息可能只是存在buffer中没有写到网络中，那么另外一端可能不能进行
                //下一步工作。
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                //5、读取信息并写回和输出致控制台
                while(true){
                    String str = in.readLine();
                    //"END"作为终止命令
                    if("END".equals(str)){
                        break;
                    }
                    out.println(str);
                    System.out.println("服务端接收到的信息：" + str);
                }

            }finally {
                //关闭socket
                System.out.println("closing.....");
                socket.close();
            }
        }finally {
            //关闭ServerSocket
            s.close();
        }

    }
}

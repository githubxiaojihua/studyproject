package com.xiaojihua.enterpriseJ2EE.network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * socke编程中的client端。
 */
public class C03JabberClient {
    public static void main(String[] args) throws IOException {
        //1、初始化服务器IP地址
        InetAddress ipAddr = InetAddress.getByName(null);
        System.out.println("addr=" + ipAddr);
        //2、构建客户端socket对象，需要指定ip地址和端口号
        Socket socket = new Socket(ipAddr,C02JabberServer.PORT);
        try{
            System.out.println("socket = " + socket);
            //3、初始化输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4、初始化输出流
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            //发送10条记录，服务器回写10条记录
            for(int i=0; i<10; i++){
                out.println("howby " + i);
                String s = in.readLine();
                System.out.println("客户端接收到的信息：" + s);
            }
            //发送终止命令
            out.println("END");
        }finally {
            System.out.println("closing......");
            socket.close();
        }
    }
}

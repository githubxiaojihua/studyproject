package com.xiaojihua.enterpriseJ2EE.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过多线程来处理多个客户端请求。改写了原来的C02JabberServer原来的只能处理一个请求
 *
 */
public class C04MultiJabberServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started!");
        try{
            while(true){
                //当接收到来自客户端的请求的时候，生成一个socket，并将其传入ServOneJabber中
                //生成一个处理线程。
                Socket socket = serverSocket.accept();
                System.out.println("connection accepted :" + socket);
                try{
                    new ServeOneJabber(socket);
                }catch(Exception e) {
                    e.printStackTrace();
                    socket.close();
                }
            }

        }finally {
            serverSocket.close();
        }

    }
}

/**
 * 使用线程，用于根据生成的socket处理某一个具体请求。
 */
class ServeOneJabber extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * 初始化socket,in,out
     * @param s
     * @throws IOException
     */
    public ServeOneJabber(Socket s) throws IOException {
        //这里不需要进行try catch判断来close socket，因为在外层调用的时候已经进行try catch了
        this.socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        start();//调用run()
    }

    @Override
    public void run() {
        try{
            while(true){
                String str = in.readLine();
                if("END".equals(str)){
                    break;
                }
                System.out.println("服务端"+ this.getName() + "接收到的信息：" + str);
                out.println(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
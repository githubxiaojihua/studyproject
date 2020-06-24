package com.xiaojihua.enterpriseJ2EE.network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * 构造多个线程client来请求服务端
 */
public class C05MutiJabberClient {
    //限定同是在线的不能超过40，注意由于threadCount的--操作，可能使得线程序号超过40，但是同时在线活跃线程不会超过40
    private static final int MAX_THREADS = 40;

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        InetAddress serverIp = InetAddress.getByName(null);
        while(true){
            if(JabberClientThread.getThreadCount() < MAX_THREADS){
                new JabberClientThread(serverIp);
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }

    }
}

/**
 * 一个客户端线程
 */
class JabberClientThread extends Thread{
    private static int count = 0;
    private int id = count++;
    private static int threadCount = 0;
    public static int getThreadCount(){
        return threadCount;
    }
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * 这里进行了多个try catch语句，因为在外层没有进行相应的try catch
     * @param serverIp
     */
    public JabberClientThread(InetAddress serverIp) {
        try {
            socket = new Socket(serverIp,C04MultiJabberServer.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Making client:" + id);
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            threadCount++;
            start();
        }catch(Exception e){
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try{
            //每个线程发送25个信息给服务器。然后发送END进行中断
            for(int i=0; i<25; i++){
                out.println("client" + id + ":" + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        }catch(Exception e){
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                socket.close();
                threadCount--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

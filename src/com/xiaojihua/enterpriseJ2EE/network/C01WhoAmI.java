package com.xiaojihua.enterpriseJ2EE.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 根据计算机名称来获取InetAddress对象（代表了一个IP地址）
 */
public class C01WhoAmI {
    public static void main(String[] args) throws UnknownHostException {
        //获取计算机名称所对应的外网IP
        InetAddress a = InetAddress.getByName("SKY-20161226WCT");
        System.out.println(a);

        //使用本地局域网IP
        a = InetAddress.getByName(null);
        System.out.println(a);
        a = InetAddress.getByName("localhost");
        System.out.println(a);
        a = InetAddress.getByName("127.0.0.1");
        System.out.println(a);
        //根据随便写的一个本地局域网IP来构建InteAddress
        a = InetAddress.getByName("192.168.223.125");
        System.out.println(a);
    }
}


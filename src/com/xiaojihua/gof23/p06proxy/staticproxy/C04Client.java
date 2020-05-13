package com.xiaojihua.gof23.p06proxy.staticproxy;

/**
 * 静态代理模式：
 * 代理类需要自己手动生成
 * 代理类和被代理类需要实现同样的接口
 */
public class C04Client {
    public static void main(String[] args){
        C01Star realStar = new C02RealStar();
        C01Star proxyStar = new C03ProxyStar(realStar);

        proxyStar.confer();
        proxyStar.signContract();
        proxyStar.bookTicket();

        proxyStar.sing();

        proxyStar.collectMoney();
    }
}

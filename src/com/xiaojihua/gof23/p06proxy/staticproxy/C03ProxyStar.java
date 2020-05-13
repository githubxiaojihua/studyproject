package com.xiaojihua.gof23.p06proxy.staticproxy;

/**
 * 静态代理对象：里面聚合着一个C01Star，真正的服务提供者
 */
public class C03ProxyStar implements C01Star {
    private C01Star star;

    public C03ProxyStar(C01Star star) {
        this.star = star;
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar.bookTicket()");
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar.collectMoney()");
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar.signContract()");
    }


    /**
     * 调用真正的服务提供者
     */
    @Override
    public void sing() {
        star.sing();
    }


}

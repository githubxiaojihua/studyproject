package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public interface C01Engine {
    void run();
    void start();
}

class LuxuryEngine implements C01Engine{

    @Override
    public void run() {
        System.out.println("转的快！");
    }

    @Override
    public void start() {
        System.out.println("启动快!可以自动启停！");
    }
}

class LowEngine implements C01Engine{

    @Override
    public void run() {
        System.out.println("转的慢！");
    }

    @Override
    public void start() {
        System.out.println("启动慢!");
    }

}
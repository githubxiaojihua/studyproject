package com.xiaojihua.gof23.p15command;

public class C04Client {
    public static void main(String[] args) {
        C02Command command = new ConcreteCommand(new C01Receiver());
        C03Invoker invoker = new C03Invoker(command);
        invoker.call();
    }
}

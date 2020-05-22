package com.xiaojihua.gof23.p16strategy;

/**
 * 客户端调用类
 */
public class C07Client {
    public static void main(String[] args) {
        C01Strategy s1 = new C02NewCostomerFewStrategy();
        C06Context ctx = new C06Context(s1);

        ctx.printPrice(8888);
    }
}

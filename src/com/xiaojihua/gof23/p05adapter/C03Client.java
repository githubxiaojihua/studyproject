package com.xiaojihua.gof23.p05adapter;

/**
 * 客户对象。其调用C02Target来进行操作
 * 但是具体的操作C01Adaptee可以做到，但是由于两个类不兼容
 * 在满足OCP原则的基础上需要使用适配器原则
 */
public class C03Client {

    /**
     * 使用了C02Target
     * @param t
     */
    public void test1(C02Target t){
        t.handleRequest();
    }

    /**
     * 测试，使用适配器来解决满足OCP原则的 类复用
     * @param args
     */
    public static void main(String[] args){

        // 使用继承来实现适配器
        /*C03Client client = new C03Client();
        C04Adapter01 adapter01 = new C04Adapter01();
        client.test1(adapter01);*/

        C03Client client = new C03Client();
        C05Adapter02 adapter02 = new C05Adapter02(new C01Adaptee());
        client.test1(adapter02);

    }
}

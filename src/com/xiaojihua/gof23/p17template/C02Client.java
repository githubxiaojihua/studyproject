package com.xiaojihua.gof23.p17template;

/**
 * 模版模式的测试
 * 一般采用匿名内部类的方式来声明子类
 */
public class C02Client {
    public static void main(String[] args) {
        C01BankTemplateMethod bm1 = new C01BankTemplateMethod() {
            @Override
            public void transact() {
                System.out.println("我要存钱");
            }
        };
        bm1.process();

        C01BankTemplateMethod bm2 = new C01BankTemplateMethod() {
            @Override
            public void transact() {
                System.out.println("我要取钱！");
            }
        };
        bm2.process();

    }
}

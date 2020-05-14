package com.xiaojihua.gof23.p07bridge;

public class C04Client {

    public static void main(String[] args){
        // 销售联想台式机
        // 通过组合来达到原来多重继承的关系，并且利于扩展
        // 增加品牌和增加电脑类型都相互不影响，扩展性强
        C02Computer c02Computer = new Desktop2(new Lenovo());
        c02Computer.sale();

        // 而且可以任意组合
        C02Computer c02Computer1 = new Laptop2(new Dell());
        c02Computer1.sale();
    }
}

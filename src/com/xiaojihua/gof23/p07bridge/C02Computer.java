package com.xiaojihua.gof23.p07bridge;

/**
 * 使用桥接设计模式来破解C01Computer那种有多个维度的集成关系
 * 实际上就是使用组合来代替集成
 *
 * 当前类代表维度一：电脑
 */
public abstract class C02Computer {
    protected C03Brand brand;

    public C02Computer(C03Brand brand) {
        this.brand = brand;
    }

    public abstract void sale();
}

class Desktop2 extends C02Computer{

    public Desktop2(C03Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        System.out.println("销售台式机");
        brand.sale();
    }
}

class Laptop2 extends C02Computer {

    public Laptop2(C03Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        System.out.println("销售笔记本");
        brand.sale();
    }
}

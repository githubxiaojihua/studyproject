package com.xiaojihua.gof23.p07bridge;

/**
 * 维度二：品牌
 */
public interface C03Brand {
   void sale();
}

class Lenovo implements C03Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }

}

class Dell implements C03Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑");
    }

}

class Shenzhou implements C03Brand {

    @Override
    public void sale() {
        System.out.println("销售神舟电脑");
    }

}

package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public interface C03Tyre {
    void revolve();
}


class LuxuryTyre implements C03Tyre {

    @Override
    public void revolve() {
        System.out.println("旋转不磨损！");
    }

}

class LowTyre implements C03Tyre {

    @Override
    public void revolve() {
        System.out.println("旋转磨损快！");
    }

}
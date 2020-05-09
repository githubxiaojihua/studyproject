package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public interface C02Seat {
    void massage();
}


class LuxurySeat implements C02Seat {

    @Override
    public void massage() {
        System.out.println("可以自动按摩！");
    }

}
class LowSeat implements C02Seat {

    @Override
    public void massage() {
        System.out.println("不能按摩！");
    }

}

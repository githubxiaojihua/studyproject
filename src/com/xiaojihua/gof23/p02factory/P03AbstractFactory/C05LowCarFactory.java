package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public class C05LowCarFactory implements C04CarFactory {
    @Override
    public C01Engine createEngine() {
        return new LowEngine();
    }

    @Override
    public C02Seat createSeat() {
        return null;
    }

    @Override
    public C03Tyre createTyre() {
        return null;
    }
}

package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public class C06LuxuryCarFactory implements C04CarFactory {
    @Override
    public C01Engine createEngine() {
        return new LuxuryEngine();
    }

    @Override
    public C02Seat createSeat() {
        return new LuxurySeat();
    }

    @Override
    public C03Tyre createTyre() {
        return new LuxuryTyre();
    }

}

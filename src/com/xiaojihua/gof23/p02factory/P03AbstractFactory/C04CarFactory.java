package com.xiaojihua.gof23.p02factory.P03AbstractFactory;

public interface C04CarFactory {
    C01Engine createEngine();
    C02Seat createSeat();
    C03Tyre createTyre();
}

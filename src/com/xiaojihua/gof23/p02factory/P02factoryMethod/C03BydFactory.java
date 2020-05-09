package com.xiaojihua.gof23.p02factory.P02factoryMethod;

import com.xiaojihua.gof23.p02factory.C01Car;
import com.xiaojihua.gof23.p02factory.C02Audi;
import com.xiaojihua.gof23.p02factory.C03Byd;

public class C03BydFactory implements C01CarFactory {
    @Override
    public C01Car createCar() {
        return new C03Byd();
    }
}

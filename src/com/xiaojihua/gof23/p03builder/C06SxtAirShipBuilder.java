package com.xiaojihua.gof23.p03builder;

/**
 * 构建者实现类之一，用于构建某一些具体的组件
 * 可以有其他的实现类用于构建其他类型的组件
 */
public class C06SxtAirShipBuilder implements C05AirShipBuilder {
    @Override
    public C02Engine builderEngine() {
        System.out.println("构建济南牌发动机");
        return new C02Engine("济南发动机");
    }

    @Override
    public C03OrbitalModule builderOrbitalModule() {
        System.out.println("构建济南牌轨道舱");
        return new C03OrbitalModule("济南轨道舱");
    }

    @Override
    public C04EscapeTower builderEscapeTower() {
        System.out.println("构建济南牌逃逸仓");
        return new C04EscapeTower("济南逃逸仓");
    }
}

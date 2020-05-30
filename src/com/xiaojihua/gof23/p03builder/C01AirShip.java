package com.xiaojihua.gof23.p03builder;

/**
 * 复杂对象，用于构建
 * 宇宙飞船
 */
public class C01AirShip {
    private C02Engine engine;
    private C03OrbitalModule orbitalModule;
    private C04EscapeTower escapeTower;

    public C02Engine getEngine() {
        return engine;
    }

    public void setEngine(C02Engine engine) {
        this.engine = engine;
    }

    public C03OrbitalModule getOrbitalModule() {
        return orbitalModule;
    }

    public void setOrbitalModule(C03OrbitalModule orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    public C04EscapeTower getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(C04EscapeTower escapeTower) {
        this.escapeTower = escapeTower;
    }

    @Override
    public String toString() {
        return "C01AirShip{" +
                "engine=" + engine +
                ", orbitalModule=" + orbitalModule +
                ", escapeTower=" + escapeTower +
                '}';
    }
}

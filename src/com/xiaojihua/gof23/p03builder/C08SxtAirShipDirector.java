package com.xiaojihua.gof23.p03builder;

/**
 * 组装接口实现类，用于组装某一具体的产品
 * 内部依赖了构建者，并通过构造方法实现注入
 */
public class C08SxtAirShipDirector implements C07AirShipDirector {

    private C05AirShipBuilder builder;

    public C08SxtAirShipDirector(C05AirShipBuilder builder) {
        this.builder = builder;
    }


    @Override
    public C01AirShip createAirShip() {
        C02Engine c02Engine = builder.builderEngine();
        C04EscapeTower c04EscapeTower = builder.builderEscapeTower();
        C03OrbitalModule c03OrbitalModule = builder.builderOrbitalModule();

        C01AirShip airShip = new C01AirShip();
        airShip.setEngine(c02Engine);
        airShip.setEscapeTower(c04EscapeTower);
        airShip.setOrbitalModule(c03OrbitalModule);

        return airShip;
    }
}

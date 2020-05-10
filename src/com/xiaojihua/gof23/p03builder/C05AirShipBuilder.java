package com.xiaojihua.gof23.p03builder;

/**
 * 构建者接口用于构建各个组件
 */
public interface C05AirShipBuilder {
    C02Engine builderEngine();
    C03OrbitalModule builderOrbitalModule();
    C04EscapeTower builderEscapeTower();
}

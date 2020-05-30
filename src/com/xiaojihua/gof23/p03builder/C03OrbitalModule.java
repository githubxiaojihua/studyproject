package com.xiaojihua.gof23.p03builder;

/**
 * 子组件
 * 轨道舱模块
 */
public class C03OrbitalModule {
    private String name;

    public C03OrbitalModule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.xiaojihua.gof23.p14mediator;

/**
 * 中介者抽象接口
 */
public interface C01Mediator {
    void register(String deptName,C02Deptment deptement);
    void command(String dname);
}

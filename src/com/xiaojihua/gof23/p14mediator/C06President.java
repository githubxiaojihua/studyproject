package com.xiaojihua.gof23.p14mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中介者模式，中介者具体类
 */
public class C06President implements C01Mediator {
    // 存储注册到中介者的对象
    Map<String,C02Deptment> deptmentMap = new HashMap<>();

    @Override
    public void register(String deptName, C02Deptment deptement) {
        deptmentMap.put(deptName,deptement);
    }

    @Override
    public void command(String dname) {
        deptmentMap.get(dname).selfAction();
    }
}

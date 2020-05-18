package com.xiaojihua.gof23.p11flywight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 */
public class C03ChessFlyWeightFactory {
    private static Map<String,C01ChessFlyWeight> map = new HashMap<>();

    public static C01ChessFlyWeight getChess(String color){
        if(map.get(color) != null){
            return map.get(color);
        }else{
            C01ChessFlyWeight chess = new ConcretChess(color);
            map.put(color,chess);
            return chess;
        }
    }
}

package com.xiaojihua.javabasic.chapter19enum.multipleDispatching;

import java.util.EnumMap;
import static com.xiaojihua.javabasic.chapter19enum.multipleDispatching.C01OutCome.*;

/**
 * 知识点：
 * 使用enum和EnumMap来进行动态多分派的一个例子。
 * 相当于建立一个二维表
 */
public enum C06RoShamBo5 implements C03Competitor<C06RoShamBo5> {
    //声明三个enum 实例
    PAPER, SCISSORS, ROCK;

    //静态二维表，用来存储每一个enum实例与各个实例之间的关系
    private static EnumMap<C06RoShamBo5,EnumMap<C06RoShamBo5,C01OutCome>> table =
            new EnumMap(C06RoShamBo5.class);

    //调用静态方法对table进行初始化
    static{
        for(C06RoShamBo5 roShamBo5 : C06RoShamBo5.values()){
            table.put(roShamBo5, new EnumMap<C06RoShamBo5, C01OutCome>(C06RoShamBo5.class));
        }
        //对PAPER进行初始化
        initEnumMap(PAPER, DRAW, LOSE, WIN);
        initEnumMap(SCISSORS, WIN, DRAW, LOSE);
        initEnumMap(ROCK, LOSE, WIN, DRAW);

    }

    /**
     * 静态方法
     * @param roShamBo5
     * @param vPaper
     * @param vScissors
     * @param vRock
     */
    public static void initEnumMap(C06RoShamBo5 roShamBo5, C01OutCome vPaper,
                                   C01OutCome vScissors, C01OutCome vRock){
        EnumMap<C06RoShamBo5,C01OutCome> map = table.get(roShamBo5);
        map.put(PAPER, vPaper);
        map.put(SCISSORS, vScissors);
        map.put(ROCK, vRock);
    }

    @Override
    public C01OutCome compete(C06RoShamBo5 roShamBo5){
        return table.get(this).get(roShamBo5);
    }

    public static void main(String[] args){
        C04RoShamBo2.play(C06RoShamBo5.class, 10);
    }
}

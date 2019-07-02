package com.xiaojihua.javabasic.chapter19enum;

import java.util.EnumSet;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * EnumSet的使用，EnumSet中的元素必须是来自一个单一enum类中的实例，并且，
 * EnumSet会保持其在enum中的声明顺序。
 *
 * EnumSet是建立在long(64bit)的基础上，然后用1bit来表示某一个enum实例是
 * 否已经add了。如果超过64个元素那么EnumSet也是可以扩展的。
 */
public class C13EnumSets {
    public static void main(String[] args){
        //基于一个单一的enum建立一个空的EnumSet
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        //增加一个实例
        points.add(AlarmPoints.STAIR1);
        print(points);

        //增加多个实例
        points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KETCHEN));
        print(points);

        //增加所有实例
        points = EnumSet.allOf(AlarmPoints.class);
        //移除多个实例
        points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KETCHEN));
        print(points);

        //移除某个范围内的实例
        points.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE4));
        print(points);

        /*
            重新创建一个EnumSet，包含原来EnumSet中不包含的哪些原enum实例。
            比如：原来执行此操作之前points的内容是[LOBBY, BATHROOM, UTILITY]
            那么执行后，points将是一个包含AlarmPoints剩余实例的新的EnumSet
         */
        points = EnumSet.complementOf(points);
        print(points);
    }
}

enum AlarmPoints{
    STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY, KETCHEN
}
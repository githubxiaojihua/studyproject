package com.xiaojihua.javabasic.chapter19enum;

import java.util.EnumSet;
import static com.xiaojihua.javabasic.util.Print.*;

public class C13EnumSets {
    public static void main(String[] args){
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.add(AlarmPoints.STAIR1);
        print(points);

        points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KETCHEN));
        print(points);

        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KETCHEN));
        print(points);

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
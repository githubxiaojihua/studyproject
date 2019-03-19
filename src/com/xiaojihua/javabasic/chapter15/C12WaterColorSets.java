package com.xiaojihua.javabasic.chapter15;

import java.util.EnumSet;
import java.util.Set;
import static com.xiaojihua.javabasic.util.Print.*;
import static com.xiaojihua.javabasic.util.Sets.*;

/**
 * 知识点：
 * 1、Sets工具类的使用。
 * 2、枚举类的使用
 * 3、EnumSet类的使用。专门用于创建枚举类型Set的工具类
 */
public class C12WaterColorSets {
    public static void main(String[] args){
        //按照给定的起止枚举初始化Set，包括起止元素
        Set<WaterColor> set1 = EnumSet.range(WaterColor.BRILLIANT_RED, WaterColor.VIRIDIAN_HUE);
        Set<WaterColor> set2 = EnumSet.range(WaterColor.CERULEAN_BLUE_HUE, WaterColor.BURNT_UMBER);
        print("set1:" + set1);
        print("set2" + set2);

        //以下是对于Sets工具类的使用
        print("union(set1,set2):" + union(set1, set2));

        Set<WaterColor> subSet = intersection(set1, set2);
        print("intersection(set1,set2):" + subSet);
        print("difference(set1,subset):"+ differenc(set1, subSet));
        print("difference(set2,subset):" + differenc(set2, subSet));
        print("complement(set1,set2):" + complement(set1, set2));
    }
}

/**
 * 枚举类型
 */
enum WaterColor{
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE,
    BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET,
    CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
    COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
    SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}
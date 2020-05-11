package com.xiaojihua.gof23.p04prototype;

import java.util.Date;

/**
 * 测试浅克隆：
 * 只克隆对象本身，对于对象所引用的对象，只克隆引用，相当于还是引用同一个对象
 */
public class C02ShallowClone {
    public static void main(String[] args) throws Exception {
        Date birtyday = new Date(123123123123123l);
        C01Sheep s1 = new C01Sheep("少利",birtyday);
        System.out.println("s1："+s1);

        C01Sheep s2 = (C01Sheep) s1.clone();
        //修改Date对象，此时s1和s2都指向了同一个对象
        birtyday.setTime(8907789729873l);
        System.out.println("修改s1的birtyday:" + birtyday);
        System.out.println("s2："+s2);
    }
}

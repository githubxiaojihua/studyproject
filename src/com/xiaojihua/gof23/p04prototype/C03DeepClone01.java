package com.xiaojihua.gof23.p04prototype;

import java.util.Date;

/**
 * 测试深克隆：使用clone方法中克隆对象属性的方式来实现深克隆
 * 不止克隆对象本身，对于对象所引用的对象，也克隆。
 *
 */
public class C03DeepClone01 {
    public static void main(String[] args) throws Exception {
        Date birtyday = new Date(123123123123123l);
        C01SheepDeepClone s1 = new C01SheepDeepClone("少利",birtyday);
        System.out.println("s1(修改前)："+s1);

        C01SheepDeepClone s2 = (C01SheepDeepClone) s1.clone();
        //修改Date对象
        birtyday.setTime(8907789729873l);
        System.out.println("s1(修改后)："+s1);
        System.out.println("修改s1的birtyday:" + birtyday);
        System.out.println("s2："+s2);
    }
}

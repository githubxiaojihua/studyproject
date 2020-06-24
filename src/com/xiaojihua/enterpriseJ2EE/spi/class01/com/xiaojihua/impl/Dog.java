package com.xiaojihua.enterpriseJ2EE.spi.class01.com.xiaojihua.impl;

import com.xiaojihua.enterpriseJ2EE.spi.class01.com.xiaojihua.IShout;

/**
 * 实现类
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}

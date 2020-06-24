package com.xiaojihua.enterpriseJ2EE.spi.class01.com.xiaojihua;

import java.util.ServiceLoader;

/**
 * 使用spi机制加载并调用相应的实现类，
 * 注意并没有使用import引入实现类，
 * 且实现类可以位于不同的jar包
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for(IShout s : shouts){
            s.shout();
        }
    }
}

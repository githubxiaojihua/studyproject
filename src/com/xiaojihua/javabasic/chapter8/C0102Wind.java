package com.xiaojihua.javabasic.chapter8;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 继承和覆盖
 */
public class C0102Wind extends C0101Instrument {
    public void play(C0103Note n){
        print("Wind.play()" + n);
    }
}

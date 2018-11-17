package com.xiaojihua.javabasic.chapter8;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 继承和覆盖
 */
public class Wind extends Instrument {
    public void play(Note n){
        print("Wind.play()" + n);
    }
}

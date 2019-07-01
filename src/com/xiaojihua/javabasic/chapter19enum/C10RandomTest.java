package com.xiaojihua.javabasic.chapter19enum;

import com.xiaojihua.javabasic.util.Enums;

/**
 * 知识点：
 * 工具类Enums的使用。
 */
public class C10RandomTest {
    public static void main(String[] args){
        for(int i=0; i<20; i++){
            System.out.println(Enums.randomNext(Activity.class));
        }
    }
}

enum Activity{
    SITTING, LYING, STANDING, HOPPING,
    RUNNING, DODGING, JUMPING, FALLING, FLYING
}
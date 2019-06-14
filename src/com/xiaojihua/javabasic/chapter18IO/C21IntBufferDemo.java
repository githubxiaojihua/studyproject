package com.xiaojihua.javabasic.chapter18IO;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 知识点：
 * “view buffer”是将ByteBuffer从基本数据类型的角度来看待。
 * view buffer就是指：IntBuffer\CharBuffer等。view buffer
 * 底层还是ByteBuffer。
 */
public class C21IntBufferDemo {
    private static final int SIZE = 1024;

    public static void main(String[] args){
        IntBuffer intBuffer = ByteBuffer.allocate(SIZE).asIntBuffer();
        intBuffer.put(new int[]{11,23,34,56,78,90,15});
        System.out.println(intBuffer.get(3));
        intBuffer.put(3,12324);
        //flip的主要作用是设置新的根据，实际byte数量设置新的limit,默认的limit和capacity一致。
        intBuffer.flip();
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}

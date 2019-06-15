package com.xiaojihua.javabasic.chapter18IO;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 知识点：
 * Buffer包含四个重要的位置索引：mark(标记 位),position(当前的位置),limit(标示为当前有效元素数的下一个位置),
 * capacity(容量)。
 * 有如下方法可以对这些索引进行操作：
 * capacity():返回buffer的容量
 * cleal():清除buffer，设置position=0，limit=capacity，通过此方法来重写一个已经存在的buffer
 * flip():设置limit=position,position=0。这是为了从buffer中读取数据做准备
 * limit():返回limit的值，比如当前有5个元素那么limit=5，从0开始
 * limit(int lim):设置limit的值
 * mark():设置mark = position，也就是在当前position设置一个mark
 * position():返回当前position的值
 * position(int pos):设置当前position的值
 * remaining()：返回limit-position
 * hasRemaining():返回true表示在position和liMit之间还有元素
 */
public class C24UsingBuffer {
    public static void scramble(CharBuffer charBuffer){
        while(charBuffer.hasRemaining()){
            charBuffer.mark();
            char c1 = charBuffer.get();
            char c2 = charBuffer.get();
            charBuffer.reset();
            charBuffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args){
        char[] charArray = "Using Buffer".toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(charArray.length * 2);
        CharBuffer charBuffer = buffer.asCharBuffer();
        charBuffer.put(charArray);
        System.out.println(charBuffer.limit());
        System.out.println(charBuffer.position());
        System.out.println(charBuffer.rewind());
        System.out.println(charBuffer.limit());
        System.out.println(charBuffer.position());
        System.out.println(charBuffer.get(12));

        scramble(charBuffer);
        System.out.println(charBuffer.rewind());

        scramble(charBuffer);
        System.out.println(charBuffer.rewind());
    }
}

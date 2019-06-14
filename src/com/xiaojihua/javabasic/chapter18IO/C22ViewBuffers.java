package com.xiaojihua.javabasic.chapter18IO;

import java.nio.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 各类viewBuffer的使用。
 * 使用byte数组初始化一个ByteBuffer，然后用不同的viewBuffer来读取
 *
 * ByteBuffer操作的是数组，转化成其它viewBuffer是直接根据viewBuffer
 * 所占用的字节数来转化的，并不是编码。
 */
public class C22ViewBuffers {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        buffer.rewind();
        print("ByteBuffer:");
        while(buffer.hasRemaining()){
            //输出位置和值
            printnb(buffer.position() + "->" + buffer.get() + ",");
        }
        print();

        //转成Short，直接将两个byte做为一个short，因此有4个位置
        ShortBuffer shortBuffer = ((ByteBuffer)buffer.rewind()).asShortBuffer();
        print("ShortBuffer:");
        while(shortBuffer.hasRemaining()){
            printnb(shortBuffer.position() + "->" + shortBuffer.get() + ",");
        }
        print();

        //rewind返回的是Buffer类
        //转成char，也是发直接将两个byte做为一个char，因此有4个位置
        CharBuffer charBuffer = ((ByteBuffer)buffer.rewind()).asCharBuffer();
        print("CharBuffer:");
        while(charBuffer.hasRemaining()){
            printnb(charBuffer.position() + "->" + charBuffer.get() + ",");
        }
        print();

        //转成int，直接将4个byte做为一个int,因此有2个位置
        IntBuffer intBuffer = ((ByteBuffer)buffer.rewind()).asIntBuffer();
        print("IntBuffer:");
        while(intBuffer.hasRemaining()){
            printnb(intBuffer.position() + "->" + intBuffer.get() + ",");
        }
        print();

        //有2个位置
        FloatBuffer floatBuffer = ((ByteBuffer)buffer.rewind()).asFloatBuffer();
        print("FloatBuffer:");
        while(floatBuffer.hasRemaining()){
            printnb(floatBuffer.position() + "->" + floatBuffer.get() + ",");
        }
        print();

        //有1个位置
        DoubleBuffer doubleBuffer = ((ByteBuffer)buffer.rewind()).asDoubleBuffer();
        print("DoubleBuffer:");
        while(doubleBuffer.hasRemaining()){
            printnb(doubleBuffer.position() + "->" + doubleBuffer.get());
        }
        print();

        //有1个位置
        LongBuffer longBuffer = ((ByteBuffer)buffer.rewind()).asLongBuffer();
        print("LongBuffer:");
        while(longBuffer.hasRemaining()){
            printnb(longBuffer.position() + "->" + longBuffer.get());
        }
        print();





    }
}

package com.xiaojihua.javabasic.chapter18IO;

import java.nio.ByteBuffer;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 使用ByteBuffer设置和读取primitives
 * ByteBuffer的limit方法是返回第一个不可读写的位置。
 *
 * 往ByteBuffer中设置基本数据类型，使用不同的<b>ByteBuffer视图</b>
 * 比如：ByteBuffer.asCharBuffer(),asShortBuffer,asIntBuffer.
 * 而读取基本数据类型则使用getChar\getInt\getLong等方法。
 *
 */
public class C20GetData {
    private static final int SIZE = 1024;//1k

    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        int i = 0;
        while(i++ < buffer.limit()){
            //get()，返回buffer当前position的byte，并且增加position
            if(buffer.get() != 0){
                print("nonezero");
                return;
            }
        }
        //可以看到初始的时候ByteBuffer的所有位被设置为0
        print("i = " + i);

        buffer.rewind();//回到起始位置
        buffer.asCharBuffer().put("howliday");
        char c;
        //getChar与get一样都会自增
        while((c = buffer.getChar())!= 0){
            printnb(c + " ");
        }
        print();

        //设置和读取short
        buffer.rewind();
        //这里不用clear，put就是重新设置，clear是在FileChannel写入的时候用
        buffer.asShortBuffer().put((short)123455);
        print(buffer.getShort());

        //设置和读取int
        buffer.rewind();
        buffer.asIntBuffer().put(123);
        print(buffer.getInt());

        //设置和读取long
        buffer.rewind();
        buffer.asLongBuffer().put(123344445);
        print(buffer.getLong());

        //设置和读取float
        buffer.rewind();
        buffer.asFloatBuffer().put(12.22222f);
        print(buffer.getFloat());

        //设置和读取double
        buffer.rewind();
        buffer.asDoubleBuffer().put(2.12345678);
        print(buffer.getDouble());



    }
}

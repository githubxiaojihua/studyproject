package com.xiaojihua.javabasic.chapter18IO;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * Endians。指的是字节顺序，分为BigEndian和LittleEndian，其中BigEndia代表高位在起始位置，
 * LittleEndian代表低位在起始位置。BigEndia使用较为广泛。
 * 例如本例中：一个char由两个byte组成，char 'a' = 97，换算成byte就是：
 * 00000000 01100001 ，其实这就是BigEndian高位在其实位置，高位指的是00000000，因为按照
 * 二进制转换成十进制的话，最低位是最右边的位代表1，右边第二位为2，第三位为4，第四位为8...
 * 如果就LittleEndian那么就是 01100001 00000000这种表示，换算成number就是24832了。
 *
 * ByteBuffer可以通过order方法设置字符顺序，参数为ByteOrder.BIG_ENDIAN，ByteOrder.LITTLE_ENDIAN
 */
public class C23Endians {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.wrap(new byte[12]);
        buffer.asCharBuffer().put("abcdef");
        //buffer.array()是可选方法的调用只能使用在，ByteBuffer是通过byte数组初始化的情况下。否则将会抛出UnsupportedOperationException
        print(Arrays.toString(buffer.array()));

        //BIGENDIAN
        buffer.order(ByteOrder.BIG_ENDIAN);
        //如果只是put一部分的话那么其他的仍然保留
        buffer.asCharBuffer().put("abcdef");
        print(Arrays.toString(buffer.array()));

        //LITTLEENDIAN
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        print(Arrays.toString(buffer.array()));

    }
}

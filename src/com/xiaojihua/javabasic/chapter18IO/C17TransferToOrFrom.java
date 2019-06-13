package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 知识点：
 * nio
 * 通过使用FileChannel的transferTo\transferFrom来实现文件的copy.
 * 相比较
 * @see C16ChannelCopy
 * 而言更加高效和简洁
 */
public class C17TransferToOrFrom {
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("H:\\data.txt").getChannel(),
                out = new FileOutputStream("H:\\data2.txt").getChannel();
        in.transferTo(0,in.size(),out);
        //作用与上一句一样，只不过是站的角度不一样。
        //out.transferFrom(in,0,in.size());
    }
}

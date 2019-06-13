package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 知识点：
 * nio
 * 通过nio实现文件的copy，需要注意flip和clear的使用
 */
public class C16ChannelCopy {
    private static final int SIZE = 1024;//k

    public static void main(String[] args) throws Exception{
        //初始化输入和输出FileChannel
        FileChannel inChannel = new FileInputStream("H:\\data.txt").getChannel(),
                outChannel= new FileOutputStream("H:\\data2.txt").getChannel();
        //按照指定的SIZE,初始化ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        //read返回读取的字节数，为-1标识读取完成
        int i = 0;
        while((i = inChannel.read(buffer)) != -1){
            buffer.flip();//为提取buffer中的数据做准备
            outChannel.write(buffer);//提取buffer中的数据并且输出到文档中
            /*
                必须清空，否则buffer，将无法存储新的数据，注释掉这一句的话就会无限循环。
                ByteBuffer有个limit属性和position属性，当进行flip的时候会position
                会为0,limit会为当前有效的字节数，当数据提取完成以后position等于limit，
                而limit这时候相当与capacity，因此虽然实际上容量是1024但是，此时是
                没有多余的控件来存储新的数据的，因此，每次inChannel.read的时候都
                只能返回0，因为读取了0字节。因此就无限循环。
                会造成输出文件过大。
             */
            buffer.clear();
        }
        outChannel.close();

    }
}

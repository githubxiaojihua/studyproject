package com.xiaojihua.javabasic.chapter18IO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 知识点：memeory-mapping file
 * 用于操作无法一次性加载到内存中的大文件。可以将大文件的指定区域映射到内存进行操作
 * 通过MappedByteBuffer类进行操作，MappedByteBuffer继承自ByteBuffer
 *
 * 本例通过读写一个大文件（128m）来进行演示
 */
public class C25LargeMappedFiles {
    private static final int SIZE = 0x8FFFFFF;//128m
    private static final String FILE = "I:\\data.txt";
    public static void main(String[] args) throws IOException {
        /*
            通过RandomAcessFile来进行读写操作，然后通过map来进行映射指定区域
         */
        MappedByteBuffer mapBuffer = new RandomAccessFile(
                FILE,"rw").getChannel().map(FileChannel.MapMode.READ_WRITE,0,SIZE);
        //往指定区域进行写入
        for(int i=0; i<SIZE; i++){
            mapBuffer.put((byte) 'X');
        }
        System.out.println("Finished write");
        //从文件进行读取，从中间开始读6个字节并转成字符
        for(int i=SIZE/2; i<SIZE/2+6; i++){
            System.out.println((char)mapBuffer.get(i));
        }

    }
}

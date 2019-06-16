package com.xiaojihua.javabasic.chapter18IO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class C25LargeMappedFiles {
    private static final int SIZE = 0x8FFFFFF;//128m
    private static final String FILE = "F:\\data.txt";
    public static void main(String[] args) throws IOException {
        MappedByteBuffer mapBuffer = new RandomAccessFile(FILE,"rw").getChannel().map(FileChannel.MapMode.READ_WRITE,0,SIZE);
        for(int i=0; i<SIZE; i++){
            mapBuffer.put((byte) 'X');
        }
        System.out.println("Finished write");
        for(int i=SIZE/2; i<SIZE/2+6; i++){
            System.out.println((char)mapBuffer.get(i));
        }

    }
}

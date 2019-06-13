package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class C18BufferToText {
    private static final int SIZE = 1024;//k

    public static void main(String[] args) throws IOException {
        FileChannel channel = new FileOutputStream("H:\\data.txt").getChannel();
        channel.write(ByteBuffer.wrap("same text".getBytes()));
        channel.close();

        channel = new FileInputStream("H:\\data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        channel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("decoded using:" + encoding + "," + Charset.forName(encoding).decode(buffer));

    }
}

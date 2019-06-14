package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 知识点：
 * nio
 * ByteBuffer操作的是字节，从字节到字符，必须在字符转换成字节的时候进行编码，然后在
 * 字节变为字符的时候进行解码。java中提供了java.nio.charset.Charset类来进行编码和
 * 解码。关于编码和解码的相关知识参考笔记中的技术文章。
 *
 * 1、Charset类的使用
 */
public class C18BufferToText {
    private static final int SIZE = 1024;//k
    private static final String FILE = "H:\\data.txt";

    public static void main(String[] args) throws IOException {
        FileChannel channel = new FileOutputStream(FILE).getChannel();
        //使用系统默认的编码方式进行编码
        channel.write(ByteBuffer.wrap("same text".getBytes()));
        channel.close();

        channel = new FileInputStream(FILE).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        channel.read(buffer);
        buffer.flip();
        /*
            以下这一句会出现乱码。
            使用asCharBuffer输出的时候并不会进行解码操作，只是单纯的将
            两个字节合并成一个char，因为java中char是由两个字节组成的，
            这样就会造成一些乱码。因为系统默认的编码方式有可能是单字节编码也
            有可能是多字节编码，但是asCharBuffer不管是单字节编码还是多字节
            编码都按两个字节进行组合然后用UTF-16进行解码，因为java中char
            就是使用UNICODE字符集和UTF-16进行编码。
         */
        System.out.println(buffer.asCharBuffer());

        //重新回到buffer的起始位置，准备提取数据。
        buffer.rewind();
        //获得系统默认的编码方式
        String encoding = System.getProperty("file.encoding");
        //使用系统默认的编码方式进行解码，由于编码的时候是通过系统默认的编码方式进行的编码，因此解码也是正确的
        System.out.println("decoded using:" + encoding + "," + Charset.forName(encoding).decode(buffer));

        //可以在写的时候指定编码方式,utf-16be（带BOM，byte order mark的UTF-16）与char的编码方式是一样的
        channel = new FileOutputStream(FILE).getChannel();
        channel.write(ByteBuffer.wrap("same text".getBytes("UTF-16BE")));
        channel.close();//一般写完的时候要close
        channel = new FileInputStream(FILE).getChannel();
        buffer.clear();//注意
        channel.read(buffer);
        buffer.flip();
        //这时候就读取正确
        System.out.println(buffer.asCharBuffer());

        /*
            通过CharBuffer来贯穿写。
            这里有一个点：ByteBuffer分配了24个字节，但是"some text"9个字符
            仅仅占了18个字节（因为按照char编码每个字符占两个字节），剩下的4个字节为0。
            因此在toString的时候会有乱码，因为char找不到相应编码所对应的字符
         */
        channel = new FileOutputStream(FILE).getChannel();
        buffer = ByteBuffer.allocate(24);
        //asCharBuffer后put字符，这样是按照char编码进行写的
        buffer.asCharBuffer().put("Some text");
        channel.write(buffer);
        channel.close();
        channel = new FileInputStream(FILE).getChannel();
        buffer.clear();//注意
        channel.read(buffer);
        buffer.flip();
        //通过CharBuffer来读，达到了前后的编码一致
        System.out.println(buffer.asCharBuffer());

    }
}

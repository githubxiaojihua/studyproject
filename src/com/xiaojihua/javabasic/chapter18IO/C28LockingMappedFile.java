package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 知识点：
 * 对memory-mapped file进行局部的锁定。
 * 对于一个大文件（无法一次性加载到内存的文件）。允许只锁定其中一部分，这样其他进程可以对未锁定
 * 的其他部分进行操作。
 *
 * 本例新建了两个线程，分别锁定同一个文件的不同区域，进行读写。
 *
 * ByteBuffer的slice方法的使用。
 *
 * locks 在JVM退出、channel关闭等时候会自动释放，也可以调用release方法手动释放。
 */
public class C28LockingMappedFile {
    private static final int SIZE = 0x8FFFFFF;//128m
    private static FileChannel fc;

    public static void main(String[] args) throws IOException {
        fc = new RandomAccessFile("H:\\data.txt","rw").getChannel();
        MappedByteBuffer mapBuffer = fc.map(FileChannel.MapMode.READ_WRITE,0,SIZE);

        //初始化文件数据
        for(int i=0; i<SIZE; i++){
            mapBuffer.put((byte)'x');
        }

        new LockFile(0,0 + SIZE/3,mapBuffer);
        new LockFile(SIZE/2,SIZE/2 + SIZE/4,mapBuffer);
    }

    /**
     * 线程类
     */
    private static class LockFile extends Thread{
        private ByteBuffer buffer;
        private int start,size;

        public LockFile(int start, int size, ByteBuffer buffer){
            this.start = start;
            this.size = size;

            /*
                注意这里要先设置limit因为main方法中的第一个new LockFile，会将limit设置
                为0+SIZE/3。如果limit在position之后的话，会都导致第二个new LockFile
                的时候设置的position(SIZE/2)超出，当前的limit(0+SIZE/3)，因此要先设置
                limit，再设置position.
             */
            buffer.limit(size);
            buffer.position(start);

            /*
                使用buffer的position 和limit创建一个新的buffer，新buffer和老buffer之间
                position\mark\limit是相互独立的，但是底层的元素是共享的，比如修改了老buffer
                的数据那么新buffer的数据也会跟着改变，反之亦然。
             */
            this.buffer = buffer.slice();
            start();//通过集成Thread的方式创建线程必须的
        }

        /**
         * 通过集成Thread来创建线程，必须有run方法
         */
        public void run(){
            try{
                FileLock lock = fc.lock(start,size,false);
                System.out.println("File locked, from:" + buffer.position() + ",size:" + size);
                /*
                    不能通过下面的for循环进行，这样会造成buffer索引溢出。
                    原因是，每次for循环中一次git和一次put会导致position增加两次，但是
                    i是每次增加一个，因此会有溢出

                 */
                /*for(int i = buffer.position(); i < buffer.limit() - 6; i++){
                    buffer.put((byte) (buffer.get() + 1));
                }*/
                while(buffer.position() < buffer.limit() - 1){
                    buffer.put((byte) (buffer.get() + 1));
                }
                lock.release();;
                System.out.println("File lock released");
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}

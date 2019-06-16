package com.xiaojihua.javabasic.chapter18IO;

import javax.xml.soap.Text;
import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class C26MappedIO {
    private static final int NUMOFINT = 4000000;
    private static final int NUMOFUNBUFFERINT = 200000;
    private static final String path = "F:\\data.txt";

    public static  abstract class Test{
        private String name;
        Test(String name){
            this.name = name;
        }

        public void doText(){
            try{
                long start = System.nanoTime();
                test();
                System.out.format(name + ":%.2f\n" ,(System.nanoTime() - start)/1.0e9);
            }catch(IOException e){
                throw new RuntimeException(e);
            }

        }

        abstract void test() throws IOException;
    }

    public static Test[] tests = new Test[]{new Test("Stream Write:") {
        @Override
        void test() throws IOException {
            DataOutputStream di = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            for(int i=0; i<NUMOFINT; i++){
                di.writeInt(i);
            }
            di.close();
        }
        },new Test("Mapped Write:") {
            @Override
            void test() throws IOException {
                RandomAccessFile rf = new RandomAccessFile(path,"rw");
                FileChannel fc = rf.getChannel();
                MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size());
                for(int i=0; i<NUMOFINT; i++){
                    mb.putInt(i);
                }
                fc.close();
            }
        },new Test("Stream Read:") {
        @Override
        void test() throws IOException {
            DataInputStream ds = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
            for(int i=0; i<NUMOFINT; i++){
                ds.readInt();
            }
            ds.close();
        }
        },new Test("Mapped Read:") {
        @Override
        void test() throws IOException {
            FileChannel fc = new FileInputStream(path).getChannel();
            MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size());
            while(mb.hasRemaining()){
                mb.getInt();
            }
            fc.close();
        }
        },new Test("Stream Read/Write:") {
        @Override
        void test() throws IOException {
            RandomAccessFile rf = new RandomAccessFile(path,"rw");
            rf.writeInt(1);
            for(int i = 1; i<NUMOFUNBUFFERINT; i++){
                rf.seek(rf.length() - 4);
                rf.writeInt(rf.read());
            }
            rf.close();
        }
        },new Test("Mapped Read/Wite:") {
        @Override
        void test() throws IOException {
            FileChannel fc = new RandomAccessFile(path,"rw").getChannel();
            MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size());
            IntBuffer ib = mb.asIntBuffer();
            ib.put(1);
            for(int i = 1; i<NUMOFUNBUFFERINT; i++){
                ib.put(ib.get(i - 1));
            }
            fc.close();
        }
    }
    };

    public static void main(String[] args){
        for(Test test : tests){
            test.doText();
        }
    }
}

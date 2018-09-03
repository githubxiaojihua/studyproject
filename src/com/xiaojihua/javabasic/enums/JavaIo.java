package com.xiaojihua.javabasic.enums;

import java.io.*;

public class JavaIo {

    public static void main(String[] args) {

        // 创建文件
        //createFile();
        // 字节流
        //charactorStream();
        // 字符流
        zifuStream();

    }

    /**
     * 1、创建文件
     */
    private static void createFile(){

        String filePath = "G:\\test";
        File file = new File(filePath);
        // 不能直接创建G:\test\text.txt，需要判断一下路径是否存在，路径不存在的话先创建路径，然后再创建文件
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            file = new File(filePath,"text.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2、字节流相关程序
     */
    private static void charactorStream(){

        // 1、输入流
        chInputStream();
        // 2、输入流（通过byte[]）
        chInputStreamByByteArray();
        // 3、输入输出流结合实现文件复制
        fileCopy();
        // 4、读写对象
        objectReadAndWrite();

    }

    /**
     * 3、字符流相关程序
     */
    private static void zifuStream(){
        // 1、输入输出流通过char[]
        readerAndWriter();
        // 2、通过使用bufferedReader 和 bufferedWriter实现文件的级联操作，即读取多个文件写入到同一个文件中
        bufferedReaderAndWriter("G:\\test\\text1.txt");
    }

    /**
     * 按照字节读取，每一个字节读取一次
     * 统计文件大小。文件有多少字节就读取多少次，效率比较低
     */
    private static void chInputStream(){
        InputStream streamReader = null;
        int count = 0;//计算字节
        try {
            streamReader = new FileInputStream("G:\\test\\text.txt");
            while(streamReader.read() != -1){
                count++;
            }
            System.out.println("文件的长度为：" + count + "字节!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过byte[]数组来当作缓存读取文件，
     * 一次性读取数组长度的字节
     */
    private static void chInputStreamByByteArray(){
        // 缓冲
        byte[] buffer = new byte[1024];
        InputStream streamReader = null;
        int count = 0;//计算字节
        int countPer = 0;//记录每次读取的字节数
        try {
            streamReader = new FileInputStream("G:\\test\\text.txt");
            while((countPer = streamReader.read(buffer)) != -1){
                count += countPer;
            }
            System.out.println("文件的长度为：" + count + "字节!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用缓存数组通过inputstream 和 outputstream
     * 实现文件复制
     */
    private static void fileCopy(){
        // 定义缓存数组
        byte[] buffer = new byte[1024];
        // 文件输入流
        FileInputStream inputStream = null;
        // 文件输出流
        FileOutputStream outputStream = null;
        // 读取的字节数
        int readNum = 0;

        try {
            inputStream = new FileInputStream("G:\\test\\text.txt");
            // 文件可以不存在直接创建，但是如果路径不存在则需要先创建路径
            outputStream = new FileOutputStream("G:\\test\\text1.txt");
            while((readNum = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,readNum);
            }
            System.out.println("通过字节输入输出流复制文件完成！-");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 读写对象
     */
    private static void objectReadAndWrite(){

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("G:\\test\\text.txt"));
            objectOutputStream.writeObject(new Student("ami",12));
            objectOutputStream.writeObject(new Student("sunny",29));
            objectOutputStream.writeObject(new Student("awa",30));

            objectInputStream = new ObjectInputStream(new FileInputStream("G:\\test\\text.txt"));
            for(int i=0; i<3; i++){
                System.out.println(objectInputStream.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 内部类
     */
    private static class Student implements Serializable{
        private String name;
        private int age;

        Student(String name,int age){
            this.name = name;
            this.age = age;
        }

        public String toString(){
            return "Student [name=" + name + ",age=" + age + "]";
        }
    }

    /**
     * 通过字符流实现读取文件内容并输出到控制台中
     * 通过使用char[]数组当作缓存
     */
    private static void readerAndWriter(){

        char[] buffer = new char[512];// 缓冲区大小
        int numRead = 0;// 一次读取的字符数量

        FileReader reader = null; // 输入字符流
        PrintWriter writer = null;// 输出字符流（控制台流）

        try {
            reader = new FileReader("G:\\test\\text.txt");
            writer = new PrintWriter(System.out);// 输出到控制台
            while((numRead = reader.read(buffer)) != -1){
                writer.write(buffer,0,numRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 通过使用bufferedReader 和 bufferedWriter实现文件的级联操作，即读取多个文件写入到同一个文件中
     * @param fileName
     */
    private static void bufferedReaderAndWriter(String ... fileName){

        String str = null;// 盛放读取的内容
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("G:\\test\\text.txt"));

            for(String name : fileName){
                bufferedReader = new BufferedReader(new FileReader(name));
                while((str = bufferedReader.readLine()) != null){
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

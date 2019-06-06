package com.xiaojihua.javabasic.chapter18IO;

import java.io.File;

/**
 * 知识点：
 * 1、使用File类来检查路径的属性以及创建路径
 */
public class C03MakeDirectories {

    /**
     * 向err输出使用方法
     */
    private static void usage(){
        StringBuilder errStr = new StringBuilder();
        errStr.append("Usage:\n")
                .append("C03MakeDirectories path1 path2 ...\n")
                .append("Create every path!\n")
                .append("C03MakeDirectories -r path1 path2\n")
                .append("Rename path2 to path2\n")
                .append("C03MakeDirectories -d path1 path2 ...\n")
                .append("Delete every path!");
        System.err.println(errStr.toString());
        System.exit(1);
    }

    /**
     * 检查File的各项属性
     * @param file
     */
    private static void fileData(File file){
        StringBuilder fileStr = new StringBuilder();
        fileStr.append("Absolute path:" + file.getAbsolutePath() + "\n")
                .append("Can read:" + file.canRead() + "\n")
                .append("Can write:" + file.canWrite() + "\n")
                .append("Get name:" + file.getName() + "\n")
                .append("Get parent:" + file.getParent() + "\n")
                .append("length:" + file.length() + "\n")
                .append("Last modified:" + file.lastModified());
        if(file.isDirectory()){
           fileStr.append("\nis a Directory.") ;
        }else{
            fileStr.append("\nis a File.");
        }

        System.out.println(fileStr.toString());
    }

    /**
     * 根据运行时输入的运行参数来执行具体操作：重命名、删除、创建
     * @param args
     */
    public static void main(String[] args){
        if(args.length < 1){
            usage();
        }

        if(args[0].equals("-r") ){
            if(args.length != 3){
                usage();
            }else{
                File old = new File(args[1]),
                        newFile = new File(args[2]);
                /*
                    renameTo主要是针对文件而非目录的，可以实现
                    将老文件重新命名，或者当指定另外一个全路径的时候
                    那么就相当于移动文件（并非copy）并且重命名了。
                    但是当目标路径不存在的时候那么是不会进行移动的
                    但是也不会报错。
                    如果两个文件都是文件夹那么是没有作用的不伦这两个
                    文件夹路径是否真实存在。
                    可以根据返回值来判断renameTo是否成功
                 */
                old.renameTo(newFile);
                fileData(old);
                fileData(newFile);

            }
            return;
        }

        int count = 0;
        boolean del = false;
        if(args[0].equals("-d")){
            count++;
            del = true;
        }
        count--;
        while(++count < args.length){
            File file = new File(args[count]);
            //判断路径是否存在，无论是文件还是路径
            if(file.exists()){
                if(del){
                    System.out.println("Deleteing file....");
                    //删除文件或者目录，当删除目录的时候目录必须为空，否则无法删除
                    file.delete();
                }
            }else{
                System.out.println("file doesnot exists");
                if(!del){
                    System.out.println("Createing file...");
                    //创建文件及目录层级
                    file.mkdirs();
                }
            }
            fileData(file);
        }
    }

}

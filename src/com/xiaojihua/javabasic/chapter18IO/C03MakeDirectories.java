package com.xiaojihua.javabasic.chapter18IO;

import java.io.File;

public class C03MakeDirectories {
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
            if(file.exists()){
                if(del){
                    System.out.println("Deleteing file....");
                    file.delete();
                }
            }else{
                if(!del){
                    System.out.println("Createing file...");
                    file.mkdirs();
                }
            }
            fileData(file);
        }
    }

}

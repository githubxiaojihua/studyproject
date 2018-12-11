package com.xiaojihua.datastructure;

import java.io.File;

/**
 * 递归打印文件系统跟名称(带缩进)
 */
public class C11RecursionPrintFile {

    /**
     * 递归打印文件目录带缩进（先序遍历）
     * @param
     */
    public static void printDirs(int index, File file){
        for(int i=0; i<index; i++){
            System.out.print("-");
        }
        System.out.println(file.getName());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fileTmp : files){
                printDirs(index+4, fileTmp);
            }
        }
    }


    public static void main(String[] args) {
        File rootFile = new File("H:\\肖吉华\\01工作任务\\黄岛调研\\01电商平台");
        printDirs(1,rootFile);
    }
}

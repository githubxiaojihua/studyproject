package com.xiaojihua.javabasic.chapter18IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、File即可以代表文件也可以代表目录
 * 2、list()返回一个String类型的数组，用于列举File目录下的目录和文件的名字。
 * list()还可以接收一个FilenameFilter接口作为过滤器使用，用于过滤返回的
 * 文件名称。
 */
public class C01DirList {
    public static void main(String[] args){
        File path = new File(".");
        String[] list;
        if(args.length == 0){
            //返回File指定目录下的所有文件和目录的名称
            list = path.list();
        }else{
            list = path.list(new Filter(args[0]));
            //list = path.list(new Filter(".*\\.iml"));

            //使用lamda表达式实现，函数接口，其中main函数的args应该声明是final的
            //但是由于lamda表达式后面没有再针对args的修改，因此也可以认为它是
            //不可改的。如果后面有改动那么必须声明为final的
            //list = path.list((dir,name) -> { return Pattern.compile(args[0]).matcher(name).matches();});

            //使用匿名内部类来创建FilenameFilter
            /*list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);
                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });*/
        }
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String s : list){
            System.out.println(s);
        }

    }
}


class Filter implements FilenameFilter{
    private Pattern pattern;
    public Filter(String regex){
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}



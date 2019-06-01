package com.xiaojihua.javabasic.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、目录工具类。列举指定目录下的所有目录和文件。如果未指定目录则默认未当前目录。
 */
public final class Directory {

    /**
     * 返回dir目录下的File数组，通过regex来过滤
     * @param dir 起始目录
     * @param regex 过滤的正则表达式
     * @return
     */
    public static File[] local(File dir,final String regex){
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }

    /**
     * 重载
     * @param dir
     * @param regex
     * @return
     */
    public static File[] local(String dir, final String regex){
        return local(new File(dir),regex);
    }

    /**
     * 一个二元对象
     * 存储了文件对象和目录对象，实现Iterable接口，默认的iterator为
     * files的iterator。
     *
     * 由于目的是方便的设置和获取files和dirs因此直接将它们的访问权限设置为
     * public。
     * 一般设置成private然后再提供getter和setter的目的是，封装底层的私有
     * 变量，对外公开符合自己逻辑和检查的getter和setter方法，来控制私有
     * 变量的访问和设置。如果只是简单的设置和访问，那么没有必要设置为private。
     */
    public static class TreeInfo implements Iterable<File>{
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        public void addALL(TreeInfo info){
            this.files.addAll(info.files);
            this.dirs.addAll(info.dirs);
        }

        @Override
        public Iterator iterator(){
            return files.iterator();
        }

        @Override
        public String toString(){
            return "dirs:" + PPrint.format(dirs) + "\n\nfiles:" + PPrint.format(files);
        }
    }

    public static TreeInfo walk(File dir,String regex){
        return recurseDirs(dir,regex);
    }

    public static TreeInfo walk(String dir,String regex){
        return walk(new File(dir),regex);
    }

    public static TreeInfo walk(File dir){
        return walk(dir,".*");
    }

    public static TreeInfo walk(String dir){
        return walk(new File(dir));
    }

    /*private static TreeInfo recurseDirs(File dir, String regex){
        TreeInfo info = new TreeInfo();
        if(dir.isDirectory()){
            info.dirs.add(dir);
            for(File file : dir.listFiles()){
                info.addALL(recurseDirs(file,regex));
            }
        }else{
            if(dir.getName().matches(regex)){
                info.files.add(dir);
            }
        }
        return info;
    }*/

    /**
     * 递归遍历起始目录下的文件和目录，然后分别存储到TreeInfo对象中。
     * 根据regex来过滤存储的文件。
     * @param dir
     * @param regex
     * @return
     */
    private static TreeInfo recurseDirs(File dir, String regex){
        TreeInfo info = new TreeInfo();
        for(File file : dir.listFiles()){
            if(file.isDirectory()){
                info.dirs.add(file);
                info.addALL(recurseDirs(file,regex));
            }else{
                if(file.getName().matches(regex)){
                    info.files.add(file);
                }
            }
        }
        return info;
    }

    public static void main(String[] args){
        if(args.length == 0){
            System.out.println(walk("."));
        }else{
            for(String arg : args){
                System.out.println(walk(arg));
            }
        }

        for(File file : local(new File("."),".*")){
            System.out.println(file.getName());
        }
    }
}

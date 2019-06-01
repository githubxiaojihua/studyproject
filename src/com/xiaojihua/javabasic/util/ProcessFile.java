package com.xiaojihua.javabasic.util;

import java.io.File;
import java.io.IOException;

/**
 * 知识点：
 * 1、写程序要高内聚低耦合，高内聚是指一个类，一个方法仅仅完成一个动作。低耦合是
 * 指类和类之间的关联性要降低。
 * 关于高内聚和低耦合的知识，参考mybase中的笔记。
 *
 * 2、本类是按照扩展名处理指定其实目录下文件，运用了策略模式。
 *
 * 3、File.getCanonicalFile()方法和File.getAbsoluteFile()的区别：
 * 主要的区别是对于文件的path的不同，这两个底层分别调用了getCanonicalPath()和
 * getAbsolutePath()，前者会自动解析路径当中的.或者..以及Unix中的符号链接等
 * 后者仅仅不会解析会带着这些东西。比如：
 * File file = new File(".\\studyproject.iml");
 * System.out.println(file.getAbsolutePath());
 * System.out.println(file.getCanonicalPath());
 * 应该以CanonicalPath/File优先使用。
 */
public class ProcessFile {

    /**
     * 定义了策略模式接口
     */
    @FunctionalInterface
    public interface Strategy{
        void process(File file);
    }

    //扩展名
    private String ext;

    public ProcessFile(String ext){
        this.ext = ext;
    }

    /**
     * 主处理程序
     * @param args 指定的目录或者文件
     * @param strategy 指定的策略
     */
    public void start(String[] args,Strategy strategy){
        try{
            if(args.length == 0){
                //处理当前目录
                processDerictoryTree(new File("."),strategy);
            }else{
                for(String arg : args){
                    File file = new File(arg);
                    if(file.isDirectory()){
                        //处理目录
                        processDerictoryTree(file,strategy);
                    }else{
                        if(!arg.endsWith(ext)){
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 针对目录，调用Directory类的walk方法，并用指定的处理策略来处理文件
     * @param root
     * @param strategy
     * @throws IOException
     */
    private void processDerictoryTree(File root,Strategy strategy) throws IOException{
        for(File file : Directory.walk(root,".*\\." + ext)){
            strategy.process(file.getCanonicalFile());
        }
    }

    public static void main(String[] args){
        new ProcessFile("java").start(args, file -> System.out.println(file));
    }


}

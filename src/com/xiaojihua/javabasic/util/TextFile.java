package com.xiaojihua.javabasic.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 知识点：
 * 1、文件读写的工具类，继承自ArrayList，可以将读取到的内容按照指定分隔符分割后存储到
 * 本ArrayList中。
 */
public class TextFile extends ArrayList<String> {

    /**
     * 静态方法：读取指定文件中的内容，然后将内容作为String返回。
     * 注意try的嵌套
     * @param file
     * @return
     */
    public static String read(String file){
        StringBuilder strBuilder = new StringBuilder();
        try{
            /*
                new File(file).getAbsoluteFile();
                当new File(file)的时候file实际上是指定的一个路径，可以是相对路径
                也可以是绝对路径。当file是绝对路径的时候比如D:\\ab\\abc.txt，那么
                getAbsolutePath返回的就是D:\\ab\\abc.txt。如果file是相对路径
                那么getAbsolutePath返回的就是当前文件（本类文件）的路径前缀加上此相对路径，前缀是
                系统自动加上的。
                new File(file).getAbsoluteFile();实际上等于
                new File(this.getAbsolutePath());这样即使file是一个相对路径
                那么系统也能自动进行补全，并且创建一个绝对路径的File对象

                public static void main(String[] args) throws Exception{

               File file = new File("Test.java");
               System.out.println(file.getPath());
               System.out.println(file.getAbsolutePath());
               System.out.println(file.getCanonicalPath());
               System.out.println(file.getParent());
                可以将以上的路径改为绝对路径实验一下
             */
            BufferedReader reader = new BufferedReader(new FileReader(new File(file).getAbsoluteFile()));
            //这个try只有finally目的只是为了关闭reader,在外层try无法关闭
            try{
                String s;
                while((s = reader.readLine())!= null){
                    strBuilder.append(s).append("\n");
                }
            }finally{
                reader.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return strBuilder.toString();

    }

    /**
     * 静态方法：将text写入到file中
     * 也注意try嵌套
     * @param file
     * @param text
     */
    public static void write(String file, String text){
        try{
            //使用了快捷方式的PrintWriter
            PrintWriter writer = new PrintWriter(new File(file).getAbsoluteFile());
            try{
                writer.write(text);
            }finally{
                //保证文件的关闭
                writer.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }


    }

    /**
     * 实例方法：将本list中存储的信息，写入到file中
     * @param file
     */
    public void write(String file){
        try{
            PrintWriter writer = new PrintWriter(new File(file).getAbsoluteFile());
            try{
                for(String s : this){
                    //追加到文件的最后
                    writer.println(s);
                }
            }finally{
                //保证文件的关闭
                writer.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定的分隔符初始化本list
     * @param file
     * @param regex
     */
    public TextFile(String file,String regex){
        super(Arrays.asList(read(file).split(regex)));
        if(get(0).equals("")){
            remove(0);
        }
    }

    /**
     * 默认按照换行符分割
     * @param file
     */
    public TextFile(String file){
        this(file,"\n");
    }

    public static void main(String[] args){
        String file = "H:\\code_80968\\studyproject\\src\\com\\xiaojihua\\javabasic\\util\\TextFile.java";
        String str = read(file);
        String outFile = "H:\\out.txt";
        write(outFile,str);

        TextFile textFile = new TextFile(file);
        String outFile2 = "H:\\out2.txt";
        textFile.write(outFile2);
        TreeSet<String> treeSet = new TreeSet<>(textFile);
        //返回所有小于a的元素
        System.out.println(treeSet.headSet("a"));

        System.out.println(textFile);
    }
}

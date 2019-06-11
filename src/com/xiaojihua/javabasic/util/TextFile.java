package com.xiaojihua.javabasic.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {

    public static String read(String file){
        StringBuilder strBuilder = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(file).getAbsoluteFile()));
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

    public static void write(String file, String text){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file).getAbsoluteFile()));
            try{
                writer.write(text);
            }finally{
                writer.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }


    }

    public void write(String file){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file).getAbsoluteFile()));
            try{
                for(String s : this){
                    //追加到文件的最后
                    writer.write(s);
                }
            }finally{
                writer.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public TextFile(String file,String regex){
        super(Arrays.asList(read(file).split(regex)));
        if(get(0) == ""){
            remove(0);
        }
    }

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
        System.out.println(treeSet.headSet("a"));
    }
}

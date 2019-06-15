package com.xiaojihua.javabasic.chapter12exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class C11InputFile {
    private BufferedReader in;
    public C11InputFile(String fileName) throws Exception{
        try{
            in = new BufferedReader(new FileReader(fileName));
        }catch(FileNotFoundException e){
            //文件还没有打开则不需要close
            System.out.println("cant open " + fileName);
            throw e;//重新抛出
        }catch(Exception e){
            //其余的情况需要close
            try{
                in.close();
            }catch (IOException ex){
                System.out.println("in.close() unsuccessful");
            }
            throw e;//重新抛出
        }finally {
            //Dont close it here!!!
        }
    }

    public String getLine(){
        String s;
        try{
            s = in.readLine();
        }catch(IOException e){
            //readLine抛出了异常，被认为是运行时错误因此重新抛出运行时异常。运行时异常可以不被声明
            throw new RuntimeException("readLine failed");
        }
        return s;
    }

    public void dispose(){
        try{
            in.close();
            System.out.println("dispose() successful");
        }catch(IOException e){
            throw new RuntimeException("in.close failed");
        }
    }
}

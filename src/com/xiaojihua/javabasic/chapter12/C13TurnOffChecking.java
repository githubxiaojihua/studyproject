package com.xiaojihua.javabasic.chapter12;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 知识点：
 * 1、检查异常包装进RuntimeException。包装成RuntimeException后可以不用在方法的头进行异常
 * 声明，调用处也可以不写try catch语句。RuntimeException会沿着调用栈往上冒泡
 * 2、检查异常用RuntimeException进行了包装后，可以调用getCause()方法来捕获并且处理特定的异常
 * 3、除了使用RuntimeException之外，也可以创建自己的RuntimeException子类。
 *
 */
public class C13TurnOffChecking {
    public static void main(String[] args){
        WrapCheckingException we = new WrapCheckingException();
        //可以直接抛出runtimeexception不用try catch语句。
        we.throwRuntimeException(1);

        for(int i = 0; i < 4; i++){
            try{
                if(i >= 3){
                    throw new SomeOthorException();
                }else{
                    //抛出经过RuntimeException包装后的抛出异常。
                    we.throwRuntimeException(i);
                }
            }catch(SomeOthorException e){
                System.out.println("SomeOtherException:" + e);
            }catch(RuntimeException runE){
                try{
                    //通过getCause抛出原生异常（经过RuntimeException包装前的异常）
                    throw runE.getCause();
                }catch(FileNotFoundException fe){
                    System.out.println("FileNotFoundException:" + fe);
                }catch(IOException ioe){
                    System.out.println("IOException:" + ioe);
                }catch(RuntimeException re){
                    System.out.println("RuntimeException:" + re);
                }catch(Throwable tw){
                    System.out.println("Throwable：" + tw);
                }
            }
        }
    }
}

class WrapCheckingException{
    public void throwRuntimeException(int type){
        try{
            switch(type){
                case 0: throw new FileNotFoundException();
                case 1: throw new IOException();
                case 2: throw new RuntimeException();
                default: return;

            }
        }catch(Exception e){//这里虽然使用了基类Exception但是getCause()仍然能得知具体的类
            //原生异常通过RuntimeException包装。原生类成为了RuntimeException的一个cause
            throw new RuntimeException(e);
        }
    }
}

class SomeOthorException extends Exception{}

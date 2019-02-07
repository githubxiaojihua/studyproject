package com.xiaojihua.javabasic.chapter12;

/**
 * 异常信息的遗漏
 */
public class C09ExceptionSilencer {
    public static void main(String[] args) {
        /**
         * 以下这种情况是异常遗漏的最简单形式
         */
        /*try{
            throw new RuntimeException();
        }finally{
            return;
        }*/
        /**
         * 如下情况中，f()所抛出的异常VeryImportantException
         * 并不会被捕获，只会捕获HoHumException异常。
         */
        try{
            C09ExceptionSilencer es = new C09ExceptionSilencer();
            try{
                es.f();
            }finally{
                es.dispose();
            }
        }catch(VeryImportantException e){
            System.out.println(e);
        }catch (HoHumException e){
            System.out.println(e);
        }
    }

    public void f() throws VeryImportantException{
        throw new VeryImportantException();
    }

    public void dispose() throws HoHumException{
        throw new HoHumException();
    }
}

class VeryImportantException extends Exception{
    public String toString(){
        return "A very important exception";
    }
}

class HoHumException extends Exception{
    public String toString(){
        return "A HOHUM exception";
    }
}
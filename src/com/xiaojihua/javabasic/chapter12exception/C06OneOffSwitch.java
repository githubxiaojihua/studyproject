package com.xiaojihua.javabasic.chapter12exception;

/**
 * 知识点：
 * 1、finally一般用于设置除了内存相关的其他任何事物，恢复其原来状态。比如：打开一个文件、网络链接等。
 * 2、finally不伦是否发生异常、无论发生的异常是否被捕获，都会执行
 */
public class C06OneOffSwitch {
    private static Switch aSwitch = new Switch();
    public static void f() throws Exception1, Exception2{}

    public static void main(String[] args) {
        try{
            aSwitch.on();
            f();
            aSwitch.off();
        }catch(Exception1 e){
            System.out.println("Exception1");
        }catch(Exception2 e){
            System.out.println("Exception2");
        }finally{
            aSwitch.off();
        }
    }
}

class Switch{
    private boolean state = false;
    public boolean read(){ return state; }
    public void off(){
        state = false;
        System.out.println(this);
    }
    public void on(){
        state = true;
        System.out.println(this);
    }
    public String toString(){
        return "status:" + state;
    }
}

class Exception1 extends Exception{}
class Exception2 extends Exception{}

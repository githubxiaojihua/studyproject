package com.xiaojihua.datastructure;

public class TestDemo<AnyType> {
    public static void main(String[] args) {
        System.out.println("Inside main");
        Caps.cap1.f(99);
    }
    static Caps caps1 = new Caps();
    static Caps caps2 = new Caps();
}


class Cap{
    Cap(int num){
        System.out.println("Cup" + num);
    }
    void f(int num){
        System.out.println("f(" + num + ")");
    }
}

class Caps{
    static Cap cap1;
    static Cap cap2;
    static{
        cap1 = new Cap(1);
        cap2 = new Cap(2);
    }

    Caps(){
        System.out.println("Cups()");
    }
}
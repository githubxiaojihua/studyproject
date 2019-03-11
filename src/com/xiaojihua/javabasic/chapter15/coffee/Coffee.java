package com.xiaojihua.javabasic.chapter15.coffee;

public class Coffee {
    private static long count = 0;
    private final long id = count++;
    public String toString(){
        return getClass().getSimpleName() + " " + id;
    }
}

class Latte extends Coffee{}
class Mocha extends Coffee{}
class Cappuccino extends Coffee{}
class Americano extends  Coffee{}

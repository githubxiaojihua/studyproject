package com.xiaojihua.javabasic.chapter5;

/**
 * 传递this
 */
public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple().getPeeled());
    }
}

class Apple{
    Apple getPeeled(){
        return Peeler.peel(this);
    }
}

class Peeler{
    static Apple peel(Apple apple){
        // peeled
        return apple;
    }
}

class Person{
    public void eat(Apple apple){
        apple = apple.getPeeled();
        System.out.println("eat a peeled apple!");
    }
}

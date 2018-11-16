package com.xiaojihua.javabasic;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test extends Villain {
    private int octNumber;
    public Test(String name, int octNumber){
        super(name);
        this.octNumber = octNumber;
    }
    public void change(String name, int octNumber){
        super.set(name);
        this.octNumber = octNumber;
    }
    public String toString(){
        return "Oct " + octNumber + ":" + super.toString();
    }

    public static void main(String[] args) {
        Test test = new Test("Liburge", 20);
        print(test);
        test.change("bob",12);
        print(test);
    }
}

class Villain{
    private String name;
    protected void set(String ne){
        name = ne;
    }
    public Villain(String name){
        this.name = name;
    }
    public String toString(){
        return "I'm a Villian and my name is " + name;
    }
}
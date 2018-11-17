package com.xiaojihua.javabasic.chapter7;

import static com.xiaojihua.javabasic.util.Print.*;//静态引入

/**
 * 知识点：protected关键字，以及静态引入
 * 尽管可以创建protected域，但是最好的方式还是将域声明为private，然后提供protected方法去控制访问权限
 */
class Villain{
    private String name;//声明为private
    //提供protected方法来控制访问权限
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

public class Orc extends Villain {
    private int octNumber;
    public Orc(String name, int octNumber){
        super(name);
        this.octNumber = octNumber;
    }
    //调用父类的protected方法，修改private域的值
    public void change(String name, int octNumber){
        super.set(name);
        this.octNumber = octNumber;
    }
    public String toString(){
        return "Oct " + octNumber + ":" + super.toString();
    }

    public static void main(String[] args) {
        Orc orc = new Orc("Liburge", 20);
        print(orc);
        orc.change("bob",12);
        print(orc);
    }
}
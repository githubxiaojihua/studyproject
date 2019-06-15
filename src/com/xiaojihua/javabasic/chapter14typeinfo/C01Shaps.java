package com.xiaojihua.javabasic.chapter14typeinfo;

import java.util.Arrays;
import java.util.List;

/**
 * 知识点：
 * 1、抽象类的使用，以及多态使用
 */
abstract class Shap{
    void drow(){ System.out.println(this + ".down()");}//间接调用了toString方法
    abstract public String toString();//所有实现类必须覆盖toString方法
}

class Circle extends Shap{
    public String toString(){
        return "Circle";
    }
}

class Square extends Shap{
    public String toString(){
        return "Square";
    }
}

class Triangle extends Shap{
    public String toString(){
        return "Triangle";
    }
}

public class C01Shaps {
    public static void main(String[] args){
        List<Shap> shaps = Arrays.asList(new Circle(), new Square(), new Triangle());
        for(Shap shap : shaps){
            shap.drow();
        }
    }
}

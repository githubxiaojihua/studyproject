package com.xiaojihua.javabasic.chapter15generic;

import java.awt.*;

/**
 * 知识点：
 * 1、泛型的边界与继承相结合。
 * 2、将C15BasicBounds里面的冗余部分进行了基于继承的改造
 */
public class C16InheritBounds {
    public static void main(String[] args){
        SolidByInherit<Bounds> solid = new SolidByInherit<>(new Bounds());
        solid.color();
        solid.getX();
        solid.getWeight();
    }
}

class HoldItem<T>{
    T item;
    public HoldItem(T t){
        item = t;
    }

    public T getItem(){
        return item;
    }
}

class ColoredByInherit<T extends HasColor> extends HoldItem<T>{
    public ColoredByInherit(T t){
        super(t);
    }

    public Color color(){
        return item.color();
    }
}

class ColoredAndDimentionByInherit<T extends Dimention & HasColor> extends ColoredByInherit<T>{
    public ColoredAndDimentionByInherit(T t){
        super(t);
    }

    public int getX(){
        return item.x;
    }
}

class SolidByInherit<T extends Dimention & HasColor & Weight> extends ColoredAndDimentionByInherit<T>{
    public SolidByInherit(T t){
        super(t);
    }

    public int getWeight(){
        return 0;
    }
}

package com.xiaojihua.javabasic.chapter15;

import java.awt.*;

/**
 * 知识点：
 * 1、通过使用extends来设置类型参数的边界，那么擦除对应只擦除
 * 到边界的非泛型上界，在泛型方法中仍然可以使用上界对应的各种
 * 方法。
 * 2、边界可以通过使用&符号指定多个（通配符只能绑定到一个边界），但是应该按照类在前接口在后
 * 的规则。各个边界之间是一种与的关系，所以指定的具体类型参数
 * 应该同时满足这些边界。
 *
 * 3、本类实际上是有一些冗余代码的
 * @see C16InheritBounds
 * 这个类将对本类进行基于继承的重写。
 */
public class C15BasicBounds {
    public static void main(String[] args){
        /**
         * Bounds类必须完全符合Solid类型参数的边界要求才行。
         * 如果Bounds去掉extends Dimention或者其他任何一项
         * ，均会出现编译错误。
         */
        Solid<Bounds> solid = new Solid<>(new Bounds());
        solid.color();
        solid.getX();
        solid.getWeight();
    }

}

interface HasColor{ java.awt.Color color(); }

/**
 * 设置类型参数的边界，这样在泛型代码中，也就是本类中
 * 就可以使用上界HasColor中所拥有的方法了
 * @param <T>
 */
class Colored<T extends HasColor>{
    T item;
    public Colored(T item){
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    /**
     * 此方法并不是因为T extends HasColor才必须实现的。
     * 那是T的事情，并不是本类的事情。
     * @return
     */
    public Color color(){
        //擦除到HasColor为止，因此可以调用color方法
        return item.color();
    }
}

class Dimention{ int x, y, z;}

/**
 * 可以有多个边界，但是要遵守类在前 接口在后的原则
 * 类型参数可以调用所有边界的字段和方法。
 * @param <T>
 */
class DementionColored<T extends Dimention & HasColor>{
    T item;
    public DementionColored(T item){
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public int getX(){
        return item.x;
    }

    public Color color(){
        return item.color();
    }
}

interface Weight{
    int getWeight();
}

class Solid<T extends Dimention & HasColor & Weight>{
    T item;
    public Solid(T item){
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public int getX(){
        return item.x;
    }

    public Color color(){
        return item.color();
    }

    public int getWeight(){
        return item.getWeight();
    }
}

class Bounds extends Dimention implements HasColor, Weight{

    @Override
    public Color color(){
        return null;
    }

    @Override
    public int getWeight(){
        return 0;
    }
}
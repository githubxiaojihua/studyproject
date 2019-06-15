package com.xiaojihua.javabasic.chapter14typeinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 知识点：
 * 1、工厂模式。
 * 2、将相关的工厂类注册到基类C07Part中的partList中
 */
public class C07Part {
    private static List<C06Factory<? extends C07Part>> partList = new ArrayList<>();
    private static Random random = new Random(47);
    //静态代码块加载
    static{
        Collections.addAll(partList,new Afilter.Factory(),new Bfilter.Factory(),
                new Cfilter.Factory(),new Abelt.Factory(),new Bbelt.Factory());
    }

    //提供统一的toString方法
    public String toString(){
        return getClass().getSimpleName();
    }

    //随机选取工厂，并使用其create方法创建对应的类
    public static C07Part getRandom(){
        int n = random.nextInt(partList.size());
        return partList.get(n).create();
    }

    public static void main(String[] args){
       for(int i=0; i<10; i++){
           System.out.println(C07Part.getRandom());
       }
    }


}

/**
 * 标志类
 */
class Filter extends C07Part{}
//标志类相关子类
class Afilter extends Filter{
    public static class Factory implements C06Factory<Afilter>{
        @Override
        public Afilter create() {
            return new Afilter();
        }
    }
}
class Bfilter extends Filter{
    public static class Factory implements C06Factory<Bfilter>{
        @Override
        public Bfilter create(){
            return new Bfilter();
        }
    }
}
class Cfilter extends Filter{
    public static class Factory implements C06Factory<Cfilter>{
        @Override
        public Cfilter create(){
            return new Cfilter();

        }    }
}

/**
 * 标志类
 */
class Belt extends C07Part{}
//标志类相关子类
class Abelt extends Belt{
    public static class Factory implements C06Factory<Abelt>{
        @Override
        public Abelt create(){
            return new Abelt();
        }
    }
}
class Bbelt extends Belt{
    public static class Factory implements C06Factory<Bbelt>{
        @Override
        public Bbelt create(){
            return new Bbelt();
        }
    }
}

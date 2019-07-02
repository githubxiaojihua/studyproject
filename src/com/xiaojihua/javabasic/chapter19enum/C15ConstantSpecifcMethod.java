package com.xiaojihua.javabasic.chapter19enum;

import java.text.DateFormat;
import java.util.Date;

/**
 * 知识点：
 * 实例常量相关的方法。可以为每一个实例常量指定一个具有自己特性表现形式的方法。
 *
 * 方法是：为enum指定一个abstract 方法然后在每一个enum实例中使用{特性方法}
 * 的方式进行实现。
 *
 * 跟C14中的简单命令和模式有很大的类似。
 * 也是多态的一种体现，但是每个enum实例并且是一个类。
 */
public enum C15ConstantSpecifcMethod {
    //每个enum实例实现自己的getInfo
    DATE_TIME{
        //类似命令行模式中的每个具体实现
        public String getInfo(){
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASS_PATH{
        public String getInfo(){
            return System.getenv("CLASSPATH");
        }
    },
    JAVA_VERSION{
        public String getInfo(){
            return System.getProperty("java.version");
        }
    };

    //类似命令行模式中的接口方法
    abstract String getInfo();

    public static void main(String[] args){
        //类似命令行模式，调用接口中的方法，传入实际的实现类
        for(C15ConstantSpecifcMethod m : values()){
            System.out.println(m.getInfo());
        }
    }
}

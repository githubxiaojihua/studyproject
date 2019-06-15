package com.xiaojihua.javabasic.chapter16array;

import com.xiaojihua.javabasic.util.Generator;

import java.util.Random;

/**
 * 知识点：
 * 1、对于C08类的改造，对于C08中的嵌套类的使用
 */
public class C09RandomGenerator {
    private static Random random = new Random(47);

    public static class Boolean implements Generator<java.lang.Boolean>{

        @Override
        public java.lang.Boolean next() {
            return random.nextBoolean();
        }
    }

    public static class Byte implements Generator<java.lang.Byte>{

        @Override
        public java.lang.Byte next() {
            return (byte) random.nextInt();
        }
    }

    public static class Charactor implements Generator<Character>{

        @Override
        public Character next() {
            return C08CounttingGenerator.chars[random.nextInt(C08CounttingGenerator.chars.length)];
        }
    }

    /**
     * 首先考虑到继承，复用了代码。
     * 实例初始化代码块的执行顺序在成员变量赋值之后
     */
    public static class String extends C08CounttingGenerator.String{
        //实力初始化代码块
        { gc = new Charactor(); }
        public String(){}
        public String(int size) {
            super(size);
        }
    }

    public static class Short implements Generator<java.lang.Short>{

        @Override
        public java.lang.Short next() {
            return (short)random.nextInt();
        }
    }

    public static class Integer implements Generator<java.lang.Integer>{
        private int value = 10000;
        public Integer(){}
        public Integer(int value){
            this.value = value;
        }
        @Override
        public java.lang.Integer next() {
            return random.nextInt(value);
        }
    }

    public static class Long implements Generator<java.lang.Long>{
        private int value = 10000;
        public Long(){}
        public Long(int size){
            value = size;
        }
        @Override
        public java.lang.Long next() {
            //return random.nextLong();
            return new java.lang.Long(random.nextInt(value));
        }
    }

    public static class Float implements Generator<java.lang.Float>{

        @Override
        public java.lang.Float next() {
            //保留两位小数的方法
            int value = Math.round(random.nextFloat() * 100);
            return ((float)value)/100;
        }
    }

    public static class Double implements Generator<java.lang.Double>{

        @Override
        public java.lang.Double next() {
            //保留两位小数
            long value = Math.round(random.nextDouble() * 100);
            return ((double)value/100);
        }
    }

    public static void main(java.lang.String[] args){
        //借用C08中的类进行测试
        GeneratorTest.test(C09RandomGenerator.class);
    }
}

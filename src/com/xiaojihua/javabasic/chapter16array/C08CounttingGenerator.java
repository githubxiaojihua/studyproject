package com.xiaojihua.javabasic.chapter16array;

import com.xiaojihua.javabasic.util.Generator;

/**
 * 知识点：
 * 1、将生成器嵌套到一个大的类里面，这样在使用的时候可以：
 * new C08CounttingGenerator.Byte().next()
 * new C08CounttingGenerator.String().next()
 * 2、通过Class.getClasses可以获取内部嵌套类的Class
 *
 */
public class C08CounttingGenerator {
    public static class Boolean implements Generator<java.lang.Boolean>{
        private boolean flag = true;

        @Override
        public java.lang.Boolean next() {
            flag = !flag;
            return flag;
        }
    }

    public static class Byte implements Generator<java.lang.Byte>{
        private byte aByte = 0;
        @Override
        public java.lang.Byte next() {
            return aByte++;
        }
    }

    public static char[] chars = "ABCDEFGHIGKabcdefghigk".toCharArray();
    public static class Charactor implements Generator<Character>{
        private int index = -1;
        @Override
        public Character next() {
            index = (index + 1) % chars.length;
            return chars[index];
        }
    }

    /**
     * 通过使用Charactor创建String生成器
     */
    public static class String implements Generator<java.lang.String>{
        private int size = 7;
        public String(){}
        public String(int size){
            this.size = size;
        }
        //父类引用指向子类对象
        Generator<Character> gc = new Charactor();

        @Override
        public java.lang.String next() {
            char[] chars = new char[size];

            for(int i=0; i<chars.length; i++){
                chars[i] = gc.next();
            }
            //根据char数组生成String
            return new java.lang.String(chars);
        }
    }

    public static class Short implements Generator<java.lang.Short>{
        private short aShort = 0;
        @Override
        public java.lang.Short next() {
            return aShort++;
        }
    }

    public static class Integer implements Generator<java.lang.Integer>{
        private int value = 0;

        @Override
        public java.lang.Integer next() {
            return value++;
        }
    }

    public static class Long implements Generator<java.lang.Long>{
        private long value = 0;

        @Override
        public java.lang.Long next() {
            return value++;
        }
    }

    public static class Float implements Generator<java.lang.Float>{
        private float value = 0;

        @Override
        public java.lang.Float next() {
            return value++;
        }
    }

    public static class Double implements Generator<java.lang.Double>{
        private double value = 0;

        @Override
        public java.lang.Double next() {
            return value++;
        }
    }
}

class GeneratorTest{
    private static int size = 10;

    /**
     * 根据传入的Class，获取其内部声明的public类和接口，以及父类的public类和接口
     * 这里要求这些类都是嵌套类，这样才不报错
     * 如果换成内部类那么第124行会报错，因为内部类是与外部类实例绑定的，
     * 外部类没有实例，何来内部类实例？
     * @param outerClass
     */
    public static void test(Class<?> outerClass)  {
        for(Class<?> type : outerClass.getClasses()){
            System.out.print(type.getSimpleName() + ":");
            try{
                Generator<?> generator = (Generator<?>) type.newInstance();
                for(int i=0; i<size; i++){
                    System.out.print(generator.next() + " ");
                }
                System.out.println();
            }catch(Exception e){
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        test(C08CounttingGenerator.class);
        System.out.println(new C08CounttingGenerator.Byte().next());
    }
}
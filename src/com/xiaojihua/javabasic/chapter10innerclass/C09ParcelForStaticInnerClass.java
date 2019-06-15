package com.xiaojihua.javabasic.chapter10innerclass;

/**
 * 知识点：静态内部类（嵌套类）。
 * 如果不需要内部类和外部类有联系可以使用嵌套类。
 * 内部类和嵌套类的区别：
 * 1、内部类隐式的保存了一个指向外部类对象的this，使用 外部类.this调用。嵌套类没有。
 * 2、创建嵌套类对象不需要外部类对象，但是内部类需要
 * 3、内部类不能有static字段和方法，嵌套类可以
 */
public class C09ParcelForStaticInnerClass {
    private static class ParcelContents implements C01Contents {
        private int i = 11;
        @Override
        public int value() {
            return i;
        }
    }

    private static class ParcelDestination implements C02Destination {
        private String lable;
        ParcelDestination(String lable){
            this.lable = lable;
        }
        @Override
        public String readLable() {
            return lable;
        }

        public static void f(){}
        static int x = 10;
        static class AnotherLevel{
            public static void f(){}
            static int x = 10;
        }
    }

    public static C01Contents contens(){
        return new ParcelContents();
    }

    public static C02Destination destination(String s){
        return new ParcelDestination(s);
    }

    public static void main(String[] args) {
        C01Contents s = contens();
        C02Destination d = destination("aaa");
    }
}

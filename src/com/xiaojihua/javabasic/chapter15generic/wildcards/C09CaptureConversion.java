package com.xiaojihua.javabasic.chapter15generic.wildcards;

/**
 * 知识点：
 * 1、在通配符方法内可以捕捉到传入的具体类型。但仅仅局限于方法体内部。
 * 本例f2能够在内部调用f1而f1是需要一个特定类型参数的，说明f2能够捕获
 * 到传入的具体类型参数，然后传递给f1.
 */
public class C09CaptureConversion {
    private static <T> void f1(C04Holder<T> holder){
        T result = holder.getT();
        System.out.println(result.getClass().getSimpleName());
    }

    private static void f2(C04Holder<?> holder){
        f1(holder);
    }

    public static void main(String[] args){
        //rawtype可以捕获到。输出Integer
        C04Holder row = new C04Holder<Integer>(1);
        f2(row);

        //输出Object
        C04Holder row1 = new C04Holder();
        row1.setT(new Object());
        f2(row1);

        //即使向上转型为？也能捕获到。输出为Double
        C04Holder<?> row3 = new C04Holder<Double>(1.0);
        f2(row3);



    }
}

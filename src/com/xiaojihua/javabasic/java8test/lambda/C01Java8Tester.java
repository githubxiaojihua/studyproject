package com.xiaojihua.javabasic.java8test.lambda;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、lambda表达式允许将函数作为方法参数进行传递。
 * 上面这一句的意思是：像第70行这样的调用 jT.operate(6,4,(a,b)->a+b));
 * 其中第三个参数为lambda表达式，实际上就是一个函数接口的实现，作为一个参数进行传递。
 *
 * 他的一般形势有：
 * (parameters) -> expression或者
 * (parameters) ->{ statements; }
 * 以下是lambda表达式的重要特征:
 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 *
 * 2、lambda表达是一般配合单方法接口或者叫做函数型接口即接口中只有一个函数。来使用.
 *
 */
public class C01Java8Tester {

    /**
     * 函数型接口
     */
    interface MathOperation{
        int operation(int a, int b);
    }

    /**
     * 函数型接口
     */
    interface GreetingService{
        void sayMessage(String message);
    }

    /**
     * 使用函数型接口
     * @param a
     * @param b
     * @param operation
     * @return
     */
    private int operate(int a, int b, MathOperation operation){
        return operation.operation(a,b);
    }

    private final static String outStr = "Hello2";
    public static void main(String[] args){

        //带类型；也相当于函数型接口的实现
        MathOperation addition = (int a, int b) -> a + b;
        print("4+5=" + addition.operation(4,5));
        //不带类型
        MathOperation subtraction = ( a, b ) -> a - b;
        //带｛｝
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        //不带｛｝
        MathOperation division = (int a, int b) -> a/b;

        C01Java8Tester jT = new C01Java8Tester();
        //使用由Lambda表达式实现的函数型接口
        print("6+3=" + jT.operate(6,3,addition));
        print("6-3=" + jT.operate(6,3,subtraction));
        print("6*3=" + jT.operate(6,3,multiplication));
        print("6/3=" + jT.operate(6,3,division));
        //直接在行内实现
        print("6+4=" + jT.operate(6,4,(a,b)->a+b));
        GreetingService service1 = (String message) -> { print("Hello " + message);};
        GreetingService service2 = message -> print("Hello " + message);
        service1.sayMessage("zhangsan");
        service2.sayMessage("lisi");

        /*
            Lamda表达式只能访问标记了final的外部变量，或者是隐含是final的外部变量。
            所谓隐含就是在lambda表达式使用了该变量后，该变量不会在进行改变了。
            outStr为外部final变量
            str为外部隐含final因为在lambda表达式之后没有再进行修改的语句了。
            但是如果打开最后一句的注释那么就会有错误

            Lambda表达式不能声明一个跟外部局部变量相同的参数或者是局部变量。
         */
        GreetingService service3 = message -> { print(outStr + " " + message);};
        String str = "Hello";
        service3 = message -> {print(str + " " + message);};
        //str = "HEll2";
    }
}

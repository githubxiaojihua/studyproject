package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、在泛型类上声明多个参数类型
 * 2、通过使用public final 的方式来代替一般先private然后提供get set方法的做法。
 * 通常的java安全性：声明first和second为private的然后只提供getFirst()和
 * getSecond()方法来访问他们。
 * 但是通过使用public final也同样可以实现如上所述的安全问题。
 *
 * 对于这个类的描述：
 * 提供了两个变量first和second但是只能通过构造函数来进行赋值，并且一旦赋值
 * 后就不能更改了。
 * 这样做是十分安全的，如果客户端程序员在使用的时候想要改变A,B类型就必须重新
 * 建立一个新的C01TwoTuple对象。
 * @param <A>
 * @param <B>
 */
public class C01TwoTuple<A, B> {
    public final A first;
    public final B second;

    public C01TwoTuple(A first, B second){
        this.first = first;
        this.second = second;
    }

    public String toString(){
        return "{" + first + "," + second + "}";
    }
}

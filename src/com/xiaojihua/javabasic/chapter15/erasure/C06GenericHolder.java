package com.xiaojihua.javabasic.chapter15.erasure;

/**
 * 知识点：
 * 泛型的擦除机制会将发型擦除到其非泛型上界，比如本例会擦除到Object,
 * 但是如果使用了extends指定了其他边界，则擦除到其他边界。
 * 但是泛型还能保证内部一致性，所谓的内部一致性指的就是一旦T确定了具体类型比如：
 * GenericHolder<String> holder = GenericHolder<>();那么holder.set（）方法就不能接收其他类型。
 * get()方法也只是返回String。
 * 这种一致性是通过在边界处执行一定的动作来保证的。
 * 边界：指的是对象进入和离开一个方法的点，在这些点上编译器执行编译期类型检测以及自动插入类型转换代码。
 * 对应如下代码，在调用set方法的时候编译器会进行静态类型检测，在调用
 * get方法的时候不需要再进行类型转换，实际上是编译器在内部自动加上了类型转换代码，
 * 可以通过javap -c 来输出编译代码观察。这里的类型检查和自动代码的插入指的就是在边界上的动作。
 *
 * 理论：泛型为保证内部一致性所发生的动作（类型检查和类型转换自动代码的插入）都是在边界处执行的。
 * 要理解泛型的擦除机制要记住上面的理论。
 *
 * 擦除机制的表现对应于下面的代码就是：在编写以上代码的过程中，T只能被当作Object来操作，因为被擦除到了object，如果使用了extends指定了非泛型上界，那么就会擦出到非泛型上界。
 * 擦除机制使得在泛型代码中不能执行其具体类的相关操作。
 * @param <T>
 */
public class C06GenericHolder<T> {
    private T obj;
    public T getObj(){
        return obj;
    }
    public void setObj(T obj){
        this.obj = obj;
    }

    public static void main(String[] args){
        C06GenericHolder<String> gh = new C06GenericHolder<>();
        gh.setObj("abc");
        String obj = gh.getObj();
    }
}

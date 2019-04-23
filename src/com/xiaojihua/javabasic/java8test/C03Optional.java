package com.xiaojihua.javabasic.java8test;

import java.util.Optional;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、Optional类的使用
 *
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 *
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 *
 * Optional 类的引入很好的解决空指针异常
 */
public class C03Optional {

    public static void main(String[] args){
        Integer value1 = null;
        Integer value2 = new Integer(20);

        //ofNullable允许参数为null参数
        Optional<Integer> a = Optional.ofNullable(value1);
        //of如果参数为null抛出NPE异常
        Optional<Integer> b = Optional.of(value2);

        C03Optional optional = new C03Optional();

        /*
            sum通过使用Operational优雅的避免了空指针
            sum2无法避免空指针，只能通过if判断空指针。
         */
        print(optional.sum(a,b));
        print(optional.sum2(null,new Integer(12)));

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        print("第一个参数的值存在：" + a.isPresent());
        print("第二个参数的值存在：" + b.isPresent());

        //如果值存在的返回他，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //获取值，值需要存在。
        Integer value2 = b.get();

        return value1 + value2;

    }

    public Integer sum2(Integer a, Integer b){
        return a + b;
    }
}

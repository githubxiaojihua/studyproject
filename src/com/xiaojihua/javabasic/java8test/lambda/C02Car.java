package com.xiaojihua.javabasic.java8test.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、标志注解FunctionalInterface的使用。表明接口是一个函数型接口，
 * 函数型接口只有一个抽象方法，但是可以有多个非抽象方法。
 * FunctionalInterface可以通过lambda表达式、
 * method references（方法引用）、constructor referrences（构造方法
 * 引用）来实现。
 *
 * 2、方法引用：通过方法的名字来指向一个方法。方法引用的操作符是::
 * 方法引用有四种方式：
 *   1）构造器引用，Class::new或者Class<T>::new
 *   2）静态方法引用，Class::static_method
 *   3）特定类的任意对象的方法引用，Class::method。method为实例方法
 *   4）特定对象的方法引用，instance::method。method为实例方法
 *
 * lanmbda表达式通常用来创建匿名方法，通常用来匿名实现函数型接口。
 * 但当需要使用已经存在的一些具名方法时候，就可以使用方法引用。
 *
 * 方法引用其实也是一种集成度比较高的lambda表达式：
 * You use lambda expressions to create anonymous methods. Sometimes, however,
 * a lambda expression does nothing but call an existing method. In those cases,
 * it's often clearer to refer to the existing method by name. Method references
 * enable you to do this; they are compact, easy-to-read lambda expressions for
 * methods that already have a name.
 * 出自：https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 */
public class C02Car {

    /**
     * 标志注解
     * 表明此接口为函数型接口
     * @param <T>
     */
    @FunctionalInterface
    interface Supplaier<T>{
        T get();
    }

    /**
     * 使用函数型接口，这样就可以在调用create方法的时候
     * 就可以在参数列表中使用lambda表达式或者函数引用等
     * @param supplaier
     * @return
     */
    public static C02Car create(Supplaier<C02Car> supplaier){
        return supplaier.get();
    }

    public static void cllit(C02Car car){
        print("cllit " + car.toString());
    }

    public void follow(C02Car othorCar){
        print(this + "follow " + othorCar.toString());
    }

    public void repair(){
        print("repair " + this.toString());
    }

    public static void main(String[] args){

        /*
            实现函数型接口，实际上就是实现接口中的方法。
            create方法的参数是Supplaier，而其方法是T get()
            只要是符合这个形式的方法都可以。比如：
             C02Car car3 = create(() -> new C02Car());
            这是通过lambda表达式来实现的
            也可以：
            C02Car car = create(C02Car::new);
            这是通过C02Car的构造函数来实现的，因为构造函数也是空参数列表
            并且返回一个对象类型。
         */
        //通过使用构造函数引用来实现函数型接口
        C02Car car = create(C02Car::new);
        C02Car car3 = create(() -> new C02Car());
        List<C02Car> cars = Arrays.asList(car);



        /*
            静态方法引用，静态方法引用所对应的静态方法的签名形式必须和待实现的
            函数方法一致：
            forEach是Iterable的默认方法，其参数是Consumer<? super T> action
            而且Consumer也是一个FunctionInterface，方法为：
            void accept(T t);
            因此C02Car::cllit可以作为其实现
         */
        cars.forEach(C02Car::cllit);

        /*
            特定类的任意对象的方法引用：就是引用某个类的任意实例的实例方法。
            这种引用会转换成等价的lambda表达式，针对下例：
            转换成
            cars.forEach((t)->t.repair());
            因为repair是一个实例函数最终是要对应到某一个具体实例上的。
            也就是说像这种实例方法的引用，在运行期间就会被自动映射到当前
            正在处理的对象上，然后进行调用。

            另外一个例子是：
            https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
            这是java8官方文档
            其中
            Reference to an Instance Method of an Arbitrary Object of a Particular Type
            部分有如下说明：
            The following is an example of a reference to an instance method of an arbitrary
            object of a particular type:
            String[] stringArray = { "Barbara", "James", "Mary", "John",
                                    "Patricia", "Robert", "Michael", "Linda" };
            Arrays.sort(stringArray, String::compareToIgnoreCase);
            The equivalent lambda expression for the method reference
            String::compareToIgnoreCase would have the formal parameter list (String a, String b),
            where a and b are arbitrary names used to better describe this example.
            The method reference would invoke the method a.compareToIgnoreCase(b).
            上文说的比较明白也是将实例方法引用进行了等价转换。最终用当前对象来调用compareToIgnoreCase
            方法。
         */
        cars.forEach(C02Car::repair);

        C02Car car2 = create(C02Car::new);
        System.out.println(car2.toString());
        /*
            特定对象的方法引用:
            下例的意思是，将cars中的每一个对象都应用到
            car2对象的follow方法中，
            最终的输出结果是car2 follow cars中的第一个，
            car2 follow cars中的第二个。

            对应的等价lambda表达式为
            cars.forEach((t) -> car2.follow(t));
            因此follow一定要有一个参数。
         */
        cars.forEach(car2::follow);
        cars.forEach((t) -> car2.follow(t));

        List<String> names = new ArrayList<>();
        names.add("zhangsan1");
        names.add("zhangsan2");
        names.add("zhangsan3");
        names.add("zhangsan4");
        names.add("zhangsan5");
        names.add("zhangsan6");
        names.add("zhangsan7");
        names.add("zhangsan8");
        names.forEach(System.out::println);
    }
}

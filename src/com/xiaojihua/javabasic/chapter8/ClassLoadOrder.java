package com.xiaojihua.javabasic.chapter8;

/**
 * 知识点：继承情况下的静态字段、非静态字段、构造方法的调用顺序
 * 输出揭示了初始化的顺序。
 *
 * 1、当要运行一个类的时候，类加载器首先加载这个类的.class文件，在加载的过程中执行所有静态初始化和静态语句块。
 * 2、若类存在基类那么先执行基类的静态初始化和静态语句块。
 * 3、然后从最基类往外依次执行静态初始化和静态语句块，初始化的顺序按照声明的顺序进行。至此类加载过程完毕。可以创建对象了。
 * 4、如果有main方法那么就开始从上往下执行main中的语句。
 * 5、没有main方法或者在main方法中有创建实例对象那么就进行成员变量的初始化。这里还是看被实例化的类有无基类
 * 如果有基类先进行基类的成员变量初始化，然后再执行基类的构造方法，然后再是子类的成员变量和构造方法。
 *
 * 记住静态变量和静态语句块只执行一次。就是再类加载的时候。
 *
 */
class Animal{
    private AnimalAction animalActionNull;
    private static AnimalAction animalActionStatic = new AnimalAction("3 Animal static");
    private AnimalAction animalActionDyn = new AnimalAction("5 Animal Dyn");
    public void dynMethod(){
        System.out.println("Animal dynMethod");
    }
    public  static void staticMethod(){
        System.out.println("Animal static method");
    }
    Animal(){
        animalActionNull = new AnimalAction("6 AnimalConstractor");
    }

}

class Dog extends Animal{
    private AnimalAction dogAnimalActionNull;
    private static AnimalAction dogActionStatic = new AnimalAction("4 Dog static");
    private AnimalAction dogAnimalActionDyn = new AnimalAction("7 dogDyn");
    public void dynMethod(){
        System.out.println("dog dynMethod");
    }
    public static void staticMethod(){
        System.out.println("dog static method");
    }
    Dog(){
        dogAnimalActionDyn = new AnimalAction("8 dogConstractor");
    }

}

class AnimalAction{
    AnimalAction(String className){
        System.out.println(className);
    }
}

class superClass{
    private static AnimalAction animalAction = new AnimalAction("0 superClass static");
}
public class ClassLoadOrder extends superClass {
    private AnimalAction animalAction = new AnimalAction("2 in main class");
    private static AnimalAction animalActionStatic = new AnimalAction("1 in main class static");

    public static void main(String[] args) {
        System.out.println("这句话将出现在2之前");
        new ClassLoadOrder();//没有这一句的话就不加载“2 in main class”
        Dog dog = new Dog();
        System.out.println("9 in main method");
    }

}

package com.xiaojihua.javabasic.chapter15generic.latantType;

import java.lang.reflect.Method;

/**
 * 知识点：
 * 构造一个方法，不关心接收的参数是什么类型，只关心其是否有特定的方法
 * 可以调用。这种横跨多个类型，只关心具体方法的做法，在java中如果只是
 * 使用泛型是无法实现的，因为泛型是通过擦除来实现的，能调用的方法只是
 * Object的方法，除非使用边界，但是使用边界就会降低泛型代码的通用性，
 * 因为绑定了边界就对泛型代码进行了限制了，并不是任何类型都可以。要达到
 * 只使用泛型不使用边界，并且只对特定方法感兴趣，只能通过泛型配合着反射
 * 来完成。
 *
 * 本例中Robot、SmartDog、Mime是不同的类，并不是一个统一的继承结构
 * 在ComunicationReflectively.perform中可以接收任何对象，然后调用
 * 其中的speak和sit方法，如果没有的话就抛出错误提示。
 */
public class C01LatantReflection {
    public static void main(String[] args){
        ComunicationReflectively.perform(new Robot());
        ComunicationReflectively.perform(new SmartDog());
        ComunicationReflectively.perform(new Mime());
    }
}


interface Perform{
    void speak();
    void sit();
}

class Robot implements Perform{
    @Override
    public void speak(){
        System.out.println("Robot speak");
    }

    @Override
    public void sit(){
        System.out.println("Robot sitting");
    }
}

class SmartDog{
    private void speak(){
        System.out.println("SmartDog speak");
    }

    private void sit(){
        System.out.println("SmartDog sitting");
    }

    public void reproduce(){}
}

class Mime{
    public void walkAgainstTheWind() {}
    public void sit() { System.out.println("Pretending to sit"); }
    public void pushInvisibleWalls() {}
    public String toString() { return "Mime"; }
}

/**
 * 通过反射和泛型的配合来提高泛型的通用性，以及只关心对应的方法。
 * 不关心具体的类型。
 */
class ComunicationReflectively{
    public static <T> void perform(T obj){
        Class<?> clazz = obj.getClass();
        try{
            try{
                Method speak = clazz.getMethod("speak");
                speak.setAccessible(true);
                speak.invoke(obj);
            }catch(NoSuchMethodException e){
                System.out.println("No Method named by speak");
            }
            try{
                Method sit = clazz.getDeclaredMethod("sit");
                sit.setAccessible(true);
                sit.invoke(obj);
            }catch(NoSuchMethodException e){
                System.out.println("no methods named by sit");
            }
        }catch(Exception e){
            throw new RuntimeException(clazz.toString(),e);
        }


    }
}
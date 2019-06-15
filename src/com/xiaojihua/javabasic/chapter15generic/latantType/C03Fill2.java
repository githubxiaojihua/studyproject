package com.xiaojihua.javabasic.chapter15generic.latantType;

import com.xiaojihua.javabasic.chapter15generic.coffee.Coffee;
import com.xiaojihua.javabasic.util.Generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 知识点：
 * 1、适配器模式简单的实现潜在类型机制。潜在类型的机制就是只关心方法，而不关心具体
 * 的类，在那些支持潜在类型的编程语言中比如C++中，实际上也是生成了一个包含相关方法
 * 的潜在的接口。那么在java中实现只需要手动写这样的一个接口，然后配合着适配器模式
 * 可以做到忽略接受到的类型但是只关心方法，达到了通用性的目的。
 *
 * 比如下面的方法fill想关心add方法，并不关心传入的具体类型，而如果只是使用T那么
 * 能操作的方法只有Object类中的，因此就需要手写一个接口，接口中有add方法，然后，通过
 * 搭配适配器模式，就能接收任何类型的方法，提高了方法的通用性。
 */
public class C03Fill2 {
    /**
     * 任何实现了Addable接口的类均可用于此方法。
     * Class.newInstance要求type需要有默认构造器，但是编译器在编译期无法确保
     * ，因此会将检查推迟到运行时，因此会抛出运行时异常。需要捕获
     * @param addable
     * @param type
     * @param size
     * @param <T>
     */
    public static <T> void fill(Addable<T> addable, Class<? extends T> type, int size){
        try{
            for(int i=0; i<size; i++){
                addable.add(type.newInstance());
            }
        }catch(Exception e){
            throw new RuntimeException("Error",e);
        }

    }

    /**
     * 通过Generator的使用可以做到编译器类型检查，编译器不允许传入
     * 不合适的生成器。这样就将上面方法中的Class.newInstance弊端解决掉了
     * @param addable
     * @param generator
     * @param size
     * @param <T>
     */
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size){
        for(int i=0; i<size; i++){
            addable.add(generator.next());
        }
    }

    public static void main(String[] args) {
        List<Coffee> list = new ArrayList<>();

        C03Fill2.fill(new CollectionAdapter<>(list), Coffee.class, 1);
        C03Fill2.fill(AdapterHelper.getCa(list), Coffee.class, 5);

        System.out.println(list);

        SimpleQueueByAdapter<Coffee> simpleQueueByAdapter = new SimpleQueueByAdapter<>();
        C03Fill2.fill(simpleQueueByAdapter, Coffee.class, 5);
        for(Coffee coffee : simpleQueueByAdapter){
            System.out.println(coffee);
        }

        BigDecimal bm = new BigDecimal(1.23);
        System.out.println(bm.ulp().toPlainString());
        BigDecimal bm1 = new BigDecimal(1.2);
        System.out.println(bm1.ulp().toPlainString());
    }

}

/**
 * 一个专用接口，用于fill方法
 * @param <T>
 */
interface Addable<T>{
    void add(T t);
}

/**
 * 一个用于所有Collection的适配器。
 * 使Collection具有addable特性。
 * 注意：如果要给一个继承关系进行适配那么，需要使用组合，
 * 将基类组合进来，这样继承关系中的所有其他类，均可使用
 * 此适配器
 * @param <T>
 */
class CollectionAdapter<T> implements Addable<T>{
    Collection<T> collection;

    public CollectionAdapter(Collection<T> collection){
        this.collection = collection;
    }

    @Override
    public void add(T t){
        collection.add(t);
    }
}

/**
 * 泛型的辅助方法。以一种更优雅的方式来使用泛型，而不比显示的在使用处声明
 * 泛型。
 * 比如59行，相关泛型的类型是全程自动传递的，不比跟58行一样明确声明。
 * 这种方式一般用在简化泛型类的实例化中。
 */
class AdapterHelper{
    public static <T> Addable<T> getCa(Collection<T> collection){
        return new CollectionAdapter(collection);
    }
}

/**
 * 如果只是适配一个具体的类，那么使用继承机制。
 * 首先继承自要适配的类，然后实现相关接口。
 * @param <T>
 */
class SimpleQueueByAdapter<T> extends SimpleQueue<T> implements Addable<T>{
    @Override
    public void add(T t){
        super.add(t);
    }
}



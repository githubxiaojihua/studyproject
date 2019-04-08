package com.xiaojihua.javabasic.chapter15.latantType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、创建一个apply()方法，用于向一个序列中的所有方法请求一个任意指定的
 * 方法。由于是“任何方法”，因此不能使用接口，只能使用反射来解决。
 */
public class C02Apply {

    /**
     * 静态方法apply用于实现上述需求
     * 通过使用反射和可变参数列表，来完成。
     *
     * 此apply方法接收一个列表，因为要使用其增强循环语法特性，因此使用了Iterable接口，这样所有的Collection
     * 都可以应用到这个方法。因为Collection的superinterface是Iterable。
     * 并且java中的List，Aarraylist等相关容器也都简介的集成或者实现了Iterable
     * 接口，因此此方法可以直接应用于这些容器。
     *
     * 但当我们想接收一系列有其他特性的对象，而此特性在java中没有对应的接口对应，更没有相应的
     * 实现类，比如要求接收的对象
     * 应该具备一个addable特性，即这些对象都应该是可以往里增加东西的，那么就没有相应的
     * 现成接口，以及实现此接口的实现类结合共我们使用，但是可以通过适配器模式来解决
     * 这个可以参考接下来的知识点学习类。
     * @param seq
     * @param method
     * @param args
     * @param <T>
     * @param <S>
     */
    public static <T, S extends Iterable<? extends T>> void apply(S seq, Method method, Object ... args){
        try{
            for(T t : seq){
                method.invoke(t,args);
            }
        }catch(Exception e){
            //由于此处一旦出现Exception那么就没有恢复的可能因此直接重新创建一个RuntimeException
            throw new RuntimeException(e);
        }

    }
}


class FilledList<T> extends ArrayList<T>{
    /**
     * 通过使用 type token生成实例.
     *
     * 参数Class<? extends T>还是很有必要使用？extends边界的
     * 因为泛型本身是非协变的，如果只写成 T，那么T的导出类将
     * 不能传入。因为Class<A>和Class<B>被认为是两个独立类型
     * 即使A extends B,这就是泛型的非协变性。
     * 这里有个区别就是如果声明了一个List<B>那么是可以往list
     * 中存A的因为A是B的子类，这个跟泛型的协变性不冲突，协变性
     * 体现在List<B>!=List<A>
     * @param type
     * @param size
     */
    public FilledList(Class<? extends T> type, int size){
        try{
            for(int i=0; i<size; i++){
                //type.newInstance()方法要求，type提供默认构造方法
                add(type.newInstance());
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}


class Shap{
    public void rotate(){
        print(this + " rotate()");
    }

    public void resize(int resize){
        print(this + " resize:" + resize);
    }
}

class Square extends Shap{
    public void rotate(){}
}

/**
 * 通过LinkedList实现简单队列，并实现了Iterable接口
 * @param <T>
 */
class SimpleQueue<T> implements Iterable<T>{
    LinkedList<T> storage = new LinkedList<>();
    public void add(T obj){
        storage.offer(obj);
    }

    public T get(){
        return storage.poll();
    }


    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}

/**
 * 本类说明public static void main不一定非得在public class中
 */
class ApplyTest{
    public static void main(String[] args) throws NoSuchMethodException{
        List<Shap> shapList = new ArrayList<>();
        for(int i=0; i<10; i++){
            shapList.add(new Shap());
        }
        C02Apply.apply(shapList,Shap.class.getMethod("rotate"),null);
        C02Apply.apply(shapList,Shap.class.getMethod("resize", int.class),5);

        List<Square> squares = new ArrayList<>();
        for(int i=0; i<10; i++){
            squares.add(new Square());
        }
        C02Apply.apply(squares,Shap.class.getMethod("rotate"),null);
        C02Apply.apply(squares,Shap.class.getMethod("resize", int.class),5);


        C02Apply.apply(new FilledList<>(Shap.class,10),Shap.class.getMethod("rotate"));
        C02Apply.apply(new FilledList<Shap>(Square.class,10),Shap.class.getMethod("rotate"));

        System.out.println("==================================================");
        SimpleQueue<Shap> shapSimpleQueue = new SimpleQueue<>();
        for(int i=0; i<5; i++){
            shapSimpleQueue.add(new Shap());
            shapSimpleQueue.add(new Square());
        }
        /*
            如果将下面的第二个参数换成Square.class会报IllegalArgumentException的
            运行时异常。原因是因为，shapSimpleQueue里面存储Shap和Square。
            当下面的第二参数是Square.class的时候。反射调用（第30行）会出现问题，因为会对
            Shap类的实例调用Square的rotate方法。当然当对Square类的实例进行调用的时候
            就不会有问题。

            如果第二参数为Shap.class那么是没有问题的。因为不管是Shap和Square都可以
            接收Shap的rotate调用，只不过对Shap实例调用的shap实例的rotate方法。
            对Square实例调用的是Square的rotate方法。
         */
        C02Apply.apply(shapSimpleQueue,Square.class.getMethod("rotate"));
    }
}

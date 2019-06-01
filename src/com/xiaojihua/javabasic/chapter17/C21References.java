package com.xiaojihua.javabasic.chapter17;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * 知识点：
 * 对于java.lang.ref有一篇非常好的文章：
 * https://objectcomputing.com/resources/publications/sett/june-2000-collaborating-with-the-java-memory-manager
 * 里面介绍了很详细包括什么是规范式映射（标准的一对一的映射关系）
 * think in java 中 canonicalized mappings（规范映射）：In such a mapping,
 * you are saving storage by creating only one instance of a particular
 * value. When the program needs that value, it looks up the existing
 * object in the mapping and uses that (rather than creating one from scratch).
 * The mapping may make the values as part of its initialization, but it’s
 * more likely that the values are made on demand
 * 1、java.lang.ref包包含了引用对象（Reference Object）的相关类。当需要持有某个对象的引用并且
 * 允许GC对其进行垃圾回收的时候就需要用到这些类。jva.lang.ref中的相关类提供了
 * 一定程度上的与GC交互的功能。
 *
 * 对于对象的清理的一些个人理解：
 * 首先对对象进行finalizable标志，然后进行finalization,最后标注为finalizaed
 * 然后对对象所占用的内存进行回收。
 * finalization用于清楚对象获取的资源（非内存资源，其他比如文件引用、端口、数据库引用等等）
 *
 * 这个例子中的入队时机不是很明确，但是记住一点：
 * 1、当需要持有某个对象的引用并且允许GC对其进行垃圾回收的时候使用
 * 2、当需求是基于内存敏感的缓存机制的时候那使用SoftReference,当使用基于规范映射的时候使用
 * WeakReference，其他暂时可以不管
 *
 * 2、Reference Object,引用对象。引用对象是将对象引用包装成一个特定的Object，从而使得这个引用
 * 可以像一个对象一样被检查和操纵。
 * 3、不同级别的Reachability(可访问性)
 *    （1）强可访问性（strongly reachable）：当一个对象可以不通过任何引用对象（Reference Object)就能访问的时候，那么
 *    就属于强可访问性。一个新new的对象就是强可访问的。
 *    （2）软可访问性（soft reachable）：非强可访问性，但是可以通过soft Reference进行访问。
 *    （3）弱可访问性（weak reachable）: 并非强可访问性，也非软可访问性，但是可以通过weak Reference进行访问
 *    当一个指向弱可访问对象的weak Reference被清理后，弱可访问对象就可以进行终结了（finalization）；
 *    （4）虚可访问性（phantom reachable): 非以上任何一种可访问性，并且对象已经被终结了（finalized），而且
 *    有一个phantom Reference指向他。
 *    （5）不可访问性（unreachable）: 对象可以被回收了，不属于上面任何一种可访问级别。
 * 4、根据不同的可访问级别java.lang.ref包中提供了不同级别的引用对象类：
 *    （1）SoftReference。GC根据memory的使用情况自主决定是否进行清理，在JVM报OutOfMemory错误之前
 *    SoftReference保证会被清理，但是具体时间是不确定的，大部分是在发生OutOfMemory的时候。
 *    当清理完成以后会将此SoftReference对象入队，
 *    而目标队列是在new SoftReference的时候通过第二个参数指定的。对于SoftReference来讲目标队列可以
 *    不提供。虚拟机的实现规范建议不清理最近使用的SoftReference Object。SoftReference通常用于实现
 *    内存敏感的缓存机制。对于SoftReference Object只要其引用本身（并非其内部包装的引用）是强可访问的
 *    （strongly reachable），也就是说在使用中，那么SoftReference Object就不会被清理。
 *    （2）WeakReference。假设GC在某一个时间点判定某个对象是弱可访问的，那么就会在那个时间点即刻进行清理，此对象的
 *    所有WeakReference引用，同时声明此对象是可终结的（finalizable）。在相同的时间点或者稍晚一些会将
 *    <b>已经清理</b>的WeakReference对象入队到，声明时候指定的队列中。WeakReference通常用于实现规范化映射，比如
 *    WeakHashMap.
 *
 *    （3）PhantomReference。PhantomReference 对象在GC判定其保存的引用所指向的对象可以被回收时候，
 *    当时或者晚些时候进行入队，PhantomReference对象不能通过get取回其引用，在get的时候总是返回null
 *    此类引用对象并不像softreference和weakreference那样在入队的时候就已经由GC进行清理了。只有当所有
 *    对于类的PhantomRefrence引用对象本身被清理后才会被清理
 */
public class C21References {
    private static int size = 10;
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    private static void checkQueue(){
        Reference<? extends VeryBig> reference = rq.poll();
        if(reference != null){
            System.out.println("in queue:" + reference.get());
        }

    }

    public static void main(String[] args){

        LinkedList<SoftReference<VeryBig>> sa =
                new LinkedList<>();
        for(int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(
                    new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            //具体的入队时间是不确定的，队列的主要作用是用来探测相关引用对象是否被清理了
            checkQueue();
        }


        LinkedList<WeakReference<VeryBig>> wa =
                new LinkedList<WeakReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(
                    new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            //具体的入队时间是不确定的，队列的主要作用是用来探测相关引用对象是否被清理了
            checkQueue();
        }

        //可以不指定入队队列
        SoftReference<VeryBig> s =
                new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w =
                new WeakReference<VeryBig>(new VeryBig("Weak"));

        //垃圾回收
        System.gc();

        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<PhantomReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            //gc动作发生的时候入队
            checkQueue();
        }


    }
}

class VeryBig{
    private int size = 10000;
    private long[] la = new long[size];
    private String ident;
    public VeryBig(String id){
        ident = id;
    }
    public String toString(){
        return ident;
    }

    @Override
    public void finalize(){
        System.out.println("finalizing " + ident);
    }
}

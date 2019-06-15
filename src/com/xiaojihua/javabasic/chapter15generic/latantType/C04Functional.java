package com.xiaojihua.javabasic.chapter15generic.latantType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 通过使用函数对象，书写通用代码。
 * 这里其实沿用了
 * @see C03Fill2
 * 中的适配器概念：设置了接口，有接口的实现类，然后泛型方法中
 * 面向接口编程。
 *
 * 函数对象，实际上就是一个对象，里面包含了需要的函数。然后将整个
 * 对象当作参数进行传递。使用函数对象的泛型方法，应该是面向接口编程
 * 的：任何实现了接口的函数对象，都可进行传递，并且使用泛型方法，只要
 * 符合规则就行，这也是策略模式。
 */
public class C04Functional {

    /*
        针对函数对象接口编程
     */

    /**
     * 接收一个可遍历对象，并将每一个对象应用Combinner函数对象
     * 的combin方法。
     * @param iterable
     * @param combinner
     * @param <T>
     * @return
     */
   public static <T> T reduce(Iterable<T> iterable, Combinner<T> combinner){
       Iterator<T> iterator = iterable.iterator();
       if(iterator.hasNext()){
           T result = iterator.next();
           while (iterator.hasNext()){
               result = combinner.combin(result, iterator.next());
           }
           return result;
       }

       return null;
   }

    /**
     * 收集参数的使用
     * 遍历列表，将每一个元素应用与Collector的function方法
     * 但是忽略其返回值，最后直接返回collector。
     * 这个collector就是收集参数，里面存储着处理的结果。
     * @param iterable
     * @param collector
     * @param <T>
     * @return
     */
   public static <T> Collector<T> forEach(Iterable<T> iterable, Collector<T> collector){
       for(T t : iterable){
           collector.function(t);
       }
       return collector;
   }

    /**
     * 对列表的每一项，应用函数对象的function方法
     * @param iterable
     * @param function
     * @param <R>
     * @param <T>
     * @return
     */
   public static <R,T> List<R> transform(Iterable<T> iterable, NaryFunction<R,T> function){
       List<R> rList = new ArrayList<>();
       for(T t : iterable){
           rList.add(function.function(t));
       }
       return rList;
   }

    /**
     * 函数对象的使用
     * @param iterable
     * @param unary
     * @param <T>
     * @return
     */
   public static <T> List<T> filter(Iterable<T> iterable, UnaryPredicate<T> unary){
       List<T> tList = new ArrayList<>();
       for(T t : iterable){
           if(unary.test(t)){
               tList.add(t);
           }
       }
       return tList;
   }

    /*
     * 函数对象接口的实现类。也可使用匿名类实现
     */

   private static class IntegerAdder implements Combinner<Integer>{

       @Override
       public Integer combin(Integer x, Integer y) {
           return x + y;
       }
   }

   private static class IntegerSubtract implements Combinner<Integer>{

       @Override
       public Integer combin(Integer x, Integer y) {
           return x - y;
       }
   }

   private static class BigDecimalAdder implements Combinner<BigDecimal>{

       @Override
       public BigDecimal combin(BigDecimal x, BigDecimal y) {
           return x.add(y);
       }
   }

   private static class BigIntegerAdder implements Combinner<BigInteger>{

       @Override
       public BigInteger combin(BigInteger x, BigInteger y) {
           return x.add(y);
       }
   }

   private static class AutoMicLongAdder implements Combinner<AtomicLong>{

       @Override
       public AtomicLong combin(AtomicLong x, AtomicLong y) {
           return new AtomicLong(x.addAndGet(y.get()));
       }
   }

   private static class GraterThan <T extends Comparable<? super T>> implements UnaryPredicate<T>{
       private T bonnd;
       public GraterThan(T bonnd){
           this.bonnd = bonnd;
       }
       @Override
       public boolean test(T t) {
           return bonnd.compareTo(t) < 0;
       }
   }

   private static class MutipuleCollector implements Collector<Integer>{
       private Integer i = 1;

       @Override
       public Integer function(Integer integer) {
           i *= integer;
           return i;
       }

       @Override
       public Integer result() {
           return i;
       }
   }

   private static class BigDecmalUlp implements NaryFunction<BigDecimal,BigDecimal>{

       @Override
       public BigDecimal function(BigDecimal bigDecimal) {
           return bigDecimal.ulp();
       }
   }

   public static void main(String[] args){
       List<Integer> li = Arrays.asList(1,2,3,4,5,6,7);
       //使用匿名内部类实现
       Integer result = reduce(li, new Combinner<Integer>() {
           @Override
           public Integer combin(Integer x, Integer y) {
               return x + y;
           }
       });
       print(result);
       //使用具体实现类实现
       result = reduce(li, new IntegerAdder());

       result = reduce(li, new IntegerSubtract());
       print(result);

       print(filter(li, new GraterThan<>(4)));

       print(forEach(li, new MutipuleCollector()).result());

       print(forEach(filter(li, new GraterThan<>(4)), new MutipuleCollector()).result());

       //==========================================================================
       //==============待学习：下面这一段的知识需要重新学些，java.math包中的各个类都需要学习一下
       //https://blog.csdn.net/noteless/article/details/83511311
       //里面牵扯到枚举类的一些知识，学习玩枚举后再来看。
       MathContext mc = new MathContext(7);
       List<BigDecimal> lbd = Arrays.asList(
               new BigDecimal(1.1, mc), new BigDecimal(2.2, mc),
               new BigDecimal(3.3, mc), new BigDecimal(4.4, mc));
       BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
       print(rbd);
       print(filter(lbd,
               new GraterThan<>(new BigDecimal(3))));
        // Use the prime-generation facility of BigInteger:
       List<BigInteger> lbi = new ArrayList<>();
       BigInteger bi = BigInteger.valueOf(11);
       for(int i = 0; i < 11; i++) {
           lbi.add(bi);
           bi = bi.nextProbablePrime();
       }
       print(lbi);
       BigInteger rbi = reduce(lbi, new BigIntegerAdder());
       print(rbi);
        // The sum of this list of primes is also prime:
       print(rbi.isProbablePrime(5));
       List<AtomicLong> lal = Arrays.asList(
               new AtomicLong(11), new AtomicLong(47),
               new AtomicLong(74), new AtomicLong(133));
       AtomicLong ral = reduce(lal, new AutoMicLongAdder());
       print(ral);
       print(transform(lbd,new BigDecmalUlp()));

   }

}

/*
    定义函数对象的类型接口
 */

/**
 * 定义一个合并两个相同元素的接口
 * @param <T>
 */
interface Combinner<T>{
    T combin(T t, T y);
}

/**
 * 定义一个一元函数接口
 * @param <R>
 * @param <T>
 */
interface NaryFunction<R,T>{
    R function(T t);
}

/**
 * 定义一个收集参数 接口
 * 所谓收集参数，就是将结果存储在本类（可以返回结果，也可以不返回）。
 * @param <T>
 */
interface Collector<T> extends NaryFunction<T,T>{
    T result();
}

/**
 * 定义一个测试接口
 * @param <T>
 */
interface UnaryPredicate<T>{
    boolean test(T t);
}




package com.xiaojihua.javabasic.java8test.stream;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//================stream中的map和flatmap=================
public class C0201StreamTest {

    /**
     * map接收所有类型的对象，目的是进行映射
     * flatmap接收stream类型对象，目的是进行扁平化处理
     */
    @Test
    public void testMapAndFlatMap(){
        //如果只有一层，则直接使用List内部String对象的方法进行map操作
        List<String> myList = Stream.of("a","b","c").map(String::toUpperCase).collect(Collectors.toList());
        TestCase.assertEquals(Arrays.asList("A","B","C"),myList);


        //如果有多层，map或者flatmap只能使用
        List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
        System.out.println(list);
        //在map中使用Collection::stream方法，使得将list中的第一个list转成流对象，并在外层会再包一层stream,形成多层stream。
        //这里有一个困惑：map中使用的函数接口Function的方法是R apply(T t);但Collection::stream
        //却是 stream()，这好像与appply(T t)的格式不符。
        //这是因为这里的Collection::stream使用的是接口引用中的特定类的任意对象的方法引用，
        //也就是 类名::实例方法，当使用这种方法引用时，实际上是在遍历的每一个实例上调用这个方法
        //因此只要是实例上的方法都可以使用，比如：上面的遍历中实例为ArrayList，那意思就是调用它的stream方法，
        //转化成lamda表达式：
        //list.stream().map(x -> x.stream())
        //这个知识点在java8test.lambda.C02Car中的第100行也介绍了
        Stream<Stream<String>> streamStream = list.stream().map(Collection::stream);
        //在flatmap中使用Collection::stream，则是先将多层List中的元素都取出来形成一个list，然后再应用
        // Collection::stream降成一层stream。
        Stream<String> stringStream = list.stream().flatMap(Collection::stream);
        //从而可以进一步进行面向string的操作
        List<String> collect = stringStream.map(String::toUpperCase).collect(Collectors.toList());
        TestCase.assertEquals(Arrays.asList("A","B"),collect);
    }
}

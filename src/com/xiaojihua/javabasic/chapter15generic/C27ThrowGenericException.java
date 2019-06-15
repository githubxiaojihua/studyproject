package com.xiaojihua.javabasic.chapter15generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：泛型与异常。泛型化异常
 * 1、泛型类不能直接的或者间接继承Throwable。而且catch语句不能捕获泛型类或者类型参数。
 * 但是类型参数可以应用于Throws语句。
 * 2、
 */
public class C27ThrowGenericException {
    public static void main(String[] args){
        ProcessorRunner<String,Failure> processorsStr = new ProcessorRunner<>();
        for(int i=0; i<3; i++){
            processorsStr.add(new StringProcessor());
        }
        try{
            processorsStr.processAll();
        }catch(Failure failure){
            System.out.println("Failure");
        }

        ProcessorRunner<Integer,Failure1> processorsInt = new ProcessorRunner<>();
        for(int i=0; i<3; i++){
            processorsInt.add(new IntegerProcessor());
        }
        try{
            processorsInt.processAll();
        }catch(Failure1 failure1){
            System.out.println("Failure1");
        }

    }
}

/**
 * 类型参数E继承自异常，并且可以throws E
 *
 * 本接口定义了一个处理器接口，里面有一个公共的处理方法
 * @param <T>
 * @param <E>
 */
interface Processor<T, E extends Exception>{
    /**
     * 处理方法
     * @param list 也叫做收集参数。因为方法本身没有返回值，执行的结果都直接存储在
     *             list中。
     * @throws E
     */
    void process(List<T> list) throws E;
}

/**
 * 处理器的应用类。继承自ArrayList,内部存储Processor
 * 遍历内部存储的Processor对list进行处理
 * 最后返回List
 * @param <T>
 * @param <E>
 */
class ProcessorRunner<T, E extends Exception> extends ArrayList<Processor<T,E>> {
    List<T> processAll() throws E{
        /*
            list 作为收集参数使用
         */
        List<T> list = new ArrayList<>();
        for(Processor<T,E> processor : this){
            processor.process(list);
        }
        return list;
    }
}

class Failure extends Exception{}

class StringProcessor implements Processor<String,Failure>{
    private static int counter = 3;
    public void process(List<String> list) throws Failure{
        if(counter-- > 1){
            list.add("Hep");
        }else{
            list.add("Nop");
        }
        if(counter < 0){
            throw new Failure();
        }
    }
}

class Failure1 extends Exception{}

class IntegerProcessor implements Processor<Integer,Failure1>{
    private static int counter = 2;
    @Override
    public void process(List<Integer> list) throws Failure1{
        if(counter-- > 0){
            list.add(47);
        }else{
            list.add(27);
        }
        if(counter < 0){
            throw new Failure1();
        }
    }
}
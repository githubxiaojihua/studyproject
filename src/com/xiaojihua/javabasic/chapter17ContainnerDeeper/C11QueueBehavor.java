package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import com.xiaojihua.javabasic.util.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、不同队列的使用
 * 除了priority queues之外其他的对于元素的排列顺序都是按照插入的顺序.
 * priority queues的元素排列顺序也是基于存储元素的Comparable接口的实现逻辑
 */
public class C11QueueBehavor {
    private static int count = 10;

    /**
     * 泛型方法，防止代码重复。
     * @param queue
     * @param gen
     * @param <T>
     */
    public static <T> void test(Queue<T> queue, Generator<T> gen){

        for(int i=0; i<count; i++){
            queue.offer(gen.next());
        }

        while(queue.peek()!=null){
            printnb(queue.remove() + " ");
        }
        print();
    }

    public static class StringGeneraotr implements Generator<String>{
        private String[] strings = "one two three four five six seven eight night ten".split(" ");
        int i = 0;

        @Override
        public String next(){
            return strings[i++%strings.length];
        }
    }

    public static void main(String[] args){
        test(new LinkedList<>(),new StringGeneraotr());
        test(new PriorityQueue<>(),new StringGeneraotr());
        test(new ArrayBlockingQueue<>(count),new StringGeneraotr());
        test(new ConcurrentLinkedQueue<>(),new StringGeneraotr());
        test(new LinkedBlockingQueue<>(),new StringGeneraotr());
        test(new PriorityQueue<>(),new StringGeneraotr());
    }
}

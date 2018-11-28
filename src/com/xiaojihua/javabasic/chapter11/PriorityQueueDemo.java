package com.xiaojihua.javabasic.chapter11;

import javax.print.attribute.standard.QueuedJobCount;
import java.util.*;

/**
 * 知识点：
 * priorityQueue使用（优先队列）
 * 优先队列在插入的时候按照一定的顺序进行排序，默认是按照自然顺序（实现了Comparable)，也可以传递比较器实现了Comparator。
 *
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //以固定的seed，生成的nextInt都是相同的
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++){
            priorityQueue.offer(rand.nextInt(i + 10));
        }
        QueueDemo.printQ(priorityQueue);//与Integer结合，默认最小的具有较高的优先级

        List<Integer> ints = Arrays.asList(25,22,20,18,14,9,3,1,1,2,3,9,14,18,21,23,25);
        priorityQueue = new PriorityQueue<>(ints);//通过list初始化
        QueueDemo.printQ(priorityQueue);

        priorityQueue = new PriorityQueue<>(ints.size(),Collections.reverseOrder());//指定初始容量以及比较器
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSHCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<>(strings);//与String结合，默认最小的具有较高优先级
        QueueDemo.printQ(stringPQ);

        stringPQ = new PriorityQueue<>(strings.size(),Collections.reverseOrder());//指定初始容量以及比较器
        stringPQ.addAll(strings);
        QueueDemo.printQ(stringPQ);

        Set<Character> charSet = new HashSet<>();
        for(char c : fact.toCharArray()){
            charSet.add(c);
        }
        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);//与Set结合，去重
        QueueDemo.printQ(characterPQ);


    }
}

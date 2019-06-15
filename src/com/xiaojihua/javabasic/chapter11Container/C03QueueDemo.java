package com.xiaojihua.javabasic.chapter11Container;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 知识点：
 * Queue的使用
 */
import static com.xiaojihua.javabasic.util.Print.*;

public class C03QueueDemo {
    public static void printQ( Queue queue){
        //peek返回队头元素，但是不删除
        while(queue.peek() != null){
            //删除U队头元素，并且删除
            printnb(queue.remove() + " ");
        }
        print();
    }

    public static void main(String[] args) {
        Queue<Integer> qi = new LinkedList<>();//可以通过Linkedlist来向上转型来生成队列
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++){
            qi.offer(rand.nextInt(i + 10));
        }
        printQ(qi);

        Queue<Character> qc = new LinkedList<>();
        for(char c : "Talk is cheap,Show me the code".toCharArray()){
            qc.offer(c);
        }
        printQ(qc);
    }
}

package com.xiaojihua.javabasic.chapter11;

import java.util.*;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点1：
 * 通过两个适配器方法使MultiIterableClass类拥有了多种Iterator。
 * 本例使用了适配器模式：想在IterableClass类（已经实现了默认的Iterable接口）的基础上，增加另外的Iterable的实现，比如
 * 实现向后遍历，或者随机遍历等。如果直接继承IterableClass并且覆盖iterator方法那么就会替换掉原有的向前遍历的方法，不能达到
 * 向前、向后和随机遍历的方法。这时候就需要适配器方法，reversed和randomized是两个适配方法。
 * 适配：在不改变原来类的情况下，增加实现特定接口的功能。
 *
 *
 *
 */
public class C08MultiIterableClass extends C07IterableClass {

    /**
     * 增加了反向遍历的Iterable
     * @return
     */
    public Iterable<String> reversed(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int index = words.length - 1;
                    @Override
                    public boolean hasNext() {
                        return index >= 0;
                    }

                    @Override
                    public String next() {
                        return words[index--];
                    }

                    @Override
                    public void remove(){
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /**
     * 增加了随机遍历的Iterable
     * @return
     */
    public Iterable<String> randomized(){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                /**
                 * 这里有一个小知识点：
                 * shuffled是通过向ArrayList的构造函数中传入Arrays.asList(words)来构建的，
                 * 因此在使用Collections.shuffle(shuffled)来打乱shuffled并不影响底层的数组，这一点可以从main函数的
                 * 输出中看到。
                 * 但是如果是直接使用Arrays.asList的返回结果的话：
                 * List<String> shuffled = Arrays.asList(words);这样的话会修改底层数组的实现。
                 * 因此：如果执行的操作会影响这个list并且不想修改底层的数组那么就在一个新的容器中创建。当然这个前提是使用
                 * Arrays.asList方法这种形式。
                 */
                List<String> shuffled = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled);
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        C08MultiIterableClass m = new C08MultiIterableClass();
        //反向遍历
        for(String s : m.reversed()){
            printnb(s + " ");
        }

        print();

        //随机遍历
        for(String s : m.randomized()){
            printnb(s + " ");
        }

        print();

        //原有的遍历
        for(String s : m){
            printnb( s + " ");
        }
    }
}

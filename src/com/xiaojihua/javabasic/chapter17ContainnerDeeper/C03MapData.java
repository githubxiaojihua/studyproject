package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import com.xiaojihua.javabasic.chapter16array.C08CounttingGenerator;
import com.xiaojihua.javabasic.chapter16array.C09RandomGenerator;
import com.xiaojihua.javabasic.util.Generator;
import com.xiaojihua.javabasic.util.MapData;
import com.xiaojihua.javabasic.util.Pair;
import static com.xiaojihua.javabasic.util.Print.*;
import java.util.Iterator;

/**
 * 知识点：
 * 1、通过MapData工具来填充Map容器
 *
 * C03MapData同时实现了Generator和Iterable接口
 */
public class C03MapData implements Generator<Pair<Integer,String>>, Iterable<Integer> {
    private int size = 9;
    private int num = 1;
    private char aChar = 'A';

    @Override
    public Pair<Integer,String> next(){
        //注意自增char然后转换为字符串
        return new Pair<>(num++, "" + aChar++);
    }

    @Override
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return num++ < size;
            }

            @Override
            public Integer next() {
                return num++;
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args){
        //调用PairGenerator和quantity的map方法
        print(MapData.map(new C03MapData(),11));
        //调用分离的keyGenerator和valueGenerator的map方法
        print(MapData.map(new C08CounttingGenerator.Charactor(),new C09RandomGenerator.String(3),9));
        //调用单独keyGenerator和固定value以及quantity方法
        print(MapData.map(new C08CounttingGenerator.Charactor(),"Value",9));
        //调用Iterable和valueGenerator的map方法
        print(MapData.map(new C03MapData(),new C09RandomGenerator.String(3)));
        //调用Iterable和固定Value的map方法
        print(MapData.map(new C03MapData(),"Pop"));
    }
}

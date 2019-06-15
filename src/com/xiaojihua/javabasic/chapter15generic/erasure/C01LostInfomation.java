package com.xiaojihua.javabasic.chapter15generic.erasure;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、getTypeParameters()是返对应类的类型参数。
 * 2、从输出可以看出实际上输出的是类型参数的占位符，并非实际的类型参数的类型
 * 3、这里引出一条定理：在泛型代码内部是没有任何类型参数类型的相关信息的，
 * 比如frobList.getClass().getTypeParameters()
 * 输出的是E（List接口声明中的参数类型占位符），而并非是Frob。
 * 4、java泛型是使用擦除（erasure）来实现的。也就是说当你使用泛型的时候，
 * 具体的类型信息是被擦除的。因此在泛型代码内部类型参数是被当作object来对待的，
 * 因此List<Integer>和List<Double>在运行是是一个类型，都被擦除成原始类型（raw type）List
 *
 *
 */
public class C01LostInfomation {
    public static void main(String[] args){
        List<Frob> frobList = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Frob> quark = new Quark<>();
        Particle<Long, Double> particle = new Particle<>();

        print(Arrays.toString(frobList.getClass().getTypeParameters()));
        print(Arrays.toString(map.getClass().getTypeParameters()));
        print(Arrays.toString(quark.getClass().getTypeParameters()));
        print(Arrays.toString(particle.getClass().getTypeParameters()));
    }
}

class Frob{}
class Fnorkle{}
class Quark<Q>{}
class Particle<Position, Moment>{}

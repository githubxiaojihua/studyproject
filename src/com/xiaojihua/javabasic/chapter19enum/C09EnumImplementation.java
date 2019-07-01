package com.xiaojihua.javabasic.chapter19enum;

import com.xiaojihua.javabasic.util.Generator;

import java.util.Random;

/**
 * 知识点：
 * enum不能继承任何类，但是可以实现接口
 */
public class C09EnumImplementation {

    /**
     * 通过泛型来实现next方法，
     * 比直接Generator<CartoonCharactor></>更具有
     * 通用性
     * @param generator
     * @param <T>
     * @return
     */
    private static <T> T next(Generator<T> generator){
        return generator.next();
    }

    public static void main(String[] args){
        CartoonCharactor cc = CartoonCharactor.CARTOON1;
        for(int i=0; i<7; i++){
            System.out.println(next(cc));
        }
    }
}

/**
 * enum实现Generator接口
 */
enum CartoonCharactor implements Generator<CartoonCharactor> {
    CARTOON1, CARTOON2, CARTOON3, CARTOON4, CARTOON5;

    private Random random = new Random(47);

    public CartoonCharactor next(){
        return values()[random.nextInt(values().length)];
    }
}
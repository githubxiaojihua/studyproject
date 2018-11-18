package com.xiaojihua.javabasic.chapter9;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * 知识点：适配器模式以及接口在适配器模式中的作用
 * 在本例中。RandomDoubles类，想要Scanner类作用与他，在不修改RandomDouble类的前提下，
 * 使用适配器模式，创建RandomDoublesAdapter类让它从RandomDoubles继承，同时实现Readable接口，
 * 实现伪继承，因为Scanner可以接收Readable接口的参数类。所以新的适配器类即是RandomDouble也是Readable类。
 *
 * 对于适配器的理解：实际上它就是一个转换器，将一个类转化为另一个类。
 * 而有了接口机制，才有了适配器，接口为适配器提供了良好的基础。
 */
public class RandomDoublesAdapter extends RandomDoubles implements Readable {
    private int count;
    RandomDoublesAdapter(int count){
        this.count = count;
    }
    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- ==0){
            return -1;
        }
        String result = Double.toString(next()) + " ";//不要忘记增加分隔符
        cb.append(result);
        return result.length();
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(new RandomDoublesAdapter(10));
            while(scanner.hasNextDouble()){
                System.out.println(scanner.nextDouble());
            }
    }
}

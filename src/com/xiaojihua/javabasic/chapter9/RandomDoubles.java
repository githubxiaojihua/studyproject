package com.xiaojihua.javabasic.chapter9;

import java.util.Random;
import static com.xiaojihua.javabasic.util.Print.*;

public class RandomDoubles {
    private static Random random = new Random(47);
    public double next(){
        return random.nextDouble();
    }

    public static void main(String[] args) {
        RandomDoubles randomDoubles = new RandomDoubles();
        for(int i = 0; i < 7; i++){
            print(randomDoubles.next());
        }
    }
}

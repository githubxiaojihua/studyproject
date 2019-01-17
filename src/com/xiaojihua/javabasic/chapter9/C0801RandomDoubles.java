package com.xiaojihua.javabasic.chapter9;

import java.util.Random;
import static com.xiaojihua.javabasic.util.Print.*;

public class C0801RandomDoubles {
    private static Random random = new Random(47);
    public double next(){
        return random.nextDouble();
    }

    public static void main(String[] args) {
        C0801RandomDoubles randomDoubles = new C0801RandomDoubles();
        for(int i = 0; i < 7; i++){
            print(randomDoubles.next());
        }
    }
}

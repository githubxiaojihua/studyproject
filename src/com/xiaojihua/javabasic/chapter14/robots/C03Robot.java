package com.xiaojihua.javabasic.chapter14.robots;

import java.util.List;
import static com.xiaojihua.javabasic.util.Print.*;

public interface C03Robot {
    String name();
    String modole();
    List<C02Operation> operations();

    class Test{
        public static void test(C03Robot robot){
            if(robot instanceof C01Null){
                print("[Null Robot]");
            }
            print(robot.name());
            print(robot.modole());
            for(C02Operation oper : robot.operations()){
                print(oper.description());
                oper.command();
            }
        }
    }
}

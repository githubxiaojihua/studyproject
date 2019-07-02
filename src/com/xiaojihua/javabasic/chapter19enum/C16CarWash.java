package com.xiaojihua.javabasic.chapter19enum;

import java.util.EnumSet;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 对于<b>实例常量相关方法</b>的举例应用。
 * 建立不同的洗车动作，用户可以选取自己的洗车动作，通过使用EnumSet
 * 来记录用户的选择。
 */
public class C16CarWash {
    public enum Cycle{
        UNDERBODY{
          public void action(){
              print("Spraying the underbody");
          }
        },
        WHEELWASH{
            public void action(){
                print("Washing the wheels");
            }
        },
        PREWASH{
            public void action(){
                print("Loosening the dirt");
            }
        },
        BASIC{
            public void action(){
                print("The basic wash");
            }
        },
        HOTWAX{
            public void action(){
                print("Applying hot wax");
            }
        },
        RINSE{
            public void action(){
                print("Rinsing");
            }
        },
        BLOWDRY{
            public void action(){
                print("Blowing dry");
            }
        };

        abstract void action();
    }

    private EnumSet<Cycle> carWash = EnumSet.of(Cycle.BASIC,Cycle.RINSE);

    public void add(Cycle cycle){
        carWash.add(cycle);
    }

    public void carWash(){
        //调用不同的具体实现类的action方法
        for(Cycle c : carWash){
            c.action();
        }
    }

    @Override
    public String toString(){
        return carWash.toString();
    }

    public static void main(String[] args){
        C16CarWash carWash = new C16CarWash();
        print(carWash);
        carWash.carWash();

        //由于EnumSet也是set因此不接收重复输入
        carWash.add(Cycle.BLOWDRY);
        carWash.add(Cycle.BLOWDRY);
        carWash.add(Cycle.RINSE);
        carWash.add(Cycle.HOTWAX);
        print(carWash);
        carWash.carWash();
    }
}

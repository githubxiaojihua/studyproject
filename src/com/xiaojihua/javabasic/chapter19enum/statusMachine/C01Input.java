package com.xiaojihua.javabasic.chapter19enum.statusMachine;

import java.util.Random;

public enum C01Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION{
        public int amount(){
            throw new RuntimeException("ABORT_TRANSACTION.amount()");
        }
    },
    STOP{
        public int amount(){
            throw new RuntimeException("STOP.amout()");
        }
    };

    private int value;

    private C01Input(){}
    private C01Input(int value){
        this.value = value;
    }

    public int amount(){
        return value;
    }

    static Random random = new Random(47);

    public static C01Input randomSelection(){
        return values()[random.nextInt(values().length -1 )];
    }


}

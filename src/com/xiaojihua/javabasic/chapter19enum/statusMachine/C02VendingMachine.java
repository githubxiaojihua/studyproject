package com.xiaojihua.javabasic.chapter19enum.statusMachine;


import java.util.EnumMap;
import static com.xiaojihua.javabasic.chapter19enum.statusMachine.C01Input.*;
import static com.xiaojihua.javabasic.util.Print.*;

public class C02VendingMachine {
    public static int amount = 0;
    private static Status status = Status.RESTING;
    private static C01Input selectItem;

    enum Transient{ TRANSIENT }
    enum Status{
        RESTING{
            public void next(C01Input input){
                switch(Category.categorize(input)){
                    case MONEY:
                        amount += input.amount();
                        status = ADD_MONEY;
                        break;
                    case ITEM:
                        status = PACKAGE;
                        break;
                    case ABORT_TRANC:
                        status = ABORT_TRN;
                        break;
                    case TERMINAL:
                        status = TERMINTED;
                }
            }
        },
        ADD_MONEY{
            public void next(C01Input input){
                switch(Category.categorize(input)){
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM:
                        status = PACKAGE;
                        break;
                    case ABORT_TRANC:
                        status = ABORT_TRN;
                        break;
                    case TERMINAL:
                        status = TERMINTED;
                }
            }

        },
        PACKAGE{

        },
        GIVE_CHARGE{

        },
        ABORT_TRN(Transient.TRANSIENT){

        },
        TERMINTED(Transient.TRANSIENT){

        };

        private boolean isTransient;
        private Status(){}
        private Status(Transient atransient){
            isTransient = true;
        }

        public void next(C01Input input){
            throw new RuntimeException("only non-transitent can invok next(input)");
        }

        public void next(){
            throw new RuntimeException("only transitent can invoke next()");
        }

        public void outPut(){
            print(amount);
        }
    }



}

enum Category{
    MONEY(NICKEL,DIME,QUARTER,DOLLAR),
    ITEM(TOOTHPASTE,CHIPS,SODA),
    ABORT_TRANC(ABORT_TRANSACTION),
    TERMINAL(STOP);

    private C01Input[] values;
    private Category(){}
    private Category(C01Input ... inputs){
        values = inputs;
    }
    private static EnumMap<C01Input, Category> categoryMap =
            new EnumMap<C01Input, Category>(C01Input.class);

    static{
        for(Category c : categoryMap.values()){
            for(C01Input input : c.values){
                categoryMap.put(input,c);
            }
        }
    }

    public static Category categorize(C01Input input){
        return categoryMap.get(input);
    }
}


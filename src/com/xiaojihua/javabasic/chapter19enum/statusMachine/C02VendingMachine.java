package com.xiaojihua.javabasic.chapter19enum.statusMachine;

import com.xiaojihua.javabasic.util.Generator;
import com.xiaojihua.javabasic.util.TextFile;
import java.util.EnumMap;
import java.util.Iterator;
import static com.xiaojihua.javabasic.chapter19enum.statusMachine.C01Input.*;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 通过enum来实现状态机。
 */
public class C02VendingMachine {

    /*
        以下字段都必须是static的，因为，他们是在enum实例的方法中使用的，而这些
        方法是enum中定义的next(C01Input），input(),output()等，这些方法被
        编译后，都编译成了static final的了。这个可以从C07Reflection.java中
        看出。因此也是一个使用enum的限制。
     */
    public static int amount = 0;//总计价格
    private static Status status = Status.RESTING;//状态机当前状态
    private static C01Input selectItem = null;//选择的商品

    enum StateDuration{ TRANSIENT }//标志enum

    /**
     * 定义各类状态
     */
    enum Status{
        //等待状态
        RESTING{
            public void next(C01Input input){
                switch(Category.categorize(input)){
                    case MONEY:
                        amount += input.amount();
                        status = ADD_MONEY;
                        break;
                    case SHUTDOWN:
                        status = TERMINTED;
                    default:
                }
            }
        },
        //放钱状态
        ADD_MONEY{
            public void next(C01Input input){
                switch(Category.categorize(input)){
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selectItem = input;
                        if(amount < input.amount()){
                            print("Insufficient maney for" + input);
                        }else{
                            status = PACKAGE;
                        }
                        break;
                    case QUIT_TRANC:
                        status = GIVE_CHARGE;
                        break;
                    case SHUTDOWN:
                        status = TERMINTED;
                        break;
                    default:
                }
            }

        },
        //给商品状态，通过标志enum来区分，不再接收input而是next()
        PACKAGE(StateDuration.TRANSIENT){
            public void next(){
                print("here is you selection: " + selectItem);
                amount -= selectItem.amount();
                status = GIVE_CHARGE;
            }
        },
        //找零钱状态 ,通过标志enum来区分，不再接收input而是next()
        GIVE_CHARGE(StateDuration.TRANSIENT){
            public void next(){
                if(amount > 0){
                    print("here is your charge:" + amount);
                    amount = 0;
                }
                status = RESTING;
            }
        },
        //停止状态
        TERMINTED{
            public void outPut(){
                print("Halted");
            }
        };

        private boolean isTransient = false;
        private Status(){}
        private Status(StateDuration atransient){
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

    /**
     * 通过设计模式将本类与C01Input解耦
     * 当前来看可以做到将C01Input的randomSelection方法gng
     * status.next方法解耦，否则，status.next方法将直接使用
     * C01Input.randomSelection方法。
     * @param generator
     */
    public static void run(Generator<C01Input> generator){

        while(status != Status.TERMINTED){
            status.next(generator.next());
            //如果是标志enum所表明的状态，那么调用next()
            while(status.isTransient){
                status.next();
            }
            status.outPut();
        }
    }


    public static void main(String[] args){
        Generator<C01Input> generator = new RandomInputGenerator();
        if(args[0] != null){
            generator = new FileInputGenerator(args[0]);
        }
        run(generator);
    }



}

/**
 * 对不同的enum进行分类
 */
enum Category{
    MONEY(NICKEL,DIME,QUARTER,DOLLAR),
    ITEM_SELECTION(TOOTHPASTE,CHIPS,SODA,SOAP),
    QUIT_TRANC(ABORT_TRANSACTION),
    SHUTDOWN(STOP);

    private C01Input[] values;

    Category(C01Input ... inputs){
        values = inputs;
    }

    //通过enumMap来存储每个C01Input的所属分类
    private static EnumMap<C01Input, Category> categoryMap =
            new EnumMap(C01Input.class);

    //初始化EnumMap
    static{
        for(Category c : Category.class.getEnumConstants()){
            for(C01Input input : c.values){
                categoryMap.put(input,c);
            }
        }
    }

    /**
     * 给定一个C01Input返回所属Category
     * @param input
     * @return
     */
    public static Category categorize(C01Input input){
        return categoryMap.get(input);
    }
}


class RandomInputGenerator implements Generator<C01Input>{
    @Override
    public C01Input next(){
        return C01Input.randomSelection();
    }
}

class FileInputGenerator implements Generator<C01Input>{
    private Iterator<String> iterator = null;

    public FileInputGenerator(String file){
        TextFile textFile = new TextFile(file,";");
        iterator = textFile.iterator();
    }

    @Override
    public C01Input next(){

        if(!iterator.hasNext()){
            return null;
        }
        //根据String类型返回对应的enum实例
        return Enum.valueOf(C01Input.class,iterator.next().trim());
    }
}



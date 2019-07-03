package com.xiaojihua.javabasic.chapter19enum.chainofresponsibilitywithenum;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 使用enum实现责任链设计模式。责任链设计模式的简单解释为：针对一个问题创建一系列的解决方案，并
 * 将解决方案串连到一起，当一个处理请求发生后会顺着解决方案链一个一个的走下去直到有一个解决方案
 * 可以解决这个请求，又或者没有任何一个解决方案能解决这个问题。
 *
 * 下面就是通过enum创建了一个Mail_Handler的责任链，而每个责任的处理顺序是由其在enum中声明的
 * 位置来决定的，
 */
public class C02PostOffice {

    /**
     * 使用enum实现一个责任链，每一个责任链用于处理一种特定情况下的mail
     */
    enum Mail_Handler{
        //用于处理 可以使用general delivery方式发送的信件
        GENERAL_DELIVERY{
            public boolean handle(C01Mail mail){
                switch(mail.generalDelivery){
                    case YES: print("Using general delivery for " + mail);
                              return true;
                    default:  return false;
                }
            }
        },
        //用于处理 可以使用自动发送方式的信件
        MACHINE_SCAN{
            public boolean handle(C01Mail mail){
                switch(mail.scannability){
                    case UNSCANNABLE: return false;
                    default:
                        switch(mail.address){
                            case INCORRECT: return false;
                            default: print("Delivery " + mail + "automatically");
                                     return true;
                        }
                }
            }
        },
        //用于处理可以用于nomally发送的信件
        VASUL_INSPECTION{
            public boolean handle(C01Mail mail){
                switch(mail.readability){
                    case ILLEGIBLE: return false;
                    default:
                        switch(mail.address){
                            case INCORRECT: return false;
                            default: print("Delivery " + mail + "nomally");
                                     return true;
                        }
                }
            }
        },
        //用于处理可用于返回发信人处的信件
        RETURN_TO_SENDER{
            public boolean handle(C01Mail mail){
                switch(mail.returnAddress){
                    case MISSING: return false;
                    default: print("Returning " + mail + "to Senter");
                             return true;
                }
            }
        };

        //抽象方法
        abstract boolean handle(C01Mail mail);
    }

    /**
     * 使用责任链处理一封信件
     * @param mail
     */
    public static void handle(C01Mail mail){
        for(Mail_Handler handler : Mail_Handler.values()){
            if(handler.handle(mail)){
                return;
            }
        }
        //如果没有任何一个责任链处理成功，则任为是dead letter
        print(mail + " is a dead letter");
    }

    public static void main(String[] args){
        /*
            调用Mail类的静态方法，生成10封信件并，使用责任链进行处理。

            C01Mail.generator方法是C01Mail的静态方法，返回了一个Iterable对象
            因此可直接放在增强for循环里使用。
            注意generator方法的实现
         */
        for(C01Mail mail : C01Mail.generator(10)){
            print(mail.mailDetail());
            handle(mail);
            print("=================================");
        }
    }
}

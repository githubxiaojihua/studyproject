package com.xiaojihua.javabasic.chapter19enum.chainofresponsibilitywithenum;

import com.xiaojihua.javabasic.util.Enums;

import java.util.Iterator;

/**
 * 构建一个Mail类，封装相关的一些属性：GeneralDelivery、Scannability、
 * Readability等。
 * 并且可以使用static方法静态的生成mail实例。
 * 自带生成器。
 */
public class C01Mail {
    public enum GeneralDelivery{YES, NO1, NO2, NO3, NO4, NO5}
    public enum Scannability{ UNSCANNABLE, YES1, YES2, YES3, YES4}
    public enum Readability{ ILLEGIBLE, YES1, YES2, YES3, YES4}
    public enum Address{ INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}
    public enum ReturnAddress{ MISSING, OK1, OK2, OK3, OK4, OK5}

    public GeneralDelivery generalDelivery;
    public Scannability scannability;
    public Readability readability;
    public Address address;
    public ReturnAddress returnAddress;

    private static int counter = 0;
    private int id = counter++;

    public String toString(){
        return "Mail" + id;
    }

    public String mailDetail(){
        return toString() + ",GeneralDelivery:" + generalDelivery + "," +
                "Scannability:" + scannability + ",Readability:" + readability +
                ",Address:" + address + ", ReturnAddress:" + returnAddress;
    }

    public static C01Mail randomMail(){
        C01Mail mail = new C01Mail();
        mail.generalDelivery = Enums.randomNext(GeneralDelivery.class);
        mail.scannability = Enums.randomNext(Scannability.class);
        mail.readability = Enums.randomNext(Readability.class);
        mail.address = Enums.randomNext(Address.class);
        mail.returnAddress = Enums.randomNext(ReturnAddress.class);
        return mail;
    }

    /**
     * 生成器。
     * 返回一个Iterable对象，可用于增强for循环
     * nums应该声明为final的，因为在匿名内部类中使用了，但是这晨
     * 没用是因为编译器认为它是final的，因为在代码中没有任何更改它的地方
     * @param nums
     * @return
     */
    public static Iterable<C01Mail> generator(int nums){
        return new Iterable<C01Mail>() {
            private int count = nums;
            @Override
            public Iterator<C01Mail> iterator() {
                return new Iterator<C01Mail>() {

                    @Override
                    public boolean hasNext() {
                        return count-- > 0;
                    }

                    @Override
                    public C01Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove(){
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

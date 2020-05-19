package com.xiaojihua.gof23.p13iterator;

public class C03Client {
    public static void main(String[] args) {
        C02ConcreteMyAggregate aggregate = new C02ConcreteMyAggregate();
        aggregate.addObje("aa");
        aggregate.addObje("bb");
        aggregate.addObje("cc");

        C01MyIterator myIterator = aggregate.createMyIterator();

        while(myIterator.hasNext()){
            System.out.println(myIterator.getCurrentObj());
            myIterator.next();
        }
    }
}

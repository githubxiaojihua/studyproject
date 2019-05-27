package com.xiaojihua.javabasic.disignPattern.flyWeight;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 知识点：
 * 1、FlyWeight 设计模式，俗成享元设计模式。
 * FlyWeight是通过与其他相似对象共享尽可能多的数据来最小化内存使用的一种对象。
 * 享元对象的部分属性和状态是共享的，而且这些对象一般都存储在外部
 * 的数据结构中，在使用的时候临时传递他们给对应的引用。
 * 比如本里中不变的部分是CoffeeFlavor的name，
 *
 * 为了能在客户和线程之间安全的共享，FlyWeight对象必须是不变的（immutable)。
 * FlyWeith应该是value object(简单说value obejct的值一旦确定就不会改变了），
 * 任意两个value object只要是值相等那么就认为其相等。
 *
 * 当系统中需要大量对象，或者对象需要非常大的内存的时候，就需要享元模式。
 *
 * 
 *
 */
public class C01FlyweightExample {

    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        shop.takeOrder("Cappuccino", 2);
        shop.takeOrder("Frappe", 1);
        shop.takeOrder("Espresso", 1);
        shop.takeOrder("Frappe", 897);
        shop.takeOrder("Cappuccino", 97);
        shop.takeOrder("Frappe", 3);
        shop.takeOrder("Espresso", 3);
        shop.takeOrder("Cappuccino", 3);
        shop.takeOrder("Espresso", 96);
        shop.takeOrder("Frappe", 552);
        shop.takeOrder("Cappuccino", 121);
        shop.takeOrder("Espresso", 121);

        shop.service();
        System.out.println("CoffeeFlavor objects in cache: " + CoffeeFlavor.flavoursInCache());
    }
}

/**
 * FlyWeight对象
 */
class CoffeeFlavor{
    private final String name;
    private static final WeakHashMap<String,CoffeeFlavor> CACHE = new WeakHashMap<>();

    private CoffeeFlavor(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public static CoffeeFlavor intern(String name){
        synchronized(CACHE){
            //如果cache有name对应的value则返回，如果没有则新增
            return CACHE.computeIfAbsent(name,CoffeeFlavor::new);
        }
    }

    public static int flavoursInCache(){
        synchronized(CACHE){
            return CACHE.size();
        }
    }


}

@FunctionalInterface
interface Order {
    void serve();

    //静态默认方法，与之对应的是普通的静态方法用关键字default
    static Order of(String flavourName, int tableNumber) {
        CoffeeFlavor flavour = CoffeeFlavor.intern(flavourName);
        return () -> System.out.println("Serving " + flavour + " to table " + tableNumber);
    }
}

class CoffeeShop {
    private final ArrayList<Order> orders = new ArrayList<>();

    public void takeOrder(String flavour, int tableNumber) {
        orders.add(Order.of(flavour, tableNumber));
    }

    public void service() {
        //特定类的实例方法引用。等同于orders.forEach((x) -> x.serve());
        orders.forEach(Order::serve);

    }
}
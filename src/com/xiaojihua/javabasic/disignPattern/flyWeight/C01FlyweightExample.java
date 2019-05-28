package com.xiaojihua.javabasic.disignPattern.flyWeight;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 知识点：
 * 1、FlyWeight 设计模式，俗成享元设计模式。
 * FlyWeight是通过与其他相似对象共享尽可能多的数据来最小化内存使用的一种对象。
 * 享元对象的部分属性和状态是共享的，而且这些对象一般都存储在外部
 * 的数据结构中，在使用的时候临时传递他们给对应的引用。
 *
 * 享元模式尝试重用现有的同类对象，如果未找到匹配的对象则重新创建。
 * 当有大量的对象的时候可能会造成内存溢出，我们可以把其中共用的部分抽象出来。其他变化的部分
 * 可以通过外部参数的方式来设置。
 *
 * 比如本里中不变的部分是CoffeeFlavor的name，
 *
 * 为了能在客户和线程之间安全的共享，FlyWeight对象必须是不变的（immutable)。
 * FlyWeith应该是value object(简单说value obejct的值一旦确定就不会改变了），
 * 任意两个value object只要是值相等那么就认为其相等。
 *
 * 当系统中需要大量对象，或者对象需要非常大的内存的时候，就需要享元模式。
 *
 * 享元对象应该包括不变字段和可变字段。其中不变字段应该包含在对象中，只要创建了对象
 * 那么就是不可改变的（线程安全），这个对象实际上是多个相似对象的高阶抽象。
 * 可变字段主要用于那多个相似对象的实际产生，可以通过外部参数甚至是外部接口的方式
 * 传递进来。比如说本例中CoffeeFlavor是享元对象，其包含name,而且name是private final的
 * 只能通过构造函数来初始化，那么一旦CoffeeFlavor构造出来后他就是不能改变的
 * 但是tableNumber是外部变化的，为了减少对象的创建（如果将tableNumber也纳入CoffeeFlavor
 * 类的字段，那么每个tableNumber都要创建一个新的对象），将tableNumber作为参数传递给
 * 具体的使用类Order，由Order来创建或者获取已经存在的CoffeeFlavor对象并将tableNumber
 * 跟CoffeeFlavor进行关联，来达到特性的CoffeeFlavor属性，从而减少了对象的创建。
 *
 * 要注意，本质上还是使用了一个对象，只是附加了一些不同的外部条件，来达到不同对象的目的。
 * 所以比如游戏中的英雄角色可能就不太适合，比如同一个英雄的两个不同属性版本，同时运行起来，
 * 这样实际还是两个对象，不能通过享元模式来公用一个底层对象。
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
 * FlyWeight对象，存储不变的公用的部分。
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
    //tableNumber代表的外部变化的元素
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
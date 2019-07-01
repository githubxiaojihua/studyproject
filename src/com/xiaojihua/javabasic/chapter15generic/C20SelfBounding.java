package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、自限定类型的使用。自限定类型就是类型参数被作为他自己的绑定参数。
 * T extends SelfBouned<T>这种形式，自限定类型要求使用
 * 的类都满足这种继承关系。在
 *
 * @see C19CRGwithBasicHolder 类中的SubType类继承自BasicHold<SubType>
 * 但是BasicHold的类型参数却不做强制要求，可以是任何类型。但是通过自限定
 * 类型就能强制BasicHold的类型参数只能是BasicHold的导出类。
 *
 * 自限定类型存在的必要条件或者要求是存在继承关系。当一个类使用自定义类型参数的时候，
 * 应该保证这个类首先要继承自一个使用自限定类型格式定义的父类，然后其具体的
 * 类型参数应该满足父类对于类型参数的要求。也就是说此类与其继承的父类的具体
 * 的类型参数应该有相同的父类。比如：SelfB类的定义，首先继承自SelfBouned
 * ，然后SelfBouned实际的类型参数为SelfA。当然严格的形式应该是SelfA的定义。
 *
 */
public class C20SelfBounding {
    public static void main(String[] args){
        SelfA a = new SelfA();
        a.setT(new SelfA());
        //下句使得a的引用指向了，下面的new SelfA()对象
        a = a.setT(new SelfA()).getT();
        System.out.println(a == null);//false
        //由于新的a对象没有setT，因此赋值后a为null
        a = a.getT();
        System.out.println(a == null);//true
        SelfC c = new SelfC();
        c = c.setAndGet(new SelfC());
        System.out.println(c == null);//false

        selfBoundMethod(new SelfA());
        selfBoundMethod(new SelfF());//迁移兼容性
    }

    public static <T extends SelfBouned<T>> T selfBoundMethod(T arg){
        return arg.setT(arg).getT();
    }
}

/**
 * 定义自限定类型
 * @param <T>
 */
class SelfBouned<T extends SelfBouned<T>>{
    private T item;
    public SelfBouned<T> setT(T item){
        this.item = item;
        return this;
    }
    public T getT(){
        return item;
    }
}

/**
 * SelfA属于自限定类型。这是自限定类型的主要用法。
 */
class SelfA extends SelfBouned<SelfA>{}

/**
 * SelfBouned的类型参数只要符合：是SelfBouned的导出类即可。
 * 这是自限定类型的次要用法
 */
class SelfB extends SelfBouned<SelfA>{}
class SelfC extends SelfBouned<SelfC>{
    public SelfC setAndGet(SelfC c){
        setT(c);
        return getT();
    }
}
class SelfD{}

/**
 * SelfD不满足要求因此不能作为类型参数
 */
//class SelfE extends SelfBouned<SelfD>{}

/**
 * 保证了迁移兼容行
  */
class SelfF extends SelfBouned{}
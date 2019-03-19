package com.xiaojihua.javabasic.chapter15.erasure;

/**
 * 知识点：
 * 1、java泛型代码内部将类型参数擦除到Object来对待.
 * 2、通过extends关键字来使用规定类型参数的范围。有了范围
 * 泛型代码内部就不会将类型参数擦除Object来对待，而是擦除
 * 到第一个范围。比如Manipulator类如果没有extends那么在
 * 代码内部T就被擦出到Object，有了extends那么就只擦出到
 * HasF.
 *
 * 观点：
 * 当希望代码跨多个类工作时才使用泛型
 *
 * 泛型的具体类型只在静态类型检查的时候才会真正出现，其他任何
 * 地方均被替换成其非泛型上界，比如List<T>被擦除成List，其他
 * 普通的类型比如T被擦除到Object，除非有extends指定范围。
 * （所谓静态类型检查指的是基于程序代码来验证类型安全的过程）。
 *
 * 擦除的主要原因是因为java要提供泛型代码的迁移兼容性，也就
 * 是说兼容那些不使用泛型的类库。擦出后的泛型代码实际上跟非
 * 泛型代码是一致的。
 *
 */
public class C02Manipulation {
    public static void main(String[] args){
        HasF sf = new HasF();
        Manipulator<HasF> mp = new Manipulator<>(sf);
        mp.manipulate();
    }
}

class HasF{
    public void f(){
        System.out.println("HasF.f()");
    }
}

/**
 * 类中的T被擦除到HasF，也就是说T被当作HasF来对待。
 * 如果没有使用边界（extends)那么T就被擦出到Object
 * @param <T>
 */
class Manipulator<T extends HasF>{
    private T obj;
    public Manipulator(T obj){
        this.obj = obj;
    }
    public void manipulate(){
        //如果类型参数为<T>以下这句没有办法编译，因为无法确定obj存在f().
        obj.f();
    }
}
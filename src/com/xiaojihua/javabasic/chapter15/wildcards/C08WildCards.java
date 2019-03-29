package com.xiaojihua.javabasic.chapter15.wildcards;


public class C08WildCards {
    /**
     * C04Holder是泛型类型的，在这里使用了其raw type。
     * 在set的时候会有warning，返回的时候返回的是Object。
     * 由于使用了raw type因此holder里面的泛型实际上
     * 相当于Object.
     * 为了保证迁移兼容性，holder可以接收任何相关变种，比如
     * 带T 的，带?的，以及extend和super的
     * @param holder
     * @param object
     */
    public static void rowArgs(C04Holder holder, Object object){
        holder.setT(object);
        holder.setT(new C08WildCards());
        Object obj = holder.getT();
    }

    /**
     * 虽然在某些方面<?>与raw type表现相同，比如像
     * @see C07UnboundedWildCard1 中表示的，但是
     * 在本方法中，holder是不允许调用setT方法，因为
     * 不确定存储的到底是什么类型的，此处的？代表了holder存储的是一个确定的类型，
     * 但是这个确定的类型还没有被指定，与row type是有根本性的区别的，rowtype
     * 意思是存储的是任何类型的混合组合。
     * 但是getT可以调用，返回的是Object
     * @param holder
     * @param object
     */
    public static void unboundedArgs(C04Holder<?> holder, Object object){
        //以下两句均报错，说明<?>与row type也是不一样的
        /*holder.setT(object);
        holder.setT(new C08WildCards());*/
        Object obj =  holder.getT();
    }


    /**
     * exact1和exact2表现上是不同的，由于exact2多了一个额外的类型参数，
     * 因此在调用的时候会匹配这两个形参的关系，不匹配的话就不会调用。
     * exact1使用起来基本没有什么限制
     * @param holder
     * @param <T>
     * @return
     */
    public static <T> T exact1(C04Holder<T> holder){
        return holder.getT();
    }

    /**
     * 要求比较严格，需要准确确定C04Holder<T>和T
     * @param holder
     * @param arg
     * @param <T>
     * @return
     */
    public static <T> T exact2(C04Holder<T> holder, T arg){
        holder.setT(arg);
        T result = holder.getT();
        return result;
    }

    /**
     * 协变。表示holder可以持有T以及T的子类。为了防止向C04Holder<？extends Fruit> holder
     * 引用实际指向C04Holder<Apple>，但是却向其
     * 增加Orange的做法因此编译器拒绝调用setT方法。
     * 但是不影响getT的调用，因为编译器能够确定返回的类型至少应该是Fruit,
     * 因此可以调用
     * @param holder
     * @param arg
     * @param <T>
     * @return
     */
    public static <T> T wildSubType(C04Holder<? extends T> holder, T arg){
        //编译错误
        //holder.setT(arg);
        T result = holder.getT();
        return result;
    }

    /**
     * 逆变。正好与协变相反，允许调用setT，因为编译器知道C04Holder的类型
     * 参数至少是T，因此可以确定一个唯一的类型参数值。
     * 但是不能确定返回的类型，因为有可能是T的其他父类，因此只能返回Object
     * ，因此getT方法可以调用，但是只能返回Object
     * @param holder
     * @param arg
     * @param <T>
     * @return
     */
    public static <T> T wildSuperType(C04Holder<? super T> holder, T arg){
        holder.setT(arg);
        Object result = holder.getT();
        T result2 = (T)holder.getT();
        return result2;
    }

    public static void main(String[] args){
        C04Holder row = new C04Holder();
        C04Holder<Long> qulifier = new C04Holder<Long>();
        C04Holder<?> unbounded = new C04Holder<Long>();
        C04Holder<? extends Long> bounded = new C04Holder<Long>();

        Long lng = 1L;

        rowArgs(row, lng);
        rowArgs(qulifier, lng);
        rowArgs(unbounded,lng);
        rowArgs(bounded,lng);

        unboundedArgs(row, lng);
        unboundedArgs(qulifier, lng);
        unboundedArgs(unbounded, lng);
        unboundedArgs(bounded, lng);

        /**
         * 当向T传递一个rowType的时候，类型是不确定的，因此返回的是Object.
         * 并且会有一个警告，因为T在rowtype中找不到他所需要的特定类型信息，
         * 因为T最终还是要对应到某一个特定的类型信息的。
         */
        Object obj1 = exact1(row);
        Long objL2 = exact1(qulifier);
        /**
         * 当向T传递一个？的时候，类型是不确定的，因此返回的是Object
         */
        Object obj3 = exact1(unbounded);
        Long obj4 = exact1(bounded);

        Long obj5 = exact2(row, lng);
        Long obj6 = exact2(qulifier, lng);
        //以下两个方法调用出错，形参类型不匹配
        /*Long obj7 = exact2(unbounded, lng);
        Long obj8 = exact2(bounded, lng);*/

        Long obj9 = wildSubType(row, lng);
        Long obj10 = wildSubType(qulifier, lng);
        Object obj11 = wildSubType(unbounded, lng);
        Long obj12 = wildSubType(bounded, lng);

        Long obj13 = wildSuperType(row, lng);
        Long obj14 = wildSuperType(qulifier, lng);
        //以下两个方法调用出错
        /*wildSuperType(unbounded, lng);
        wildSuperType(bounded, lng);*/

    }
}

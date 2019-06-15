package com.xiaojihua.javabasic.chapter15generic.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、通过使用通配符可以使泛型容器进行协变，但是这样做了以后不能往
 * flist中add任何其他的对象，比如Apple,Fruit,甚至是Object.
 * 2、get返回的类型是Fruit
 * 3、add方法无法调用但是仍然可以调用其他相关方法比如get\contains\indexof。
 * 主要原因是在List的源码中add的参数是泛型类型参数，而其他几个方法
 * 的参数却是Object，因此当我们指定List< ? extends Fruit>的时候add
 * 的参数就变成“? extends Fruit”这种描述（存在extends通配符），因此编译器不知道应该接收
 * 那个类型，所以也就拒绝所有类型了。而contains\indexof等方法的参数
 * 是Object因此不具有通配符，编译器很明白要做的工作，因此允许调用。
 * 更近一步的解释：flist现在是List<? extends Fruit>的，可以理解为一个可以存储
 * 所有从Fruit集成类型的list，但这并不表示List会持有Fruit的任何类型，？通配符
 * 引用的是一个具体类型（因为使用了泛型最终List还是只能存储某一确定类型，可以是Apple也可以是Orange），
 * 现在就变成了？应该是一个具体类型，但是flist确没有指定
 * 任何具体类型（而是所有继承于Fruit的多个类型），这就造成了flist不知道他会存储
 * 什么具体类型（如果选择Apple，由于? extends Fruit那么理论上也可以存储Orange，这就跟数组的协变行一样了
 * 比如List<? extends Fruit> flist = new ArrayList<Apple>();但实际往里放一个orange），
 * 因此编译器就不会让add或者set甚至是get来读取和设置里面的元素，选择在编译器就给出提示，因为是不安全的和
 * 不确定的。
 *
 * 也就是说如果方法的参数是配合extends通配符使用的泛型类型参数，那么编译器就会拒绝。
 *
 *
 */
public class C03GenericsAndConveriant {
    public static void main(String[] args){
        /**
         * ? extends Fruit，并不代表说flist可以存储所有Fruit的类型
         * 因为最终？还是需要对应到某一具体类型，比如Fruit。那么就可以
         * 存储所有Fruit的类型。但是下面的写法会导致编译器无法确定？所
         * 代表的具体类型，这也是为什么不能add的原因，因为编译器不确定
         * flist所存储的具体类型是什么。而List<Fruit>跟List<Apple>
         * 被认为是不一样的类型。flist可能被指向Fruit一下的任何一个类型
         * 比如一个List<Orange>或者
         * 其他Fruit类型，这就会造成跟数组一样的协变问题，而泛型却能够
         * 在编译器进行提示。
         */
        List<? extends Fruit> flist = new ArrayList<Apple>();
        //以上写法不能进行add任何类型甚至是Object，原因如上
        /*flist.add(new Apple());
        flist.add(new Fruit());
        flist.add(new Object());*/
        flist.add(null);
        Fruit fruit = flist.get(0);//返回的时候返回Fruit，因为不管最终是什么类型，可定不会超过Fruit

        flist.contains(new Apple());
        flist.indexOf(new Apple());
    }
}

package com.xiaojihua.javabasic.chapter19enum.multipleDispatching;

import java.util.Random;

/**
 * 知识点：
 * 以下关于分派的知识点可以参考《深入理解java虚拟机》8.3.2的分派章节
 * 0、静态类型和动态类型：假设存在Human\Man\Woman三个类，Human为另外两个类的父类，现有语句：Human man = new Man()。
 * 其中Human就是静态类型，Man为动态类型，静态类型和动态类型都可以在程序的执行过程中发生改变，比如：
 * (Man)man、(Woman)man就属于静态类型的变化；而man = new Woman()则属于动态类型变化。
 * 1、分派或者叫分发。分派是指根据方法的接受者（或者叫实际执行者）以及参数确定目标方法。因为面向对象的编程语言中的
 * 重载、重写、多态等特性再调用某个方法的时候需要 使用分派机制来确定真正的目标方法的执行版本。
 * 2、分派分为静态分派和动态分派。
 * 3、静态分派：依赖静态类型来定位方法的执行版本的分派动作。静态分派发生在编译阶段，最典型的应用是方法的重载。
 * 4、动态分派：依赖动态类型来定位方法的执行版本的分派动作。动态分派发生在运行期间，最典型的应用是多态和方法的重写。
 * 也就是说在程序运行期间会根据方法所属的实际类型来选择方法的执行版本。
 * 5、单分派和多分派：方法的接收者（实际的执行者）和方法的参数统称为 方法的宗量（方法的接受者是一个宗量，方法的一个参数
 * 是一个宗量），分派的时候就是基于方法的宗量来决定方法的执行版本，而根据分派时候基于多少宗量可以将分派分为单分派和
 * 多分派。单分派是根据一个方法宗量来选择方法，多分派是根据多个方法宗量来选择方法。
 * 6、java是静态多分派，动态单分派的。静态多分派指的是，在编译期间可以根据方法的接受者（执行者）以及方法的参数进行
 * 方法版本的定位（比如方法的重载）。动态单分派是指，只根据方法的接受者（实际执行者）的类型进行方法的定位，比如：
 * Human类有个abc(Human cba)方法，并且Man和Woman都对此方法进行了重写（也就是存在多态），如果有语句
 * Human man = new Man();
 * Human otherMan = new Man();
 * man.abc(otherMan);
 * 那么经过编译期间的静态分配后，man.abc(otherMan）方法实际是定位到了Human.abc(Human cba)方法上,因为man和otherMan的
 * 静态类型都是Human,，然后在进行动态分派的时候
 * 会根据man的实际类型这一个宗量来判断方法实际执行者，经过判断将方法定位到了Man.abc(Human cba)这个已经重写的方法上，
 * 之所以只适用一个宗量来定位方法，是因为方法的参数已经定了，不会对方法的选择造成影响，方法的签名已经固定，能影响方法
 * 定位的只有方法的实际执行者。关于方法的签名这里要补充说明一点：abc(Human),abc(Man),abc(Woman)是三个不同的前面，java
 * 允许这样的签名存在，也就是说，及时Man类中存在 Man.abc(Human）,Man.abc(Man),Man.abc(Woman)三个方法，也不影响先前的定位
 * 因为方法签名已经确定了。如果是动态多分派那么应该在运行期间同时根据方法的接受者方法的参数两方面的宗量来定位方法的
 * 执行版本，其结果是，在运行期间除了确定好实际执行者之外还需要根据参数的实际类型来定位方法，那么可能回定位到
 * Man.abc(Man)或者Man.abc(Woman)上。
 *
 * 具体到本例如果是动态多分配那么在Paper\Scissors\Rock三个类中就不需要增加compete方法了，在C02RoShamBo类的
 * compet方法中直接调用item1.eval(item2)就行了，因为动态分派的时候可以根据参数的实际类型选择目标方法，但是java
 * 不支持动态多分派只能使用两次方法调用来进行两次动态 单分派。item1.compete(iteme2)一次，item2.eval(this)一次，this是为了
 * 在进行第二次动态单分派时保留第一次分派所确定的类型。
 *
 * 本类演示了java动态两次分派，通过两次方法调用。模拟石头剪刀布的游戏
 *
 */
public class C02RoShamBo {
    private static Random random = new Random(47);
    private static final int SIZE = 20;

    public static Item randomGen(){
        switch(random.nextInt(3)){
            default:
            case 0: return new Paper();
            case 1: return new Scissors();
            case 2: return new Rock();
        }
    }

    public static void compete(Item item1, Item item2){
        System.out.println(item1 + " vs " + item2 + ":" + item1.compete(item2));
    }

    public static void main(String[] args){
        for(int i=0; i<SIZE; i++){
            compete(randomGen(),randomGen());
        }
    }


}

interface Item{
    C01OutCome compete(Item item);
    C01OutCome eval(Paper paper);
    C01OutCome eval(Scissors scissors);
    C01OutCome eval(Rock rock);
}

class Paper implements Item{
    @Override
    public C01OutCome compete(Item item){
        return item.eval(this);
    }

    @Override
    public C01OutCome eval(Paper paper){
        return C01OutCome.DRAW;
    }

    @Override
    public C01OutCome eval(Scissors scissors){
        return C01OutCome.WIN;
    }

    @Override
    public C01OutCome eval(Rock rock){
        return C01OutCome.LOSE;
    }

    @Override
    public String toString(){
        return "Paper";
    }
}

class Scissors implements Item{
    @Override
    public C01OutCome compete(Item item){
        return item.eval(this);
    }

    @Override
    public C01OutCome eval(Paper paper){
        return C01OutCome.LOSE;
    }

    @Override
    public C01OutCome eval(Scissors scissors){
        return C01OutCome.DRAW;
    }

    @Override
    public C01OutCome eval(Rock rock){
        return C01OutCome.WIN;
    }

    @Override
    public String toString(){
        return "Scissors";
    }
}

class Rock implements Item{
    @Override
    public C01OutCome compete(Item item){
        return item.eval(this);
    }

    @Override
    public C01OutCome eval(Paper paper){
        return C01OutCome.WIN;
    }

    @Override
    public C01OutCome eval(Scissors scissors){
        return C01OutCome.LOSE;
    }

    @Override
    public C01OutCome eval(Rock rock){
        return C01OutCome.DRAW;
    }

    @Override
    public String toString(){
        return "Rock";
    }
}

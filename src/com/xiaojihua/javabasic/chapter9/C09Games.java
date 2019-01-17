package com.xiaojihua.javabasic.chapter9;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：接口的应用及工厂设计模式、开闭原则。
 * 所谓的工厂设计模式就是生成遵循某个接口对象的模式，这个跟直接调用某个具体的构造函数不同，在工厂对象上
 * 调用的是创建方法，将生成接口的某个实现的对象。使得代码和接口的实现完全分离，我们可以透明的将某个实现替换为另一个实现。
 *
 * 本例假设正在创建一个对弈游戏框架，比如在同一个棋盘上下国际象棋和西洋跳棋。
 * 更具体来说这个是工厂方法模式，也是比较常用的一种。
 *
 * 好处是，如果将来还要增加围棋或者中国象棋的话，只需要新增一个围棋或中国象棋的Game实现类，然后增加一个对应的工厂实现类，
 * 其他的代码均不需要更改。这满足了设计模式的原则：开闭原则。
 *
 * 开闭原则：对扩展开放，对修改关闭。在程序需要拓展的时候，不能去修改原有的代码，而是要扩展原有代码，实现一个热插拔的效果。
 * 是为了是程序的扩展性好易于维护和升级。
 *
 * 补充：
 * 工厂模式的主要作用是解耦，将使用具体产品实现类的代码与实现类进行解耦。
 * 对应到本例就是：将Games类（使用具体产品：Checkers或者Chess）的代码与Checkers和Chess类的实例化进行分离，
 * 在Games不会有new Checkers或者new Chess这样的代码。一般而言不应该在构造方法中写过多的代码，而将具体初始化的逻辑
 * 在new实现类的时候在使用类中如Game中设置，如果初始化过程比较复杂以及Game类也比较复杂，那么就导致Game类和具体的实现类
 * 耦合度很高，这样在更改Checkers或者Chess初始化过程或者增加另一个Game类的实现类会导致new处或者Games处的代码要修改。
 * 但是通过使用工厂模式的话，就将Games类与Game的具体实现类的初始化过程进行了分离，Games使用的具体实现类是通过工厂的创造方法比如
 * getGame方法得到的，那么如果修改的话在这里修改就可以其他地方不需要动。如果要增加新的Game那么增加新的具体实现类以及相应的工厂即可
 * 其他的地方也不用动。因此实现了完全解耦。
 * 工厂方法都需要搭配这接口来使用，在工厂类内部以及使用具体产品实现类的代码中，使用的都是具体类的向上转型的接口引用。
 *
 *
 */
interface Game{ boolean move(); }
interface GameFactory{ Game getGame(); }

/**
 * 西洋跳棋，实现Game接口
 */
class Checkers implements Game{
    private int move;
    private static final int MOVES = 3;
    @Override
    public boolean move() {
        print("Checkers move" + move);
        return ++move != MOVES;
    }
}

/**
 * 国际象棋，实现Game接口
 */
class Chess implements Game{
    private int move;
    private static final int MOVES = 4;
    @Override
    public boolean move() {
        print("Chess move" + move);
        return ++move != MOVES;
    }
}

/**
 * 西洋跳棋游戏工厂，实现GameFacotory类，返回Game接口引用
 */
class CheckersFactory implements GameFactory{

    @Override
    public Game getGame() {
        return new Checkers();
    }
}

/**
 * 国际象棋工厂，实现GameFactory类，返回Game接口引用
 */
class ChessFactory implements GameFactory{

    @Override
    public Game getGame() {
        return new Chess();
    }
}
public class C09Games {
    /**
     * 使用GameFactory接口引用
     * @param gameFactory
     */
    public static void playGame(GameFactory gameFactory){
        Game g = gameFactory.getGame();
        while(g.move());
    }

    public static void main(String[] args) {
        playGame(new CheckersFactory());
        playGame(new ChessFactory());
    }
}

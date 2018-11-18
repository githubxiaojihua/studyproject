package com.xiaojihua.javabasic.chapter9;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：接口的应用及工厂设计模式、开闭原则。
 * 所谓的工厂设计模式就是生成遵循某个接口对象的模式，这个跟直接调用某个具体的构造函数不同，在工厂对象上
 * 调用的是创建方法，将生成接口的某个实现的对象。使得代码和接口完全分离，我们可以透明的将某个实现替换为另一个实现。
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
 * 西洋跳棋游戏工厂，实现GameFacotory类
 */
class CheckersFactory implements GameFactory{

    @Override
    public Game getGame() {
        return new Checkers();
    }
}

/**
 * 国际象棋工厂，实现GameFactory类
 */
class ChessFactory implements GameFactory{

    @Override
    public Game getGame() {
        return new Chess();
    }
}
public class Games {
    public static void playGame(GameFactory gameFactory){
        Game g = gameFactory.getGame();
        while(g.move());
    }

    public static void main(String[] args) {
        playGame(new CheckersFactory());
        playGame(new ChessFactory());
    }
}

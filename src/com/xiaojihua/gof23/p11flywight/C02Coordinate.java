package com.xiaojihua.gof23.p11flywight;

/**
 * 外部状态，非共享享元类，存储不能被共享的
 * UnsharedConcreteFlyWight
 */
public class C02Coordinate {
    private int x,y;

    public C02Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

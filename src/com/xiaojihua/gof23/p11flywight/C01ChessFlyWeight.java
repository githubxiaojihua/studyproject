package com.xiaojihua.gof23.p11flywight;

/**
 * 抽象享元类，用于对外提供公用方法，访问和设置内部状态
 */
public interface C01ChessFlyWeight {
    void setColor(String c);
    String getColor();
    void display(C02Coordinate c);
}

class ConcretChess implements  C01ChessFlyWeight{
    private String color;

    @Override
    public void setColor(String c) {
        this.color = c;
    }

    @Override
    public String getColor() {
        return color;
    }

    public ConcretChess(String color) {
        this.color = color;
    }

    @Override
    public void display(C02Coordinate c) {
        System.out.println("棋子颜色："+color);
        System.out.println("棋子位置："+c.getX()+"----"+c.getY());
    }
}

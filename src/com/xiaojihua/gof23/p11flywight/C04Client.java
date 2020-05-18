package com.xiaojihua.gof23.p11flywight;

public class C04Client {
    public static void main(String[] args) {
        C01ChessFlyWeight chess1 = C03ChessFlyWeightFactory.getChess("黑色");
        C01ChessFlyWeight chess2 = C03ChessFlyWeightFactory.getChess("黑色");
        System.out.println(chess1==chess2);
        System.out.println("增加外部状态的处理===========");
        chess1.display(new C02Coordinate(100,100));
        chess2.display(new C02Coordinate(200,200));

    }
}

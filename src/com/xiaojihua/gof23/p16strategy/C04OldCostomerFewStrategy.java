package com.xiaojihua.gof23.p16strategy;

/**
 * 报价策略三：
 * 老客户的小批量订单打9折
 */
public class C04OldCostomerFewStrategy implements C01Strategy{
    @Override
    public double getPricy(double standardPrice) {
        System.out.println("打9折");
        return standardPrice * 0.9;
    }
}

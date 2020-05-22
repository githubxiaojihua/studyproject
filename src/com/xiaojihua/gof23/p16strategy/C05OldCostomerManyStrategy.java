package com.xiaojihua.gof23.p16strategy;

/**
 * 报价策略四：
 * 老客户的小批量订单打8折
 */
public class C05OldCostomerManyStrategy implements C01Strategy{
    @Override
    public double getPricy(double standardPrice) {
        System.out.println("打8折");
        return standardPrice * 0.8;
    }
}

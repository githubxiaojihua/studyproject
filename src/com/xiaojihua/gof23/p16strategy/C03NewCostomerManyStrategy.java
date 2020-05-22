package com.xiaojihua.gof23.p16strategy;

/**
 * 报价策略二：
 * 新客户的大批量订单打85折
 */
public class C03NewCostomerManyStrategy implements C01Strategy{
    @Override
    public double getPricy(double standardPrice) {
        System.out.println("打85折");
        return standardPrice * 0.85;
    }
}

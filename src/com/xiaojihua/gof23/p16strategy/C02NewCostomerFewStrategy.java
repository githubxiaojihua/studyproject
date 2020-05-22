package com.xiaojihua.gof23.p16strategy;

/**
 * 报价策略一：
 * 新客户的小批量订单不打折
 */
public class C02NewCostomerFewStrategy implements C01Strategy{
    @Override
    public double getPricy(double standardPrice) {
        System.out.println("不打折");
        return standardPrice;
    }
}

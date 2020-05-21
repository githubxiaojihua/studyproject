package com.xiaojihua.gof23.p14mediator;

public class C04Finacial implements C02Deptment {
    private C01Mediator mediator;

    public C04Finacial(C01Mediator mediator) {
        this.mediator = mediator;
        mediator.register("finacial",this);
    }

    @Override
    public void selfAction() {
        System.out.println("数钱1");
    }

    @Override
    public void outAction() {
        System.out.println("汇报工作！没钱了，钱太多了！怎么花?");
    }
}

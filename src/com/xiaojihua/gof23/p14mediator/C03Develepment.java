package com.xiaojihua.gof23.p14mediator;

/**
 * 开发部
 */
public class C03Develepment implements C02Deptment {
    private C01Mediator mediator;

    public C03Develepment(C01Mediator mediator) {
        this.mediator = mediator;
        mediator.register("develepment",this);
    }

    @Override
    public void selfAction() {
        System.out.println("专心搞科研！");
    }

    @Override
    public void outAction() {
        System.out.println("没钱了，请领导拨款！");
    }
}

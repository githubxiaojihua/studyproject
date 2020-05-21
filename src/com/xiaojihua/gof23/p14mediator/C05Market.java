package com.xiaojihua.gof23.p14mediator;

public class C05Market implements C02Deptment {
    private C01Mediator mediator;

    public C05Market(C01Mediator mediator) {
        this.mediator = mediator;
        mediator.register("market",this);
    }

    @Override
    public void selfAction() {
        System.out.println("跑市场！接项目");
    }

    @Override
    public void outAction() {
        System.out.println("汇报工作！项目承接的进度，需要资金支持！");
        // 通过中介者协调同事部门的工作
        mediator.command("finacial");
    }
}

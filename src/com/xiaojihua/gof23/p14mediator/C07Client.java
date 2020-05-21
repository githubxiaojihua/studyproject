package com.xiaojihua.gof23.p14mediator;

public class C07Client {
    public static void main(String[] args) {
        C01Mediator mediator = new C06President();
        C02Deptment develepment = new C03Develepment(mediator);
        C02Deptment finacial = new C04Finacial(mediator);
        C02Deptment market = new C05Market(mediator);

        market.selfAction();//自己本部门的工作
        market.outAction();// 内部通过中介者调用了finacial部门的内部工作
    }
}

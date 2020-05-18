package com.xiaojihua.gof23.p12chainofresponsbility;

public class C07Client {
    public static void main(String[] args) {
        C02Leader a = new C03Director("张三");
        C02Leader b = new C04Manager("李四");
        C02Leader b2 = new C05ViceGeneralManager("李小四");
        C02Leader c = new C06GeneralManager("王五");
        //组织责任链对象的关系
        a.setNextLeader(b);
        b.setNextLeader(b2);
        b2.setNextLeader(c);

        //开始请假操作
        C01LeaveRequest req1 = new C01LeaveRequest("TOM", 15, "回英国老家探亲！");
        a.handleRequest(req1);
    }
}

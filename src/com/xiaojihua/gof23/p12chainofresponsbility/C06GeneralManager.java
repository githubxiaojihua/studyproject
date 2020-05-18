package com.xiaojihua.gof23.p12chainofresponsbility;

public class C06GeneralManager extends C02Leader {
    public C06GeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(C01LeaveRequest request) {
        if(request.getLeaveDays()<30){
            System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReson());
            System.out.println("总经理："+this.name+",审批通过！");
        }else{
            System.out.println("莫非"+request.getEmpName()+"想辞职，居然请假"+request.getLeaveDays()+"天！");
        }
    }
}

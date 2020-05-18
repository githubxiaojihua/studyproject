package com.xiaojihua.gof23.p12chainofresponsbility;

public class C05ViceGeneralManager extends C02Leader {
    public C05ViceGeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(C01LeaveRequest request) {
        if(request.getLeaveDays()<20){
            System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReson());
            System.out.println("副总经理："+this.name+",审批通过！");
        }else{
            if(this.nextLeader!=null){
                this.nextLeader.handleRequest(request);
            }
        }
    }
}

package com.xiaojihua.gof23.p12chainofresponsbility;

public class C04Manager extends C02Leader {
    public C04Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(C01LeaveRequest request) {
        if(request.getLeaveDays()<10){
            System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReson());
            System.out.println("经理："+this.name+",审批通过！");
        }else{
            if(this.nextLeader!=null){
                this.nextLeader.handleRequest(request);
            }
        }
    }
}

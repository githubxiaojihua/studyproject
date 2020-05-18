package com.xiaojihua.gof23.p12chainofresponsbility;

/**
 * 主任
 * @author Administrator
 *
 */
public class C03Director extends C02Leader {

    public C03Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(C01LeaveRequest request) {
        if(request.getLeaveDays()<3){
            System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReson());
            System.out.println("主任："+this.name+",审批通过！");
        }else{
            if(this.nextLeader!=null){
                this.nextLeader.handleRequest(request);
            }
        }
    }
}

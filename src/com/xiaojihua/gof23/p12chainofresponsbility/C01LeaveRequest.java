package com.xiaojihua.gof23.p12chainofresponsbility;

/**
 * 封装请假的基本信息
 */
public class C01LeaveRequest {
    private String empName;
    private int leaveDays;
    private String reson;

    public C01LeaveRequest(String empName, int leaveDays, String reson) {
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reson = reson;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }
}

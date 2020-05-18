package com.xiaojihua.gof23.p12chainofresponsbility;

/**
 * 领导抽象类
 */
public abstract class C02Leader {
    protected String name;
    protected C02Leader nextLeader;//责任链上的后继对象

    public C02Leader(String name) {
        this.name = name;
    }

    public void setNextLeader(C02Leader nextLeader) {
        this.nextLeader = nextLeader;
    }

    /**
     * 处理请求的核心的业务方法
     * @param request
     */
    public abstract void handleRequest(C01LeaveRequest request);
}

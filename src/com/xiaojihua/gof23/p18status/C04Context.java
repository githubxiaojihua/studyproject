package com.xiaojihua.gof23.p18status;

/**
 * 维护当前状态，定义当前状态
 */
public class C04Context {
    private C01Status status;

    /**
     * 修改状态，调用状态行为
     * @param status
     */
    public void setStatus(C01Status status) {
        System.out.println("修改状态");
        this.status = status;
        // 调用状态行为
        this.status.handle();
    }
}

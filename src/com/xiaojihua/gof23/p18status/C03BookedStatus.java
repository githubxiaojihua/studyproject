package com.xiaojihua.gof23.p18status;

/**
 * 具体状态，封装当前状态的行为
 */
public class C03BookedStatus implements C01Status {
    @Override
    public void handle() {
        System.out.println("房间已经预定！别人不可再预定！");
    }
}

package com.xiaojihua.gof23.p18status;

/**
 * 测试状态模式
 */
public class C05Client {
    public static void main(String[] args) {
        C04Context ctx = new C04Context();
        ctx.setStatus(new C02FreeStatus());
        ctx.setStatus(new C03BookedStatus());
        ctx.setStatus(new C03CheckedInStatus());
    }
}

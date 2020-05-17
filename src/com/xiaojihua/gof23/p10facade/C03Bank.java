package com.xiaojihua.gof23.p10facade;

// 银行
public interface C03Bank {
    void openAccount();//开户
}

class GongshangBank implements C03Bank{

    @Override
    public void openAccount() {
        System.out.println("在工商银行办理开户");
    }
}

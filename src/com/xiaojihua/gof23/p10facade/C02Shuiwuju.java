package com.xiaojihua.gof23.p10facade;

// 税务局
public interface C02Shuiwuju {
    // 税务登记
    void taxCertificate();
}

class HaidianShuiwu implements C02Shuiwuju{

    @Override
    public void taxCertificate() {
        System.out.println("海淀区税务局办理税务登记!");
    }
}

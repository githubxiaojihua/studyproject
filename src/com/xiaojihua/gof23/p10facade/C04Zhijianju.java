package com.xiaojihua.gof23.p10facade;

// 质监局
public interface C04Zhijianju {
    void orgCodeCertificate();// 办理组织机构代码证
}

class HaidianZhiJian implements C04Zhijianju{

    @Override
    public void orgCodeCertificate() {
        System.out.println("在海淀区质监局办理组织机构代码证！");
    }
}

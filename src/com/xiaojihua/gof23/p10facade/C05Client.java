package com.xiaojihua.gof23.p10facade;

// 具体的注册公司的人员
public class C05Client {
    public static void main(String[] args) {
        // 未使用门面模式之前，需要没一个部门都需要自己去跑
        C01Gongshangju gs = new HaidianGongshagnju();
        gs.checkName();
        C02Shuiwuju sw = new HaidianShuiwu();
        sw.taxCertificate();
        C03Bank bk = new GongshangBank();
        bk.openAccount();
        C04Zhijianju zj = new HaidianZhiJian();
        zj.orgCodeCertificate();

        //使用门面模式
        C06Facade facade = new C06Facade();
        facade.register();

    }
}

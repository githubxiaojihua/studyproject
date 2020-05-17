package com.xiaojihua.gof23.p10facade;

/**
 * 门面模式
 */
public class C06Facade {
    public void register(){
        System.out.println("门面模式");
        C01Gongshangju gs = new HaidianGongshagnju();
        gs.checkName();
        C02Shuiwuju sw = new HaidianShuiwu();
        sw.taxCertificate();
        C03Bank bk = new GongshangBank();
        bk.openAccount();
        C04Zhijianju zj = new HaidianZhiJian();
        zj.orgCodeCertificate();
    }
}

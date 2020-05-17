package com.xiaojihua.gof23.p10facade;

import com.xiaojihua.gof23.p08composite.C01Component;

/**
 * 工商局
 */
public interface C01Gongshangju {
    // 检查是否重名
    void checkName();
}

class HaidianGongshagnju implements C01Gongshangju{


    @Override
    public void checkName() {
        System.out.println("海淀区工商局检查是否重名！");
    }
}

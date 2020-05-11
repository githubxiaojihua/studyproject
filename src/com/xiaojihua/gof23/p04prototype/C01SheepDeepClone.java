package com.xiaojihua.gof23.p04prototype;

import java.util.Date;

/**
 * 被克隆的对象.
 * 需要实现Cloneable接口
 */
public class C01SheepDeepClone implements Cloneable{
    private String name;
    private Date birtyday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirtyday() {
        return birtyday;
    }

    public void setBirtyday(Date birtyday) {
        this.birtyday = birtyday;
    }

    public C01SheepDeepClone(String name, Date birtyday) {
        this.name = name;
        this.birtyday = birtyday;
    }

    public C01SheepDeepClone() {
    }

    @Override
    public String toString() {
        return "C01Sheep{" +
                "name='" + name + '\'' +
                ", birtyday=" + birtyday +
                '}';
    }

    /**
     * 覆盖原来的clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = super.clone();//直接调用object对象的clone()方法！
        //添加如下代码实现深复制(deep Clone)
        C01SheepDeepClone c01SheepDeepClone = (C01SheepDeepClone) object;
        //把属性也进行克隆！
        c01SheepDeepClone.setBirtyday((Date) this.birtyday.clone());
        return object;
    }
}

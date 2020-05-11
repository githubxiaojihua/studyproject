package com.xiaojihua.gof23.p04prototype;

import java.io.Serializable;
import java.util.Date;

/**
 * 被克隆的对象.
 * 需要实现Cloneable接口
 * 单纯浅克隆无需实现Serializable接口，当使用序列化和反序列化进行深克隆的时候才需要
 */
public class C01Sheep implements Cloneable,Serializable {
    private static final long serialVersionUID = 1l;

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

    public C01Sheep(String name, Date birtyday) {
        this.name = name;
        this.birtyday = birtyday;
    }

    public C01Sheep() {
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
        return super.clone();//直接调用object对象的clone()方法！
    }
}

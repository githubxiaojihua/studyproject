package com.xiaojihua.gof23.p20memento;

/**
 * 负责人类
 * 负责管理备忘录对象
 * 当备忘点较多的时候可以使用，list或者stack来存储备忘对象
 */
public class C03CareTaker {
    private C02EmpMemento memento;

    public C02EmpMemento getMemento() {
        return memento;
    }

    public void setMemento(C02EmpMemento memento) {
        this.memento = memento;
    }
}

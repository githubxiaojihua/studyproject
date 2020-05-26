package com.xiaojihua.gof23.p19observer;

/**
 * 具体的观察者
 */
public class C04ObserverA implements C02Observer {
    private int myStatus;//myState需要跟目标对象的state值保持一致！

    public int getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(int myStatus) {
        this.myStatus = myStatus;
    }

    @Override
    public void update(C01Subject subject) {
        // 将传递过来的目标对象转换为具体的目标对象，然后根据目标对象的状态更新本地状态
        myStatus = ((C03ConcreteSubject) subject).getStatus();
    }
}

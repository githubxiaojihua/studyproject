package com.xiaojihua.gof23.p15command;

/**
 * 命令的调用者，发起者
 */
public class C03Invoker {

    // 持有命令 //也可以通过容器List<Command>容纳很多命令对象，进行批处理。数据库底层的事务管理就是类似的结构！
    private C02Command c02Command;

    public C03Invoker(C02Command c02Command) {
        this.c02Command = c02Command;
    }

    //业务方法 ，用于调用命令类的方法
    public void call(){
        c02Command.execute();
    }
}

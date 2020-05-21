package com.xiaojihua.gof23.p15command;

/**
 * 命令的抽象接口
 */
public interface C02Command {
    /**
     * 这个方法是一个返回结果为空的方法。
     * 实际项目中，可以根据需求设计多个不同的方法
     */
    void execute();
}

class ConcreteCommand implements C02Command{

    // 持有命令真正执行者的引用
    private C01Receiver receiver;

    public ConcreteCommand(C01Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //命令真正执行前或后，执行相关的处理！
        receiver.action();
    }
}
package com.xiaojihua.javabasic.disignPattern.command;

/**
 * 知识点：
 * 命令设计模式。由command\receiver\invoker\client组成
 * command对象用来封装具体的执行动作，可分为一个interface(通常只有一个方法)，多个
 * 实现类，每一个实现类都具体实现某些具体动作。
 * receiver对象用来内聚封装相关实际操作的，command的实现类通过关联reciver来执行动作。
 * invoker是位于command对象与具体使用者（消费者）之间的，用于解耦command对象与消费者对象
 * 它面向的是command中的接口进行操作
 * client指的是消费者。
 *
 * 在本例中
 * command: C01（接口）、c03,c04（具体实现类）
 * receiver:C02，c03和c04中将C02做为了成员变量，调用c02中的具体操作
 * invokerC05：面向C01的接口调用。
 * client：C06,给C05传入不同的command实现类，执行不同的操作
 *
 */
public class C06ClientClass {
    public static void main(String[] args){
        C05TextFileOperationExecutor executor = new C05TextFileOperationExecutor();
        String result = executor.executeOperation(new C03OpenFileOperation(new C02TextFiles("abc.doc")));
        System.out.println(result);
        result = executor.executeOperation(new C04SaveFileOperation(new C02TextFiles("ddc.doc")));
        System.out.println(result);

        System.out.print("========使用lamda表达式和函数引用实现上面的功能");
        result = executor.executeOperation(() -> new C02TextFiles("ccd.doc").openFile());
        System.out.println(result);
        C02TextFiles textFiles = new C02TextFiles("AAB.DOC");
        result = executor.executeOperation(textFiles::saveFile);
        System.out.println(result);
    }
}

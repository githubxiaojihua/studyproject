package com.xiaojihua.javabasic.chapter19enum;

import java.util.EnumMap;
import java.util.Map;

/**
 * 知识点：
 * EnumMap的使用。EnumMap的key来源是单一enum实例，并且保持实例所声明的顺序。
 * EnumMap声明以后，是只能接收指定enum的实例做为key。
 *
 * 简单的命令行模式应用，命令行模式有一个命令接口，和多个命令的实现类。
 * 更标准的命令行模式看
 * @see com.xiaojihua.gof23.p15command.command.C01TextFileOperation 相关
 */
public class C14EnumMaps {
    public static void main(String[] args){
        //初始化EnumMap,使用指定的AlarmPoints
        EnumMap<AlarmPoints, Command> maps = new EnumMap<>(AlarmPoints.class);

        //增加map，命令行模式接口实现
        maps.put(AlarmPoints.KETCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("KETCHEN alerm");
            }
        });

        //增加
        maps.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("Bathroom alerm");
            }
        });

        //遍历map
        for(Map.Entry<AlarmPoints,Command> e : maps.entrySet()){
            System.out.print(e.getKey() + ":");
            //调用命令行接口的方法，实则调用具体实现类
            e.getValue().action();
        }

        //如果获取一个没有put的key那么会报错NullPointerException
        try{
            maps.get(AlarmPoints.LOBBY).action();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

/**
 * 命令行模式接口
 */
interface Command{
    void action();
}

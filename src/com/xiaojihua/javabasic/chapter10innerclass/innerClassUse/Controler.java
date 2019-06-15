package com.xiaojihua.javabasic.chapter10innerclass.innerClassUse;

import java.util.ArrayList;
import java.util.List;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：控制框架
 * 将控制的不变逻辑实现，具体的可变逻辑为Event的action方法，
 * action的具体方法由实现Event的类实现。
 * 定义Event列表，根据event的状态调用具体的action
 */
public class Controler {
    private List<Event> eventList = new ArrayList<>();
    public void addEvent(Event e){
        this.eventList.add(e);
    }
    public void run(){
        while(this.eventList.size() > 0){
            for(Event e : new ArrayList<Event>(eventList)){
                if(e.ready()){
                    print(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}

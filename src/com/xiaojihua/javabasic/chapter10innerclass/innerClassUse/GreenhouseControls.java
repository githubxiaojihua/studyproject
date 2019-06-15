package com.xiaojihua.javabasic.chapter10innerclass.innerClassUse;

/**
 * 知识点：控制框架的具体实现
 * 使用内部类，可以在单一的类里面产生对同一个基类Event的多种导出版本，对于温室系统的每一个行为，
 * 都继承一个新的Event内部类，并在要实现的action()中编写控制代码
 */
public class GreenhouseControls extends Controler {
    private boolean light = false;
    public class LightOn extends Event{
        LightOn(long delayTime){
            super(delayTime);
        }
        public void action(){
            light = true;
        }
        public String toString(){
            return "Light on";
        }
    }
    public class LightOff extends Event{
        LightOff(long delayTime){
            super(delayTime);
        }
        public void action(){
            light = false;
        }
        public String toString(){
            return "Light off";
        }
    }

    private boolean water = false;
    public class WaterOn extends Event{
        WaterOn(long delayTime){
            super(delayTime);
        }
        public void action(){
            water = true;
        }
        public String toString(){
            return "Water on";
        }
    }
    public class WaterOff extends Event {
        WaterOff(long delayTime) {
            super(delayTime);
        }

        public void action() {
            water = false;
        }

        public String toString() {
            return "water off";
        }
    }

    public class Bell extends Event{
        Bell(long delayTime){
            super(delayTime);
        }
        public void action(){
            addEvent(new Bell(delayTime));
        }
        public String toString(){
            return "Bell!";
        }
    }

    public class Restart extends Event{
        private Event[] eventList;
        Restart(long delayTime, Event[] eventList){
            super(delayTime);
            this.eventList = eventList;
            for(Event e : eventList){
                addEvent(e);
            }
        }
        public void action(){
            for(Event e : eventList){
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        public String toString(){
            return "Restarting System";
        }
    }

    public static class Terminate extends Event{
        Terminate(long delayTime){
            super(delayTime);
        }
        public void action(){
            System.exit(0);
        }
        public String toString(){
            return "Terminate";
        }
    }
}

package com.xiaojihua.javabasic.chapter19enum;

/**
 * 知识点：
 * enum可以与switch无缝对接，由于switch是接受int类型的case的，但是enum可以通过ordinal()方法
 * 获取实例的声明顺序，因此可以与switch进行有效结合。
 */
public class C06TrafficLight {
    private Singnal color = Singnal.RED;

    public void change(){
        switch(color){
            default:
            //case中不需要Singnal.GREEN，但是语句中需要写。
            case GREEN:     color = Singnal.YELLOW;
                            break;
            case YELLOW:    color = Singnal.RED;
                            break;
            case RED:       color = Singnal.GREEN;
                            break;
        }
    }

    @Override
    public String toString(){
        return "The tracffic light is " + color;
    }

    public static void main(String[] args){
        C06TrafficLight trafficLight = new C06TrafficLight();
        for(int i=0; i<7; i++){
            System.out.println(trafficLight);
            trafficLight.change();
        }
    }
}

enum Singnal{
    GREEN, YELLOW, RED;
}

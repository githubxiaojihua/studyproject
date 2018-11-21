package com.xiaojihua.javabasic.chapter10.innerClassUse;

/**
 * 知识点：
 */
public class GreenHoseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] events = {
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800)
        };
        gc.addEvent(gc.new Restart(2000, events));
        gc.addEvent(new GreenhouseControls.Terminate(200000));
        gc.run();
    }
}

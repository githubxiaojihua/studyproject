package com.xiaojihua.javabasic;

import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test implements Runnable{
    private int countDowen = 10;
    private static int taskCount = 0;
    private final int taskId = taskCount++;

    @Override
    public void run(){
        while (countDowen-- > 0){
            System.out.println(this);
        }
    }

    public String toString(){
        return "#" + taskId + "(" + (countDowen > 0 ? countDowen : "LiftOff") + ")";
    }


    public static void main(String[] args) {
        Thread t = new Thread(new Test());
        t.start();
    }


}
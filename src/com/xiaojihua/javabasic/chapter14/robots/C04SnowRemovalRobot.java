package com.xiaojihua.javabasic.chapter14.robots;

import java.util.Arrays;
import java.util.List;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 一个普通的java类
 */
public class C04SnowRemovalRobot implements C03Robot {
    private String name;
    private String modole;

    C04SnowRemovalRobot(String name){
        this.name = name;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public String modole(){
        return "SnowRomoval servie 11";
    }

    public List<C02Operation> operations(){
        return Arrays.asList(
                new C02Operation() {
                    @Override
                    public String description() {
                        return name + " can remove snow!";
                    }

                    @Override
                    public void command() {
                        print(name + " remove snow!");
                    }
                },
                new C02Operation() {
                    @Override
                    public String description() {
                        return name + " can break ice";
                    }

                    @Override
                    public void command() {
                        print(name + " break ice");
                    }
                },
                new C02Operation() {
                    @Override
                    public String description() {
                        return name + " can say jok";
                    }

                    @Override
                    public void command() {
                        print(name + " saying a jok");
                    }
                }
        );
    }

    public static void main(String[] args){
        C03Robot.Test.test(new C04SnowRemovalRobot("snow wite"));
    }

}

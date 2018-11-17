package com.xiaojihua.javabasic.chapter7;

/**
 * 知识点：复用类的方式之一：代理
 * 复用类有三种方式：组合、继承、以及代理
 * 其中代理是介于组合和继承之间。
 * 既有组合的属性（使用其他类构建新类），也有继承的属性（将父类的接口暴露给导出类，但是这个暴露是代理了组合对象的公共接口）
 * 在此类中：
 * 使用组合SpaceShipControlls类的方法代替了从他继承。同时通过代理方法将SpaceShipControlls类的公共借口暴露。
 * 这种方式避免了继承的层级架构，更加灵活。
 */
public class SpaceShipDelegation {
    private String name;
    private SpaceShipControlls controlls = new SpaceShipControlls();//代理类
    public SpaceShipDelegation(String name){
        this.name = name;
    }
    //代理方法
    public void up(int velocity){
        controlls.up(velocity);
    }
    public void down(int velocity){
        controlls.down(velocity);
    }
    public void left(int velocity){
        controlls.left(velocity);
    }
    public void right(int velocity){
        controlls.right(velocity);
    }
    public void back(int velocity){
        controlls.back(velocity);
    }
    public void foword(int velocity){
        controlls.foword(velocity);
    }

    public static void main(String[] args) {
        SpaceShipDelegation delegation = new SpaceShipDelegation("delegation");
        delegation.foword(100);
    }


}

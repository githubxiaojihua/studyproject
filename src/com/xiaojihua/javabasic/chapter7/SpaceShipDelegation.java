package com.xiaojihua.javabasic.chapter7;

/**
 * 复用类的方式之一：代理
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

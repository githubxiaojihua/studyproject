package com.xiaojihua.javabasic.chapter19enum;

/**
 * 知识点：
 * 重载toString()方法，enum类中也可以对方法进行重载。与普通类是一样的
 */
public enum C05SpaceShip {
    //定义实例
    SCOUT, CARGO, TRANSPROT, CRUISER, BATTLESHIP, MOTHERSHIP;

    /**
     * 重载toString方法。
     * 返回实例名称（首字母大写，其他都是小写）
     * @return
     */
    public String toString(){
        String id = name();//通过调用name方法来获取每一个实例的声明名称
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args){
        //遍历输出各个enum实例，调用重载的toString方法
        for(C05SpaceShip ship : C05SpaceShip.values()){
            System.out.println(ship);
        }
    }
}

package com.xiaojihua.javabasic.chapter19enum;

/**
 * 除了不能继承enum之外，可以基本上将enum看作一个普通的类，可以像enum中添加方法，甚至main方法
 * 如下例子中：OzWitch提供了构造方法，那么在实例中就可以按照构造方法的要求设置实例。
 * OzWith中声明了一个description私有变量，同时声明了一个以此为参数的构造方法。以及一个public的get方法。
 * 在写实例的时候就可以写成WEST(****)，这种形式。
 * 注意：实例序列必须在所有方法之前，此处的实例序列有WEST,NORTH,SOUTH,EAST四个。
 * 如果有自定义的方法那么实例序列的最后要加上分号
 *
 * enum类与普通类基本一致，除了不能继承这个特性以外。
 * 不能从一个enum类进行继承。
 */
public enum C04OzWitch {
    // 下面声明实例序列同时进行初始化。下面每一个都代表OzWitch的一个实例。
    //如果实例的声明格式，与构造方法不一致那么会报错
    WEST("I am WEST"),
    NORTH("I am NORTH"),
    SOUTH("I am SOUTH"),
    EAST("I an EAST");// 注意此处一定要加上分号，因为后面有自定义方法

    // 自定义变量
    private String description;

    // OzWith自定义构造方法
    //构造方法必须是private和默认访问权限的
    private C04OzWitch(String description){
        this.description = description;
    }

    /*
        也可以增加额外的构造函数，那么在声明实例的话，也可以声明满足此构造函数
        的实例如：WEST1("S1","S2")
        这就跟普通的类是一样的。

        构造函数只是用来实例化上面所声明的那些实例。WEST,NORTH,SOUTH,EAST。
        不能于实例化其它的enum对象。
     */
    /*private OzWitch(String s1, String s2){

    }*/

    // 自定义公共方法
    public String getDescription(){
        return this.description;
    }

    /**
     * 覆盖toString方法
     * @return
     */
    public String toString(){
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    /**
     * main函数，调用了每一个实例的公用方法getDescription.以及被覆盖的toString方法。
     * @param args
     */
    public static void main(String[] args) {
        // 遍历实力序列中的每一个实例，然后调用其公用方法
        for(C04OzWitch witch : C04OzWitch.values()){
            System.out.println(witch + " : " + witch.getDescription());
        }
    }
}

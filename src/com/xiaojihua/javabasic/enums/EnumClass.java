package com.xiaojihua.javabasic.enums;

/**
 * 枚举类基本特性
 */
enum Shrubbery{
    // 下面的每一个值都代表Shrubbery的一个实例
    GROUND, CRAWING, HANGING
}
public class EnumClass {

    public static void main(String[] args) {

        for(Shrubbery s : Shrubbery.values()){
            // ordinal返回一个int值，从0开始，代表每个enum实例在声明时候的次序
            System.out.println(s + " ordial:" + s.ordinal());
            // enum实现了compareable接口因此可以使用compareTo接口
            System.out.print(s.compareTo(Shrubbery.CRAWING) + " ");
            // 可以使用==进行比较
            System.out.print((s == Shrubbery.CRAWING) + " ");
            // 可以使用equals进行比较
            System.out.print(s.equals(Shrubbery.CRAWING) + " ");
            // 调用getDeclaringClass可以获得所属的enum类
            System.out.println(s.getDeclaringClass());
            // name返回声明时候实例的名字，比如GROUND
            System.out.println(s.name());
            System.out.println("--------------------------------");
        }

        // valueOf是Enum类声明的静态方法，根据给定的名字返回相应的enum实例
        for (String s : "HANGING CRAWING GROUND".split(" ")){
            Shrubbery shrub = Enum.valueOf(Shrubbery.class,s);
            System.out.println(shrub);
        }
    }
}

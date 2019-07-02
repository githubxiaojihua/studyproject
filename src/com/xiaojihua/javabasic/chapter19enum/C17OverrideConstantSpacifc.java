package com.xiaojihua.javabasic.chapter19enum;

/**
 * 知识点：
 * 不仅可以通过abstract方式来进行实例常量相关方法的指定，
 * 也可以用普通方法。
 * 而且用普通方法并不要求所有实例都需要进行重载f(),
 * 但是如果使用abstract方法，则需要每一个实例都需要重载f()
 */
public enum C17OverrideConstantSpacifc {
    NUT, BOLT,
    WASHER{
        /**
         * 重载方法
         */
        public void f(){
            System.out.println("overriden f()");
        }
    };

    /**
     * 默认方法
     */
    public void f(){
        System.out.println("default f()");
    }

    public static void main(String[] args){
        for(C17OverrideConstantSpacifc c : values()){
            c.f();
        }
    }
}

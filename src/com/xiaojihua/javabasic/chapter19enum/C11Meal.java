package com.xiaojihua.javabasic.chapter19enum;

import com.xiaojihua.javabasic.util.Enums;

/**
 * 知识点：
 * 1、通过interface来能enum进行分组。
 * 2、通过再interface的基础上封装一层enum来对每一类enum进行访问
 *
 * 本例：
 * 先将各类food在C11Food中进行了分类
 * 然后用enum类Course对分类后的enum进行封装
 * 最后生成5组套餐。套餐中的每一类分组都从自己的分组中随机选一个菜。
 */
public class C11Meal {
    public static void main(String[] args){
        for(int i=0; i<5; i++){
            //通过Course可以进行自动遍历
            for(Course coutse : Course.values()){
                System.out.println(coutse.next());
            }
            System.out.println("================================");
        }

    }
}

/**
 * 通过interface来对enum进行分类。现在APPE1是Appetizer，同是也是C11Food
 *
 * Food food = Appetizer.APPE1
 */
interface C11Food {
    enum Appetizer implements C11Food{
        APPE1, APPE2, APPE3, APPE4, APPE5
    }

    enum MainCourse implements C11Food{
        MAIN1, MAIN2, MAIN3, MAIN4, MAIN5
    }

    enum Dessert implements C11Food{
        DESS1, DESS2, DESS3
    }

    enum Coffee implements C11Food{
        COFFEE1, COFFEE2, COFFEE3, COFFEE4, COFFEE5, COFFEE6
    }
}

/**
 * 上面的接口对food进行了分组，但是无法进行组内元素的遍历。
 * 虽然可以手工使用 C11Food.Appetizer.values();
 * 但是不能进行自动的遍历。比如 ：
 * for(C11Food food: C11Food.values()){
 *     food.next()
 * }
 * 这也就是Course的用处
 */
enum Course{
    APPETIZER(C11Food.Appetizer.class),
    MAINCOURSE(C11Food.MainCourse.class),
    DESSERT(C11Food.Dessert.class),
    COFFEE(C11Food.Coffee.class);

    private C11Food[] values;
    private Course(Class<? extends C11Food> clazz){
        values = clazz.getEnumConstants();
    }

    /**
     * 随机生成某个分类下的单一实例
     * @return
     */
    public C11Food next(){
        return Enums.randomNext(values);
    }
}
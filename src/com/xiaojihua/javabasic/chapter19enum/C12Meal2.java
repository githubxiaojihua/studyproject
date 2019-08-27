package com.xiaojihua.javabasic.chapter19enum;

import com.xiaojihua.javabasic.util.Enums;

/**
 * 知识点：
 * 对C11Meal的集成化改造，将interface放到了enum内部，
 * 并将包装用的enum和main调用都放到了C12Meal2中。
 */
public enum C12Meal2 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    //每一个实例都有一个values对应到，实例形参中的所有实例。
    private Food[] values;

    /*
        每一个实例在进行初始化的时候都会执行此构造方法。
        而且是在类运行的时候就会自动创建好每个实例：APPETIZER\MAINCOURSE等。
        即使main函数为空那也会进行初始化，在没有调用每个实例的时候，
        每个实例就已经被初始化了。
     */
    private C12Meal2(Class<? extends Food> clazz){
        //System.out.println("Controctor");
        values = clazz.getEnumConstants();

    }

    /**
     * 公共实例方法，使用了Enums工具类
     * @return
     */
    public Food randomNext(){
        return Enums.randomNext(values);
    }

    /**
     * 还是使用了interface对每个food进行了分类，这样其实APPE1,MAIN2等每个
     * 实例同时也是Food类型的。
     */
    public interface Food{
        enum Appetizer implements Food{
            APPE1, APPE2, APPE3, APPE4, APPE5
        }

        enum MainCourse implements Food{
            MAIN1, MAIN2, MAIN3, MAIN4, MAIN5
        }

        enum Dessert implements Food{
            DESS1, DESS2, DESS3
        }

        enum Coffee implements Food{
            COFF1, COFF2, COFF3, COFF4, COFF5, COFF6
        }
    }

    public static void main(String[] args){
        for(int i=0; i<5; i++){
            for(C12Meal2 meal2 : C12Meal2.values()){
                System.out.println(meal2.randomNext());
            }
            System.out.println("===============================");
        }
    }
}

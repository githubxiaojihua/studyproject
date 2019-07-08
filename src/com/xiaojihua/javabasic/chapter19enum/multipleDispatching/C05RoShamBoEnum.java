package com.xiaojihua.javabasic.chapter19enum.multipleDispatching;

import static com.xiaojihua.javabasic.chapter19enum.multipleDispatching.C01OutCome.*;

/**
 * 知识点：
 * 通过enum来进行动态分派
 * C03Competitor定义了单独的比较接口
 * C04定义了两个static方法compete和play分别用于一次比较两个enum和分多次比较
 * 两个enum。
 *
 * 本例定义了三个实例PAPER,SCISSOR,ROCK并且通过构造函数分别对其vPaper,vScissors，vRock
 * 的结果进行了初始化。然后根据compete的参数返回相应的outCome。
 * 其实与C02RoShamBo一样也是进行了两次动态分派，一次是compete方法，另一次是switch
 */
public enum C05RoShamBoEnum implements C03Competitor<C05RoShamBoEnum> {
    //对于PAPER来说，vPaper=DRAW,vScissors=LOSE,vRock=WIN
    PAPER(DRAW, LOSE, WIN),
    SCISSOR(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);

    private C01OutCome vPaper, vScissors, vRock;
    private C05RoShamBoEnum(C01OutCome vPaper, C01OutCome vScissors, C01OutCome vRock){
        this.vPaper = vPaper;
        this.vScissors = vScissors;
        this.vRock = vRock;
    }

    /**
     * 实例方法，根据competitor的实际类型返回对应的outCome
     * @param competitor
     * @return
     */
    @Override
    public C01OutCome compete(C05RoShamBoEnum competitor){
        switch(competitor){
            default:
            case PAPER: return vPaper;
            case SCISSOR: return vScissors;
            case ROCK: return vRock;
        }
    }


    public static void main(String[] args){
        /*
            调用静态方法，使用静态方法可以有效减少。实例化时对于泛型代码的一些键入量
            有点类似于chapter15generic/latantType/C03Fill2.java的AdapterHelper方法的说明
         */
        C04RoShamBo2.play(C05RoShamBoEnum.class,10);
    }
}

package com.xiaojihua.datastructure;

/**
 * 霍纳法则：
 * 是一种求解多项式的算法
 * 假设，有n+2个数，a0,a1,a2,a3,...an和x组成的一个多项式，形式如下：
 * S = a0*x^0 + a1*x^1 + a2*x^2 + a3*x^3 + ... an*x^n
 * 求S的值
 * 通常的算法是一项一项的求值然后累加，但是这样要进行n*(n+1)/2次乘法运算和n次加法运算。
 * 霍纳算法是一个改进的算法：不断的将x作为公因子从多项式中提取出来：
 * S = (...(an*x+an-1)x + ...)x + a0
 * 比如对于多项式：
 * S = 2x^4 - x^3 -3x^2 + x -5
 *   = x(2x^3 - x^2 -3x +1) -5
 *   = x(x(2x^2 - x -3) +1) -5
 *   = x(x(x(2x -1) -3) +1) -5
 * 这种方法的时间复杂度为O(n)
 */
public class C17HornerRule {

    public static void main(String[] args) {
        System.out.println(horner(new int[]{-5,1,-3,-1,2},3));
    }

    /**
     * 霍纳算法的实现
     * @param p（多项式中次幂由低到高的系数）
     * @param x （x的取值）
     * @return
     */
    public static long horner(int[] p, int x){

        long result= p[p.length - 1];
        for(int i=p.length-2; i>=0; i--){
            result = x * result + p[i];
        }
        return result;
    }

}

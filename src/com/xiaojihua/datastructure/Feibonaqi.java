package com.xiaojihua.datastructure;

/**
 * 两种方式实现斐波那契数列的求解
 */
public class Feibonaqi {

    public static void main(String[] args) {
        System.out.println(fibonaqiForRecur(20));
        System.out.println(fibonaqiForNomal(20));
    }

    /**
     * 递归方式实现，
     * 此递归的实现违反了递归算法的部分定义，即每次递归都不重复计算。
     * 在运算n-2的时候，其中的大部分都在运算n-1的时候运算过了。
     * @param n
     * @return
     */
    public static int fibonaqiForRecur(int n){

        if(n <= 2){
            return 1;
        }

        return fibonaqiForRecur(n-1) + fibonaqiForRecur(n-2);
    }

    /**
     * 非递归方式实现
     * @param n
     * @return
     */
    public static int fibonaqiForNomal(int n){

        if(n <= 2){
            return 1;
        }
        int n1 = 1, n2 = 1, sum = 0;
        for(int i=0; i<n-2; i++){
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }

        return sum;
    }
}

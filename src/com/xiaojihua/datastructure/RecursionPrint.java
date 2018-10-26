package com.xiaojihua.datastructure;

/**
 * 递归打印
 *
 * 思路：
 * 首先理解%运算。5321%10=1,532%10=2,53%10=3,5%10=5
 * 递归的基准情况为当n<=10的时候打印第一位。
 * 那么剩下的事情就是怎样不断的向这个结果靠近，那就是n/10
 *
 */
public class RecursionPrint {

    public static void print(int n){

        if(n>10){
            print(n/10);
        }
        System.out.println(n%10);
    }

    public static void main(String[] args) {
        print(5321);
    }
}

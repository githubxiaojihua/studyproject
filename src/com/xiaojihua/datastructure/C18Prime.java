package com.xiaojihua.datastructure;

/**
 * 判断一个数是否为素数/质数
 */
public class C18Prime {

    public static void main(String[] args) {
        System.out.println(isPrime(37));
        System.out.println(nextPrime(32));
    }

    public static boolean isPrime(int n){

        //2,3直接返回true
        if(n == 2 || n == 3){
            return true;
        }
        //1或者非2偶数直接返回false
        if(n == 1 || n % 2 == 0){
            return false;
        }

        // 从3开始判断小于n的奇数，判断范围为（3 --- Math.sqrt(n)）
        // 这么判断的理由是;
        // 1、能进行到下面for循环的n均为奇数，因为如果是偶数那么在上面的if语句中就直接返回false了。而奇数只能被奇数整除，因此只需要比较奇数就可以
        // 2、一个合数（非质数）必定是除1和他本身之外的两个数的乘积，而这两个数，必定一个小于等于它的平方根，一个大于等于它的平方根。
        for(int i = 3; i * i <= n; i += 2){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }

    /**
     * 返回大于n的第一个质数
     * @param n
     * @return
     */
    public static int nextPrime(int n){

        // 首先确保为奇数
        if(n % 2 == 0){
            n++;
        }
        // 通过一个空的for循环确定n的值
        for( ; !isPrime(n); n += 2);
        return n;
    }
}

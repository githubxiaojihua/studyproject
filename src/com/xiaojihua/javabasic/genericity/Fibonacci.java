package com.xiaojihua.javabasic.genericity;

/**
 * 泛型的应用之一：生成器
 * 通过生成器的next方法，产生fibonacci数列的前n项值。
 *
 */
public class Fibonacci implements Generate<Integer> {

    int count = 0;

    /**
     * 求解数列的第n项值
     * @param n
     * @return
     */
    private int fib(int n){
        if(n < 2){
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for(int i = 0; i < 18; i++){
            System.out.println(fibonacci.next());
        }
    }
}

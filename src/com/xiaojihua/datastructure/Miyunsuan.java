package com.xiaojihua.datastructure;

public class Miyunsuan {

	public static void main(String[] args) {
		
		System.out.println(pow2(2,4));

	}

	/**
	 * 递归方式实现幂运算
	 * @param x
	 * @param y
	 * @return
	 */
	public static long pow1(int x, int y){
		
		if(y == 1){
			return x;
		}
		
		return x*pow1(x,y-1);//这里并非是尾递归，尾递归的定义是，函数中所有的递归调用都在函数的最后一条语句，并且不能处于表达式中。
	}

	/**
	 * 改进后的递归幂运算。
	 * @param x
	 * @param y
	 * @return
	 */
	public static long pow2(int x, int y){
		
		if(y == 0){
			return 1;
		}
		
		if(y == 1){
			return x;
		}
		
		if(y%2 == 0){
			return pow2(x*x,y/2);//这个是尾递归
		}else{
			return pow2(x*x,y/2)*x;
		}
	}

}

package com.xiaojihua.datastructure;

public class Recursion1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printOut(345423456);
	}

	/**
	 *
	 * @param n
	 */
	public static void printOut(int n){

		if(n > 10){
			printOut(n/10); // 递归体，总能想一个基准情况靠拢
		}
		System.out.print(n%10);// 基准情况
	}

}

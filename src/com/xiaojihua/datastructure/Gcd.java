package com.xiaojihua.datastructure;

public class Gcd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long rem = gcd(1989,1590);
		System.out.println(rem);
	}

	/**
	 * 欧几里得算法，求两个整数的最大公因数
	 * @param m
	 * @param n
	 * @return
	 */
	public static long gcd(long m,long n){

		while(n != 0){
			long rem = m%n;
			m = n;
			n = rem;
		}

		return m;
	}

}
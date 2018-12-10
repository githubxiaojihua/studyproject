package com.xiaojihua.datastructure;

public class C05Gcd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long rem = gcd(1590,1989);
		System.out.println(rem);
	}

	/**
	 * 欧几里得算法，求两个整数的最大公因数
	 * O(logN)
	 * 此时间复杂度的计算依据M%N<M/2(证明方法参考课本定理2.1)
	 * 也就是说M/2是其上限，而M/2对应的时间复杂对为logN
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
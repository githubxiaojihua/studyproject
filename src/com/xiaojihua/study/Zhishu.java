package com.xiaojihua.study;

public class Zhishu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(probRelPrim(20));
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

	public static double probRelPrim(int n){

		int rel = 0, tot = 0;

		for(int i=1; i<=n; i++){
			for(int j=i+1; j<=n; j++){
				tot++;
				if(gcd(i,j)==1){
					rel++;
				}
			}
		}

		return (double)rel/tot;
	}

}

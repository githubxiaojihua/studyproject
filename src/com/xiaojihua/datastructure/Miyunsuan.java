package com.xiaojihua.datastructure;

public class Miyunsuan {

	public static void main(String[] args) {
		
		System.out.println(pow2(2,4));

	}
	
	public static long pow1(int x, int y){
		
		if(y == 1){
			return x;
		}
		
		return x*pow1(x,y-1);
	}
	
	public static long pow2(int x, int y){
		
		if(y == 0){
			return 1;
		}
		
		if(y == 1){
			return x;
		}
		
		if(y%2 == 0){
			return pow2(x*x,y/2);
		}else{
			return pow2(x*x,y/2)*x;
		}
	}

}

package com.xiaojihua.datastructure;

public class C04BanarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arr = {-3,-2,-2,-1,2,4,5,6};
		int index = binarySearch(arr,-1);
		System.out.println(index);
	}

	/**
	 * 折半查找，通过泛型实现，接收所有实现comparable接口的类型
	 * 时间复杂度为O(logN)
	 * 循环从hight-low = N-1开始，并保持hight-low≥-1，且每次循环后hight-low都是上一次的折半
	 * ，于是循环的次数为log(N-1) + 2 .
	 * 比如hight-low=128那么迭代的值为：64，32,16，8,4,2，1，0，-1
	 * @param a
	 * @param x
	 * @return
	 */
	public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a,AnyType x){

		int low = 0, hight = a.length - 1;

		while(low <= hight){
			int mid = (low + hight)/2;
			if(a[mid].compareTo(x) < 0){
				low = mid + 1;
			}else if(a[mid].compareTo(x)>0){
				hight = mid - 1;
			}else{
				return mid;
			}

		}
		return -1;

	}

}
package com.xiaojihua.datastructure;

/**
 * 递归实现折半查找
 * @author Administrator
 *
 */
public class Dgzbcz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arr = {-3,-2,-2,-1,2,4,5,6};
		int index = bannarySearch(arr,-2,0,arr.length-1);
		System.out.println(index);
	}


	public static <AnyType extends Comparable<? super AnyType>> int bannarySearch(AnyType[] a,AnyType x,int low,int hight){

		// 不断推进
		int mid = (low+hight)/2;

		// 基本情况1
		if(a[mid].compareTo(x)==0){
			return mid;
		}
		// 基本情况2
		if(low == hight){
			return -1;
		}

		// 不断推进
		return a[mid].compareTo(x)>0?bannarySearch(a,x,low,mid):bannarySearch(a,x,mid+1,hight);

	}

}
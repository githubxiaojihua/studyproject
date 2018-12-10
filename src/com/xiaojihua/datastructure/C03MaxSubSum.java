package com.xiaojihua.datastructure;

/**
 * 求最大组序列的算法
 * 题干：给定（可能有负数）的整数A1,A2,A3...AN,求∑j k=i Ak的最大值。（为方便期间，如果所有数均为负数，则最大序列和为0）。
 * @author Administrator
 *
 */
public class C03MaxSubSum {

	public static void main(String[] args) {

		int[] arr = {4,-3,5,-2,-1,2,6,-2};

		int max =  maxSubSum4(arr,0,arr.length-1);

		System.out.println(max);

	}

	/**
	 * O(N3)。
	 * 关于时间复杂度的计算：
	 * 属于三层for循环因此时间复杂度为O(N*N*N*1)=O(N³)
	 * @param paramArr
	 * @return
	 */
	public static int maxSubSum1(int[] paramArr){

		int maxSubSum = 0;

		for(int i=0; i<paramArr.length; i++){//运行N次

			for(int j=i; j< paramArr.length; j++){//运行最多为N次，我们设定为N次

				int sumTmp = 0;

				for(int k=i; k<=j; k++){//j-i+1次最多也为N次，设定为N次
					sumTmp += paramArr[k];//设消耗一个时间单位
				}

				if(sumTmp > maxSubSum){
					maxSubSum = sumTmp;
				}
			}

		}

		return maxSubSum;
	}

	/**
	 * O(n2)，计算方法同上
	 * @param paramArr
	 * @return
	 */
	public static int maxSubSum2(int[] paramArr){

		int maxSubSum = 0;

		for(int i=0; i<paramArr.length; i++){

			int sumTmp = 0;

			for(int j=i; j<paramArr.length; j++){
				sumTmp += paramArr[j];
				if(maxSubSum < sumTmp){
					maxSubSum = sumTmp;
				}
			}

		}
		return maxSubSum;
	}

	/**
	 * O(N)
	 * 算法正确性的分析参考书本，但是还不是很明白
	 * @param paramArr
	 * @return
	 */
	public static int maxSubSum3(int[] paramArr){

		int maxSum = 0, thisSum = 0;
		for(int j=0; j<paramArr.length; j++){
			thisSum += paramArr[j];

			if(thisSum > maxSum){
				maxSum = thisSum;
			}else if(thisSum < 0){
				thisSum = 0;
			}
		}

		return maxSum;
	}


	/**
	 * O(NlogN)
	 * 具体的计算逻辑可以参考课本2.4节
	 * 使用了分治算法设计方法
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	public static int maxSubSum4(int[] a,int left, int right){

		int leftMaxSum=0,rightMaxSum=0,leftTempSum=0,rightTempSum=0;
		int center = (left+right)/2;

		//基本情况
		if(left == right){
			return a[left]>0?a[left]:0;
		}

		//左边包括最后一个元素的最大和
		for(int i=center; i>=left; i--){
			leftTempSum += a[i];
			if(leftTempSum>leftMaxSum){
				leftMaxSum = leftTempSum;
			}
		}

		//右边包括第一个元素的最大和
		for(int i=center+1; i<=right; i++){
			rightTempSum += a[i];
			if(rightTempSum>rightMaxSum){
				rightMaxSum = rightTempSum;
			}
		}

		//左边最大和(递归)
		int leftMax = maxSubSum4(a, left, center);
		//右边最大和(递归)
		int rightMax = maxSubSum4(a, center+1, right);

		return max3( leftMax, rightMax,
				leftMaxSum + rightMaxSum );


	}

	private static int max3( int a, int b, int c )
	{
		return a > b ? a > c ? a : c : b > c ? b : c;
	}



}
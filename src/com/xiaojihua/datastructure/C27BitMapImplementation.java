package com.xiaojihua.datastructure;

public class C27BitMapImplementation {
    private static final int DEFAULT_SIZE = 10000000;
    private int[] arr;

    public C27BitMapImplementation(int number){
        arr = new int[(number >> 5) + 1];
    }

    public C27BitMapImplementation(){
        this(DEFAULT_SIZE);
    }

    /**
     * 设置n对应bit位为1
     * @param n
     */
    public void addValue(int n){
        /**
         * n >> 5 相当与n/32 用于确定n所在arr中的位置
         * n & 0x1f 相当于 n%32
         */
        arr[n >> 5] |= 1 << (n & 0x1f);
    }

    /**
     * 判断n是否已经存储
     * @param n
     * @return
     */
    public boolean isExist(int n){
        return (arr[n >> 5] & (1 << (n & 0x1f))) != 0;
    }

    /**
     * 打印bitMap
     */
    public void printBitMap(){
        for(int i = 0; i < arr.length; i++){
            System.out.println("arr[" + i + "]:" + Integer.toBinaryString(arr[i]));
        }
    }

    public static void main(String[] args) {
        int[] arr = {0,1,5,30,32,64,56,159,120,21,17,35,45};
        C27BitMapImplementation bitMap = new C27BitMapImplementation(159);
        for(int i : arr){
            bitMap.addValue(i);
        }
        bitMap.printBitMap();
        System.out.println(bitMap.isExist(21));
    }
}

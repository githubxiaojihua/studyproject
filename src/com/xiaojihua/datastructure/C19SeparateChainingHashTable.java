package com.xiaojihua.datastructure;

import java.util.LinkedList;
import java.util.List;

/**
 * 散列是一种以常数平均时间 执行插入、删除、查询的技术。
 * 散列函数：假设列表的大小为tableSize,每个被存储的值根据一定的规则被映射到0到tableSize-1这个范围中的一个数值，并被存储。
 * 在散列函数中，为了保证计算简单并且分布均匀，一般要求tableSize为质数。
 * 一种比较好的散列函数：
 * public static int hash(String key,int tableSize){
 *      int hashVal = 0;
 *      for(int i=0; i<key.length(); i++){
 *          hashVal = 37 * hashVal + key.charAt(i);
 *      }
 *      hashVal %= tableSize;
 *      if(hashVal < 0){
 *          hashVal += tablSize;
 *      }
 *      return hashVal;
 * }
 * 以上算法依据的是霍纳法则计算多项式的值。
 *
 * 散列：
 * 分离链表法，使用链表来解决冲突（hash值相同的元素）
 * @param <AnyType>
 */
public class C19SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;// 默认数组大小
    private List<AnyType>[] theLists;// 存储数据的链表数组
    private int currentSize;// 元素数量

    /**
     * 重写默认构造函数，按默认大小创建链表数组
     */
    C19SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 根据指定大小构建。
     * @param size
     */
    C19SeparateChainingHashTable(int size){
        theLists = new LinkedList[nextPrime(size)];
        for(int i=0; i<theLists.length; i++){
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    /**
     * 判断是否包含某个具体值
     * @param x
     * @return
     */
    public boolean contains(AnyType x){
        List<AnyType> linkedList = theLists[myHash(x)];
        return linkedList.contains(x);
    }

    /**
     * 插入元素
     * 这里有一个装填因子的概念，这里的装填因子为1，通过++currentSize > theLists.length来描述。
     * 装填因子为散列表中元素个数与列表大小的比。
     * 元素个数即为存储在各个linkedList中的元素个数
     * 列表大小为数组大小
     *
     * 当装填因子大于1的时候，就需要重新散列，调用reHash函数。
     * 这样能保证散列表能有一个比较好的分布，并且冲突（属于同一个hash值的元素）较少。
     * @param x
     */
    public void insert(AnyType x){
        List<AnyType> linkedList = theLists[myHash(x)];
        if(!linkedList.contains(x)){
            linkedList.add(x);
            if(++currentSize > theLists.length){
                // rehash
                reHash();
            }
        }
    }

    /**
     * 重新散列函数
     * 当散列表的装填因子超出后调用。
     * 将散列表的大小设置为大于两倍原来大小的第一个质数
     * 然后根据新的散列表进行重新插入（重新计算位置）
     */
    private void reHash(){
        List<AnyType>[] oldList = theLists;
        theLists = new LinkedList[nextPrime(2*theLists.length)];
        for(int i=0; i<theLists.length; i++){
            theLists[i] = new LinkedList<>();
        }
        currentSize = 0;
        for(List<AnyType> list : oldList){
            for(AnyType x : list){
                insert(x);
            }
        }
    }

    /**
     * 删除元素
     * @param x
     */
    public void remove(AnyType x){
        List<AnyType> linkedList = theLists[myHash(x)];
        if(linkedList.contains(x)){
            linkedList.remove(x);
            currentSize--;
        }
    }

    /**
     * 清空链表
     */
    public void makeEmpty(){
        for(int i=0; i<theLists.length; i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    /**
     * 计算用于散列表的hash值
     * @param x
     * @return
     */
    private int myHash(AnyType x){
        // 结合课本，着重看一下hashCode的源码
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if(hashVal < 0){
            hashVal += theLists.length;
        }
        return hashVal;
    }

    /**
     * 返回大于n的第一个质数
     * @param n
     * @return
     */
    private static int nextPrime(int n){

        // 首先确保n为奇数
        if(n % 2 == 0){
            n++;
        }
        // 通过一个空的for循环确定n的取值
        for(; !isPrime(n); n += 2);
        return n;

    }

    /**
     * 判断一个数是否为质数
     * @param n
     * @return
     */
    private static boolean isPrime(int n){

        //2,3直接返回true
        if(n == 2 || n == 3){
            return true;
        }
        //1或者非2偶数直接返回false
        if(n == 1 || n % 2 == 0){
            return false;
        }

         // 从3开始判断小于n的奇数，判断范围为（3 --- Math.sqrt(n)）
         // 这么判断的理由是;
         // 1、能进行到下面for循环的n均为奇数，因为如果是偶数那么在上面的if语句中就直接返回false了。而奇数只能被奇数整除，因此只需要比较奇数就可以
         // 2、一个合数（非质数）必定是除1和他本身之外的两个数的乘积，而这两个数，必定一个小于等于它的平方根，一个大于等于它的平方根。
        for(int i = 3; i * i <= n; i+=2){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 嵌套类，内部使用。区别于内部类
     */
    private static class Employee{
        private String name;
        private double salary;
        private int seniority;

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Employee) && name.equals(((Employee) obj).name);
        }

        public int hashCode(){
            return name.hashCode();
        }
    }

    public static void main(String[] args) {
        C19SeparateChainingHashTable<Integer> h = new C19SeparateChainingHashTable<>();
        final int NUMS = 2000000;
        final int GAP  =   37;

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
            h.insert(i);
        }

        for( int i = 1; i < NUMS; i+= 2 ){
            h.remove( i );
        }

        System.out.println(h.currentSize);
    }
}

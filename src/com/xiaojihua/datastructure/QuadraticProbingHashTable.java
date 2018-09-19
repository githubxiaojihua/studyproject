package com.xiaojihua.datastructure;

/**
 * 探测散列表：不使用分离链接的散列表，装填因子等于0.5，不使用分离链接解决冲突的方法是尝试其他单元，直到找到
 * 空的单元为止，常见的操作是h0(x),h1(x),h2(x),h3(x)...相继被试选，其中hi(x)=(hash(x)+f(i)) mod tablesize,
 * 切f(0)=0,函数f就是冲突解决函数。
 *
 * 探测散列表有三种冲突
 * 散列：线性探测法、平方探测法、双散列法
 *
 * 平方探测法
 * 规则：使用平法探测法，并且表的大小是素数，那么当表至少有一半为空的时候，总能够插入一个新元素(书中有详细的证明过程)。
 * 删除探测散列表元素应该使用懒惰删除，否则contains操作都将失效。平方探测方法是消除线性探测法的一次聚集问题的
 * 冲突解决方法。平方探测法就是冲突函数为二次的探测方法。流行的选择是f(i)=i^2
 *
 * 线性探测法：检测到冲突的时候（其哈希值对应的位置已经有数据），以当前元素为基础对与后续元素逐个依次检测（必要时可以绕回）。
 * 在线性探测法中f是i的线性函数，典型的情况是f(i)=i,相当于逐个探测。线性检测会造成插入的值都集中在一个连续的区域中，这样会造成
 * 查找、插入会检测很多次，这叫做一次聚集问题。
 *
 * 实现：不使用链表数组，使用散列表项所构成的数组
 */
public class QuadraticProbingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 11;//默认大小
    private HashEntity<AnyType>[] array;//列表
    private int currentSize;//当前列表中的有效元素个数
    private int occupied;//当前列表被占用改了多少，由于是懒惰删除。

    /**
     * 默认构造函数
     */
    QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 按照指定大小初始化散列表
     * @param size
     */
    QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }

    /**
     * 插入操作
     * @param x
     * @return
     */
    public boolean insert(AnyType x){

        int hashVal = findPos(x);//返回一个可插入的位置：为空或者已经存在相同元素的位置

        //如果位置为空，或者位置出于已删除状态
        if(isActive(hashVal)){
            return false;
        }
        //如果是插入null位置，那么需要将occupied加一，其他情况不加。
        if(array[hashVal] == null){
            occupied++;
        }
        //插入元素
        array[hashVal] = new HashEntity<>(x);
        currentSize++;//有效个数增加
        //当实际占用个数大于列表一半的时候，增加列表长度并重新计算hash值。
        if(occupied > array.length/2){
            // rehash
            reHash();
        }

        return true;
    }

    /**
     * 删除元素
     * @param x
     * @return
     */
    public boolean remove(AnyType x){

        int pos = findPos(x);
        if(isActive(pos)){
            array[pos].isActive = false;
            currentSize--;
            return true;
        }else{
            return false;
        }

    }

    /**
     * 判断值是否存在
     * @param x
     * @return
     */
    public boolean contains(AnyType x){
        return isActive(findPos(x));
    }

    /**
     * 当元素数量大于装填因子则将链表大小扩展为大于原来2倍容量的第一个质数大小
     * 然后将列表中的再活元素根据重新计算的hash值重新存储。
     */
    private void reHash(){

        HashEntity<AnyType>[] oldArray = array;
        allocateArray(2 * oldArray.length);
        currentSize = 0;
        occupied = 0;
        for(HashEntity<AnyType> entity : oldArray){
            if(entity != null && entity.isActive){
                insert(entity.element);
            }
        }

    }


    /**
     * 检测存储项是否可用
     * @param position
     * @return
     */
    public boolean isActive(int position){
        return array[position]==null?false:array[position].isActive;
    }

    /**
     * 分配array容量
     * @param arraySize
     */
    private void allocateArray(int arraySize){
        array = new HashEntity[nextPrime(arraySize)];
    }

    /**
     * 清空散列表
     */
    public void makeEmpty(){
        currentSize = 0;
        occupied = 0;
        for(int i=0; i<array.length; i++){
            array[i] = null;
        }
    }

    /**
     * 二次探测方法确定存储位置。
     * 书中缩写是二次探测方法，但是方法中使用的仍然是线性方法（不解？）
     *
     * 在列表中查找为空或者与存储值相同的位置
     * @param x
     * @return
     */
    public int findPos(AnyType x){

        int hashVal = myHash(x);
        int offset = 1;
        // 先判断是否为null ，然后判断是否与戴插入元素相同
        while(array[hashVal] != null && !array[hashVal].element.equals(x)){
            hashVal += offset;
            offset += 2;//此处为何仍然使用线性方法，二次探测方法比应该是i^2形式的吗？
            if(hashVal >= array.length){
                hashVal -= array.length;
            }
        }
        return hashVal;
    }

    /**
     * 计算用于散列表的hash值
     * @param x
     * @return
     */
    private int myHash(AnyType x){

        int hashVal = x.hashCode();
        hashVal %= array.length;
        if(hashVal < 0){
            hashVal += array.length;
        }
        return hashVal;
    }

    /**
     * 获取大于n的下一个质数
     * @param n
     * @return
     */
    private int nextPrime(int n){

        //确保n为奇数
        if(n % 2 == 0){
            n++;
        }

        for(; !isPrime(n); n += 2);
        return n;
    }

    /**
     * 判断一个数是否为质数
     * @param n
     * @return
     */
    private boolean isPrime(int n){
        //2,3直接返回true
        if(n==2 || n==3){
            return true;
        }
        //1或者非2偶数直接返回false
        if(n==1 || n%2==0){
            return false;
        }

        // 从3开始判断小于n的奇数，判断范围为（3 --- Math.sqrt(n)）
        // 这么判断的理由是;
        // 1、能进行到下面for循环的n均为奇数，因为如果是偶数那么在上面的if语句中就直接返回false了。而奇数只能被奇数整除，因此只需要比较奇数就可以
        // 2、一个合数（非质数）必定是除1和他本身之外的两个数的乘积，而这两个数，必定一个小于等于它的平方根，一个大于等于它的平方根。
        for(int i=3; i*i<=n; i+=2){
            if(n%i == 0){
              return false;
            }
        }
        return true;
    }


    /**
     * 嵌套类定义散列表存储项
     * @param <AnyType>
     */
    private static class HashEntity<AnyType>{

        public AnyType element;//存储的数据
        public boolean isActive;//是否已经删除，false代表已删除

        HashEntity(AnyType element){
            this(element,true);
        }

        HashEntity(AnyType element, boolean isActive){
            this.element = element;
            this.isActive = isActive;
        }
    }

    public static void main(String[] args) {
        QuadraticProbingHashTable<String> H = new QuadraticProbingHashTable<>( );

        final int NUMS = 2000000;
        final int GAP  =   37;

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( ""+i );

        System.out.println(H.contains("8999"));

        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( ""+i );

        System.out.println(H.currentSize);
    }
}

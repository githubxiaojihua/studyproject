package com.xiaojihua.datastructure;

/**
 * 堆的通用实现二叉堆
 *
 * 编写方法的原则：
 * 1、要条理清晰
 * 2、分离可供其他方法使用的公用方法
 * 3、应充分利用其他方法
 * @param <AnyType>
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType[] array;

    /**
     * 构造方法
     */
    BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    BinaryHeap(int size){
        currentSize = 0;
        array = (AnyType[]) new Comparable[size + 1];// 注意实例化的写法，不可写成new AnyType[newSize];
    }

    /**
     * 根据给定列表创建二叉堆
     * @param items
     */
    BinaryHeap(AnyType[] items){

        currentSize = items.length;
        array = (AnyType[]) new Comparable[(2 * currentSize)*11/10];
        for(int i=0; i<currentSize; i++){
            array[i] = items[i];
        }

        buildHeap();
    }


    /**
     * 插入操作
     * 插入从第一个元素开始插入。第0个元素另有他用
     * @param x
     */
    public void insert(AnyType x){

        //判断当列表填满后，则扩展
        if(currentSize == array.length - 1){
            enLargeArray(2 * array.length + 1);
        }
        int hole = ++currentSize;
        //上滤法插入X。hole必须是大于1，如果等于1则直接插入
        for(; hole > 1 && x.compareTo(array[hole/2])<0; hole /= 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    /**
     * 删除（自写）
     */
    public AnyType deleteMinByMySelf(){
        //暂存返回值
        AnyType minValue = array[1];
        //判断列表是否已为空
        if(isEmpty()){
            System.out.println("列表为空！");
            return null;
        }
        //通过for循环进行下滤操作
        int hole = 1;
        //判断当findMinChildren返回值x在列表范围之内，并且不为空，则将x代表的元素装填到hole的位置，令hole等于x
        for(;findMinChildren(hole) < array.length-1 && array[findMinChildren(hole)]!=null; hole =findMinChildren(hole)){
            array[hole] = array[findMinChildren(hole)];
        }

        //根据最后选定的节点是否有兄弟节点分别进行处理
        //此时hole代表的应该是一个叶子节点了
        if(array[hole] != null && array[hole + 1] != null){
            array[hole] = array[currentSize--];
        }else{
            array[hole] = null;
        }
        return minValue;
    }


    /**
     * 根据节点索引选取最小儿子
     * 区分处理有左右儿子以及只有做儿子的情况
     * @param hole
     * @return
     */
    private int findMinChildren(int hole){

        AnyType leftChileren = null;
        AnyType rightChileren = null;
        if(2 * hole < array.length-1){
            leftChileren = array[2 * hole];
            rightChileren = array[2 * hole + 1];
        }

        if(leftChileren != null && rightChileren != null){
            return leftChileren.compareTo(rightChileren)>0?2 * hole + 1:2 * hole;
        }else{
            //二叉堆要不存在左右节点，要不只存在做节点
            return 2 * hole;
        }
    }

    /**
     * 根据给定的列表建立二叉堆
     */
    public void buildHeap(){

        for(int i = currentSize/2; i > 0; i--){
            percolateDown(i);
        }
    }

    /**
     * 书中删除方法
     * @return
     */
    public AnyType deleteMin(){

        if(isEmpty()){
            throw new UnderflowException();
        }
        AnyType minElement = findMin();
        array[1] = array[currentSize];
        percolateDown(1);
        return minElement;
    }
    /**
     * 下滤的具体算法（可以重用）
     * @param hole
     */
    private void percolateDown(int hole){

        int child;
        AnyType tmp = array[hole];

        //此处不会出现array[chile]或者array[chile+1]超出数组界限的问题
        //因为在插入操作，当cruuentSize == array.length-1的时候就会自动扩展，也就是说currentSize最大就是length-1
        //下面的预计保证2*hole<currentSize也就是保证了2*hole最大是length-2。
        for(;2 * hole < currentSize; hole = child){
            child = 2 * hole;
            if(array[child].compareTo(array[child+1]) > 0){
                child++;
            }
            if(tmp.compareTo(array[child]) > 0){
                array[hole] = array[child];
            }else{
                break;
            }
        }

        array[hole] = tmp;
    }
    /**
     * 获取最小值
     * @return
     */
    public AnyType findMin(){
        if(isEmpty()){
            throw new UnderflowException();
        }
        return array[1];
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }

    public String toString(){
        StringBuffer sbStr = new StringBuffer();
        for(int i=1; i<currentSize; i++){
            sbStr.append((AnyType)array[i]).append(" ");
        }
        return sbStr.toString();
    }

    /**
     * 当长度不够时，进行扩展
     * @param newSize
     */
    private  void enLargeArray(int newSize){
        AnyType[] oldArray = array;
        array = (AnyType[])new Comparable[newSize];
        for(int i=0; i<oldArray.length; i++){
            array[i] = oldArray[i];
        }
    }


    public static void main(String[] args) {
        int numItems = 10;
        BinaryHeap<Integer> h = new BinaryHeap<>( );
        int i = 37;

        for( i = 1; i<11; i++ )
            h.insert( i );
        System.out.println(h.toString());

        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        h.deleteMinByMySelf();
        System.out.println(h.toString());


    }
}

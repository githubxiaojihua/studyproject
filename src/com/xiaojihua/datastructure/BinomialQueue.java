package com.xiaojihua.datastructure;

/**
 * 二项队列的实现
 * @param <AnyType>
 */
public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_TREES = 1;
    private int currentSize;//优先队列中的元素素量
    private Node<AnyType> [] theTrees; //用于盛放所有二项树的根节点

    /**
     * 无参构造
     */
    public BinomialQueue(){
        theTrees = new Node[DEFAULT_TREES];
        makeEmpty();
    }

    /**
     * 有参构造：使用一个元素初始化二项队列
     * @param item
     */
    public BinomialQueue(AnyType item){
        currentSize = 1;
        theTrees = new Node[1];
        theTrees[0] = new Node<>(item);
    }

    /**
     * 合并操作
     * @param rhs
     */
    public void merge(BinomialQueue<AnyType> rhs){

        if(this == rhs){//忽略别名
            return;
        }
        currentSize += rhs.currentSize;
        if(currentSize > capacity()){//当currentsize>thetrees.length*2的时候进行扩展？
            expandTheTrees(Math.max(theTrees.length, rhs.theTrees.length) + 1);
        }
        Node<AnyType> curr = null;
        for(int i=0,j=1; j<=currentSize; j *= 2, i++){
            Node<AnyType> t1 = theTrees[i];
            Node<AnyType> t2 = i<rhs.theTrees.length?rhs.theTrees[i]:null;

            int switchNum = t1==null?0:1;
            switchNum += t2==null?0:2;
            switchNum += curr==null?0:4;

            switch (switchNum){
                case 0://此高度没有树
                case 1://此高度只有this有树
                    break;
                case 2://此高度只有rhs
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 3://此高度this和rhs都有树
                    curr = combinTree(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 4://此高度只有carr有树.
                    theTrees[i] = curr;
                    curr = null;
                    break;
                case 5://此高度this和carr有树
                    curr = combinTree(t1,curr);
                    theTrees[i] = null;
                    break;
                case 6://此高度rhs和carry有树
                    curr = combinTree(t2,curr);
                    rhs.theTrees[i] = null;
                    break;
                case 7://此高度this、carr、rhs有树
                    theTrees[i] = curr;
                    curr = combinTree(t1,t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }
        for(int i=0; i<rhs.theTrees.length; i++){
            rhs.theTrees[i] = null;
        }
        rhs.currentSize = 0;
    }

    /**
     * 插入
     * @param x
     */
    public void insert(AnyType x){
        merge(new BinomialQueue<>(x));
    }

    /**
     * 查询最小值
     * @return
     */
    public AnyType findMin(){
        if(isEmpty()){
            throw new UnderflowException();
        }
        return theTrees[findMinIndex()].element;
    }

    /**
     * 删除最小值
     * H:为删除操作之前的优先队列
     * H':为删除最小值所在的二项树后的优先队列
     * H'':为删除最小值后的二项树后行程的优先队列
     * @return
     */
    public AnyType deleteMin(){
        if(isEmpty()){
            throw new UnderflowException();
        }

        int minIndex = findMinIndex();//返回最小根的索引
        AnyType minItem = theTrees[minIndex].element;

        Node<AnyType> deleteTree = theTrees[minIndex].leftChild;//最小根所在的二项树的第一个孩子

        //H''
        BinomialQueue<AnyType> deleteTrees = new BinomialQueue<>();
        deleteTrees.expandTheTrees(minIndex);//根据最小根所在的索引初始化新优先队列，H''的高度恰好应该是索引值-1
        deleteTrees.currentSize = (1<<minIndex) - 1;//计算容量，容量= 2^minIndex -1

        //从优先队列的后边开始初始化，因为从combin方法来看，第一个孩子一定是最高的
        for(int i=minIndex-1; i>=0; i--){
            deleteTrees.theTrees[i] = deleteTree;
            deleteTree = deleteTree.nextSibling;
            deleteTrees.theTrees[i].nextSibling = null;

        }

        //H'，将最小根所在的二项树从H中删除，最后合并H',H''
        theTrees[minIndex] = null;
        currentSize -= deleteTrees.currentSize + 1;

        merge(deleteTrees);
        return minItem;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }

    /**
     * 清空
     */
    public void makeEmpty(){
        currentSize = 0;
        for(int i=0; i<theTrees.length; i++){
            theTrees[i] = null;
        }
    }

    /**
     * 扩展根节点列表数组
     * @param newNumTrees
     */
    private void expandTheTrees(int newNumTrees){
        Node<AnyType> [] old = theTrees;
        int oldNumTrees = theTrees.length;
        theTrees = new Node[newNumTrees];
        for(int i=0; i<Math.min(oldNumTrees,newNumTrees); i++){
            theTrees[i] = old[i];
        }
        for(int i = oldNumTrees; i<newNumTrees; i++){
            theTrees[i] = null;
        }
    }

    /**
     * 返回最小值索引。
     * 注意此时二项队列不能为空
     * @return
     */
    private int findMinIndex(){
        int i;
        int minIndex;

        for(i=0; theTrees[i] == null; i++);

        for(minIndex=i; i<theTrees.length; i++){
            if(theTrees[i]!=null && theTrees[i].element.compareTo(theTrees[minIndex].element)<0){
                minIndex = i;
            }
        }

        return minIndex;
    }



    /**
     * 合并两个同样大小的二项树
     * @param t1
     * @param t2
     * @return
     */
    private Node<AnyType> combinTree(Node<AnyType> t1, Node<AnyType> t2){

        if(t1.element.compareTo(t2.element) > 0){
            return combinTree(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    /**
     * 返回整个二项队列的元素数量
     * 规律：
     * 二项队列是一个数组标识
     * 数组的下标代表唯一的高度。比如0代表高度为0的二项树、1代表高度为1的二项树。。。
     * 二项树：高度为k的二项树恰好有2^k个节点。
     * 整个二项队列的元素数量是2的二项队列数组长度次方-1。
     * @return
     */
    private int capacity(){
        return (1 << theTrees.length) - 1;
    }

    /**
     * 内部类定义节点
     * @param <AnyType>
     */
    private static class Node<AnyType>{

        AnyType element;//元素
        Node<AnyType> leftChild;//leftchild
        Node<AnyType> nextSibling;//rightchild

        Node(AnyType element, Node<AnyType> leftChild, Node<AnyType> nextSibling){
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }

        Node(AnyType element){
            this(element, null, null);
        }
    }

    public static void main(String[] args) {
        int numItems = 10000;
        BinomialQueue<Integer> h  = new BinomialQueue<>( );
        BinomialQueue<Integer> h1 = new BinomialQueue<>( );
        int i = 37;

        System.out.println( "Starting check." );

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            if( i % 2 == 0 )
                h1.insert( i );
            else
                h.insert( i );

        h.merge( h1 );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );

        System.out.println( "Check done." );
    }
}

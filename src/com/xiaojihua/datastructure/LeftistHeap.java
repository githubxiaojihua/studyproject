package com.xiaojihua.datastructure;

/**
 * 左式堆的实现
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {

    private Node<AnyType> root;//root节点

    /**
     * 构造函数
     */
    LeftistHeap(){
        root = null;
    }

    /**
     * 合并的第一个方法
     * 去除合并同一对象别名的情况，并且将被合并的对象，root置null
     * @param rhs
     */
    public void merge(LeftistHeap<AnyType> rhs){
        //合并别名，则不做任何操作
        if(this == rhs){
            return;
        }
        //调用合并的第二个方法
        root = merge(root,rhs.root);
        //将root置null
        rhs.root = null;
    }

    /**
     * 合并的第二个方法
     * 确保进入合并第三个方法的时候，h1和h2均不为空。并且h1是两者中的小者。
     * @param h1
     * @param h2
     * @return
     */
    private Node<AnyType> merge(Node<AnyType> h1, Node<AnyType> h2){

        if(h1 == null){
            return h2;
        }

        if(h2 == null){
            return h1;
        }

        if(h1.element.compareTo(h2.element) < 0){
            return merge1(h1,h2);
        }else{
            return merge1(h2,h1);
        }
    }

    /**
     * 合并的第三个方法也是主方法。
     * 递归的合并两个节点。
     * 合并思路：将大者的当前节点（h2）与小者当前节点(h1)的右子树进行合并。
     * @param h1
     * @param h2
     * @return
     */
    private Node<AnyType> merge1(Node<AnyType> h1, Node<AnyType> h2){
        if(h1.left == null){
            h1.left = h2;
        }else{
            h1.right = merge(h1.right,h2);
            if(h1.left.npl < h1.right.npl){
                //交换
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }

        return h1;
    }

    /**
     * 交换左右节点的左右子树
     * @param t
     * @param <AnyType>
     */
    private static <AnyType> void swapChildren(Node<AnyType> t){

        Node<AnyType> tmpNode = t.left;
        t.left = t.right;
        t.right = tmpNode;
    }

    /**
     * 插入操作
     * @param x
     */
    public void insert(AnyType x){

        root = merge(new Node<>(x), root);
    }

    /**
     * 删除最小元素
     * @return
     */
    public AnyType deleteMin(){

        if(isEmpty()){
            throw new UnderflowException();
        }

        AnyType minItem = root.element;

        root = merge(root.left, root.right);
        return minItem;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * 清空
     */
    public void makeEmpty(){
        root = null;
    }

    /**
     * 返回最小值
     * @return
     */
    public AnyType findMin(){
        if(isEmpty()){
            throw new UnderflowException();
        }
        return root.element;
    }

    /**
     * 使用嵌套类定义左式堆的节点
     * @param <AnyType>
     */
    private static class Node<AnyType>{

        private AnyType element;
        private Node<AnyType> left;
        private Node<AnyType> right;
        private int npl;

        Node(AnyType element){
            this(element,null,null);
        }

        Node(AnyType element, Node<AnyType> left, Node<AnyType> right){
            this.element = element;//节点存储的值
            this.left = left;//左儿子
            this.right = right;//右儿子
            npl = 0;//零路径长度
        }
    }

    public static void main(String[] args) {
        int numItems = 100;
        int i = 37;
        LeftistHeap<Integer> h = new LeftistHeap<>();
        LeftistHeap<Integer> h1 = new LeftistHeap<>();

        for(i=37; i!=0; i=(i+37)%numItems)
            if(i%2 == 0)
                h.insert(i);
            else
                h1.insert(i);

        h.merge(h1);

        System.out.println(h.deleteMin());
        System.out.println(h.deleteMin());
    }
}

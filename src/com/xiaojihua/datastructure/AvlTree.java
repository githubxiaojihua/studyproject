package com.xiaojihua.datastructure;

/**
 * 平衡二叉树
 * @param <AnyType>
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {

    private AvlNode<AnyType> root;
    // 触发平衡调整的临界值。大于此值则进行调整
    private static final int ALLOWED_IMBALANCE = 1;

    public AvlTree(){
        root = null;
    }

    /**
     * 公共方法：调用内部方法插入元素
     * @param x
     */
    public void insert(AnyType x){
        root = this.insert(x,root);
    }

    /**
     * 公共方法：调用内部方法打印树
     */
    public void printTree(){
        this.printTree(root);
    }

    /**
     * 内部方法：返回节点的高度
     * @param t
     * @return
     */
    private int height(AvlNode<AnyType> t){
        return t==null?-1:t.height;
    }

    /**
     * 对节点进行平衡调整
     * @param t
     * @return
     */
    private AvlNode<AnyType> balance(AvlNode<AnyType> t){

        if(t == null){
            return t;
        }
        // 当左子树比右子树高2时，进行对左子树的调整
        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE){
            // 当左子树比右子树大或者相等的时候，进行左子树单旋转调整。
            if(height(t.left.left) >= height(t.left.right)){
                t = this.rotateWithLeftChild(t);
            }else{
                // 对左子树进行双旋转调整
                t = this.doubleWithLeftChild(t);
            }
        }else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
            if(height(t.right.right) >= height(t.right.left)){
                t = this.rotateWithRightChild(t);
            }else{
                t = this.doubleWithRightChild(t);
            }
        }
        // 更新节点高度
        t.height = Math.max(height(t.left),height(t.right));
        return t;
    }

    /**
     * 左子树单旋转：针对二叉树的情况一
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2){

        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        // 更新高度，只需要更新变更的节点即k1和k2。因为旋转后k2是k1的右儿子，因此应该先更新k2
        // k1的左儿子和k2的右儿子没有变化。
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),height(k2)) + 1;
        return k1;
    }

    /**
     * 右子树单旋转：针对二叉树的情况四
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2){

        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right),height(k2)) + 1;
        return  k1;
    }

    /**
     * 对于左右这种，情况二
     * 先进性
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3){

        // 现将k3的左子树进行右子树单旋转
        k3.left = this.rotateWithRightChild(k3.left);
        // 再将k3进行左子树单旋转
        return this.rotateWithLeftChild(k3);
    }

    /**
     * 对于右左这种，情况三
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3){

        // 先将k3的右子树进行左子树单旋转
        k3.right = this.rotateWithLeftChild(k3.right);
        // 再将k3进行右子树单旋转
        return this.rotateWithRightChild(k3);
    }

    /**
     * 内部方法：打印节点子树
     * @param t
     */
    private void printTree(AvlNode<AnyType> t){

        if(t!=null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }

    }
    /**
     * 内部方法：插入元素
     * @param x
     * @param t
     * @return
     */
    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t){

        if(t == null){
            return new AvlNode<>(x);
        }

        int compareRes = x.compareTo(t.element);

        if(compareRes > 0 ){
            t.right = insert(x,t.right);
        }else if(compareRes < 0){
            t.left = insert(x,t.left);
        }else{
            // do nothing
        }

        // 每插入一个节点都要进行其路径上所有节点的平衡调整
        return this.balance(t);
    }

    /**
     * 嵌套类定义AVLNODE
     * @param <AnyType>
     */
    private static class AvlNode<AnyType>{

        // 元数据
        public AnyType element;
        // 左子树
        public AvlNode<AnyType> left;
        // 右子树
        public AvlNode<AnyType> right;
        // 高度
        public int height;

        /**
         * 构造方法1
         * @param theElement
         */
        AvlNode(AnyType theElement){
            this.element = theElement;
            this.left = null;
            this.right = null;
        }

        /**
         * 构造方法2
         * @param theElement
         * @param left
         * @param right
         */
        AvlNode(AnyType theElement, AvlNode<AnyType> left, AvlNode<AnyType> right){
            this.element = theElement;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

    }

    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        final int NUMS = 4000;
        final int GAP = 37;

        for(int i=GAP; i!=0; i = (i + GAP)%NUMS){
            avlTree.insert(i);
        }

        avlTree.printTree();
    }
}

package com.xiaojihua.datastructure;

public class BinarySearchTree<AnyType extends Comparable> {

	private BinaryNode<AnyType> root;

	/**
	 * 无参初始化二叉查找树
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * 置空
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * 判空
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * public方法查找树中是否包含x（从root查起）
	 *
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x) {
		return this.contains(x, root);
	}

	/**
	 * 递归判断树是否包含指定元素
	 *
	 * @param x
	 * @param t
	 * @return
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {

		if (t == null) {
			return false;
		}

		int result = x.compareTo(t.element);

		if (result > 0) {
			return contains(x, t.right);
		} else if (result < 0) {
			return contains(x, t.left);
		} else {
			return true;
		}
	}

	/**
	 * 公共方法：查询二叉树中最小值
	 *
	 * @return
	 */
	public AnyType findMin() {

		if (isEmpty()) {
			throw new UnknownError();
		}
		return this.findMin(root).element;
	}

	/**
	 * 公共方法：查询二叉树中最大值。
	 *
	 * @return
	 */
	public AnyType findMax() {
		if (isEmpty()) {
			throw new UnknownError();
		}
		return this.findMax(root).element;
	}

	/**
	 * 公用插入方法
	 * @param x
	 */
	public void insert(AnyType x){
		root = this.insert(x, root);
	}

	/**
	 * 公用方法：删除元素
	 * @param x
	 */
	public void remove(AnyType x){
		this.remove(x, root);
	}

	/**
	 * 公用方法：打印树
	 */
	public void printTree(){
		this.printTree(root);
	}

	/**
	 * 内部方法打印树
	 * @param t
	 */
	private void printTree(BinaryNode<AnyType> t){
		if(t != null){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	/**
	 * 内部方法：删除一个元素
	 * @param x
	 * @param t 默认都是从root开始
	 * @return
	 */
	private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){

		if(t == null){
			return t;
		}
		int compareResult = x.compareTo(t.element);
		if(compareResult>0){
			t.right = remove(x,t.right);
		}else if(compareResult<0){
			t.left = remove(x,t.left);
		}else if(t.left != null && t.right != null){
			t.element = this.findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}else{
			t = (t.left != null?t.left:t.right);
		}

		return t;
	}

	/**
	 * 内部方法：从给定节点下插入元素x
	 * @param x
	 * @param t 默认都是从root开始
	 * @return
	 */
	private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){

		if(t == null){
			return new BinaryNode<AnyType>(x,null,null);
		}

		int compareResult = x.compareTo(t.element);
		if(compareResult>0){
			t.right = insert(x,t.right);
		}else if(compareResult<0){
			t.left = insert(x,t.left);
		}else{
		}

		return t;
	}

	/**
	 * 内部方法，根据节点查找其下最小节点。（递归）
	 *
	 * @param t 默认都是从root开始
	 * @return
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {

		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}

	}

	/**
	 * 内部方法，根据节点查找其下最大节点（非递归）
	 *
	 * @param t 默认都是从root开始
	 * @return
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {

		if (t == null) {
			return null;
		}
		while (t.right != null) {
			t = t.right;
		}
		return t;
	}

	/**
	 * 嵌套类定义二叉查找树的节点
	 *
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	private static class BinaryNode<AnyType> {
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;

		/**
		 * 初始化root节点
		 *
		 * @param element
		 */
		BinaryNode(AnyType element) {
			this(element, null, null);
		}

		/**
		 * 全参数初始化
		 *
		 * @param element
		 *            元数据
		 * @param lf
		 *            左子树
		 * @param rt
		 *            右字数
		 */
		BinaryNode(AnyType element, BinaryNode<AnyType> lf,
				   BinaryNode<AnyType> rt) {
			this.element = element;
			this.left = lf;
			this.right = rt;
		}
	}

	public static void main( String [ ] args )
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>( );
		final int NUMS = 4000;
		final int GAP  =   37;

		System.out.println( "Checking... (no more output means success)" );

		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
			//System.out.println(i);
			t.insert( i );
		}

		t.printTree();
       /* for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }*/
	}
}

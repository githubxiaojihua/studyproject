package com.xiaojihua.datastructure;

public class CbLockTree {


	private TreeNode<NodeInfo> rootNode;// 定义根节点

	/**
	 * 初始化整棵树，初始情况下只有root节点
	 */
	public void init(){
		NodeInfo rootInfo = new NodeInfo(1,"webpage",0,0,700,500);
		rootNode = new TreeNode<NodeInfo>(rootInfo);
	}

	/**
	 * 根据id返回对应的node
	 * @param id
	 * @param node
	 * @return
	 */
	public TreeNode<NodeInfo> listAll(long id,TreeNode<NodeInfo> node){

		if(node == null){
			return null;
		}else if(node.element.ID == id ){
			return node;
		}else{
			TreeNode<NodeInfo> tempNode = listAll(id,node.firstChild);
			if(tempNode == null){
				tempNode = listAll(id,node.nextSibling);
			}
			return tempNode;
		}

	}

	/**
	 * 从node节点开始按层次打印树
	 * @param node
	 * @param deepth
	 */
	public void listAll(TreeNode<NodeInfo> node,int deepth){
		if(node != null){
			for(int i=1; i<=deepth; i++){
				System.out.print(" ");
			}
			System.out.println(node.element.name);
			listAll(node.firstChild,deepth + 1);
			listAll(node.nextSibling,deepth);
		}
	}

	/**
	 * 在制定id下增加node，默认增加到该id的最后一个子节点之后
	 * @param id
	 * @param node
	 * @return
	 */
	public boolean addNode(long id,TreeNode<NodeInfo> node){

		boolean flag = false;
		TreeNode<NodeInfo> parentNode = listAll(id,rootNode);
		if(parentNode.firstChild == null){
			parentNode.firstChild = node;
			flag = true;
		}else{
			TreeNode<NodeInfo> child = parentNode.firstChild;
			while(child.nextSibling != null){
				child = child.nextSibling;
			}
			child.nextSibling = node;
			flag = true;
		}

		return flag;
	}

	/**
	 * 定义节点
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	private static class TreeNode<AnyType>{

		AnyType element;//节点本身数据
		TreeNode firstChild;//第一个孩子
		TreeNode nextSibling;//下一个兄弟

		public TreeNode(){

		}

		public TreeNode(AnyType theElement){
			this(theElement,null,null);
		}

		public TreeNode(AnyType theElement, TreeNode<AnyType> theFirstChild, TreeNode<AnyType> theNextSibling){
			element = theElement;
			firstChild = theFirstChild;
			nextSibling = theNextSibling;
		}

	}

	/**
	 * 节点数据类型
	 * @author Administrator
	 *
	 */
	private static class NodeInfo{

		private long ID;
		private String name;
		private float positionX;
		private float positionY;
		private int hight;
		private int weight;

		public NodeInfo(long theId,String theName,float thePositionX,float thePositionY,int theHight,int theWeight){
			ID = theId;
			name = theName;
			positionX = thePositionX;
			positionY = thePositionY;
			hight = theHight;
			weight = theWeight;
		}

		public NodeInfo(){}
	}


	public static void main(String[] args) {
		//初始化树
		CbLockTree cbLockTree = new CbLockTree();
		cbLockTree.init();

		//增加节点
		NodeInfo rootInfo = new NodeInfo(2,"vb1",0,1,200,100);
		TreeNode<NodeInfo> node = new TreeNode<NodeInfo>(rootInfo);
		cbLockTree.addNode(1, node);

		NodeInfo rootInfo2 = new NodeInfo(3,"vb2",0,1,200,100);
		TreeNode<NodeInfo> node2 = new TreeNode<NodeInfo>(rootInfo2);
		cbLockTree.addNode(1, node2);

		NodeInfo rootInfo3 = new NodeInfo(4,"vb3",0,1,200,100);
		TreeNode<NodeInfo> node3 = new TreeNode<NodeInfo>(rootInfo3);
		cbLockTree.addNode(1, node3);

		NodeInfo rootInfo1_1 = new NodeInfo(5,"vb1_1",0,1,200,100);
		TreeNode<NodeInfo> node1_1 = new TreeNode<NodeInfo>(rootInfo1_1);
		cbLockTree.addNode(2, node1_1);

		NodeInfo rootInfo1_2 = new NodeInfo(6,"vb1_2",0,1,200,100);
		TreeNode<NodeInfo> node1_2 = new TreeNode<NodeInfo>(rootInfo1_2);
		cbLockTree.addNode(2, node1_2);

		NodeInfo rootInfo2_1 = new NodeInfo(7,"vb2_1",0,1,200,100);
		TreeNode<NodeInfo> node2_1 = new TreeNode<NodeInfo>(rootInfo2_1);
		cbLockTree.addNode(3, node2_1);

		NodeInfo rootInfo2_1_1 = new NodeInfo(8,"vb2_1_1",0,1,200,100);
		TreeNode<NodeInfo> node2_1_1 = new TreeNode<NodeInfo>(rootInfo2_1_1);
		cbLockTree.addNode(7, node2_1_1);

		//打印树
		cbLockTree.listAll(node2_1,0);


	}
}


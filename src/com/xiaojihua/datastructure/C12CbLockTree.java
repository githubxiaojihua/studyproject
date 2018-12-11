package com.xiaojihua.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 一个网页可以看成一个具有树型结构的矩形区域R，根据某种特性R可以被划分成若干不相交的矩形小块，每个小块同样能被继续细分。
 * 请设计一种数据结构CbLockTree，利用它可以描述上面所述的树型结构，树中每个节点可以认为有唯一的ID。要求：
 * 1、树节点基本信息包含：ID,name,position(左上角位置),size（宽度和高度）
 * 2、提供函数以遍历树的方式根据name得到节点（如果多个节点具有相同的名字则返回所有）
 * 3、提供函数将一个节点插入树中指定节点的下面
 */
public class C12CbLockTree {

	private TreeNode<NodeInfo> rootNode;// 定义根节点
	//定义根节点默认值
	private static final long DEFAULT_ID = 1;
	private static final String DEFAULT_NAME = "WEBPAGE";
	private static final float DEFAULT_POSITION_X = 0;
	private static final float DEFAULT_POSITION_Y = 0;
	private static final float DEFAULT_SIZE_HIGHT = 700;
	private static final float DEFAULT_SIZE_WEIGHT = 500;
	//获取node方法的公用容器
	private List<TreeNode<NodeInfo>> nodes = new ArrayList<>();

	/**
	 * 根据默认值初始化树
	 */
	public void init(){
		this.init(DEFAULT_ID,DEFAULT_NAME,DEFAULT_POSITION_X,DEFAULT_POSITION_Y,DEFAULT_SIZE_HIGHT,DEFAULT_SIZE_WEIGHT);
	}

	/**
	 * 根据参数初始化树
	 * @param ID
	 * @param name
	 * @param positionX
	 * @param positionY
	 * @param hight
	 * @param weight
	 */
	public void init(long ID,String name,float positionX,float positionY,float hight,float weight){
		NodeInfo rootInfo = new NodeInfo(ID,name,positionX,positionY,hight,weight);
		rootNode = new TreeNode<NodeInfo>(rootInfo);
	}

	/**
	 * 获取所有具有相同name属性的节点
	 * @param name
	 * @return
	 */
	public List<TreeNode<NodeInfo>> getNodesByName(String name){
		return this.getNodesByName(name,rootNode,nodes);
	}

	/**
	 * 递归获取所有具有相同name属性的节点
	 * @param name
	 * @param node 搜索起始位置
	 * @param nodes 最终结果
	 * @return
	 */
	private List<TreeNode<NodeInfo>> getNodesByName(String name,TreeNode<NodeInfo> node,List<TreeNode<NodeInfo>> nodes){

		// node为null直接返回null
		if(node == null){
			return null;
		}
		// name相等则增加到nodes列表中
		if(node.element.getName().equals(name)){
			nodes.add(node);
		}
		// 遍历子节点
		getNodesByName(name,node.firstChild,nodes);
		// 遍历兄弟节点
		getNodesByName(name,node.nextSibling,nodes);

		return nodes;
	}

	/**
	 * 根据id返回node
	 * @param id
	 * @return
	 */
	public TreeNode<NodeInfo> getNodeByID(long id){
		return this.getNodeByID(id,rootNode);
	}

	/**
	 * 根据id返回对应的node
	 * @param id
	 * @param node 查询起始node
	 * @return
	 */
	private TreeNode<NodeInfo> getNodeByID(long id,TreeNode<NodeInfo> node){

		if(node == null){
			return null;
		}else if(node.element.getID() == id ){
			return node;
		}else{
			TreeNode<NodeInfo> tempNode = getNodeByID(id,node.firstChild);
			if(tempNode == null){
				tempNode = getNodeByID(id,node.nextSibling);
			}
			return tempNode;
		}

	}

	/**
	 * 在指定id下增加node，默认增加到该id的最后一个子节点之后
	 * @param id
	 * @param node
	 * @return
	 */
	public boolean addNode(long id,TreeNode<NodeInfo> node){

		boolean flag = false;
		TreeNode<NodeInfo> parentNode = getNodeByID(id,rootNode);

		if(parentNode == null){
			throw new NoSuchElementException();
		}

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
	 * 从node节点开始按层次打印树（先序遍历）
	 * @param node
	 * @param deepth
	 */
	public void listAll(TreeNode<NodeInfo> node,int deepth){
		if(node != null){
			for(int i=1; i<=deepth; i++){
				System.out.print(" ");
			}
			System.out.println(node.element.getName());
			listAll(node.firstChild,deepth + 1);
			listAll(node.nextSibling,deepth);
		}
	}

	/**
	 * 定义树节点
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	private static class TreeNode<AnyType>{

		AnyType element;//节点本身数据
		TreeNode firstChild;//第一个孩子
		TreeNode nextSibling;//下一个兄弟

		public TreeNode(AnyType theElement){
			this(theElement,null,null);
		}

		public TreeNode(AnyType theElement, TreeNode<AnyType> theFirstChild, TreeNode<AnyType> theNextSibling){
			element = theElement;
			firstChild = theFirstChild;
			nextSibling = theNextSibling;
		}

	}

	public static void main(String[] args) {
		//初始化树
		C12CbLockTree cbLockTree = new C12CbLockTree();
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

		NodeInfo rootInfo1_2 = new NodeInfo(6,"vb1_1",0,1,200,100);
		TreeNode<NodeInfo> node1_2 = new TreeNode<NodeInfo>(rootInfo1_2);
		cbLockTree.addNode(2, node1_2);

		NodeInfo rootInfo2_1 = new NodeInfo(7,"vb2_1",0,1,200,100);
		TreeNode<NodeInfo> node2_1 = new TreeNode<NodeInfo>(rootInfo2_1);
		cbLockTree.addNode(3, node2_1);

		NodeInfo rootInfo2_1_1 = new NodeInfo(8,"vb2_1_1",0,1,200,100);
		TreeNode<NodeInfo> node2_1_1 = new TreeNode<NodeInfo>(rootInfo2_1_1);
		cbLockTree.addNode(7, node2_1_1);

		//打印树
		cbLockTree.listAll(cbLockTree.rootNode,0);
		//获取相同name的节点
		String searName = "vb1_1";
		System.out.println("name:" + searName + "的节点一共有：" + cbLockTree.getNodesByName("vb1_1").size() + "个");


	}

}

/**
 * 节点数据类型
 * @author Administrator
 *
 */
class NodeInfo{

	private long ID;// 唯一id
	private String name;
	private float positionX;// 左上角位置x
	private float positionY;// 左上角位置y
	private float hight;//高度
	private float weight;//宽度

	public NodeInfo(long theId,String theName,float thePositionX,float thePositionY,float theHight,float theWeight){
		ID = theId;
		name = theName;
		positionX = thePositionX;
		positionY = thePositionY;
		hight = theHight;
		weight = theWeight;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public float getHight() {
		return hight;
	}

	public void setHight(float hight) {
		this.hight = hight;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
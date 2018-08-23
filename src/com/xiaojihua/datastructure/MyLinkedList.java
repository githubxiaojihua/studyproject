package com.xiaojihua.datastructure;

import java.util.Iterator;

/**
 * 双联表
 * @param <AnyType>
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

	private int theSize;
	private int modCount = 0;
	private Node<AnyType> beginMaker;
	private Node<AnyType> endMaker;

	/**
	 * 构造方法
	 */
	public MyLinkedList(){
		this.clear();
	}

	/**
	 * 清除
	 */
	public void clear(){
		beginMaker = new Node<AnyType>(null,null,null);
		endMaker = new Node<AnyType>(null,beginMaker,null);
		beginMaker.next = endMaker;
		theSize = 0;
		modCount++;

	}

	/**
	 * 长度
	 * @return
	 */
	public int size(){
		return theSize;
	}

	/**
	 * 在链表末尾增加元素
	 * @param x
	 * @return
	 */
	public boolean add(AnyType x){
		this.add(size(), x);
		return true;
	}

	/**
	 * 在指定index下插入元素
	 * @param index
	 * @param x
	 */
	public void add(int index, AnyType x){
		this.addBefore(this.getNode(index), x);
	}

	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size()>0;
	}

	/**
	 * 在指定NODE前增加元素
	 * @param p
	 * @param x
	 */
	public void addBefore(Node<AnyType> p, AnyType x){
		Node<AnyType> newNode = new Node<AnyType>(x,p.pre,p);
		newNode.pre.next = newNode;
		p.pre = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * 获取指定位置元素
	 * @param index
	 * @return
	 */
	public AnyType get(int index){
		return this.getNode(index).data;
	}

	/**
	 * 设置元素内容
	 * @param index
	 * @param x
	 * @return
	 */
	public AnyType set(int index,AnyType x){
		Node<AnyType> node = this.getNode(index);
		AnyType oldValue = node.data;
		node.data = x;
		return oldValue;
	}

	/**
	 * 删除指定位置的元素
	 * @param index
	 * @return
	 */
	public AnyType remove(int index){
		return this.remove(this.getNode(index));
	}

	/**
	 * 删除一个NODE
	 * @param node
	 * @return
	 */
	private AnyType remove(Node<AnyType> node){
		node.next.pre = node.pre;
		node.pre.next = node.next;
		theSize--;
		modCount++;
		return node.data;
	}
	/**
	 * NODE类(嵌套类)
	 * @author Administrator
	 *
	 * @param <AnyType>
	 */
	private static class Node<AnyType>{

		public Node(AnyType data, Node<AnyType> pre, Node<AnyType> next){
			this.data = data;
			this.pre = pre;
			this.next = next;
		}
		public AnyType data;
		public Node<AnyType> pre;
		public Node<AnyType> next;

	}

	/**
	 * 根据index返回对应位置的NODE
	 * @param index
	 * @return
	 */
	public Node<AnyType> getNode(int index){
		Node<AnyType> p;

		if(index<0 || index>size()){
			throw new IndexOutOfBoundsException();
		}

		if(index < size()/2){
			p = beginMaker;
			for(int i=0; i<index; i++){
				p = p.next;
			}
		}else{
			p = endMaker;
			for(int i=size(); i>index; i--){
				p = p.pre;
			}
		}

		return p;
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * ToStirng
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer("[");
		for(AnyType x : this){
			sb.append(x + " ");
		}
		sb.append("]");
		return new String(sb);
	}

	/**
	 * 内部类
	 * @author Administrator
	 *
	 */
	private class LinkedListIterator implements Iterator<AnyType>{

		private Node<AnyType> current = beginMaker.next;
		//内部存储一份修改次数，用于跟外部的比对，外部每次remove该值均会增加，迭代器内部remove也调用的是外部的remove，因此要保证两个一致，一旦不一致则不允许删除。
		//如果没有这个变量的话，容易造成在调用迭代器期间，调用了外部的remove，改变了正在迭代的链表结构。这容易造成迭代数据的错误。
		private int expectedModCount = modCount;
		//该变量保证，在迭代器remove后必须next()一下后才能再次删除。
		private boolean okToRemove = false;

		/**
		 * 是否存在下一个元素
		 * @return
		 */
		public boolean hasNext(){
			return current != endMaker;
		}

		/**
		 * 获取下一个元素
		 * @return
		 */
		public AnyType next(){
			if(modCount != expectedModCount){
				throw new java.util.ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}

			AnyType nexItem = current.data;
			current = current.next;
			okToRemove = true;
			return nexItem;
		}

		/**
		 * 迭代器的remove
		 */
		public void remove(){
			if(modCount != expectedModCount){
				throw new java.util.ConcurrentModificationException();
			}
			if(!okToRemove){
				throw new IllegalStateException();
			}

			MyLinkedList.this.remove(current.pre);
			okToRemove = false;
			expectedModCount++;

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedList<String> mylinkedList = new MyLinkedList<String>();
		mylinkedList.add("first");
		mylinkedList.add(0,"secoend");
		mylinkedList.add("third");
		mylinkedList.set(2, "fine");
		System.out.println(mylinkedList.size());
		for(String str : mylinkedList){
			System.out.println(str);
		}
		java.util.Iterator<String> itr = mylinkedList.iterator();
		while( itr.hasNext( ) )
		{
			itr.next( );
			itr.remove( );
			System.out.println( mylinkedList );
		}
	}

}

package com.xiaojihua.datastructure;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;

import java.io.File;
import java.util.*;

/**
 * 练习专用类
 *
 */
public class TestDemo<AnyType extends Comparable<? super AnyType>> {

    private Node<AnyType> root;
    private static final int ALLOW_IMBLANCE = 1;

    TestDemo(){
        root= null;
    }

    public void printTree(){
        printTree(root);
    }

    /**
     * 内部方法：打印节点子树
     * @param t
     */
    private void printTree(Node<AnyType> t){

        if(t!=null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }

    }

    public void checkBalance(){
        checkBalance(root);
    }

    private int checkBalance(Node<AnyType> node){
        if(node == null){
            return -1;
        }
        int hl = checkBalance(node.left);
        int hr = checkBalance(node.right);
        if(Math.abs(hl - hr) > ALLOW_IMBLANCE || hl != hight(node.left) || hr != hight(node.right)){
            System.out.println("oops");
        }
        return hight(node);
    }

    public int heightForEpilogue(){
        return heightForEpilogue(root);
    }

    private int heightForEpilogue(Node<AnyType> node){
        if(node == null){
            return -1;
        }

        return Math.max(heightForEpilogue(node.left), heightForEpilogue(node.right)) + 1;
    }



    public void remove(AnyType x){
        root = remove(x, root);
    }

    private Node<AnyType> remove(AnyType x, Node<AnyType> node){
        if(node == null){
            return null;
        }
        int compareResult = x.compareTo(node.element);
        if(compareResult > 0){
            node.right = remove(x, node.right);
        }else if(compareResult < 0 ){
            node.left = remove(x, node.left);
        }else{
            if(node.left != null && node.right != null){
                node.element = findMin(node.right).element;
                node.right = remove(node.element, node.right);
            }else{
                node = node.left == null ? node.right : node.left;
            }
        }

        return balance(node);
    }

    public Node<AnyType> findMin(Node<AnyType> node){
        if(node == null){
            return null;
        }
        if(node.left == null){
            return node;
        }
        return findMin(node.left);
    }

    public void insert(AnyType x){
        root = insert(x, root);
    }

    private Node<AnyType> insert(AnyType x, Node<AnyType> node){
        if(node == null){
            return new Node<>(x);
        }
        int compareResult =x.compareTo(node.element);
        if(compareResult > 0){
            node.right = insert(x, node.right);
        }else if(compareResult <0){
            node.left = insert(x, node.left);
        }else{

        }
        return balance(node);
    }

    private Node<AnyType> balance(Node<AnyType> node){
        if(node == null){
            return null;
        }

        if(hight(node.left) - hight(node.right) > ALLOW_IMBLANCE){
            if(hight(node.left.left) >= hight(node.left.right)){
                node = rotateWithLeftChild(node);
            }else{
                node = doubleWithLeftChild(node);
            }
        }

        if(hight(node.right) - hight(node.left) > ALLOW_IMBLANCE){
            if(hight(node.right.right) >= hight(node.right.left)){
                node =rotateWithRightChild(node);
            }else{
                node = doubleWithRightChild(node);
            }
        }

        node.hight = Math.max(hight(node.left), hight(node.right)) + 1;
        return node;
    }

    /**
     * 单旋转左子树
     * @param k2
     * @return
     */
    private Node<AnyType> rotateWithLeftChild(Node<AnyType> k2){
        Node<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.hight = Math.max(hight(k2.left),hight(k2.right)) + 1;
        k1.hight = Math.max(hight(k1.left), hight(k2)) + 1;
        return k1;
    }

    /**
     * 单旋转右子树
     * @param k2
     * @return
     */
    private Node<AnyType> rotateWithRightChild(Node<AnyType> k2){
        Node<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.hight = Math.max(hight(k2.left), hight(k2.right)) + 1;
        k1.hight = Math.max(hight(k1.right), hight(k2)) + 1;
        return k1;
    }

    /**
     * 双旋转左子树
     * @param k3
     * @return
     */
    private Node<AnyType> doubleWithLeftChild(Node<AnyType> k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 双旋转右子树
     * @param k3
     * @return
     */
    private Node<AnyType> doubleWithRightChild(Node<AnyType> k3){
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private int hight(Node<AnyType> node){
        return node == null ? -1 : node.hight;
    }

    private static class Node<AnyType>{
        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;
        int hight;

        Node(AnyType element){
            this(element, null, null);
        }

        Node(AnyType element, Node<AnyType> left, Node<AnyType> right){
            this.element = element;
            this.left = left;
            this.right = right;
            hight = 0;
        }
    }


    public static void main(String[] args) {
        TestDemo<Integer> avlTree = new TestDemo<>();
        final int NUMS = 4000;
        final int GAP = 37;

        for(int i=GAP; i!=0; i = (i + GAP)%NUMS){
            avlTree.insert(i);
        }

        avlTree.remove(2);
        avlTree.printTree();
        avlTree.checkBalance();
        System.out.println("通过直接获取root节点的高度变量获取树的高度：" + avlTree.hight(avlTree.root));
        System.out.println("通过后序遍历获取整个树的高度：" + avlTree.heightForEpilogue());
    }
}






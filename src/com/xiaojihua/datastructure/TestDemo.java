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

    TestDemo(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(AnyType x){
        root = insert(x, root);
    }

    private Node<AnyType> insert(AnyType x, Node<AnyType> node){
        if(node == null){
            return new Node<>(x);
        }
        int comparResult = x.compareTo(node.element);
        if(comparResult > 0){
            node.right = insert(x, node.right);
        }else if(comparResult < 0){
            node.left = insert(x, node.left);
        }else{

        }
        return node;
    }

    public void remove(AnyType x){
        root = remove(x, root);
    }

    private Node<AnyType> remove(AnyType x, Node<AnyType> node){
        if(node == null){
            return node;
        }
        int comparResult = x.compareTo(node.element);
        if(comparResult > 0){
            node.right = remove(x, node.right);
        }else if(comparResult < 0){
            node.left = remove(x, node.left);
        }else{
            if(node.left != null && node.right != null){
                node.element = findMin(node.right).element;
                node.right = remove(node.element,node.right);
            }else{
                node = node.left == null ? node.right : node.left;
            }
        }
        return node;
    }

    private Node<AnyType> findMin(Node<AnyType> node){
        if(node == null){
            return null;
        }else if(node.left == null){
            return node;
        }else{
            return findMin(node.left);
        }
    }

    private Node<AnyType> findMax(Node<AnyType> node){
        if(node == null){
            return null;
        }
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    private boolean contains(AnyType x, Node<AnyType> node){
        if(node == null){
            return false;
        }
        int comparResult = x.compareTo(node.element);
        if(comparResult > 0 ){
            return contains(x, node.right);
        }else if(comparResult < 0){
            return contains(x, node.left);
        }else{
            return true;
        }

    }


    private static class Node<AnyType>{
        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;

        Node(AnyType element, Node<AnyType> left, Node<AnyType> right){
           this.element = element;
           this.left = left;
           this.right = right;
        }

        Node(AnyType element){
            this(element, null, null);
        }
    }
}






package com.xiaojihua.datastructure;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;
import com.xiaojihua.javabasic.Test;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

/**
 * 练习专用类
 *
 */
public class TestDemo<AnyType extends Comparable<? super AnyType>> {
    private LeftistNode<AnyType> root;

    TestDemo(){
        root = null;
    }

    public void merge(TestDemo<AnyType> rhs){
        if(this == rhs){
           return;
        }
        //root = merge
        rhs.root = null;
    }

    public LeftistNode<AnyType> merge(LeftistNode<AnyType> root1, LeftistNode<AnyType> root2){
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        if(root1.element.compareTo(root2.element) < 0){
            return merge1(root1, root2);
        }else{
            return merge1(root2, root1);
        }
    }

    public LeftistNode<AnyType> merge1(LeftistNode<AnyType> root1, LeftistNode<AnyType> root2){
        if(root1.left == null){
            root1.left = root2;
        }else{
            root1.right = merge(root1.right, root2);
            if(root1.left.npl < root1.right.npl){
                //交换
                swapChild(root1);
            }
            root1.npl = root1.right.npl + 1;
        }

        return root1;
    }

    private static <AnyType> void swapChild(LeftistNode<AnyType> node){
        LeftistNode<AnyType> temp = node.right;
        node.right = node.left;
        node.left = temp;
    }

    private static class LeftistNode<AnyType>{
        AnyType element;
        LeftistNode<AnyType> left;
        LeftistNode<AnyType> right;
        int npl;

        LeftistNode(AnyType element, LeftistNode<AnyType> left, LeftistNode<AnyType> right){
            this.element = element;
            this.left = left;
            this.right = right;
            this.npl = 0;
        }

        LeftistNode(AnyType element){
            this(element,null,null);
        }
    }


}






package com.xiaojihua.datastructure;

public class TestDemo<AnyType extends Comparable<? super AnyType>> {
    private LeftistNode<AnyType> root;
    TestDemo(){ root = null;}

    public void marge(TestDemo<AnyType> rhs){
        if(this == rhs){
            return;
        }
        root = marge(root, rhs.root);
    }

    private LeftistNode<AnyType> marge(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        if(h1.element.compareTo(h2.element) > 0){
            return marge1(h2, h1);
        }else{
            return marge1(h1, h2);
        }
    }

    private LeftistNode<AnyType> marge1(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2){
        if(h1.left == null){
            h1.left = h2;
        }else{
            h1.right = marge(h1.right, h2);
            if(h1.right.npl > h1.left.npl){
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void swapChildren(LeftistNode<AnyType> h1){
        LeftistNode<AnyType> temp= h1.left;
        h1.left = h1.right;
        h1.right = temp;
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
            this(element, null, null);
        }
    }

}

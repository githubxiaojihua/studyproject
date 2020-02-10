package com.xiaojihua.datastructure.leetCode;

import java.math.BigInteger;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 这个算法与C02是同一道题，但是这个时间是50ms但是使用的内存非常小（由于尾递归的原因）
 */
public class C02Test2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
           return getNode(getNums(l1,new BigInteger("0"),0).add(getNums(l2,new BigInteger("0"),0)));
    }

    public static BigInteger getNums(ListNode node, BigInteger nums, int cur){
        if(node == null){
            return nums;
        }
        return getNums(node.next,new BigInteger(node.val + "").multiply(new BigInteger("10").pow(cur)).add(nums),cur + 1);
    }

   /* public static ListNode getNode(BigInteger num){

        System.out.println("数字是："  + num);
        String[] strArr = num.toString().split("");
        ListNode node = null, temp = null;
        for(int i = strArr.length - 1; i>=0; i--){
            if(node == null){
                node = new ListNode(Integer.parseInt(strArr[i]));
                temp = node;
            }else{
                temp.next = new ListNode(Integer.parseInt(strArr[i]));
                temp = temp.next;
            }
        }
        return node;
    }*/

    public static ListNode getNode(BigInteger num){

        System.out.println("数字是："  + num);
        String[] strArr = num.toString().split("");
        ListNode node = null, temp = null;
        for(int i = strArr.length - 1; i>=0; i--){
            if(node == null){
                node = new ListNode(Integer.parseInt(strArr[i]));
                temp = node;
            }else{
                temp.next = new ListNode(Integer.parseInt(strArr[i]));
                temp = temp.next;
            }
        }
        return node;
    }


    public static void main(String[] args){

        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(1);
        ListNode node13 = new ListNode(1);
        ListNode node14 = new ListNode(1);
        ListNode node15 = new ListNode(1);
        ListNode node16 = new ListNode(1);
        ListNode node17 = new ListNode(1);
        ListNode node18 = new ListNode(1);
        ListNode node19 = new ListNode(1);
        ListNode node110 = new ListNode(1);
        node1.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node16;
        node16.next = node17;
        node17.next = node18;
        node18.next = node19;
        node19.next = node110;

        //System.out.println(getNums(node1,new BigInteger("0"),0));


        ListNode node2 = new ListNode(9);
        //ListNode node21 = new ListNode(2);
        //ListNode node22 = new ListNode(1);
        //node2.next = node21;
        //node21.next = node22;


        ListNode node = addTwoNumbers(node1,node2);
        //System.out.println(node.val);

        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }


    }
}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
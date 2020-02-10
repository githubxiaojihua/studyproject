package com.xiaojihua.datastructure.leetCode;

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
 * 这个算法与C02是同一道题，但是这个时间是2ms但是使用的内存较大（由于递归的原因）
 */
public class C03Test3 {
    private static ListNode1 node = null;
    public static ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
            getNode(l1,l2,0,null);
            return node;
    }

    public static ListNode1 getNode(ListNode1 l1, ListNode1 l2,int carrBit,ListNode1 tempNode){
        int result = 0;
        int tmpVal = 0;
        if(l1 == null && l2 == null){
            return carrBit>0?(tempNode.next = new ListNode1(carrBit)):null;
        }else{
            if(l1 != null && l2 != null){
                result = l1.val + l2.val + (carrBit>0?carrBit:0);
            }else if(l1 == null && l2 != null){
                result = l2.val + (carrBit>0?carrBit:0);
            }else{
                result = l1.val + (carrBit>0?carrBit:0);
            }

            if(result >= 10){
                tmpVal = result%10;
                carrBit = result / 10;
            }else{
                tmpVal = result;
                carrBit = 0;
            }
            if(tempNode == null){
                tempNode = new ListNode1(tmpVal);
                node = tempNode;
            }else{
                tempNode.next = new ListNode1(tmpVal);
                tempNode = tempNode.next;
            }
            return getNode(l1==null?null:l1.next,l2==null?null:l2.next,carrBit,tempNode);
        }

    }




    public static void main(String[] args){

        ListNode1 node1 = new ListNode1(1);
        ListNode1 node11 = new ListNode1(1);
        ListNode1 node12 = new ListNode1(1);
        ListNode1 node13 = new ListNode1(1);
        ListNode1 node14 = new ListNode1(1);
        ListNode1 node15 = new ListNode1(1);
        ListNode1 node16 = new ListNode1(1);
        ListNode1 node17 = new ListNode1(1);
        ListNode1 node18 = new ListNode1(1);
        ListNode1 node19 = new ListNode1(1);
        ListNode1 node110 = new ListNode1(1);
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


        ListNode1 node2 = new ListNode1(9);
        //ListNode node21 = new ListNode(2);
        //ListNode node22 = new ListNode(1);
        //node2.next = node21;
        //node21.next = node22;


        ListNode1 node = addTwoNumbers(node1,node2);
        //System.out.println(node.val);

        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }


    }
}


class ListNode1 {
     int val;
     ListNode1 next;
     ListNode1(int x) { val = x; }
 }
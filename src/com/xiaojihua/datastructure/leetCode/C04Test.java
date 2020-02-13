package com.xiaojihua.datastructure.leetCode;

import java.util.*;


/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C04Test {
    public static void main(String[] args){
       C04Test t = new C04Test();
        System.out.println(t.lengthOfLongestSubstring("dvdf"));

    }

    /**
     * 1、遍历字符串的每个字符
     * 2、判断是否在subStrMap中存在
     *    2.1 不存在则put进去，且tmp+1
     *    2.2 存在获取响应的tmp
     *        2.2.1 找到字符在subStrMap中的索引
     *        2.2.2 将索引更新为当前与其冲突的索引
     *        2.2.3 确定出现重复后包含当前字符的最大连续字符串的开始位置
     *              这个位置通常是，冲突字符的下一个位置。
     *              但是有特殊情况就是可能这个位置开始是不连续的，因为可能
     *              以前存在过其他冲突的字符，因此需要确定continueIndex
     *              的位置（当第一次出现冲突的时候continueIndex=Index,
     *              后来出现冲突的时候就要看谁大，大的作为continueIndex
     *              的新值，然后通过i-continueIndex获得出现冲突后的最大连续
     *              字符串的长度。
     *        不论是否出现冲突，都比较tmp和最终longest的值，取大值为最终结果
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int longest = 0, tmp = 0, continueIndex = 0;
        //用来盛放已经遍历的字符串，并且勇于判断是否有重复字符
        Map<Character,Integer> subStrMap = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if(!subStrMap.containsKey(c)){
                subStrMap.put(c,i);
                tmp = tmp + 1;
            }else{
                int index = subStrMap.get(c);
                subStrMap.put(c,i);
                if(index > continueIndex){
                    continueIndex = index;
                }
                tmp = i - continueIndex;
            }
            if(tmp > longest){
                longest = tmp;
            }
        }
        return longest;
    }
}

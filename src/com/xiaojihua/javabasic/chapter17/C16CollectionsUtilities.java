package com.xiaojihua.javabasic.chapter17;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、Collections类的一些工具方法的使用
 */
public class C16CollectionsUtilities {
    public static List<String> list =
            Arrays.asList("one Two three Four five six one".split(" "));
    public static void main(String[] args){
        print(list);
        //disjoint返回true则标识两个集合没有相同的元素。
        //singletonList返回一个不可修改的只包含一个元素的集合
        print("'list' disjoint 'four'?:" +
                Collections.disjoint(list,Collections.singletonList("Four")));

        //返回集合当中的最大和最小元素，依据的是元素的comparable接口实现
        print("max:" + Collections.max(list));
        print("min:" + Collections.min(list));

        //根据跟定的Comparator返回list的最大和最小元素
        print("max w/ comparator:" + Collections.max(list,String.CASE_INSENSITIVE_ORDER));
        print("min w/ comparator:" + Collections.min(list,String.CASE_INSENSITIVE_ORDER));

        //返回子集合在目标集合中的位置（正向和反向）
        List<String> subList = Arrays.asList("Four five six".split(" "));
        print("index of subList：" + Collections.indexOfSubList(list,subList));
        print("last index of subList:" + Collections.lastIndexOfSubList(list,subList));

        //替换集合中所有的指定元素为新元素
        Collections.replaceAll(list,"one","Yo");
        print("after replaceALL one to Yo:" + list);

        //翻转集合元素
        Collections.reverse(list);
        print("after Collections.reverse:" + list);

        //将集合中的元素向后移动执行的位数，末尾移动到开头
        Collections.rotate(list,3);
        print("rotate 3:" + list);

        List<String> source = Arrays.asList("in the matrix".split(" "));
        //将source中的元素按照index的位置copy到list中，list中的原有元素被覆盖
        Collections.copy(list,source);
        print("copy:" + list);

        //交换元素位置
        Collections.swap(list,0,list.size() - 1);
        print("swap:" + list);

        //随机排序集合
        Collections.shuffle(list,new Random(47));
        print("shuffle:" + list);

        //用一个指定的元素来填充list
        Collections.fill(list,"pop");
        print("fill:" + list);
        //返回指定元素在集合中的出现频率
        print("frequency of 'pop':" + Collections.frequency(list,"pop"));

        //返回一个新的list，不可修改，并且含有指定个数的固定元素
        List<String> dups = Collections.nCopies(3,"snap");
        print("dups:" + dups);
        print("list disjoint dups:" + Collections.disjoint(list,dups));

        /*
            Enumeration是java早期的遗留类，他类似于现在的iterator，主要方法是
            hasMoreElements和addElements两个方法，可以通过
            Collections.enumeration()方法来获取。

            Vector也是java早起遗留的类，可以当作arrayList来使用，但是ArrayList
            更好，效率更高。其次他内部都是synchronized实现的因此效率可能较低。
         */
        Enumeration<String> e = Collections.enumeration(dups);
        Vector<String> vector = new Vector<>();
        while(e.hasMoreElements()){
            vector.addElement(e.nextElement());
        }
        ArrayList<String> arrayList = Collections.list(vector.elements());
        print("arrayList:" + arrayList);

    }
}

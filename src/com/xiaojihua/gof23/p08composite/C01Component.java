package com.xiaojihua.gof23.p08composite;

/**
 * 组合模式核心结构实例：
 * 抽象组件（component）：定义了叶子和容器构件的共同点
 * 叶子(Leaf)：无子节点
 * 容器（Composite):有容器特征，可以包含子节点 。
 */

/**
 * 抽象组件
 */
public interface C01Component {
    void operation();
}

/**
 * 叶子
 */
interface Leaf extends C01Component{
}

/**
 * 容器组件
 * 容器组件通常包含一个children的list然后再其operation中进行遍历递归操作
 */
interface Composite extends C01Component{
    void add(C01Component c);
    void remove(C01Component c);
    C01Component getChildren(int index);
}

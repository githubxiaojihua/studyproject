package com.xiaojihua.datastructure;

/**
 * 不相交集类find/union操作
 * 不相交集类，是解决等价问题的一种有效数据结构。
 * 这种数据结构的主要目的是找到某个元素属于哪个等价类，以及合并两个等价类为一个等价类。
 * 由于不需要关心和比较元素的值，只需要表示元素的位置以及所属的等价类，因此可以
 * 用树来表示每一个集合，因为树上的每一个元素都有一个相同的根。初始状态下每个集合含有一个元素，也就是根元素。
 * 由于只关心元素的位置以及等价类，可以用数组表示树。
 *
 * 不相交集类的应用是画迷宫
 * 具体的应用类是Maze.java
 */
public class DisjSets {

    private int[] a;//用于存储树，数组的每个成员a[i]表示元素i的父亲。如果i是根则s[i] = -1

    /**
     * 初始化
     * 数组每个元素初始化为-1代表，各集合是相互独立的。
     * @param numElements
     */
    public DisjSets(int numElements){
        a = new int[numElements];
        for(int i = 0; i < numElements; i++){
            a[i] = -1;
        }
    }

    /**
     * 按高度求并（还有按大小求并，这里没有实现）
     * 每个根存储的高度为实际高度-1，因为初始值为-1.
     * 当合并两个等价类的时候，将高度小的树合并到高度大的树，即改变较小树的跟的父链接为大树的根。
     * 然后大树的根所存储的高度-1。
     *
     * 其实这里没有进行检查：只有属于不同等价类的两个元素才能合并。可通过find查找后进行比较
     * @param root1
     * @param root2
     */
    public void union(int root1, int root2){
        //root2比root1高，那么将root1的父链指向root2。这里要注意是谁越小代表越高
        if(a[root1] > a[root2]){
            a[root1] = root2;
        }else{
            //当合并两个高度相等的等价类时，以root1作为根，并且将合并后的树的高度增加1
            if(a[root1] == a[root2]){
                a[root1]--;
            }
            a[root2] = root1;
        }
    }

    /**
     * 路径压缩的find方法
     * 返回索引x所在的等价类，同时将从根到x的各个元素的父连指向根，实现路径压缩
     * @param x
     * @return
     */
    public int find(int x){
        if(a[x]< 0){
            return x;
        }
        return a[x] = find(a[x]);
    }

    public static void main( String [ ] args )
    {
        int NumElements = 128;
        int NumInSameSet = 16;

        DisjSets ds = new DisjSets( NumElements );
        int set1, set2;

        //对等价类进行合并，最终合并成了8个等价类，分别是0,16,32,48,64,80,96,112
        for( int k = 1; k < NumInSameSet; k *= 2 )
        {
            for( int j = 0; j + k < NumElements; j += 2 * k )
            {
                set1 = ds.find( j );
                set2 = ds.find( j + k );
                ds.union( set1, set2 );
            }
        }

        for( int i = 0; i < NumElements; i++ )
        {
            System.out.print( ds.find( i )+ "*" );
            if( i % NumInSameSet == NumInSameSet - 1 )
                System.out.println( );
        }
        System.out.println( );
    }

}

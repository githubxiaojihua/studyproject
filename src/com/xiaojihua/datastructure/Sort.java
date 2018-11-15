package com.xiaojihua.datastructure;

import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 各类排序
 * 定理：
 * 1、通过交换相邻元素进行排序的任何算法平均都需要Ω(N²)时间
 * 2、对于N个互异项的随机排列进行堆排序所用的比较的平均次数为2NlogN-O(NlogN)
 */
public class Sort {

    private static final int CUTOFF = 10;

    /**
     * 插入排序 O(N^2)
     * 插入排序由N-1趟排序完成，
     * 对于i=1到N-1趟，插入排序保证从位置0到i的元素为已排序状态。
     * 策略：
     * 在第i趟排序中，我们将位置i上的元素向左移动，直到在前i+1个元素中的正确位置被找到。
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a){
        int j;
        for(int i = 1; i < a.length; i++){
            AnyType tmp = a[i];//存储带排序元素
            //在前i+1个元素中找到合适的位置，大于此元素的值都后移，给这个元素空出位置
            for(j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--){
                a[j] = a[j - 1];
            }
            a[j] = tmp;//在合适的位置赋值
        }

    }

    /**
     * 插入排序，从某个位置开始，到某个位置结束
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right){
        for(int i = left + 1; i <= right; i++){
            int j;
            AnyType tmp = a[i];
            for(j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--){
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 冒泡排序O(N^2)
     * 一趟排序将最大值放到数组的最后一个元素（通过位置交换）
     * 经过n-1趟排序即可完成排序
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void bubbingSort(AnyType[] a){
        for(int i = 0; i < a.length; i++){//共需要n-1趟排序
            //每一趟排序都将其中最大值后移（通过位置交换）
            for(int j = 0; j < a.length - 1 - i; j++){
                if(a[j].compareTo(a[j+1]) > 0){
                    AnyType tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 希尔排序O(N^2)
     * 希尔排序使用一个增量序列来排序，h1,h2,h3,...ht，只要h1等于1任何增量排序都可以。
     * h_k代表增量（即上面的h1,h2,h3....ht），ht代表增量序列的最大值。
     * 当使用增量h_k进行一趟排序后，保证对于每一个i(0-N-1)，都有a[i]<=a[i+h_k]，所有相隔h_k的元素都被排序。
     *
     * 具体做法是：
     * 对于增量h_k的一趟排序，对于h_k,h_k+1,h_k+2...,N-1中的每一个位置i，把其上的元素放到，i,i-h_k,i-2h_k...,h_k中的合适位置中。
     * 实际上就是各个相同间隔数组进行插入排序
     *
     * 这里采用的ht为N/2，h_k为h_k+1/2
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType [] a){
        int j;
        for(int gap = a.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < a.length; i++){
                AnyType tmp = a[i];
                for(j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0 ; j -= gap){
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    /**
     * 堆排序方法一：二叉堆的下滤方法的修改版，用于堆排序
     * @param a 待排序的数组
     * @param hole 需要下滤的元素，通常是从最后一个非根节点前移
     * @param n 代表当前堆的元素数量
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void preDown(AnyType [] a, int hole, int n){
        int child;
        AnyType tmp;
        for(tmp = a[hole]; (child = leftChile(hole)) < n; hole = child){
            if(child + 1 < n && a[child].compareTo(a[child + 1]) < 0){
                child++;
            }
            if(a[hole].compareTo(a[child]) < 0){
                a[hole] = a[child];
            }else{
                break;
            }
        }
        a[hole] = tmp;
    }

    /**
     * 对排序方法二：计算指定位置的左儿子
     * @param i
     * @return
     */
    public static int leftChile(int i){
        return 2 * i + 1;
    }

    /**
     * 堆排序O(NlogN)
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a){
        //建立二叉堆
        for(int i = a.length / 2 - 1; i >= 0; i--){
            preDown(a, i, a.length);
        }
        //通过交换根节点和最后一个节点来实现删除，并且每次删除都将堆的长度缩小1，
        //位于堆最后的单元就是刚刚删除的元素
        for(int i = a.length - 1; i > 0; i--){
            swapReferences(a, 0, i);//删除根元素
            preDown(a, 0, i);
        }
    }

    /**
     * 归并排序，入口方法
     * 最坏O(NlogN)
     * @param a 待排序的数组
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a){
        AnyType[] tmpArr = (AnyType[])new Comparable[a.length];
        mergeSort(a, tmpArr, 0, a.length - 1);
    }

    /**
     * 归并排序，递归排序
     * 递归的对数组a进行归并排序
     * @param a
     * @param temp
     * @param left
     * @param right
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] temp, int left, int right){
        if(left < right){
            int center = (left + right) / 2;
            mergeSort(a, temp, left, center);
            mergeSort(a, temp, center + 1, right);
            merge(a, temp, left, center + 1, right);//对左右已经排序的数组进行合并，并排序。
        }
    }

    /**
     * 归并排序，合并两个已排序的数组，并且排序（通过一趟排序），
     * 策略：
     * 数组A,B,C。A,B为已排序的数组（排序规则一致），C为两个数组容量之和并且是空的。
     * 分别有三个指针指向，ABC的起始位置0。
     * 比较A,B当前指针位置位置，取较小者放入C的指针位置，然后C指针后移，同时A,B中较小者指针后移，
     * 重复上一步，直到AB有一个为空，然后将不为空的数组剩下的元素依次拷贝到C，完成排序。
     *
     * 在这里是将数组a，拆分成左右两个数组，按照如上思路进行排序并且放入temp，然后将temp相应位置的元素
     * 拷贝回数组a，最终完成对数组a的排序。
     * 这里前提是左右两个数组均已是排序状态。
     *
     * 配合着调用本方法的递归，最开始比较的是两个由独立元素组成的两个数组。
     * @param a 待排序的数组
     * @param temp 盛放已排序元素的临时数组
     * @param leftPos 左边数组的起始位置
     * @param rightPos 左右数组的分割位置
     * @param rightEnd 右边数组的结束位置
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] temp, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if(a[leftPos].compareTo(a[rightPos]) <= 0){
                temp[tmpPos++] = a[leftPos++];
            }else{
                temp[tmpPos++] = a[rightPos++];
            }
        }

        while(leftPos <= leftEnd){
            temp[tmpPos++] = a[leftPos++];
        }

        while(rightPos <= rightEnd){
            temp[tmpPos++] = a[rightPos++];
        }

        for(int i = 0; i < numElements; i++, rightEnd--){
            a[rightEnd] = temp[rightEnd];
        }
    }

    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * 简单的快速排序
     * 策略：
     * 1、随便选取一项，然后分成三组，小于备选项的一组，等于备选项的一组，大于备选项的一组。
     * 2、递归的对第一和第三组进行排序，然后讲三组接龙。
     * 根据递归的基本原理，结果保证是对原始列表的一个有序排列。
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void simpleQuitSort(List<AnyType> a){
        if(a.size() > 1){
            List<AnyType> smaller = new ArrayList<>();
            List<AnyType> same = new ArrayList<>();
            List<AnyType> larger = new ArrayList<>();

            AnyType center = a.get(a.size() / 2);
            for(AnyType item : a){
                if(item.compareTo(center) < 0){
                    smaller.add(item);
                }else if(item.compareTo(center) > 0){
                    larger.add(item);
                }else{
                    same.add(item);
                }
            }

            simpleQuitSort(smaller);
            simpleQuitSort(same);
            simpleQuitSort(larger);

            a.clear();
            a.addAll(smaller);
            a.addAll(same);
            a.addAll(larger);
        }
    }

    /**
     * 快速排序 --入口方法
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a){
        quickSort(a, 0, a.length -1);
    }

    /**
     * 快速排序-- ，采用三数中值法确定，比较的中心点（枢轴）
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    public static <AnyType extends Comparable<? super AnyType>> AnyType media3(AnyType[] a, int left, int right){
        int center = (left + right) / 2;
        if(a[left].compareTo(a[center]) > 0){
            swapReferences(a, left, center);
        }
        if(a[left].compareTo(a[right]) > 0 ){
            swapReferences(a, left, right);
        }
        if(a[center].compareTo(a[right]) > 0){
            swapReferences(a, center, right);
        }
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 快速排序---主方法
     * 策略：
     * 1、当数组元素个数N大于等于10的时候采用插入排序，否则采用递归的快速排序
     * 2、确定比对枢轴，i,j分别定位于第一个元素和倒数第二个元素，当遇到≥枢轴的值时，i,j分别停止。当i<j将i,j进行交换，再比较
     * 3、当i≥j的时候停止循环。
     * 4、将枢轴跟i进行交换。
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right){
        if(left + CUTOFF <= right){
            AnyType pivot = media3(a, left, right);
            int i = left, j = right - 1;
            for(; ;){
                while(a[++i].compareTo(pivot) < 0){}
                while(a[--j].compareTo(pivot) < 0){}
                if(i < j){
                    swapReferences(a, i, j);
                }else{
                    break;
                }
            }
            swapReferences(a, i, right - 1);
            quickSort(a, left, i + 1);
            quickSort(a, i + 1, right);
        }else{
            insertionSort(a, left, right);
        }

    }

    public static void main(String[] args) {
        Integer[] a = {34,8,64,51,32,21};
        quickSort(a);
        System.out.println(Arrays.toString(a));

        List<Integer> myList = new ArrayList<>();
        myList.add(34);
        myList.add(8);
        myList.add(64);
        myList.add(51);
        myList.add(32);
        myList.add(21);
        simpleQuitSort(myList);
        System.out.println(myList);
    }
}

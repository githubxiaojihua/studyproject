package com.xiaojihua.javabasic.java8test;

import java.util.*;
import java.util.stream.Collectors;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、java8中stream的使用。
 * stream提供了一种简单处理元素集合的方式。这种风格将要处理的元素看作是一种流，
 * 流在管道中传输，并且可以在管道的节点上进行处理，比如筛选、排序、聚合等
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等
 * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等
 */
public class C02Stream {
    public static void main(String[] args){

        print("使用java7：");

        List<String> strings = Arrays.asList("abc","","bc","efg","abcd","","jkl");
        print("列表：" + strings);

        long count = getCountEmptyStringUsingJava7(strings);
        print("空字符串的数量为：" + count);

        count = getCountLength3UsingJava7(strings);
        print("字符串的长度为3的数量为：" + count);

        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        print("删选后的列表为：" + filtered);

        String mergedString = getMergedStringUsingJava7(strings,", ");
        print("合并后的字符串：" + mergedString);

        List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);
        print("numbers列表：" + numbers);
        List<Integer> squaresList = getSquares(numbers);
        print("平方数列表：" + squaresList);

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        print("列表：" + integers);
        print("列表中最大的数 : " + getMax(integers));
        print("列表中最小的数 : " + getMin(integers));
        print("所有数之和 : " + getSum(integers));
        print("平均数 : " + getAverage(integers));
        print("随机数: ");

        Random random = new Random();
        for(int i=0; i<10; i++){
            print(random.nextInt());
        }

        print("============使用java8=========================================");

        print("列表：" + strings);

        count = strings.stream().filter(string -> string.isEmpty()).count();
        print("空字符串的数量为：" + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        print("字符串长度为3的数量为：" + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        print("筛选后的列表：" + filtered);

        mergedString = strings.stream().filter(string -> string.isEmpty()).collect(Collectors.joining(", "));
        print("合并后的字符串：" + mergedString);

        squaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        print("SquaredList:" + squaresList);

        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();

        print("列表中最大的值：" + stats.getMax());
        print("列表中最小的值：" + stats.getMin());
        print("所有数之和：" + stats.getSum());
        print("平均数：" + stats.getAverage());

        print("随机数：");

        random.ints().limit(10).sorted().forEach(System.out::println);

        //并行流
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        print("空字符串的数量：" + count);


    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;
        for(String str : strings){
            if(str.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;
        for(String str : strings){
            if(str.length()==3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> result = new ArrayList<>();
        for(String str : strings){
            if(!str.isEmpty()){
                result.add(str);
            }
        }
        return result;
    }

    private static String getMergedStringUsingJava7(List<String> strings,String separator){
        StringBuilder strB = new StringBuilder();
        for(String str : strings){
            if(!str.isEmpty()){
                strB.append(str).append(separator);
            }
        }
        return strB.toString();
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squares = new ArrayList<>();
        for(Integer i : numbers){
            int square = new Integer(i.intValue() * i.intValue());
            if(!squares.contains(square)){
                squares.add(square);
            }
        }
        return squares;
    }

    private static int getMax(List<Integer> integers){
        int max = integers.get(0).intValue();
        for(Integer i : integers){
            if(max < i.intValue()){
                max = i.intValue();
            }
        }

        return max;
    }

    private static int getMin(List<Integer> integers){
        int min = integers.get(0).intValue();
        for(Integer i : integers){
            if(min > i.intValue()){
                min = i.intValue();
            }
        }

        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}

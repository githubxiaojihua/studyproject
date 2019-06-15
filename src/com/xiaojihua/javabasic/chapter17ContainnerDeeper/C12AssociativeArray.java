package com.xiaojihua.javabasic.chapter17ContainnerDeeper;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、简单实现Map。
 * @param <K>
 * @param <V>
 */
public class C12AssociativeArray<K,V> {
    private Object[][] array;
    private int index = 0;

    public C12AssociativeArray(int size){
        //初始化数组可以将第二维度空着，也可以使用变量初始化第一维度
        array = new Object[size][2];
    }

    public void put(K key, V value){
        if(index >= array.length){
            throw new IndexOutOfBoundsException();
        }
        array[index++] = new Object[]{key,value};
    }

    public V get(K key){
        for(int i=0; i<index; i++){
            if(key.equals(array[i][0])){
                return (V) array[i][1];
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuffer strBuffer = new StringBuffer();
        for(int i=0; i<index; i++){
            strBuffer.append(array[i][0].toString());//能显示转换的就显示转换
            strBuffer.append(":");
            strBuffer.append(array[i][1].toString());
            strBuffer.append("\n");
        }
        return strBuffer.toString();
    }

    public static void main(String[] args){
        C12AssociativeArray<String,String> map = new C12AssociativeArray<>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");

        try {
            map.put("extra", "object");
        } catch(IndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));
    }

}

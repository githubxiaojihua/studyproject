package com.xiaojihua.javabasic.chapter14;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 结合泛型使用的RTTI
 */
class FillData{
    public static long count;
    public long id = count++;
    public String toString(){
        return Long.toString(id);
    }
}

public class  C04FilledList <T> {
    Class<T> type;
    C04FilledList(Class<T> type){
        this.type = type;
    }
    public List<T> create(int eleNum){
        List<T> list = new ArrayList<>();
        for(int i=0; i<eleNum; i++){
            try {
                list.add(type.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public static void main(String[] args){
        C04FilledList<FillData> fd = new C04FilledList<>(FillData.class);
        System.out.println(fd.create(13));
    }
}

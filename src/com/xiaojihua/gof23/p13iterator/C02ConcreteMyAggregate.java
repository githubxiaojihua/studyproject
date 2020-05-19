package com.xiaojihua.gof23.p13iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义聚合类
 */
public class C02ConcreteMyAggregate {
    private List<Object> list = new ArrayList<>();

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void addObje(Object obj){
        list.add(obj);
    }

    public void removeObje(Object obj){
        list.remove(obj);
    }

    /**
     * 返回迭代器对象
     * @return
     */
    public C01MyIterator createMyIterator(){
        return new ConcreteIterator();
    }


    /**
     * 内部类实现迭代器，可访问外部类数据
     */
    private class ConcreteIterator implements C01MyIterator{
        private int cusor;

        @Override
        public void first() {
            cusor = 0;
        }

        @Override
        public void next() {
            if(cusor < list.size()){
                cusor++;
            }
        }

        @Override
        public boolean hasNext() {
            return cusor < list.size();
        }

        @Override
        public boolean isFirst() {
            return cusor==0?true:false;
        }

        @Override
        public boolean isLast() {
            return cusor==(list.size())?true:false;
        }

        @Override
        public Object getCurrentObj() {
            return list.get(cusor);
        }
    }
}

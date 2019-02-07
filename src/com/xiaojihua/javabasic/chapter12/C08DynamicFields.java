package com.xiaojihua.javabasic.chapter12;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 知识点：
 * 1、异常链，当捕捉了一个异常抛出了另一个异常，但是想保留原来异常的信息，可以在抛出新异常
 * 的时候指定异常的cause。
 * 2、指定cause有两种方式，其中Error,Exception,RuntimeException都提供了构造函数来
 * 接收case。其他的异常需要调用initCause来设置。
 * 3、此类的方法设置比较好，可以参考。做到了代码重用。
 */
public class C08DynamicFields {
    //定义二维数组存储键值对，二维数组0代表key，String类型，二维数组1代表value,任何类型
    private Object[][] fields;

    /**
     * 初始化二维数组
     * @param initalSize
     */
    public C08DynamicFields(int initalSize){
        fields = new Object[initalSize][2];
        for(int i=0; i < initalSize; i++){
            fields[i] = new Object[]{null,null};
        }
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Object[] obj : fields){
            builder.append(obj[0]);
            builder.append(":");
            builder.append(obj[1]);
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * 判断数组中是否存在指定的ID
     * @param id
     * @return
     */
    private int hasField(String id){
        for(int i=0; i < fields.length; i++){
            if(id.equals(fields[i][0])){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取指定id所在的一维数组的位置，如果不存在的抛出错误
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    private int getFieldNumer(String id) throws NoSuchFieldException{
        int fieldNumber = hasField(id);
        if(fieldNumber == -1){
            throw new NoSuchFieldException();
        }
        return fieldNumber;
    }

    /**
     * 将指定id存储到最新的位置，如果没有空余位置则扩展一个，
     * 最终返回位置下标。
     * @param id
     * @return
     */
    private int makeField(String id){
        for(int i=0; i < fields.length; i++){
            if(fields[i][0] == null){
                fields[i][0] = id;
                return i;
            }
        }

        Object[][] tmp = new Object[fields.length + 1][2];
        for(int i=0; i<tmp.length; i++){
            if(i < fields.length){
                tmp[i] = fields[i];
            }else{
                tmp[i] = new Object[]{null, null};
            }
        }
        fields = tmp;
        return makeField(id);
    }

    /**
     * 返回指定id对应的value
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    public Object getField(String id) throws NoSuchFieldException {
        return fields[getFieldNumer(id)][1];
    }

    /**
     * 设置字段与值
     * @param id
     * @param value
     * @return
     * @throws DynamicException
     */
    public Object setField(String id, Object value) throws DynamicException{
        if(value == null){
            //通过指定异常的cause来指定原始异常，通常用于抛出新异常的同时，保留原始异常的信息
            //通过initCause来实现
            DynamicException dy = new DynamicException();
            dy.initCause(new NullPointerException());
            throw dy;
        }
        int fieldNumber = hasField(id);
        if(fieldNumber == -1){
            fieldNumber = makeField(id);
        }
        Object resule = null;
        try{
            resule = getField(id);
        }catch(NoSuchFieldException e){
            //通过指定异常的cause来指定原始异常，通常用于抛出新异常的同时，保留原始异常的信息
            //通过使用构造函数来实现
            throw new RuntimeException(e);
        }

        fields[fieldNumber][1] = value;
        return resule;
    }

    public static void main(String[] args) {
        C08DynamicFields dynamicFields = new C08DynamicFields(3);
        System.out.println(dynamicFields);
        System.out.println("==================================");
        try{
            dynamicFields.setField("d","A value for d");
            dynamicFields.setField("number",47);
            dynamicFields.setField("number2",48);
            System.out.println(dynamicFields);
            System.out.println("==================================");
            dynamicFields.setField("id","A new value for d");
            dynamicFields.setField("number3",11);
            System.out.println("df:" + dynamicFields);
            System.out.println("print dy.getField(d):" + dynamicFields.getField("d"));
            Object field = dynamicFields.setField("d", null);
        }catch(NoSuchFieldException e){
            e.printStackTrace(System.out);
        }catch(DynamicException e){
            e.printStackTrace(System.out);
        }
    }

}

class DynamicException extends Exception{}

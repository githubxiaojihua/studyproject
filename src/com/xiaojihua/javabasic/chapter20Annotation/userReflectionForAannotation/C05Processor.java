package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * annotaion处理器使用annotation来生成外部文件（这里以输出创建表格的SQL语句）
 *
 * 本例是，通过在javabean中使用注解，来生成此javabean的sql创建语句。
 *
 * 本例是使用反射机制来读取annotation和相关字段的。
 */
public class C05Processor {
    public static void main(String[] args) throws Exception{

        //获取C05Member类的Class对象，将生成此javabean的SQL语句
        Class<?> clazz = Class.forName("com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation.C05Member");
        //获取annotion对象
        C01DBTable dbTable = clazz.getAnnotation(C01DBTable.class);
        if(dbTable == null){
            System.out.println("No DbTable annotion in class");
            return;
        }
        //根据annotion获取设置的表名，若没有设置则用类名
        String tableName = dbTable.name();
        if(tableName.length() < 1){
            tableName = clazz.getName().toUpperCase();
        }
        //用于存储字段创建语句。
        List<String> declaredMethods = new ArrayList<>();
        //通过反射来读取字段
        for(Field f : clazz.getDeclaredFields()){

            //f.setAccessible(true);
            //获取声明的annotation数组。因为不知道具体的annotion
            Annotation[] anns = f.getDeclaredAnnotations();
            //如果写=0有点窄，写<1比较好一点
            if(anns.length < 1){
                continue;
            }

            StringBuilder strBuilder = new StringBuilder();

            //如果annotion是C03SqlString
            if(anns[0] instanceof C03SqlString){
                C03SqlString ssAnn = (C03SqlString) anns[0];
                String fieldName = ssAnn.name();
                if(fieldName == null || fieldName.equals("")){
                    fieldName = f.getName().toUpperCase();
                }
                strBuilder.append(fieldName + "\t varchar(" + ssAnn.value() + ") ");
                strBuilder.append(deailConstrains(ssAnn.constraints()));
                declaredMethods.add(strBuilder.toString());
            }

            //如果annotion是C04SInt
            if(anns[0] instanceof C04SqlInt){
                C04SqlInt sqlInt = (C04SqlInt) anns[0];
                String fieldName = sqlInt.name();
                if(fieldName == null || fieldName.equals("")){
                    fieldName = f.getName().toUpperCase();
                }
                strBuilder.append(fieldName + "\t int ");
                strBuilder.append(deailConstrains(sqlInt.constraints()));
                declaredMethods.add(strBuilder.toString());
            }
        }

        StringBuilder sqlString = new StringBuilder();
        sqlString.append("CREATE TABLE ").append(tableName).append("(\n");
        for(String s : declaredMethods){
            sqlString.append("\t").append(s).append(",").append("\n");
        }
        sqlString.append(");");

        System.out.println(sqlString);


    }

    /**
     * 处理每个字段的contraints，有可能是primary key , unique, not null等
     * @param constraints
     * @return
     */
    public static String deailConstrains(C02Constraints constraints){
        StringBuilder strBuilder = new StringBuilder();
        if(constraints.isPrimary()){
            strBuilder.append(" primary key ");
        }

        if(!constraints.nullAble()){
            strBuilder.append(" not null ");
        }

        if(constraints.unique()){
            strBuilder.append(" unique ");
        }
        return strBuilder.toString();
    }
}

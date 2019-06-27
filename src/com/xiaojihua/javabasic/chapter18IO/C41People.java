package com.xiaojihua.javabasic.chapter18IO;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * 知识点：
 * 使用XOM从XML中构建对象
 * 配合C40
 */
public class C41People extends ArrayList<C40Person> {
    public C41People(String filePath) throws Exception{
        //build文档，使用参数为inputstream的build方法，还有一个String的方法，但是对于window目录会报错
        Document doc = new Builder().build(
                new BufferedInputStream(new FileInputStream(filePath)));
        Element root = doc.getRootElement();
        Elements elements = root.getChildElements();
        for(Element element : elements){
            add(new C40Person(element));
        }
    }

    public static void main(String[] args) throws Exception{
        C41People people = new C41People("H:\\person.xml");
        System.out.println(people);
    }
}

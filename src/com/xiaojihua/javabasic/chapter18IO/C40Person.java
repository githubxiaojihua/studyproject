package com.xiaojihua.javabasic.chapter18IO;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * java序列化有一定的局限性：只能在java体系中进行序列化和反序列化，
 * XML作为通用格式，可以到多个语言和平台中进行传输，java对XML进行了
 * 支持javax.xml.*。本例使用了一个外部类库XOM。
 *
 * 使用XOM类库，将对象写入到XML中。
 * XOM类库是通过maven进行依赖导入的
 *
 * 读取并重建看C41
 */
public class C40Person {
    private String firstName, lastName;

    public C40Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 将person转化成Element
     * @return
     */
    public Element toXml(){
        Element person = new Element("person");
        Element firstName = new Element("firstName");
        firstName.appendChild(this.firstName);
        Element lastName = new Element("lastName");
        lastName.appendChild(this.lastName);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }

    /**
     * 根据Element构造person
     * @param person
     */
    public C40Person(Element person){
        firstName = person.getFirstChildElement("firstName").getValue();
        lastName = person.getFirstChildElement("lastName").getValue();
    }

    public String toString(){ return firstName + " " + lastName; }

    /**
     * 将形成的XML文档导出到指定输出流
     * @param out
     * @param doc
     * @throws Exception
     */
    public static void formatStr(OutputStream out, Document doc) throws Exception{
        //Serializer类用于将XML文档美化成可阅读的格式，
        Serializer serializer = new Serializer(out,"UTF-8");
        serializer.setIndent(4);//缩进
        serializer.setMaxLength(60);//最大长度
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception{
        List<C40Person> personList = new ArrayList<>();
        personList.add(new C40Person("zhang","san"));
        personList.add(new C40Person("li","si"));
        personList.add(new C40Person("wang","wu"));
        System.out.println(personList);

        Element root = new Element("people");
        for(C40Person p : personList){
            root.appendChild(p.toXml());
        }
        //输出到标准流
        Document doc = new Document(root);
        formatStr(System.out,doc);

        //输出到文件
        String path = "H:\\person.xml";
        formatStr(new BufferedOutputStream(new FileOutputStream(path)),doc);

    }
}

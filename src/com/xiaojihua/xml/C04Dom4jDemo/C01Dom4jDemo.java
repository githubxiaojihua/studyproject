package com.xiaojihua.xml.C04Dom4jDemo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Dom解析的原理:
 *  * 	将整个XML文件 加载到内存,为我们生成一个Document类型的对象
 *  *
 *  * 使用Dom4J的API解析XML文件
 *  *
 *  * 1.把整个要解析的XML文件加载到内存
 *  * 		Dom4J提供了一个核心类,用来加载XML文件  SAXReader
 *  * 2.会生产一个Documnet对象
 *  * 		SAXReader中有一个方法
 *  * 		public Document read(File/InputStream/Reader);
 *  * 3.通过Document对象获取根标签
 *  * 		public Element getRootElement();
 *  *
 *  * 4.由于根标签没有属性,不用获取了.如果有用以下方法获取
 *  * 			public String attributeValue("属性名")
 *  *
 *  * 5.获取根标签的子标签:得到是集合
 *  * 			public List<Element> elements();//获取该标签的所有子标签
 *  *
 *  * 			public List<Element> elements(String name);//获取该标签的所有叫做name的子标签
 *  * 			public Element element();//获取该标签的第一个子标签
 *  * 			public Element element(String name);//获取该标签的第一个叫做name的子标签
 *  *
 *  *
 *  * 6.遍历子标签的集合
 */
public class C01Dom4jDemo {
    public static void main(String[] args) throws DocumentException {
        //1、构建解析对象
        SAXReader reader = new SAXReader();
        //2、构建document对象
        /**
         * 通过File参数读取xml文件，其中的路径需要注意。
         * 如果放在根目录下直接写文件名就行
         */
        Document document = reader.read(new File("src/com/xiaojihua/xml/C04Dom4jDemo/beans.xml"));
        /**
         * 通过流来获取
         * /com/xiaojihua/xml/C04Dom4jDemo/beans.xml------绝对路径
         * beans.xml------相对路径
         */
        //Document document = reader.read(C01Dom4jDemo.class.getResourceAsStream("/com/xiaojihua/xml/C04Dom4jDemo/beans.xml"));
        //3、获取根标签
        Element rootEle = document.getRootElement();
        System.out.println(rootEle.getName());
        //4、获取根标签的自标签
        List<Element> beanEle = rootEle.elements();
        //5.遍历elements集合
        for (Element beanElement : beanEle) {
            //6.获取子标签bean的属性
            String idValue = beanElement.attributeValue("id");
            String classNameValue = beanElement.attributeValue("className");
            System.out.println(idValue+".."+classNameValue);
            //获取bean的子标签
            List<Element> propertyElements = beanElement.elements();
            //遍历property标签
            for (Element propertyElement : propertyElements) {
                //propertyElement Property标签
                //获取Property的属性
                String nameValue = propertyElement.attributeValue("name");
                String valuevalue = propertyElement.attributeValue("value");
                System.out.println(nameValue+"==="+valuevalue);

            }
        }
    }
}

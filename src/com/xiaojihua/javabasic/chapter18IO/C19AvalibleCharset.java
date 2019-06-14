package com.xiaojihua.javabasic.chapter18IO;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 通过Charset类，来输出当前系统中可用的charset以及其别名
 * 每个Charset都有一个规范名（通常是大写）以及多个别名。
 */
public class C19AvalibleCharset {
    public static void main(String[] args){
        SortedMap<String,Charset> avalibale = Charset.availableCharsets();
        Iterator<String> it = avalibale.keySet().iterator();
        while(it.hasNext()){
            String charsetName = it.next();
            printnb(charsetName);
            Iterator<String> aliases = avalibale.get(charsetName).aliases().iterator();
            if(aliases.hasNext()){
              printnb(":");
            }
            while(aliases.hasNext()){
                printnb(aliases.next());
                if(aliases.hasNext()){
                    printnb(",");
                }
            }
            print();
        }

    }
}

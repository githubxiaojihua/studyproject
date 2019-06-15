package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、Matcher.group的使用，group代表了由括号分割的正则表达式，
 * 每次find()后group就会根据正则表达是中的括号分割来填充。比如
 * A(B(C))D，group(0)代表ABCD，group(1)代表BC, group(2)代表C
 */
public class C13Groups {
    public static final  String poem = "Twas brillig, and the slithy toves\n"+
                                       "Did gyre and gimble in the wabe.\n"+
                                       "All mimsy were the borogoves,\n" +
                                       "And the mome raths outgrabe.\n\n"+
                                       "Beware the Jabberwock, my son,\n";
    public static void main(String[] args) {
        //（？m）是属于匹配模式
        Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(poem);
        while(matcher.find()){
            for(int i = 0; i < matcher.groupCount(); i++){
                System.out.print("[" + matcher.group(i) + "]");
            }
            System.out.println();
        }
    }
}

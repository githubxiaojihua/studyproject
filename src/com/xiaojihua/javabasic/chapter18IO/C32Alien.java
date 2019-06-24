package com.xiaojihua.javabasic.chapter18IO;

import java.io.Serializable;

/**
 * 知识点：
 * 从存放对象序列化后的二进制文件中恢复对象，除了需要此二进制文件外，还需要被序列化对象的
 * class。需要JVM能够找到对象的class，如果找不到class对象会报ClassNotFoundException
 * 本例中：
 * C32Alien为序列化的对象
 * C3201FreeAlien为序列化
 * C3202ThawAlien为反序列化。
 * 目的是让反序列化时候找不到C32Alien的class对象，从而报错。但是如果再idea中运行的话是
 * 不会报错的，因为C32Alien编译后的class文件会自动加到classpath中，JVM是能找到的。要
 * 想完全重现问题需要在文件系统中手工建立目录，手工建立java文件（用txt），然后手工执行java
 * 命令（在命令行中）。
 * 让C32Alien\C3201FreeAlien在同一个目录，
 * C3202ThawAlien在子目录中，这样前两个文件编译后的class文件是在C3202的上一层
 * 运行C3202的时候是找不C32Alien的class文件的。
 *
 * 所以从非idea运行的结果来看，要想正确反序列化除了存放对象序列化后的二进制文件外，还需要
 * JVM能找到序列化对象的class。
 */
public class C32Alien implements Serializable {
}

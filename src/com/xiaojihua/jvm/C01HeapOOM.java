package com.xiaojihua.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemoryError异常实践：java堆溢出。
 * 运行参数：
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 以上参数设置java堆最大和最小都是20m，-Xms和-Xmx设置为大小一致也表示
 * 禁止自动扩展。
 * -XX:+HeapDumpOnOutOfMemoryError，在出现OOM的时候导出内存堆快照hprof文件，
 * 通过相关工具如MAT可以进行分析
 *
 * 本例使用限制内存堆大小，并不断创建对象的方式，模拟java堆的OOM
 */
public class C01HeapOOM {
    public static class OOMObject{}

    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }
}

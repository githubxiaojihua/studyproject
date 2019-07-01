package com.xiaojihua.javabasic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 知识点：
 * 从java内部执行操作系统程序。比如本例执行javap
 */
public class OSExecution {
    public static void command(String command){
        boolean errFlag = false;//如果Process执行错误则为true
        try{
            //构建Process
            Process process = new ProcessBuilder(command.split(" ")).start();
            //从Process中读取其正常的输出信息，注意process.getInputStream，并且设置为默认的编码方式，否则容易乱码
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //从Process中读取错误信息
            /*
                在调试中遇到了乱码的情况，因为输出信息里面有中文，牵扯到中文一般就会有。
                java中从字节到字符，从字符到字节是需要解码和编码的，所谓的解码和编码
                是指按照一定的编码方式将字符变换成一个字节或者多个字节的01串。不同的
                编码规则对于同一个字符会编码出不同的01串。解码也是同样的。
                要不乱码就要保证编码和解码的规则是一致的或者是兼容的。另外系统有自己的
                默认编码方式，如果程序中不指定的话那么会选择默认的编码方式。
                关于编码有两篇比较好的文章可以参考笔记中的技术文章。

                在调试中的乱码是因为，系统默认的编码方式是GBK，当将错误信息输出到console的时候，
                来源是Process的ErrorStream，是按照默认编码方式编码后的字节流，而且BufferedReader
                是字符流，底层是通过char来标识的，而且java中的char是Unicocde字符集，采用
                的是UTF-16的编码方式也就是所有字符都是用两个字节来标识，这样就对不起来了
                发生了编码和解码错误，因此需要指定，Reader读的时候的编码方式为GBK,这样就
                统一起来了，乱码就消失了。
                可以去掉GBK试一下。
             */
            BufferedReader err = new BufferedReader(new InputStreamReader(
                    process.getErrorStream(),"GBK"));

            String s;
            while((s = reader.readLine()) != null){
                System.out.println(s);
            }

            while((s = err.readLine()) != null){
                errFlag = true;
                //输出到标准err
                System.err.println(s);
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }

        //如果是Process报的错误，则通过自定义异常将其区分
        if(errFlag){
            throw new OSExecutionException("OSExecution:" + command);
        }

    }

    public static void main(String[] args){
        command("javap target\\classes\\com\\xiaojihua\\javabasic\\util\\OSExecution");
    }
}

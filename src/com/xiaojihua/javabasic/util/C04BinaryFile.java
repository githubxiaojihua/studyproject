package com.xiaojihua.javabasic.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 工具类：
 * 读取二进制文件返回byte[]数组
 */
public class C04BinaryFile {
    public static byte[] read(File file) throws IOException {
        BufferedInputStream bs = new BufferedInputStream(new FileInputStream(file));
        try{
            byte[] bytes = new byte[bs.available()];
            bs.read(bytes);
            return bytes;
        }finally{
            bs.close();
        }
    }

    public static byte[] read(String file) throws IOException{
        return read(new File(file).getAbsoluteFile());
    }
}

package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：文件锁。通过Channel的tryLock()和lock()来获取
 * tryLock是非block的，获取不到锁则返回，lock()是block会一直阻塞直到获取到锁，或者调用Lock()方法的
 * 线程被interrupted、相应的Channel被closed。
 * 通过lock.release()方法来释放锁。
 *
 * 可以通过tryLock(long position, long size, boolean shared)和
 * lock(long position, long size, boolean shared)来通锁定文件的一部分，第三个参数用来
 * 设置是否为共享锁。
 *
 * 关于共享锁和独占锁：
 * 共享锁，请允允许其它共享锁定，不允许其它独占锁定，允许共享读，但只允许一个写。
 * 独占锁，不允许其它共享锁，也不允许其它独占锁。允许独占读和独占写。
 *
 * 说明：带有参数的lock和tryLock，只是锁定参数所规定的区域，文件大小增加了，但是锁定区域是不会增加的。
 * 没有参数的lock和tryLock是锁定整个文件，文件大小增加了，也会锁定整个文件。
 *
 * 获取的锁是共享锁还是独占锁是取决于操作系统的，如果操作系统不去支持共享锁，那么即使申请了共享锁也只
 * 能得到独占锁。
 * 通过lock.isShared()来判断是否为共享锁
 */
public class C27FileLocking {
    public static void main(String[] args) throws Exception {
        FileOutputStream outputStream = new FileOutputStream("H:\\data.txt");
        FileLock lock = outputStream.getChannel().tryLock();
        if(lock != null){
           System.out.println("File locked");
           TimeUnit.MILLISECONDS.sleep(3000);
           System.out.println(lock.isShared());
           lock.release();
           System.out.println("lock released");
        }
        System.out.println(lock.isShared());
        outputStream.close();

    }
}

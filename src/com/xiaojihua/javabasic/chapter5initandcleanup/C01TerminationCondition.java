package com.xiaojihua.javabasic.chapter5initandcleanup;

/**
 * 知识点：finalize方法的一个有用的用法：用于对对象可以进行垃圾回收的检验。
 * 比如，一个对象中使用了某个打开的文件，规定：在这个对象被回收前，应该将打开的文件关闭，
 * 但是由于某种原因，程序员没有关闭，那么这个文件将一直由进程占用着，出于打开状态，当然垃圾回收器还是能回收对象的，但是有一些在回收对象时该做的
 * 并没有完成。
 * finalize作为垃圾回收前的最后一个可认为干预的编码，可以用于对这些不满足回收终结条件的对象进行终结条件的检验。
 */
public class C01TerminationCondition {
    public static void main(String[] args) {
        Book novel = new Book(true, "book1");
        //正常的清理，将应该做的都做了
        novel.checkIn();//垃圾回收时不回收
        //只声明了对象，但是没有将该做的做了，由于没有引用，因此是垃圾回收的对象
        new Book(true, "book2");
        System.gc();//强制调用，垃圾回收器，但并不是立即进行垃圾回收。
    }
}

class Book{
    boolean checkOut = false;
    String name;
    Book(boolean checkOut, String name){
        this.checkOut = checkOut;
        this.name = name;
    }
    void checkIn(){
        checkOut = false;
    }

    public void finalize(){
        if(checkOut){
            System.out.println(name + " error, check out");
        }
    }
}
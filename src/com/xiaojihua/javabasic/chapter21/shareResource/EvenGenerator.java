package com.xiaojihua.javabasic.chapter21.shareResource;

/**
 * 具体的IntGenerator，返回偶数
 * 知识点：
 * 1、如何在多线程情况下控制共享资源的顺序访问，避免多线程之间相互修改。
 * 2、共享资源一般是以对象形式存在的内存片段，也可以是文件、io、或者是
 * 打印机。要控制对共享资源的访问，得先把它包装进一个对象，然后
 * 把<b>所有</b>要访问这个资源的方法标记为synchronized
 * 3、如果某个任务处于一个对标记为synchronized的方法的调用中，那么在
 * 该线程从该方法返回之前，其他所有要调用类中<b>任意</b>synchronized方法
 * 的线程都会被堵塞。
 * 4、共享资源变量应该设置为private的，并提供访问方法。
 * 5、所有对象都含有一个锁，当调用对象任意synchronized方法时，对象被加锁，
 * 所有其他synchronized方法必须等到前一个方法释放锁以后才能被调用。并且
 * 这个锁的数量可以增加，比如一个对象的synchronized方法中调用了同一个对象的另一个synchronized
 * 方法，那么当前锁的数量加1，当一个synchronized方法调用完成锁数量减1直到为0，释放锁。
 * 当然只有首先获得锁的任务才能继续访问synchronized方法，增加和减少锁的数量。
 */
public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;

    /**
     * 此方法是多线程会发生相互干扰的地方。
     * 两条自增语句，可能一个线程执行了第一句，另一个线程读取了值，再执行第一句或者第二句，这样造成
     * 了原来逻辑的混乱，IntGenerator会cancel()。
     * 按照正常的逻辑两条自增语句应该必须一起执行才对。
     * 解决的办法是synchronized。
     * 如果不加程序会很快结束，如果加上则程序必须手工停止才行。
     * @return
     */
    @Override
    synchronized public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}

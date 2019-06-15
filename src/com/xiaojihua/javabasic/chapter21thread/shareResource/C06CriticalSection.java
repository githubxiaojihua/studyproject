package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本例主要表述在方法上加synchronized和使用synchronized块的效率问题，
 * 后者比前者的效率更高
 */

/**
 * Pair为非线程安全的类，因为当x==y的时候status才算是正常，否则就throw Exception。
 * 而自增操作并非线程安全的，并且本类中的方法未加synchronized方法。
 * 因此在多线程环境中，很容易造成status的不正常。
 *
 */
class Pair{
    private int x,y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Pair(){
        this(0,0);
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public void incrementX(){ x++; }
    public void incrementY(){ y++; }

    public String toString(){
        return "x:" + x + ",y:" + y;
    }

    //内部异常类，用于在对象status不对的时候抛出
    public class PairValueNotEqualsException extends RuntimeException{
        public PairValueNotEqualsException(){
            super("Pair value not equals:" + Pair.this);
        }
    }

    public void checkStatus(){
        if(x != y){
            throw new PairValueNotEqualsException();
        }
    }
}

/**
 * 设计模式：模版模式
 * 将通用功能在抽象类中实现，将变化的代码通过抽象方法在子类中实现
 *
 * 通过使用本类将原本线程不安全的Pair类进行了线程安全的包装
 */
abstract class PairManager{
    //使用原子类保证本Integer的原子性
    //由于普通的Integer的自增操作不是原子的，但是原子类的操作却是原子的，其他的
    //原子类包括：AtomicInteger,AtomicLong,AtomicReference
    AtomicInteger checkCount = new AtomicInteger(0);
    protected Pair p = new Pair();
    //提供线程安全的List对象
    private List<Pair> storge = Collections.synchronizedList(new ArrayList<Pair>());

    synchronized public Pair getPair(){
        return new Pair(p.getX(),p.getY());
    }

    /**
     * 本方法用于模拟有一定时间消耗的消费程序。
     * @param p
     */
    protected void store(Pair p){
        storge.add(p);
        try{
            TimeUnit.MILLISECONDS.sleep(50);
        }catch (InterruptedException e){

        }
    }
    //抽象方法用于子类实现可变逻辑
    public abstract void increment();
}

/**
 * 子类实现可变逻辑
 * 直接在方法上增加synchronized
 */
class PairManager1 extends PairManager{

    @Override
    synchronized public void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

/**
 * 子类实现可变逻辑
 * 使用synchronized块（效率更高）
 */
class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        /**
         * synchronized块必须给定一个对象作为参数，在本例中是正在被调用的对象this
         * 如果某个线程获取了此synchronized对象参数上的锁那么该对象其他的synchronized
         * 方法和synchronized块儿就不能被调用了。
         * 这个参数可以是this之外的其他对象，但是一旦指定为其他对象，那么其他相关的同步方法和
         * 同步块应该都是以此对象作为参数，否则同步机制是各自独立的
         */
        synchronized (this){
            p.incrementX();
            p.incrementY();
            temp = getPair();

        }
        //store使用了线程安全的list因此可以将此语句放到synchonized块中。
        store(temp);
    }
}

class PairManipulator implements Runnable{
    private PairManager pm;
    PairManipulator(PairManager pm){
        this.pm = pm;
    }
    public void run(){
        while(true){
            pm.increment();
        }
    }
    public String toString(){
        return "Pair:" + pm.getPair() + " checkerCount:" + pm.checkCount.get();
    }
}

class PairChecker implements Runnable{
    private PairManager pm;
    PairChecker(PairManager pm){
        this.pm = pm;
    }
    public void run(){
        while(true){
            pm.checkCount.incrementAndGet();
            pm.getPair().checkStatus();
        }
    }
}


public class C06CriticalSection {
    static void testApproaches(PairManager pm1, PairManager pm2){
        ExecutorService service = Executors.newCachedThreadPool();
        PairManipulator
                manipulator1 = new PairManipulator(pm1),
                manipulator2 = new PairManipulator(pm2);
        PairChecker
                pairChecker1 = new PairChecker(pm1),
                pairChecker2 = new PairChecker(pm2);
        service.execute(manipulator1);
        service.execute(manipulator2);
        service.execute(pairChecker1);
        service.execute(pairChecker2);

        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Interrrupted");
        }
        System.out.println("pm1:" + manipulator1 + "\npm2:" + manipulator2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pman1 = new PairManager1(),
                pman2 = new PairManager2();
        testApproaches(pman1, pman2);
    }
}

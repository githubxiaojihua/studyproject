package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 使用内部类实现线程
 */
public class C07ThreadVariations {
    public static void main(String[] args){
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

/**
 * 使用继承自Thread的具名内部类，实现内部线程
 */
class InnerThread1{
    private Inner inner;

    public InnerThread1(String name){
        inner = new Inner(name);
    }

    private class Inner extends Thread{
        private int countDown = 5;

        public Inner(String name){
            super(name);
            start();
        }
        @Override
        public void run(){
            try{
                while(true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    sleep(10);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return getName() + countDown;
        }
    }
}

/**
 * 使用继承自Thread的匿名内部类，实现内部线程
 */
class InnerThread2{
    private Thread t;

    public InnerThread2(String name){
        t = new Thread(name){
            private int countDown = 5;
            @Override
            public void run(){
                try{
                    while(true){
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        sleep(10);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            @Override
            public String toString(){
                return getName() + countDown;
            }
        };
        t.start();
    }
}
/**
 * 使用实现Runnable的具名内部类，实现内部线程
 */
class InnerRunnable1{
    private Inner inner;

    public InnerRunnable1(String name){
        inner = new Inner(name);
    }

    /**
     * 类似于自管理的线程实现方式
     */
    private class Inner implements Runnable{
        private int countDown = 5;
        private Thread t;

        public Inner(String name){
            t = new Thread(this,name);
            t.start();
        }

        @Override
        public void run(){
            try{
                while(true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return Thread.currentThread().getName() + countDown;
        }
    }
}

/**
 * 使用实现Runnable的匿名内部类，实现内部线程
 */
class InnerRunnable2{
    private Thread t;

    public InnerRunnable2(String name){
        t = new Thread(new Runnable() {
            private int countDown = 5;
            @Override
            public void run() {
                try{
                    while(true){
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            @Override
            public String toString(){
                return Thread.currentThread().getName() + countDown;
            }
        },name);
        t.start();
    }
}

/**
 * 使用一个独立的方法来运行一个线程
 */
class ThreadMethod{
    private String name;
    private int countDown = 5;
    private Thread t;

    public ThreadMethod(String name){
        this.name = name;
    }

    public void runTask(){
        if(t == null){
            t = new Thread(name){
                @Override
                public void run(){
                    try{
                        while(true){
                            System.out.println(this);
                            if(--countDown == 0){
                                return;
                            }
                            sleep(10);
                        }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString(){
                    return getName() + countDown;
                }
            };
        }
        t.start();
    }
}

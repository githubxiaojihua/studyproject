package com.xiaojihua.javabasic.chapter17;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.List;

public class C21References {
    private static int size = 10;
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    private static void checkQueue(){
        Reference<? extends VeryBig> reference = rq.poll();
        if(reference != null){
            System.out.println("in queue:" + reference.get());
        }

    }

    public static void main(String[] args){
        LinkedList<SoftReference<VeryBig>> sa =
                new LinkedList<SoftReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(
                    new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }
        LinkedList<WeakReference<VeryBig>> wa =
                new LinkedList<WeakReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(
                    new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s =
                new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w =
                new WeakReference<VeryBig>(new VeryBig("Weak"));

        System.gc();

        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<PhantomReference<VeryBig>>();
        for(int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}

class VeryBig{
    private int size = 10000;
    private long[] la = new long[size];
    private String ident;
    public VeryBig(String id){
        ident = id;
    }
    public String toString(){
        return ident;
    }

    @Override
    public void finalize(){
        System.out.println("finalizing " + ident);
    }
}

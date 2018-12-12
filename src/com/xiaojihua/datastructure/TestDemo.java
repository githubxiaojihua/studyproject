package com.xiaojihua.datastructure;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

/**
 * 练习专用类
 *
 */
public class TestDemo<AnyType> {
    private static final int DEFAULT_SIZE = 11;
    private HashEntity<AnyType>[] array;
    private int currentSize;
    private int occupied;

    TestDemo(){
        this(DEFAULT_SIZE);
    }

    TestDemo(int size){
        allocateTheArray(size);
        makeEnpty();
    }

    public boolean insert(AnyType x){
        int index = findPos(x);
        if(isActive(index)){
            return false;
        }
        if(array[index] == null){
            occupied++;
        }

        array[index] = new HashEntity<>(x);
        currentSize++;

        if(occupied >= array.length / 2){
            //rehash
            rehash();
        }
        return true;
    }

    public boolean remove(AnyType x){
        int index = findPos(x);
        if(isActive(index)){
            array[index].isActive = false;
            currentSize--;
            return true;
        }else{
            return false;
        }
    }

    public boolean contains(AnyType x){
        return isActive(findPos(x));
    }

    private void rehash(){
        HashEntity<AnyType>[] oldArr = array;
        array = new HashEntity[nextPrime(2 * oldArr.length)];
        for(int i = 0; i < array.length; i++){
            array[i] = null;
        }
        occupied = 0;
        currentSize =0;
        for(HashEntity<AnyType> entity : oldArr){
            if(entity != null && entity.isActive){
                insert(entity.element);
            }
        }
    }

    private int findPos(AnyType x){
        int hashValue = myHash(x);
        int offset = 1;
        while(array[hashValue] != null && !array[hashValue].element.equals(x)){
            hashValue += offset;
            offset += 2;
            if(hashValue >= array.length){
                hashValue -= array.length;
            }
        }
        return hashValue;
    }

    private boolean isActive(int index){
        return array[index] != null && array[index].isActive;
    }

    private int myHash(AnyType x){
        int hashCode = x.hashCode();
        hashCode %= array.length;
        if(hashCode < 0){
            hashCode += array.length;
        }
        return hashCode;
    }

    private void allocateTheArray(int size){
        array = new HashEntity[nextPrime(size)];
    }

    private void makeEnpty(){
        currentSize = 0;
        occupied = 0;
        for(int i = 0; i < array.length; i++){
            array[i] = null;
        }
    }

    private static class HashEntity<AnyType>{
        private AnyType element;
        private boolean isActive;

        HashEntity(AnyType element, boolean isActive){
            this.element = element;
            this.isActive = isActive;
        }

        HashEntity(AnyType element){
            this(element, true);
        }
    }


    private int nextPrime(int n){

        if(n % 2 == 0){
            n++;
        }
        for(; !isPrime(n); n += 2);
        return n;

    }

    private boolean isPrime(int n){

        if(n == 2 || n == 3){
            return true;
        }
        if(n == 1 || n % 2 == 0){
            return false;
        }

        for(int i = 3; i * i <= n; i+=2){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestDemo<String> H = new TestDemo<>( );

        final int NUMS = 2000000;
        final int GAP  =   37;

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( ""+i );

        System.out.println(H.contains("8999"));

        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( ""+i );

        System.out.println(H.currentSize);
    }

}






package com.xiaojihua.datastructure;


import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 练习专用类
 *
 */
public class TestDemo {

    int[] s;
    TestDemo(int initNum){
        s = new int[initNum];
        for(int i = 0; i < s.length; i++){
            s[i] = -1;
        }
    }

    public int find(int x){
        if(s[x] < 0){
            return x;
        }
        return s[x] = find(s[x]);
    }

    public void union(int root1, int root2){
       if(s[root1] < s[root2]){
           s[root2] = root1;
       }else{
           if(s[root1] == s[root2]){
               s[root2]--;
           }
           s[root1] = root2;
       }
    }



    public static void main(String[] args) {
        int numElements = 128;
        int numInSameSet = 16;

        TestDemo ds = new TestDemo( numElements );
        int set1, set2;

        for( int k = 1; k < numInSameSet; k *= 2 )
        {
            for( int j = 0; j + k < numElements; j += 2 * k )
            {
                set1 = ds.find( j );
                set2 = ds.find( j + k );
                ds.union( set1, set2 );
            }
        }

        for( int i = 0; i < numElements; i++ )
        {
            System.out.print( ds.find( i )+ "*" );
            if( i % numInSameSet == numInSameSet - 1 )
                System.out.println( );
        }
        System.out.println( );

    }

}






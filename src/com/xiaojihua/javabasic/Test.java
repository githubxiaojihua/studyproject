package com.xiaojihua.javabasic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test {
    private static Class[] pets = {Pet1.class, Pet2.class, Pet3.class, Pet4.class, Pet5.class};
    private static Random random = new Random(47);

    public static PetTemp next() throws IllegalAccessException, InstantiationException {
        return (PetTemp) pets[random.nextInt(pets.length)].newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println(next());
    }
}

class PetTemp{
    private String name;

    PetTemp(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}

class Pet1 extends PetTemp{
    Pet1(){
        super("Pet1");
    }
}

class Pet2 extends PetTemp{
    Pet2(){
        super("Pet2");
    }
}

class Pet3 extends PetTemp{
    Pet3(){
        super("Pet3");
    }
}

class Pet4 extends PetTemp{
    Pet4(){
        super("Pet4");
    }
}

class Pet5 extends PetTemp{
    Pet5(){
        super("Pet5");
    }
}
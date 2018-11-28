package com.xiaojihua.javabasic.chapter11.pets;

public class Pet implements Comparable<Pet>{
    public String name;
    public Pet(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }


    @Override
    public int compareTo(Pet o) {

        return this.name.compareTo(o.name);
    }
}


class Hamster extends Pet{
    Hamster(){
        super("Hamster");
    }
}
class Cymirc extends Pet{
    Cymirc(){
        super("Cymirc");
    }
}

class Mouse extends Pet{
    Mouse(){
        super("Mouse");
    }
}

class Rat extends Pet{
    Rat(){
        super("Rat");
    }
}

class Manx extends Pet{
    Manx(){
        super("Manx");
    }
}
class Mutt extends Pet{
    Mutt(){
        super("Mutt");
    }
}

class Pug extends Pet{
    Pug(){
        super("Pug");
    }
}

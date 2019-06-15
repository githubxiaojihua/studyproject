package com.xiaojihua.javabasic.chapter11Container.pets;

public class C00Pet implements Comparable<C00Pet>{
    public String name;
    public C00Pet(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }


    @Override
    public int compareTo(C00Pet o) {

        return this.name.compareTo(o.name);
    }
}


class Hamster extends C00Pet {
    Hamster(){
        super("Hamster");
    }
}
class Cymirc extends C00Pet {
    Cymirc(){
        super("Cymirc");
    }
}

class Mouse extends C00Pet {
    Mouse(){
        super("Mouse");
    }
}

class Rat extends C00Pet {
    Rat(){
        super("Rat");
    }
}

class Manx extends C00Pet {
    Manx(){
        super("Manx");
    }
}
class Mutt extends C00Pet {
    Mutt(){
        super("Mutt");
    }
}

class Pug extends C00Pet {
    Pug(){
        super("Pug");
    }
}

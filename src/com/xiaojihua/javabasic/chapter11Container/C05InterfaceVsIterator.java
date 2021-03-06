package com.xiaojihua.javabasic.chapter11Container;

import com.xiaojihua.javabasic.chapter11Container.pets.C00Pet;
import com.xiaojihua.javabasic.chapter11Container.pets.Pets;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：Collection和Iterator的使用
 * 他们俩都是两个接口，都实现了接口与实现分离，
 * 两个display通过各自的遍历途径进行遍历
 */
public class C05InterfaceVsIterator {
    /**
     * Iterator遍历
     * @param iterator
     */
    public static void display(Iterator<C00Pet> iterator){
        while(iterator.hasNext()){
            printnb(iterator.next().name + " ");
        }
        print();
    }

    /**
     * Collection实现了Iterable接口，因此可以使用foreach遍历
     * @param collection
     */
    public static void display(Collection<C00Pet> collection){
        for(C00Pet pet : collection){
            printnb(pet.name + " ");
        }
        print();
    }

    public static void main(String[] args) {
        List<C00Pet> pets = Pets.arrayList(8);
        Set<C00Pet> petSet = new HashSet<>(pets);//使用list初始化set
        Map<String,C00Pet> petMap = new LinkedHashMap<>();
        String[] names = "Ralpy,Eric,Robin,Lacey,Britney,Sam,Spot,Fultty".split(",");
        for(int i = 0; i < names.length; i++){
            petMap.put(names[i],pets.get(i));
        }
        //调用各类display方法
        display(pets);
        display(petSet);
        display(pets.iterator());
        display(petSet.iterator());
        print(petMap);
        print(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}

package com.xiaojihua.javabasic.chapter11;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：LIST相关的接口和操作
 */
public class ListFeatures {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Rat());
        pets.add(new Manx());
        pets.add(new Cymirc());
        pets.add(new Mutt());
        pets.add(new Pug());
        pets.add(new Cymirc());
        pets.add(new Pug());

        print("1:" + pets);

        Hamster hamster = new Hamster();
        pets.add(hamster);

        print("2:" + pets);
        print("3:" + pets.contains(hamster));//是否包含
        pets.remove(hamster);//删除具体对象
        print("removed hamster:" + pets);
        Pet p = pets.get(2);//按索引获取对象
        print("4:" + p + " " + pets.indexOf(p));//获取具体对象的索引
        Pet cymirc = new Cymirc();
        print("5:" + pets.indexOf(cymirc));//通过equles比较对象，虽然new的是一个类的对象但是并非equles，所以返回-1
        print("6:" + pets.remove(cymirc));//同上，返回false
        print("7:" + pets.remove(p));
        print("8:" + pets);
        pets.add(3,new Mouse());//在指定位置插入
        print("9:" + pets);
        List<Pet> sub = pets.subList(1,4);//返回[1,4)，下标是从0开始
        print("sublist" + sub);
        print("10:" + pets.containsAll(sub));//是否包含sub中的元素，无序
        Collections.sort(sub);//这个方法使用内部排序，要求参数实现Comparable
        print("sorted sublist:" + sub);
        print("sorted pets:" + pets);//对于sub的任何修改都反应在原始列表中，反之亦然。
        print("11:" + pets.containsAll(sub));//排序不影响是否包含
        Random random = new Random(47);
        Collections.shuffle(sub,random);//按照指定的随机因子，随机排序
        print("shuffled sublist:" + sub);
        print("12:" + pets.containsAll(sub));
        print("sorted pets:" + pets);//对于sub的任何修改都反应在原始列表中，反之亦然。
        List<Pet> copy = new ArrayList<>(pets);
        sub = Arrays.asList(pets.get(1),pets.get(4));//接收可变类型参数返回List
        print(sub);
        copy.retainAll(sub);//使copy只保留在sub中存在的元素
        print("13:" + copy);
        copy = new ArrayList<>(pets);
        copy.remove(2);
        print("14:" + copy);
        copy.removeAll(sub);//删除copy中包含在sub中的元素
        print("15:" + copy);
        copy.set(1,new Mouse());
        print("16:" + copy);
        copy.addAll(2,sub);
        print("17:" + copy);
        print("18:" + pets.isEmpty());
        pets.clear();//清空
        print("19:" + pets);
        print("20:" + pets.isEmpty());
        pets.addAll(copy);
        Object[] o = pets.toArray();//转换成数组，默认为object
        print("21:" + o[3]);
        Pet[] pa = pets.toArray(new Pet[0]);//转化成特定类型的数组
        print("22：" + pa[3].name);
    }
}

class Pet implements Comparable<Pet>{
    public String name;
    Pet(String name){
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





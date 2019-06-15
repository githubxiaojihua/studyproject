package com.xiaojihua.javabasic.chapter14typeinfo;

import java.util.ArrayList;

/**
 * 知识点：
 * 1、空对象的使用。空对象提供了一种除了使用NULL表示对象为空以外的表示方法。
 * 空对象一般都继承自其对应的对象，因此可以接收所有发送给对应对象的消息，但是
 * 通常都设置了固定的返回值用以表示是一个空的对象。
 *
 * 空对象更像是null的一种更加智能化的代替物。
 *
 * 2、空对象并不能完全取代Null的作用，只是提供了一种选择方式，比如：下例
 *
 * 首先声明一个Null接口（只是一个标识接口），空对象实现它，因此可以使用instanceof
 * 来判断一个对象是否为空对象。
 * 然后声明Person类，其内部使用一个嵌套类来声明一个空对象类（继承Person，并且实现
 * Null接口），
 * 最后使用static final来生成空对象类的一个单例。
 *
 * Position包括title和Person，但是允许Person为空对象，意思是有这个职位但是
 * 还没有找到合适的人。使用空对象的一个主要的好处就是他是一个对象更接近于面向对象
 * 的编程思维，是对于问题域中一个虚幻对象的抽象，而null只是一个空。
 *
 * 最后主类C12Staff通过集成ArrayList来存储所有的员工（职位和人员信息）
 *
 * 3、可变参数列表的使用。
 */
public class C12Staff extends ArrayList<Position> {

    //接收可变参数列表，并遍历列表
    public void add(String ... titles){
        for(String title : titles){
            add(new Position(title));
        }
    }

    public void add(String title, Person person){
        add(new Position(title,person));
    }

    public C12Staff(String title, Person person){
        this.add(title,person);
    }

    //接收可变参数列表，并传递可变列表
    public C12Staff(String ... titles){
        this.add(titles);
    }

    public boolean isPositionAvalid(String title){
        //遍历本Arraylist
        for(Position p : this){
            //由于Person.NULL是单例模式因此可以使用==或者equals()来进行比较
            //当然和可以使用instanceof操作符来判断
            if(p.getTitle().equals(title) && p.getPerson() == Person.NULL){
                return true;
            }
        }
        return false;
    }

    public void fillPosition(String title, Person person){
        if(person != null) {
            for (Position p : this) {
                if (p.getTitle().equals(title) && p.getPerson() == Person.NULL) {
                    p.setPerson(person);
                    return;
                }
            }
        }
        throw new RuntimeException("position " + title + "is not avalible! Or person Object is null");
    }

    public static void main(String[] args){
        C12Staff staffs = new C12Staff("President", "CTO",
                "Marketing Manager", "Product Manager",
                "Project Lead", "Software Engineer",
                "Software Engineer", "Software Engineer",
                "Software Engineer", "Test Engineer",
                "Technical Writer");
        staffs.fillPosition("President", new Person("jihua","xiao","shandongjinan"));
        staffs.fillPosition("Project Lead", new Person("san","zhang","jinan"));
        if(staffs.isPositionAvalid("Software Engineer")){
            staffs.fillPosition("Software Engineer",new Person("wu","li","shandong"));
        }
        System.out.println(staffs);

    }
}

//声明一个空对象接口（标识接口）
interface Null{}

class Person{
    private final String firstName;
    private final String lastName;
    private final String address;

    public Person(String firstName, String lastName, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     * 嵌套类定义Person的空对象类
     * 继承自Person因此可以接收和返回Person所有的信息
     * 同时继承自Null接口因此可以使用instanceof进行
     * 类型判断
     */
    public static class NullPerson extends Person implements Null{
        //设置固定的数据
        private NullPerson(){
            super("None","None","None");
        }
        public String toString(){ return "NullPerson"; }
    }

    //生成单例的空对象
    public static final Person NULL = new NullPerson();

    @Override
    public String toString(){
        return "Person:" + firstName + " " + lastName + " " + address;
    }
}


class Position{
    private String title;
    private Person person;

    public Position(String title, Person person){
        this.title = title;
        this.person = person;
        if(person == null){
            this.person = Person.NULL;
        }
    }

    public Position(String title){
        this.title = title;
        this.person = Person.NULL;
    }

    public String getTitle(){ return title;}
    public void setTitle(String title){ this.title = title; }
    public Person getPerson(){ return person; }
    public void setPerson(Person person){
        this.person = person;
        if(person == null){
           this.person = Person.NULL;
        }
    }

    @Override
    public String toString(){
        return "Position:" + title  + "," + person + ";";
    }
}

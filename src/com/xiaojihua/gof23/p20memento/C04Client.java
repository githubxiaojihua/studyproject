package com.xiaojihua.gof23.p20memento;

public class C04Client {
    public static void main(String[] args) {
        C03CareTaker careTaker = new C03CareTaker();
        C01Emp emp = new C01Emp("张三",22,9899d);
        System.out.println("第一次打印："+"---" + emp.getName() + "---" + emp.getAge() + "---" + emp.getSalary());
        //备忘
        careTaker.setMemento(emp.memento());
        //改变emp的值
        emp.setAge(11);
        emp.setSalary(90000d);
        System.out.println("第二次打印："+"---" + emp.getName() + "---" + emp.getAge() + "---" + emp.getSalary());

        //恢复
        emp.recovery(careTaker.getMemento());
        System.out.println("第三次打印："+"---" + emp.getName() + "---" + emp.getAge() + "---" + emp.getSalary());

    }
}

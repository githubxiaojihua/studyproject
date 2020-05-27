package com.xiaojihua.gof23.p20memento;

/**
 * 备忘录类
 *
 */
public class C02EmpMemento {
    private String name;
    private Integer age;
    private Double salary;

    /**
     * 根据源发器来构建备忘录类
     * @param emp
     */
    public C02EmpMemento(C01Emp emp){
        this.name = emp.getName();
        this.age = emp.getAge();
        this.salary = emp.getSalary();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

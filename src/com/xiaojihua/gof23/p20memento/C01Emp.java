package com.xiaojihua.gof23.p20memento;

/**
 * 源发器类
 */
public class C01Emp {
    private String name;
    private Integer age;
    private Double salary;

    /**
     * 进行备忘操作，返回备忘录对象
     * @return
     */
    public C02EmpMemento memento(){
        return new C02EmpMemento(this);
    }

    /**
     * 进行数据恢复，恢复至指定备忘录对象存储的时刻
     * @param memento
     */
    public void recovery(C02EmpMemento memento){
        this.name = memento.getName();
        this.age = memento.getAge();
        this.salary = memento.getSalary();
    }

    public C01Emp(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
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

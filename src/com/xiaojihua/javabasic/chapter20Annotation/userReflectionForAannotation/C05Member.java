package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

/**
 * 简单javabean应用定义的annotion
 */
@C01DBTable(name = "IMember")
public class C05Member {
    //当只有一个value()属性被设置的时候可直接写值
    @C03SqlString(30)
    private String firstName;
    @C03SqlString(30)
    private String lastName;
    @C04SqlInt
    private int age;

    /*
        除了value还指定了constraints所以不能用上面的shorcut
        另外注意嵌套annotion的使用
     */
    @C03SqlString(value = 30, constraints = @C02Constraints(isPrimary = true))
    private String handle;
    private int memberCount;

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getAge(){
        return age;
    }

    public String getHandle(){
        return handle;
    }

    @Override
    public String toString(){
        return handle;
    }
}

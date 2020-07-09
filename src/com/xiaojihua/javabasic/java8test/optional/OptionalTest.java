package com.xiaojihua.javabasic.java8test.optional;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Junit中的Assert在4.12以后就过时了，原来的assertEquals。。。。都已经取消
 * 用TestCase取代
 */
public class OptionalTest {

    //=========================创建===============================
    /**
     * 创建Optional空对象
     */
    @Test
    public void testCreateOptional(){
        Optional<String> empty = Optional.empty();
        //判断是否有值
        TestCase.assertFalse(empty.isPresent());
    }

    /**
     * 使用of方法创建
     * 注意name不能为空，如果为空则抛出NPE
     */
    @Test
    public void testCreateOptional2(){
        String name = "zhangsan";
        Optional<String> opt = Optional.of(name);
        TestCase.assertTrue(opt.isPresent());
    }

    /**
     * 使用of方法创建
     * 注意name不能为空，如果为空则抛出NPE
     */
    @Test(expected = NullPointerException.class)
    public void testNPE(){
        String name = null;
        Optional<String> opt = Optional.of(name);
    }

    /**
     * 使用ofNullable方法创建可以为空的Optional对象
     */
    @Test
    public void testNullable(){
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        TestCase.assertFalse(opt.isPresent());
    }

    //=========================验值===============================

    /**
     * 当从方法的返回值获取到Optional对象的时候我们可以对其中的值进行判断
     * 是否存在
     */
    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("Baeldung");
        TestCase.assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        TestCase.assertFalse(opt.isPresent());
    }

    /**
     * *************重要******************
     * 当从方法的返回值获取到Optional对象的时候我们可以对其中的值进行判断
     * 是否存在
     * ifPresent接收一个函数接口，如果Optional中存在相关对象则执行函数接口的实现
     * ，可以直接使用lambda表达式，也可以使用匿名内部类来实现。
     */
    @Test
    public void testIfPresent() {
        Optional<String> opt = Optional.of("Baeldung");
        //lanmbda
        opt.ifPresent((name)-> System.out.println(name));
        //class
        opt.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("使用匿名内部类的方式打印name:" + s);
            }
        });

        //如果不使用optional则需要做如下判断，optional使得代码简单了，可读性更高了。
        /*if(name != null) {
            System.out.println(name.length());
        }*/
    }

    /**
     * orElse提供了默认值，注意返回值为泛型类型T，而并非是Optional
     */
    @Test
    public void testDefaultValue() {
        String name = null;
        String opt = Optional.ofNullable(name).orElse("zhangsan");
        TestCase.assertEquals(opt,"zhangsan");

    }

    /**
     * *************重要******************
     * orElseGet提供了一个生成器，参数是一个Supplier函数接口
     * 可以用于产生默认值，这与orElse有本质区别
     */
    @Test
    public void testDefaultValueFun() {
        String name = null;
        String opt = Optional.ofNullable(name).orElseGet(()->"join");
        TestCase.assertEquals(opt,"join");

    }


    /**
     * *************重要******************
     * 测试orElse和orElseGet两个方法的区别和用法
     */
    @Test
    public void testDiff_orElse_orElseGet() {
        //1、当name为空的时候都会调用getMyDefault方法
        String name = null;
        //使用lambda表达式获取默认值
        System.out.println("Using orElseGet.....");
        String opt = Optional.ofNullable(name).orElseGet(this::getMyDefault);
        System.out.println("Using orElse.....");
        //通过调用函数来获取默认值
        opt = Optional.ofNullable(name).orElse(getMyDefault());
        TestCase.assertEquals(opt,"Default value");

        System.out.println("=========测试分割========");
        System.out.println("Using orElseGet.....");
        //2、当name不为空的时候只有OrElse调用getMyDefault方法，创建了一个不会使用的对象
        String name2 = "zhangsan";
        //使用lambda表达式获取默认值
        System.out.println("Using orElse.....");
        String opt2 = Optional.ofNullable(name2).orElseGet(this::getMyDefault);
        //通过调用函数来获取默认值
        opt2 = Optional.ofNullable(name2).orElse(getMyDefault());
        TestCase.assertEquals(opt2,"zhangsan");

    }


    /**
     * 测试orElseThrow当Optional对象不包括对应值的时候抛出指定异常对象。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        String name = null;
        String opt = Optional.ofNullable(name).orElseThrow(IllegalArgumentException::new);

    }

    /**
     * get方法的测试，当Optional中没有对象的时候会抛出异常
     */
    @Test(expected = NullPointerException.class)
    public void testGet() {
        String name = "zhangsan";
        Optional<String> opt = Optional.ofNullable(name);
        String test = opt.get();
        TestCase.assertEquals(name,test);

        name = null;
        opt = Optional.of(name);
        test = opt.get();//会抛出NullPointerException异常
    }



    public String getMyDefault(){
        System.out.println("Getting Defautl value");
        return "Default value";
    }


    //=========================与stream相相结合===============================

    /**
     * filter的使用，对optional中的元素进行过滤，满足条件的返回正常optional对象，
     * 不满足条件的返回empty optional对象
     */
    @Test
    public void testFlter() {
        Integer year = 2016;
        Optional<Integer> yearOpt = Optional.of(year);
        boolean is2016 = yearOpt.filter((y)->y==2016).isPresent();
        TestCase.assertTrue(is2016);

        boolean is2017 = yearOpt.filter(y->y==2017).isPresent();
        TestCase.assertFalse(is2017);
    }

    //=========================filter的使用案例：模拟一个买一定价格范围内路由器的程序====================================
    /**
     * 不使用optional来进行价格范围的判断
     * @param modem
     * @return
     */
    public boolean priceInrange(Modem modem){
        boolean isInRange = false;
        if(modem != null && modem.getPrice()!=null && (modem.getPrice()>=10 && modem.getPrice()<=16)){
            isInRange = true;
        }
        return isInRange;
    }

    /**
     * 使用Option来进行价格范围的判断。
     * 结合filter和map，省去了多余的if语句。
     * @param modem
     * @return
     */
    public boolean priceInrangeUseOpt(Modem modem){
        //map处理：如果optional中有对象则返回处理后的Optional对象，还则返回empty Optional
        return Optional.ofNullable(modem).map(Modem::getPrice).filter(p->p>=10).filter(p->p<=16).isPresent();
    }

    /**
     * 使用原始方式进行测试
     */
    @Test
    public void testWithoutFilter() {
        TestCase.assertTrue(priceInrange(new Modem(10.0)));
        TestCase.assertFalse(priceInrange(new Modem(9.9)));
        TestCase.assertFalse(priceInrange(new Modem(null)));
        TestCase.assertFalse(priceInrange(new Modem(16.5)));
        TestCase.assertFalse(priceInrange(null));
    }

    /**
     * 测试使用了Optional的filter
     */
    @Test
    public void testFilter() {
        TestCase.assertTrue(priceInrange(new Modem(10.0)));
        TestCase.assertFalse(priceInrange(new Modem(9.9)));
        TestCase.assertFalse(priceInrange(new Modem(null)));
        TestCase.assertFalse(priceInrange(new Modem(16.5)));
        TestCase.assertFalse(priceInrange(null));
    }

    /**
     * map的使用，map就是一个映射，将optional中的原始值映射为新的值，但不会
     * 改变原来的值。
     * map也是返回一个Optional，是一个经过map映射后返回结果的Optional
     */
    @Test
    public void testMap() {
        List<String> companyName = Arrays.asList("company1","company2","company3","company4","company5");
        Optional<List<String>> listOpt = Optional.ofNullable(companyName);
        //map为Optional<Integer>
        int size = listOpt.map(List::size).orElse(0);
        TestCase.assertEquals(5,size);
    }

    /**
     * 使用optional的filter和map进行组合，来验证密码的正确性
     */
    @Test
    public void testMapAndFilter() {
        String password = "password ";
        Optional<String> optPass = Optional.ofNullable(password);
        boolean correctPass = optPass.filter(p->p.equals("password")).isPresent();
        TestCase.assertFalse(correctPass);
        //map接收一个函数接口，使用方法引用来实现
        correctPass = optPass.map(String::trim).filter(p->p.equals("password")).isPresent();
        TestCase.assertTrue(correctPass);
    }

    /**
     * map和flatMap的区别:map接收所有类型的数据目的是为了进行映射操作，flatmap接收optional对象目的是为了将
     * 多个optional进行扁平化处理。
     * map和flatMap在Optional和Stream中都有，作用也基本相同。
     * 在Optional中，如果存在嵌套的Optional，如下面的Person类，某其被包装为
     * Optional类，但是其内部的getName返回的也是一个Optional的类，那么使用
     * map的时候就是将map返回的值再也行optional的包装，形成了Optional<Optional<String>>类型。
     * 而使用flatMap会首先将getName返回的Optional解包，形成单独的String对象，再包装成
     * optional对象，这样是将多层Optional将成了一层，也就是扁平化处理。
     */
    @Test
    public void testMapAndFlatMap() {
        Person person = new Person("jhon",25,"123456");
        Optional<Person> optionalPerson = Optional.of(person);
        //使用map获取Optional类型的名字，会形成多层包装，需要多层解包
        Optional<Optional<String>> optName = optionalPerson.map(Person::getName);
        Optional<String> name = optName.orElseThrow(IllegalArgumentException::new);
        String nameStr = name.orElse("");
        TestCase.assertEquals("jhon",nameStr);

        //使用flatmap会先解包，从而避免了多层封装。
        Optional<String> name2 = optionalPerson.flatMap(Person::getName);
        nameStr = name2.orElse("");
        TestCase.assertEquals("jhon",nameStr);
    }

}

/**
 * 用于测试filter的类
 */
class Modem{
    private Double price;

    public Modem(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

/**
 * 测试map()和 flatMap()
 */
class Person{
    private String name;
    private Integer age;
    private String password;

    //===============get方法返回Optional包装类
    public Optional<String> getName(){
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge(){
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword(){
        return Optional.ofNullable(password);
    }

    public Person(String name, Integer age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

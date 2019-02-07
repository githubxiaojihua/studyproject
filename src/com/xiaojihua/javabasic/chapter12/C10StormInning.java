package com.xiaojihua.javabasic.chapter12;

/**
 * 知识点:
 * 1、异常的限制：派生类覆盖的方法所声明的异常列表，不能超过父类的异常列表。
 * 2、接口方法中声明的异常，不会影响基类与派生类上述关系，比如event方法。但是
 * 如果方法只存在于接口中，那么同样遵循这个规则。
 *
 * 这个规则的主要作用是作用域基类或者接口的方法调用，仍然能作用于派生类或者实现类
 * 异常的声明应该是由上到下减少的，这与集成相反，集成是从上到下增加的。
 *
 * 3、派生类构造方法必须处理基类构造方法所抛出的异常。
 */
public class C10StormInning extends Inning implements Strom {
    /**
     * 可以抛出基类构造方法中为声明的异常，但是必须包含基类构造方法中的异常
     * 派生类不能捕捉基类构造函数的异常
     * 比如下面的默认构造函数，必须throws BaseBallException
     * 不能 try{super()}catch(BaseBallException e)
     * 即使这么做了也没有用。
     * @throws BaseBallException
     * @throws RainedOut
     */
    public C10StormInning() throws BaseBallException, RainedOut{}
    public C10StormInning(String s) throws Foul, BaseBallException{}

    /**
     * 普通的方法在覆盖基类方法的时候，不能抛出基类方法不存在的异常声明。编译报错
     * @throws PopFoul
     */
    //@Override
    //public void walk() throws PopFoul{}

    /**
     * 实现的接口中存在与基类相同的方法，但是其上声明的异常，并不能改变，基类异常
     * 对于子类异常的限制，也就是说，此处的RainedOut异常，虽然在实现的接口中对应的
     * 方法中声明了，但是基类中却没有声明，因此，导出类中不能扩大异常的声明。编译报错
     * @throws RainedOut
     */
    //public void event() throws RainedOut{}

    /**
     * 如果异常换做其他的，那么会有编译错误
     *
     * @throws RainedOut
     */
    public void rainHard() throws RainedOut{};

    /**
     * 覆盖基类的方法，可以补抛出任何异常，或者小于基类的异常列表，
     * 但就是不能大于基类的异常列表。
     */
    public void event(){}

    /**
     * 覆盖基类的方法所抛出的异常，可以是基类声明异常的派生子异常。
     * @throws PopFoul
     */
    public void atBot() throws PopFoul{}

    public static void main(String[] args) {
        try{
            C10StormInning si = new C10StormInning();
            si.atBot();
        }catch(PopFoul e){
            System.out.println("Pop Foul");
        }catch(RainedOut e){
            System.out.println("Rained out");
        }catch(BaseBallException e){
            System.out.println("base ball exception");
        }

        try{
            //当进行了向上转型后，对比上一块代码，其声明的异常为，Inning.atBot（）方法抛出的异常。
            Inning i = new C10StormInning();
            i.atBot();
        }catch(Strike e){
            System.out.println("Pop Foul");
        }catch (Foul e){
            System.out.println("Foul");
        } catch(RainedOut e){
            System.out.println("Rained out");
        }catch (BaseBallException e){
            System.out.println("base ball exception");
        }
    }
}

class BaseBallException extends Exception{}
class Foul extends BaseBallException{}
class Strike extends BaseBallException{}

abstract class Inning{
    public Inning() throws BaseBallException{}

    /**
     * 声明了异常列表，强制其调用者必须处理所抛出的异常
     * @throws BaseBallException
     */
    public void event() throws BaseBallException{
        //do nothing
    }


    public abstract void atBot() throws Foul, Strike;
    public void walk(){}//
}

class StromException extends Exception{}
class RainedOut extends StromException{}
class PopFoul extends Foul{}

interface Strom{
    public void event() throws RainedOut;
    public void rainHard() throws RainedOut;
}

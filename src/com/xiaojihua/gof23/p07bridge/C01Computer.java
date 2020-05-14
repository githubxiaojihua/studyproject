package com.xiaojihua.gof23.p07bridge;

/**
 * 多种维度的继承关系导致类的集成体系庞大
 * 维度一：电脑类型
 * 维度二：品牌
 *
 * 电脑
 *   |--台式机
 *        |----联想台式机
 *        |----神州台式机
 *        |----戴尔台式机
 *   |--笔记本
 *        |----联想笔记本
 *        |----神州笔记本
 *        |----戴尔笔记本
 *   |--平板电脑
 *        |----联想平板电脑
 *        |----神州平板电脑
 *        |----戴尔平板电脑
 *
 * 缺点是集成结构太深，太复杂
 *
 * 缺点：违反单一原则，一个类比如联想笔记本，有两个因素会引起类的变化
 */

// 顶级接口
public interface C01Computer {
    void sale();
}

// 一级
class Desktop implements C01Computer{

    @Override
    public void sale() {
        System.out.println("销售台式机");
    }
}

class Laptop implements C01Computer{

    @Override
    public void sale() {
        System.out.println("销售笔记本");
    }
}

class Pad implements C01Computer{

    @Override
    public void sale() {
        System.out.println("销售平板电脑");
    }
}

// 二级
class LenovoDesktop extends Desktop{

    @Override
    public void sale() {
        System.out.println("销售联想台式机");
    }
}
class LenovoLaptop extends Laptop{

    @Override
    public void sale() {
        System.out.println("销售联想笔记本");
    }
}
class LenovoPad extends Pad{

    @Override
    public void sale() {
        System.out.println("销售联想平板电脑");
    }
}



class ShenzhouDesktop extends Desktop {
    @Override
    public void sale() {
        System.out.println("销售神舟台式机");
    }
}
class ShenzhouLaptop extends Laptop {
    @Override
    public void sale() {
        System.out.println("销售神舟笔记本");
    }
}
class ShenzhouPad extends Pad {
    @Override
    public void sale() {
        System.out.println("销售神舟平板电脑");
    }
}


class DellDesktop extends Desktop {
    @Override
    public void sale() {
        System.out.println("销售戴尔台式机");
    }
}
class DellLaptop extends Laptop {
    @Override
    public void sale() {
        System.out.println("销售戴尔笔记本");
    }
}
class DellPad extends Pad {
    @Override
    public void sale() {
        System.out.println("销售戴尔平板电脑");
    }
}
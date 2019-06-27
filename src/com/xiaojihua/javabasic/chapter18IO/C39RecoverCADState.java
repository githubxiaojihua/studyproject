package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 配合C38进行测试，因为在C38中直接测试是没有效果的。
 * 在C38回收后的测试才是有效果的。
 */
public class C39RecoverCADState {
    public static void main(String[] args) throws Exception{
        String path = "H:\\data.out";
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
/*
        List<Class<? extends Shap>>shapTypts = (List<Class<? extends Shap>>) inputStream.readObject();
*/
        Line.deserializeStaticState(inputStream);
        List<Shap> shaps = (List<Shap>) inputStream.readObject();
        System.out.println(shaps);
    }
}

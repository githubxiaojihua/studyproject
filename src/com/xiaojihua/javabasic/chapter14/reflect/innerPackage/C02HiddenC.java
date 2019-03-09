package com.xiaojihua.javabasic.chapter14.reflect.innerPackage;

import com.xiaojihua.javabasic.chapter14.reflect.C01A;
import static com.xiaojihua.javabasic.util.Print.*;

public class C02HiddenC {
    public static C01A getC(){
        return new C();
    }
}

class C implements C01A{
    public void f(){ print("public void C.f()"); }
    public void g(){ print("public void C.g()"); }
    void u(){ print("void C.u()"); }
    protected void w(){ print("protected void C.w()"); }
    private void x(){ print("private void C.x()"); }
}

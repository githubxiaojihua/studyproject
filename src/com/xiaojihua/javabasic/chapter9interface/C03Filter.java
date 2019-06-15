package com.xiaojihua.javabasic.chapter9interface;

public class C03Filter {
    public String name(){ return getClass().getSimpleName(); }
    public C04WaveForm process(C04WaveForm input){ return input; }
}

class LowPass extends C03Filter {
    public C04WaveForm process(C04WaveForm input){ return input;}
}

class HighPass extends C03Filter {
    public C04WaveForm process(C04WaveForm input){ return input; }
}


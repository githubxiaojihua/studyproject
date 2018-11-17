package com.xiaojihua.javabasic.chapter9;

public class Filter {
    public String name(){ return getClass().getSimpleName(); }
    public WaveForm process(WaveForm input){ return input; }
}

class LowPass extends Filter{
    public WaveForm process(WaveForm input){ return input;}
}

class HighPass extends Filter{
    public WaveForm process(WaveForm input){ return input; }
}


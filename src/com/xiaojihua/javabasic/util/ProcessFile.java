package com.xiaojihua.javabasic.util;

import java.io.File;

public class ProcessFile {
    public interface Strategy{
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessFile(Strategy strategy, String ext){
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args){
        if(args.length == 0){

        }else{
            for(String arg : args){
                if(!arg.endsWith(ext)){
                    arg = arg + "." + ext;
                }
                File file = new File(arg).getAbsoluteFile();

            }
        }
    }
}

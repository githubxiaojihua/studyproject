package com.xiaojihua.gof23.p15command.command;

public class C02TextFiles {
    private String name;

    public C02TextFiles(String name){
        this.name = name;
    }

    public String openFile(){
        return "C02TextFiles.openFile():" + name;
    }

    public String saveFile(){
        return "C02TextFiles.saveFile():" + name;
    }
}

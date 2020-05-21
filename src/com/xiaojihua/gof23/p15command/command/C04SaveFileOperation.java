package com.xiaojihua.gof23.p15command.command;

public class C04SaveFileOperation implements C01TextFileOperation {
    private C02TextFiles textFiles;

    public C04SaveFileOperation(C02TextFiles textFiles){
        this.textFiles = textFiles;
    }

    @Override
    public String execute(){
        return textFiles.saveFile();
    }
}

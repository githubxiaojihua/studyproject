package com.xiaojihua.javabasic.disignPattern.command;

public class C03OpenFileOperation implements C01TextFileOperation {
    private C02TextFiles textFiles;

    public C03OpenFileOperation(C02TextFiles textFiles){
        this.textFiles = textFiles;
    }

    @Override
    public String execute(){
        return textFiles.openFile();
    }
}

package com.xiaojihua.javabasic.disignPattern.command;

import java.util.ArrayList;
import java.util.List;

public class C05TextFileOperationExecutor {
    private List<C01TextFileOperation> list = new ArrayList<>();

    public String executeOperation(C01TextFileOperation operation){
        list.add(operation);
        return operation.execute();
    }

}

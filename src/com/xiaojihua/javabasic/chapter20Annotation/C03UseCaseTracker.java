package com.xiaojihua.javabasic.chapter20Annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 通过反射来读取类中的annotion及其属性
 */
public class C03UseCaseTracker {
    public static void trackUseCase(List<Integer> usecases, Class<?> c){
        for(Method m : c.getDeclaredMethods()){
            C01UseCase useCase = m.getAnnotation(C01UseCase.class);
            if(useCase != null){
                System.out.println("Fount use case + " + useCase.id() + " and " +
                        "description:" + useCase.description());
                usecases.remove((Integer)useCase.id());
            }
        }
        for(int i : usecases){
            System.out.println("Warning: missing use case-" + i);
        }

    }

    public static void main(String[] args){
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCase(useCases, C02PasswordUtil.class);

    }
}

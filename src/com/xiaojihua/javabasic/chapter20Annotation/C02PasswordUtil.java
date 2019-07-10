package com.xiaojihua.javabasic.chapter20Annotation;

import java.util.List;

/**
 * 知识点：
 * annotion的使用类
 */
public class C02PasswordUtil {

    @C01UseCase(id = 47, description = "description mast contain one numberic")
    public boolean validPassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @C01UseCase(id = 48)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @C01UseCase(id = 49, description = "new pass cant be same as prevPass")
    public boolean checkForNewPassword(List<String> prevPass, String newPass){
        return !prevPass.contains(newPass);
    }
}

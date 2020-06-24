package com.xiaojihua.javabasic.chapter18IO;

import java.util.prefs.Preferences;

/**
 * 知识点：
 * Preferences的使用。
 * Preferences用于自动保存和提取用户的偏好设置与程序的配置信息。其存储的位置取决
 * 于OS，例如在windows中是存储在注册表中（HKEY_CURRENT_USER-SOFTWARE-JavaSoft-Prefs-com....）。
 * 存储的结构是节点树的结构。
 * Preferences只能用于存储基本数据类型与String（不超过8k)
 */
public class C43PreferencesDemo {
    public static void main(String[] args) throws Exception{
        /*
            除了Perferences.userNodeForPackage还有
            Preferences.systemNodeForPackage，分别用于存储用户个性化信息和
            全局信息。
            userNodeForPackage和systemNodeForPackage是用于在注册表中声明信息存储
            的目录结构，比如：使用C43PreferencesDemo.class那么就会在
            HKEY_CURRENT_USER-SOFTWARE-JavaSoft-Prefs下面有com-xiaojihua-javabasic-chapter18IO
            然后再有下面的location\footerware等键值对
         */
        Preferences preferences = Preferences.userNodeForPackage(C43PreferencesDemo.class);
        //可以put，也可以putInt、putBoolean等
        preferences.put("location","os");
        preferences.put("footerware","nick");
        preferences.putInt("companies",4);
        preferences.putBoolean("are there watches?", true);

        /*
            一般使用的时候都是按如下来做，先获取再操作，再存储。
            get\getInt\getBoolean等，都需要提供一个默认值
         */
        int usageCount = preferences.getInt("usageCount",0);
        usageCount++;
        preferences.putInt("usageCount",usageCount);

        /*
            获取keys
         */
        for(String key : preferences.keys()){
            System.out.println(key + ":" + preferences.get(key,null));
        }

        System.out.println("how many company?" + preferences.getInt("companies",0));
    }
}

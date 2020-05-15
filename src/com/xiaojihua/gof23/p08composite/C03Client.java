package com.xiaojihua.gof23.p08composite;

/**
 * 测试组合模式对于树形结构的处理
 */
public class C03Client {
    public static void main(String[] args){
        // 组合树形结构
        C02AbstractFile f2,f3,f5,f6;
        Folder f1 = new Folder("我的收藏");
        f2 = new ImageFile("图片文件.jpg");
        f3 = new TextFile("文本文件.txt");
        f1.add(f2);
        f1.add(f3);

        Folder f4 = new Folder("电影");
        f5 = new VideoFile("倚天屠龙记.avi");
        f6 = new VideoFile("神雕侠侣.avi");
        f4.add(f5);
        f4.add(f6);

        f1.add(f4);

        f1.killVirus();
    }
}

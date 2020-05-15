package com.xiaojihua.gof23.p08composite;

import java.util.ArrayList;
import java.util.List;
/**
 * 通过模拟杀毒软件的杀毒过程来学习组合模式
 */

/**
 * 抽象构件
 */
public interface C02AbstractFile {
    void killVirus();
}
//===================以下是Leaf

/**
 * 这里也可以先定义相关Leaf接口（扩展自abstractFile），然后实现
 */
class ImageFile implements C02AbstractFile{
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---图像文件："+name+",进行查杀！");
    }
}

class TextFile implements C02AbstractFile{
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---文本文件："+name+",进行查杀！");
    }
}

class VideoFile implements C02AbstractFile {
    private String name;

    public VideoFile(String name) {
        super();
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---视频文件："+name+",进行查杀！");
    }
}

//================以下是容器组件（composite)
/**
 * 这里也可以先定义相关composite接口（扩展自abstractFile），然后实现
 */
class Folder implements C02AbstractFile{
    private String fileName;
    private List<C02AbstractFile> children = new ArrayList<>();

    public Folder(String fileName) {
        this.fileName = fileName;
    }

    public void add(C02AbstractFile file){
        children.add(file);
    }

    public void remove(C02AbstractFile file){
        children.remove(file);
    }

    public C02AbstractFile getChildren(int index){
        return children.get(index);
    }

    @Override
    public void killVirus() {
        /**
         * 对于容器组件以及叶子组件提供了统一的处理方式，
         * 这里采用了递归。
         * 即先处理本容器内容，然后遍历其孩子，而其孩子可能是容器和可能是子节点
         * 调用其统一的接口，通过递归来进行处理，
         * 所以尤其适合处理树形结构。
         */
        System.out.println("---文件夹："+fileName+",进行查杀");

        for(C02AbstractFile file : children){
            file.killVirus();
        }
    }
}

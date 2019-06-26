package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 知识点：
 * 序列化最好保证"原子性"，也就是说不要做一部分工作，序列化，然后又做一部分工作，
 * 再序列化。应该将所有工作做完后再一次性序列化。比如本例main方法中，要序列化
 * shapTypes和shaps那么先将要做的工作都做完，然后将序列化的操作放到一块儿。
 *
 * 显示了如何序列化static字段。
 */
public class C38StoreCadState {
    public static void main(String[] args) throws Exception{
        List<Class<? extends Shap>> shapTypts = new ArrayList<>();
        shapTypts.add(Circle.class);
        shapTypts.add(Square.class);
        shapTypts.add(Line.class);



        List<Shap> shaps = new ArrayList<>();
        for(int i=0; i<10; i++){
            shaps.add(Shap.shapFactory());
        }

        for(int i=0; i<10; i++){
            shaps.get(i).setColor(Shap.GREEN);
        }

        String path = "H:\\data.out";
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(path));
/*
        outputStream.writeObject(shapTypts);
*/
        Line.serializeStaticState(outputStream);

        outputStream.writeObject(shaps);
        outputStream.close();;

        System.out.println(shaps);

        /*
            注释掉下面的原因是，写在这里体现不出对于static字段的序列化和反序列化。
            因为在本类运行期间，所有的static字段的设置都会保留。这一部分的运行结果
            与单独一个文件运行是不一样的，单独运行的话，那时候本类已经被回收，因此
            能显示出对于static字段的不同设置。
            书本上也是单独一个文件
            查看C39

         */
        /*ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        shapTypts = (List<Class<? extends Shap>>) inputStream.readObject();
        Line.deserializeStaticState(inputStream);
        shaps = (List<Shap>) inputStream.readObject();
        System.out.println(shaps);*/


    }
}

abstract class Shap implements Serializable{
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    public int xPos, yPos, demension;
    public static Random random = new Random(47);
    private static int count = 0;

    public Shap(int xPos, int yPos, int demension){
        this.xPos = xPos;
        this.yPos = yPos;
        this.demension = demension;
    }

    public String toString(){
        return getClass() + "color[" + getColor() + "]," + "xPos[" + xPos + "], yPos[" + yPos + "], demension[" + demension + "]\n";
    }

    public static Shap shapFactory(){
        int xpos = random.nextInt(10);
        int ypos = random.nextInt(10);
        int demention = random.nextInt(10);
        switch(count++ % 3){
            default:
            case 0: return new Circle(xpos, ypos, demention);
            case 1: return new Square(xpos, ypos, demention);
            case 2: return new Line(xpos, ypos, demention);
        }
    }

    public abstract void setColor(int newColor);
    public abstract int getColor();
}

class Circle extends Shap{
    private static int COLOR = RED;
    public Circle(int xPos, int yPos, int demension){
        super(xPos, yPos, demension);
    }

    @Override
    public void setColor(int color){
        COLOR = color;
    }

    @Override
    public int getColor(){
        return COLOR;
    }

}

class Square extends Shap{
    private static int COLOR;

    public Square(int xPos, int yPos, int demention){
        super(xPos, yPos, demention);
        COLOR = RED;
    }

    @Override
    public void setColor(int color){
        COLOR = color;
    }

    @Override
    public int getColor(){
        return COLOR;
    }
}

class Line extends Shap{
    private static int COLOR = RED;

    public Line(int xPos, int yPos, int demention){
        super(xPos, yPos, demention);
    }

    public static void serializeStaticState(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeInt(COLOR);
    }

    public static void deserializeStaticState(ObjectInputStream inputStream) throws IOException{
        COLOR = inputStream.readInt();
    }

    @Override
    public void setColor(int color){
        COLOR = color;
    }

    @Override
    public int getColor(){
        return COLOR;
    }

}

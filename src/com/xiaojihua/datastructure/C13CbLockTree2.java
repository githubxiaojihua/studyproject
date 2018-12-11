package com.xiaojihua.datastructure;

import com.sun.org.apache.xalan.internal.lib.NodeInfo;

import java.io.File;
import java.util.*;

/**
 * 进行了重写，将部分内容进行了重构
 * 一个网页可以看成一个具有树型结构的矩形区域R，根据某种特性R可以被划分成若干不相交的矩形小块，每个小块同样能被继续细分。
 * 请设计一种数据结构CbLockTree，利用它可以描述上面所述的树型结构，树中每个节点可以认为有唯一的ID。要求：
 * 1、树节点基本信息包含：ID,name,position(左上角位置),size（宽度和高度）
 * 2、提供函数以遍历树的方式根据name得到节点（如果多个节点具有相同的名字则返回所有）
 * 3、提供函数将一个节点d插入树中指定节点的下面
 */
public class C13CbLockTree2 {
    private Node<NodeInfoTemp> root;//根节点

    C13CbLockTree2(Node<NodeInfoTemp> root){
        this.root = root;
    }

    public Node<NodeInfoTemp> getTree(){
        return root;
    }

    /**
     * 将一个节点插入树中的指定节点下面
     * 默认增加到当前节点的最后一个儿子
     * @param node
     * @param x
     */
    public void addUnder(Node<NodeInfoTemp> node, NodeInfoTemp x){
        Node<NodeInfoTemp> newNode = new Node<>(x);
        if(node.firstChildren == null){
            node.firstChildren = newNode;
        }else{
            Node<NodeInfoTemp> temp = node.firstChildren;
            while(temp.nextSlid != null){
                temp = temp.nextSlid;
            }
            temp.nextSlid = newNode;
        }
        System.out.println("新增节点成功！");
    }

    /**
     * 根据name遍历整棵树，返回相同name的node
     * @param name
     * @return
     */
    public List<Node<NodeInfoTemp>> findByName(String name){
        List<Node<NodeInfoTemp>> nodeList = new ArrayList<>();
        return findByName(root,name,nodeList);

    }

    /**
     * 根据name遍历树，返回相同name的Node
     * @param node
     * @param name
     * @param list
     * @return
     */
    private List<Node<NodeInfoTemp>> findByName(Node<NodeInfoTemp> node,String name, List<Node<NodeInfoTemp>> list){
        if(node.nodeInfo.getName().equals(name)){
            list.add(node);
        }
        if(node.firstChildren != null){
            findByName(node.firstChildren,name,list);
        }
        if(node.nextSlid != null){
            findByName(node.nextSlid,name,list);
        }
        return list;
    }

    /**
     * 返回被r区域覆盖的所有id
     * @param r
     * @return
     */
    public List<Long> findIdByR(NodeInfoTemp r){
        List<Long> nodeList = new ArrayList<>();
        return findIdByR(getTree(),r,nodeList);
    }

    /**
     * 返回被r区域覆盖的所有id
     * 能否通过后序遍历来实现：如果所有子节点都被覆盖那么只输出父节点？
     * @param node
     * @param r
     * @param nodeList
     * @return
     */
    private List<Long> findIdByR(Node<NodeInfoTemp> node, NodeInfoTemp r,List<Long> nodeList){

        if(converEnough(node.nodeInfo,r)){
            nodeList.add(node.nodeInfo.getId());
        }
        if(node.firstChildren != null){
            findIdByR(node.firstChildren,r,nodeList);
        }
        if(node.nextSlid != null){
            findIdByR(node.nextSlid,r,nodeList);
        }
        return nodeList;
    }

    /**
     * 是否达到了90%覆盖率
     * @param node
     * @param r
     * @return
     */
    public boolean converEnough(NodeInfoTemp node, NodeInfoTemp r){
        float nodeArea = node.getArea();
        float crossArea = crossArea(node,r);
        float rate = (crossArea / nodeArea)* 100;
        return rate >= 90;
    }

    /**
     * 计算覆盖面积
     * @param node
     * @param r
     * @return
     */
    public float crossArea(NodeInfoTemp node, NodeInfoTemp r){
        float nodeX = node.getPoint().getX();
        float nodeY = node.getPoint().getY();
        float nodeWidth = node.getSize().getWidth();
        float nodeHight = node.getSize().getHight();
        float rX = r.getPoint().getX();
        float rY = r.getPoint().getY();
        float rWidth = r.getSize().getWidth();
        float rHight = r.getSize().getHight();

        float crossWidth = 0;
        //长度的计算
        if(nodeX >= rX && (nodeX + nodeWidth) <= (rX + rWidth)){
            crossWidth = nodeWidth;
        }else if(nodeX >= rX && nodeX < (rX + rWidth) && (nodeX + nodeWidth) > (rX + rWidth)){
            crossWidth = rWidth - (Math.abs(nodeX - rX));
        }else if (nodeX < rX && (nodeX + nodeWidth) <= (rX + rWidth) && (nodeX + nodeWidth) > rX){
            crossWidth = nodeWidth - (Math.abs(nodeX - rX));
        }

        float crossHight = 0;
        //宽度的计算
        if(nodeY >= rY && (nodeY + nodeHight) <= (rY + rHight)){
            crossHight = nodeHight;
        }else if(nodeY >= rY && nodeY < (rY + rHight) && (nodeY + nodeHight) > (rY + rHight)){
            crossHight = rHight - (Math.abs(rY - nodeY));
        }else if(nodeY < rY && (nodeY + nodeHight) <= (rY + rHight) && (nodeY + nodeHight) > rY){
            crossHight = nodeHight - (Math.abs(rY - nodeY));
        }
        //返回覆盖面积
        return crossWidth * crossHight;

    }

    /**
     * 定义NODE内部类
     * @param <AnyType>
     */
    private static class Node<AnyType>{
        AnyType nodeInfo;
        Node<AnyType> firstChildren;
        Node<AnyType> nextSlid;

        Node(AnyType info, Node<AnyType> firstChildren, Node<AnyType> nextSlid){
            this.nodeInfo = info;
            this.nextSlid = nextSlid;
            this.firstChildren = firstChildren;
        }

        Node(AnyType info){
            this(info,null,null);
        }

        public String toString(){
            return nodeInfo.toString();
        }
    }


    public static void main(String[] args) {
        //初始化树
        NodeInfoTemp nodeInfoTemp = new NodeInfoTemp(1,"root",new Point(0,0),new Size(100,200));
        C13CbLockTree2 testDemo = new C13CbLockTree2(new Node<>(nodeInfoTemp));
        //新增节点
        System.out.println("====开始新增节点=======");
        NodeInfoTemp nodeInfoTemp2 = new NodeInfoTemp(2,"root",new Point(0,0),new Size(100,200));
        testDemo.addUnder(testDemo.getTree(),nodeInfoTemp2);
        //根据名称返回所有节点
        System.out.println("====开始查找相同名字的节点=======");
        System.out.println(testDemo.findByName("root"));
        System.out.println("====查找被r覆盖的节点=======");
        NodeInfoTemp r = new NodeInfoTemp(3,"root",new Point(0,0),new Size(100,50));
        System.out.println(testDemo.findIdByR(r));
    }

}

/**
 * 节点信息类
 */
class NodeInfoTemp{
    private long id;
    private String name;
    private Point point;
    private Size size;

    NodeInfoTemp(long id, String name, Point p, Size s){
        this.id = id;
        this.name = name;
        this.point = p;
        this.size = s;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String toString(){
        return "id:" + id + ",name:" + name;
    }

    /**
     * 返回面积
     * @return
     */
    public float getArea(){
        return size.getHight() * size.getWidth();
    }
}

/**
 * 节点坐标类
 */
class Point{
    private float x;
    private float y;
    Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

/**
 * 节点尺寸类
 */
class Size{
    private float width;
    private float hight;
    Size(float width, float hight){
        this.width = width;
        this.hight = hight;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHight() {
        return hight;
    }

    public void setHight(float hight) {
        this.hight = hight;
    }
}





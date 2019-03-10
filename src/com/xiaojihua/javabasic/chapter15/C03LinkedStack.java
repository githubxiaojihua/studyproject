package com.xiaojihua.javabasic.chapter15;

/**
 * 知识点：
 * 1、自定义实现下推栈。
 * 2、末端哨兵的使用
 * @param <T>
 */
public class C03LinkedStack<T> {

    /**
     * 嵌套类实现内部节点
     * 注意使用的类型参数为U。
     * @param <U>
     */
    private static class Node<U>{
        U element;
        Node<U> next;
        Node(){
            element = null;
            next = null;
        }

        Node(U ele, Node<U> next){
            this.element = ele;
            this.next = next;
        }

        //探测是否为空
        public boolean end(){
            return (element == null) && (next == null);
        }
    }

    /**
     * 末端哨兵：
     * 在生成C03LinkedStack对象的时候就创建了，
     * 默认是一个空的node。
     * 当push的时候top指向新节点，新节点的next
     * 就是原来的top，因此，末端哨兵起到了一个在
     * 栈为空时候的探测。
     *
     * 还有另外如果在pop的时候，到达了末端哨兵，
     * 那么就不会在继续top = top.next了
     * 因此虽然可以一直调用pop但是top一直会停留在
     * 末端哨兵处。而返回的值也一直是null。
     */
    private Node<T> top = new Node<>();

    public void push(T ele){
        top = new Node<>(ele, top);
    }

    public T pop(){
        T result = top.element;
        //到达末端哨兵，不在继续next
        if(!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args){
        String[] strArr = "my name is xiaojihua".split(" ");
        C03LinkedStack<String> linkedStack = new C03LinkedStack<>();
        for(String str : strArr){
            linkedStack.push(str);
        }

        //由于pop会直接弹出并返回元素，因此将元素放到while外面接收，然后再while内部再重新赋值。
        String s = null;
        while((s = linkedStack.pop()) != null){
            System.out.println(s);
        }
    }
}

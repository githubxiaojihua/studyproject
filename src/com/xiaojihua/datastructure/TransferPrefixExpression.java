package com.xiaojihua.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 将前缀表达式转换为后缀表达式（操作符号只有+*（）），优先级由高到低（），* ，+
 * 1、当读取到一个操作数的时候（在本例中是char），立即把它放到输出中
 * 2、操作符不立即输出，放到栈中。
 * 3、如果遇到右括号则弹出栈，并将弹出的符号放到输出中，直到遇到左括号为止，左括号只弹出但是不输出
 * 4、如果遇到+*（等则将栈等优先级高的元素弹出（含优先级相同的）并放到输出中，知道遇到更低优先级。但是除非是在处理）否则绝不将（弹出。
 * 5、弹出工作完成以后则压入当前操作符。
 * 6、如果输入结束则将栈中所有元素弹出，并放到输出中。
 */
public class TransferPrefixExpression {

    public static void main(String[] args) {
        String preFixExp = "a+b*c+(d*e+f)*g";
        System.out.println(transfer(preFixExp));
    }

    /**
     * 转换主方法
     * @param expression
     * @return
     */
    public static String transfer(String expression){
        StringBuffer postFixExpression = new StringBuffer();//输出
        Stack<Character> opStack = new Stack();//栈
        //开始输入
        for(int i=0; i<expression.length(); i++){
            if(Character.isLetter(expression.charAt(i))){
                postFixExpression.append(expression.charAt(i));//直接输出
            }else{
                char op = expression.charAt(i);
                String optStr = popOpts(opStack,op);//弹出的操作符
                postFixExpression.append(optStr);
                if(op != ')'){
                    opStack.push(expression.charAt(i));//压入操作符
                }
            }
        }
        //输入结束弹出所有操作符
        while(!opStack.empty()){
            postFixExpression.append(opStack.pop());
        }
        return postFixExpression.toString();
    }

    /**
     * 检验是否应该弹出。通过判断优先级，如果栈顶的优先级高那么弹出
     * @param op1 栈顶操作符
     * @param op2 输入操作符
     * @return
     */
    public static boolean isPop(char op1, char op2){
        Map<Character,Integer> priorityMap = new HashMap<Character,Integer>();
        //设置操作符优先级，越小优先级越高
        priorityMap.put('(',0);
        priorityMap.put(')',0);
        priorityMap.put('*',1);
        priorityMap.put('+',2);

        int op1Num = priorityMap.get(op1);
        int op2Num = priorityMap.get(op2);
        return op1Num<=op2Num;
    }

    /**
     * 弹出操作符的主方法。
     * 分两种情况：当前处理的操作符为），或者其他
     * 第一中情况，弹出到（为止的所有操作符
     * 第二种情况，根据需要弹出操作符，如果有未处理的（则终止
     * @param stack
     * @param opt
     * @return
     */
    public static String popOpts(Stack<Character> stack,char opt){
        StringBuffer optStr = new StringBuffer();
        if(opt == ')'){
            while( !stack.empty() &&stack.peek()!='('){
                char popChar = stack.pop();
                optStr.append(popChar);
            }
            stack.pop();
        }else{
            while(!stack.empty() && isPop(stack.peek(),opt)){
                if(stack.peek() == '('){
                    break;
                }
                char popChar = stack.pop();
                optStr.append(popChar);
            }
        }
        return optStr.toString();
    }
}

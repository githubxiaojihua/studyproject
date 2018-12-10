package com.xiaojihua.datastructure;

import java.util.*;

/**
 * 练习专用类
 *
 */

/**
 * 将前缀表达式转换为后缀表达式（操作符号只有+*（）），优先级由高到低（），* ，+
 * 1、当读取到一个操作数的时候（在本例中是char），立即把它放到输出中
 * 2、操作符不立即输出，放到栈中。
 * 3、如果遇到右括号则弹出栈，并将弹出的符号放到输出中，直到遇到左括号为止，左括号只弹出但是不输出
 * 4、如果遇到+*（等则将栈等优先级高的元素弹出（含优先级相同的）并放到输出中，直到遇到更低优先级。但是除非是在处理）否则绝不将（弹出。
 * 5、弹出工作完成以后则压入当前操作符。
 * 6、如果输入结束则将栈中所有元素弹出，并放到输出中。
 *
 * 假设：表达式由字母和操作符组成
 */
public class TestDemo {
    private static final Map<Character,Integer> operMap = new HashMap<>();
    static{
        operMap.put('(',0);
        operMap.put(')',0);
        operMap.put('*',1);
        operMap.put('+',2);
    }

    public static String translate(String expression){
        StringBuffer str = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for(char c : expression.toCharArray()){
            if(Character.isLetter(c)){
                str.append(c);
            }else{
                str.append(popOpreater(stack,c));
                if(c != ')'){
                    stack.push(c);
                }

            }
        }
        while(!stack.empty()){
            str.append(stack.pop());
        }
        return str.toString();
    }

    public static String popOpreater(Stack<Character> stack, char operator){
        StringBuffer str = new StringBuffer();

        if(')' == operator){
            while(!stack.empty() && stack.peek() != '('){
                str.append(stack.pop());
            }
            stack.pop();
        }else{
            while(!stack.empty() && isPop(stack.peek(),operator)){
                if(stack.peek() == '('){
                    break;
                }
                str.append(stack.pop());
            }
        }
        return str.toString();
    }

    public static boolean isPop(char peekOper,char oper){
        return operMap.get(peekOper) <= operMap.get(oper);
    }

    public static void main(String[] args) {
        String preFixExp = "a+b*c+(d*e+f)*g";
        System.out.println(translate(preFixExp));
    }

}




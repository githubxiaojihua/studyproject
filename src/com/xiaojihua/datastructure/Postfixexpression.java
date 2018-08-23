package com.xiaojihua.datastructure;

import java.util.Stack;

/**
 * 栈实现计算后缀表达式
 * @author Administrator
 *
 */
public class Postfixexpression {

	public static void main(String[] args) {
		String expression = "6523+8*+3+*";
		System.out.println(calculate(expression));
	}

	/**
	 * 计算主方法
	 * @param expression
	 * @return
	 */
	public static int calculate(String expression){

		// 声明空栈
		Stack<Integer> pfStack = new Stack<Integer>();

		// 依次遍历字符串，是数字则压入栈，遇到操作符则出栈运算后将结果入栈
		// 这里默认字符串元素不是数字就是操作符
		for(int i=0; i<expression.length(); i++){
			Character ch = expression.charAt(i);
			if(ch.isDigit(ch)){
				pfStack.push(Character.getNumericValue(ch));
			}else{
				int x = pfStack.pop();
				int y = pfStack.pop();
				int result = doOperation(x,y,ch);
				pfStack.push(result);
			}
		}

		return pfStack.pop();
	}

	/**
	 * 实施具体操作。根据操作符计算结果。只提供加减乘除法
	 * @param x
	 * @param y
	 * @param operater
	 * @return
	 */
	public static int doOperation(int x,int y,char operater){

		int result = 0;

		switch(operater){

			case '+':
				result = x + y;
				break;
			case '-':
				result = x - y;
				break;
			case '*':
				result = x * y;
				break;
			case '/':
				result = x / y;
				break;
		}

		return result;
	}

}
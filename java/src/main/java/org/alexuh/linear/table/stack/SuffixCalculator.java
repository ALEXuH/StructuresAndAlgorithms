package org.alexuh.linear.table.stack;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/12
 * 后缀表达式计算结果 & 中缀表达式转后缀表达式
 *
 * 后缀表达式(逆波兰表达式): 符号在数字后面
 *
 * 后缀表达式计算结果思想:
 * 从左到右进行扫描当遇到数字时入栈,遇到符号时弹出栈顶的2个数据进行运算并将结果入栈，直到扫描到最后
 *
 */
public class SuffixCalculator {

    public static void main(String[] args) {

        // 中缀表达式转后缀
        System.out.println(inFixToSuffixFix("1 + ( ( 2 + 3 ) * 4 ) - 5")); // 123+4*+5-

        // 中缀表达式   1+((2+3)*4)-5 => 后缀表达式  123+4*+5-
        // 给定后缀表达式计算结果
        String expr = "1 2 3 + 4 * + 5 - ";
        System.out.println(suffixCalculate(expr));  // 16
    }

    // 后缀表达式计算
    public static int suffixCalculate(String expr) {
        String pattern = "\\d+";
        Stack<String> values = new Stack<>();
        String[]  elements = expr.split(" ");

        for (String ele : elements) {
            // 匹配为数字入栈
            if (Pattern.matches(pattern, ele)) {
                values.push(ele);
            } else {
               // 匹配为运算符：计算并压入栈
                values.push(caculate(values.pop(), values.pop(), ele));
            }
        }
        return Integer.parseInt(values.pop());
    }

    // 下面的元素在前 运算符 栈顶元素在后
    private static String caculate(String s, String s1, String ch) {
        int num = 0;
        switch (ch) {
            case "+":
                num = Integer.parseInt(s) + Integer.parseInt(s1);
                break;
            case "-":
                num = Integer.parseInt(s1) - Integer.parseInt(s);
                break;
            case "*":
                num = Integer.parseInt(s1) * Integer.parseInt(s);
                break;
            case "/":
                num = Integer.parseInt(s1) / Integer.parseInt(s);
                break;
        }
        return String.valueOf(num);
    }


    // 中缀表达式转后缀
    // 1.初始化2个栈，s1存储操作数, s2存储符号（运算符和括号）
    // 2.从左向右扫描中缀表达式，如果为操作数直接压入s1
    // 3.如果遇到运算符，当s2为空或者为左括号直接压入栈,不为空时比较与栈顶元素优先级,如果比栈顶元素优先级高则入栈，否则将栈顶元素压入s1并将新的元素压入s2
    //   如果遇到右括号则依次弹出s2的元素压入s1直到遇到左括号
    // 4.扫描完成后依次将s2的数据弹出压入s1，返回s1的倒叙结果即为后缀表达式
    public static String inFixToSuffixFix(String expr) {
        Stack<String> s1 = new Stack();  // 存储操作数
        Stack<String> s2 = new Stack();  // 存储符号（运算符和括号）
        String[]  elements = expr.split(" ");
        String pattern = "\\d+";

        for(String ele : elements) {
            // 匹配到操作数和左括号
            if (Pattern.matches(pattern, ele)) {
                s1.push(ele);
            } else {
                // 匹配到符号
                if (s2.isEmpty()) {
                    s2.push(ele);
                } else {
                    if (ele.equals(")")) {
                        // 找到左括号结束循环并弹出左括号
                        while(!s2.peek().equals("(")) {
                            s1.push(s2.pop());
                        }
                        s2.pop();
                    } else {
                        // 符号优先级大于栈顶或者等于左括号
                        if (getPriority(ele) > getPriority(s2.peek()) || ele.equals("(")){
                            s2.push(ele);
                        } else {
                            s1.push(s2.pop());
                            s2.push(ele);
                        }
                    }
                }
            }
        }
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        StringBuilder sb = new StringBuilder();
        while(!s1.isEmpty()) {
            sb.append(s1.pop());
        }
        return sb.reverse().toString();
    }

    // 判断符号运算优先级
    private static int getPriority(String expr) {
        int p = 0;
        switch (expr) {
            case "(":
                p = 0;
                break;
            case "+":
            case "-":
                p = 1;
                break;
            case "*":
            case "/":
                p = 2;
                break;
        }
        return p;
    }
}

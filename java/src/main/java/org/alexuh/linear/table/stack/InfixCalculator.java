package org.alexuh.linear.table.stack;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 * 使用栈实现简易计算器
 *
 * 中缀表达式计算: 符号在数字中间
 *解法：
 * 1.使用2个栈，栈1栈2，对表达式进行扫描
 * 2.当遇到为数字时存储到栈1
 * 3.当遇到为符号时，如果符号栈为空则直接存入继续扫描，如果符号栈不为空且目前栈顶符号的优先级大于等于扫描到符号，则取出栈1的2个元素进行计算，然后将数据重新压入栈1，最后将扫描到的符号存入栈2。如果扫描到的符号优先级高于栈顶的符号，则直接压入栈
 * 4.持续上述步骤，如果符号栈中不为空，则从栈1中取出数进行计算，直到符号栈中为空，此时栈1中仅有一个结果即为计算值
 */
public class InfixCalculator {

    public static void main(String[] args) {
        String expr = "7*10+2*30-10+20"; // 第一次 7 10   * 扫描到+时计算继续扫描  70 2  30 +  * - 扫描到-小于栈顶计算前面数据 70 60 + -  以此类推
        //String expr = "7*10+2*30-10-20/10"; //  70 50 20 + +
        ArrayStack valueStack = new ArrayStack();
        ArrayStack exprStack = new ArrayStack();
        char[] exprChar = expr.toCharArray();

        for(char a : exprChar) {
            System.out.println(a);
        }

        List<String> exprs = new ArrayList<>();
        exprs.add("+");
        exprs.add("-");
        exprs.add("*");
        exprs.add("/");

        StringBuilder sb = new StringBuilder();
        for(char i : exprChar) {
            if (exprs.contains(String.valueOf(i))) {
                // 值入栈和符号入栈并判断
                valueStack.add(sb.toString());
                if (exprStack.isEmpty()) {
                    exprStack.add(String.valueOf(i));
                } else {
                    // 判断栈顶优先级
                    String top = exprStack.get(); // 栈顶
                    if (getPriority(top) >= getPriority(String.valueOf(i))) {
                        valueStack.add(caculate(valueStack.get(), valueStack.get(), top));
                        exprStack.add(String.valueOf(i));
                    } else {
                        exprStack.add(top);
                        exprStack.add(String.valueOf(i));
                    }
                }
                // 重置stringbuilder
                sb.delete(0, sb.length());
            } else{
                sb.append(i);
            }
        }
        valueStack.add(sb.toString());

        // 计算栈中的剩余数据
        while(!exprStack.isEmpty()) {
            valueStack.add(caculate(valueStack.get(), valueStack.get(), exprStack.get()));
        }
        System.out.println("-------------");
        System.out.println("计算的值为: " + valueStack.get());
        System.out.println("-------------");

    }

    // 计算值
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

    // 判断符号运算优先级
    private static int getPriority(String expr) {
        int p = 0;
        switch (expr) {
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

package org.alexuh.questions.leecode;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/26
 *
 * 栈和队列相关问题
 *
 * 优先队列
 *
 */
public class StackAndQueueQuestions {

    public static void main(String[] args) {
        System.out.println(new StackAndQueueQuestions().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 解法: 利用栈的后进先出特性解决
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] sc = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < sc.length; i++) {
            if (stack.isEmpty() || sc[i] == '(' || sc[i] == '{' || sc[i]=='[') {
                stack.push(sc[i]);
            } else {
                char v = stack.peek();
                if (sc[i] == ')' && v == '('){
                    stack.pop();
                } else if (sc[i] == '}' && v == '{') {
                    stack.pop();
                } else if (sc[i] == ']' && v == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     *
     * 解法：消消乐类似于上面一道，利用栈的特性进行消除
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        char[] sc = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < sc.length; i++) {
            if (stack.isEmpty()) {
                stack.push(sc[i]);
            } else {
                char v = stack.peek();
                if (v == sc[i]) {
                    stack.pop();
                } else {
                    stack.push(sc[i]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }

    /**
     * 150. 逆波兰表达式求值
     *根据 逆波兰表示法，求表达式的值。
     *
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     *
     * 解法：使用栈
     * 1.遍历后缀表达式依次入栈遇到符号拿出栈顶的2个元素进行计算
     *
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for(String c : tokens) {
            if (Pattern.matches("\\d+", c)) {
                stack.push(c);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                switch (c) {
                    case "+":
                        stack.push(String.valueOf(num2 + num1));
                        break;
                    case "-":
                        stack.push(String.valueOf(num2 - num1));
                        break;
                    case "*":
                        stack.push(String.valueOf(num2 * num1));
                        break;
                    case "/":
                        stack.push(String.valueOf(num2 / num1));
                        break;
                    default:

                }
            }
        }
        return Integer.parseInt(stack.pop());
    }


}

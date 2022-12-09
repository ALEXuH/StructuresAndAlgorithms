package org.alexuh.linear.table.stack;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 */
public class StackTest {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
        // 入栈
        stack.add("1");
        stack.add("2");
        stack.add("3");

        // 出栈
        System.out.println(stack.get());
        System.out.println(stack.get());
        System.out.println(stack.get());
    }
}

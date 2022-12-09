package org.alexuh.linear.table.stack;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 *
 * 栈：先进后出,只允许栈顶插入和删除
 */
public class ArrayStack {

    public int top = -1; // 栈顶
    public String[] arr = new String[10];
    public int size; // 栈中元素个数

    // 添加数据 入栈
    public void add(String v) {
        if(top == arr.length - 1) {
            System.out.println("栈已满");
            return;
        }
        top++;
        arr[top] = v;

    }

    // 取出数据 出栈
    public String get() {
        if (top == -1) {
            System.out.println("栈为空");
        }
        String v = arr[top];
        top--;
        return v;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

}

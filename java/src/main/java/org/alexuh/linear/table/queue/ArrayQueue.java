package org.alexuh.linear.table.queue;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/19
 *
 * 队列： 先进先出
 * 使用数组模拟队列 环形数组队列（核心思想： 当头或尾指针到数组最后时将其下标重置为0）
 */
public class ArrayQueue {
    public int maxSize; // 队列最大容量

    public int size = 0; // 当前队列存在元素个数

    public int  last = 0; // 队列尾 只能插入(指向的是当前的下一位)
    public int first = 0; // 队列头 只能删除
    public int[] arr; // 存放数据的数组

    // 初始化队列
    public ArrayQueue(int queueSize) {
        maxSize = queueSize;
        arr = new int[maxSize];
    }

    // 判断队列是否已满
    public boolean isFull() {
        return size == maxSize;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 添加数据 队尾 入列
    public void add(int v) {
        if (isFull()) {
            throw new RuntimeException("队列已满 不能添加数据");
        }
        // 如果last在最后则重置为0
        arr[last] = v;
        size++;
        if (last == maxSize - 1) {
            last = 0;
        } else {
            last++;
        }


    }

    // 获取数据 出列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int v = arr[first];
        size--;
        // 如果first在最后则重置为0
        if (first == maxSize - 1) {
            first = 0;
        } else {
            first++;
        }
        return v;
    }

    // 展示当前队列的所有值
    public void show() {
        if (first == last) {
            // 当last到最后时也会重置为0，此时队列为满状态
            if (size == 0) {
                System.out.println("当前队列为空");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                }
            }
        } else if (first < last) {
            for (int i = first; i <= last - 1; i++) {
                System.out.println(arr[i]);
            }
        } else {
            for (int i = last; i <= first - 1; i++) {
                System.out.println(arr[i]);
            }
        }

    }

}

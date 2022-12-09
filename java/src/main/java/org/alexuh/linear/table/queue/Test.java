package org.alexuh.linear.table.queue;

import org.alexuh.linear.table.queue.ArrayQueue;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/19
 */
public class Test {

  public static void main(String[] args) {
    try {
        // 队列测试
        ArrayQueue queue = new ArrayQueue(3);
        // 入列
        queue.add(1);
        queue.add(2);
        queue.add(3);
        //queue.add(4);
        System.out.println("-----");
        queue.show();
        System.out.println("--------- 取出2个数据");
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println("------ 在添加2个数据");
        queue.add(5);
        queue.add(6);
        //queue.add(7);
        queue.show();
        System.out.println("-------在取出2个数据");
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println("--------- 打印当前剩余数据");
        queue.show();
    }catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
  }
}

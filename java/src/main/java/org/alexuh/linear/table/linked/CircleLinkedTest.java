package org.alexuh.linear.table.linked;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 * 约瑟夫问题单向环形链表测试
 */
public class CircleLinkedTest {

    public static void main(String[] args) {
        CircleLinked link = new CircleLinked();
        link.generateLinkedlist(5);
        link.list();
        //link.killOrder2(1, 2,5); // 2 -> 4 -> 1 -> 5 -> 3
        link.killOrder(1, 3,5); // 3 -> 1 ->  5 ->  2 ->  4
    }

}

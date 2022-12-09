package org.alexuh.linear.table.linked;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 *
 * 单向环形链表 ，首尾相连
 */
public class CircleLinked {

    CircleNode head = null; // 头节点

    // 直接生成个数为n的环形链表
    public void generateLinkedlist(int n) {
        // 添加到最后一个节点,无head的链表，第一个需要特殊生成
        if (n == 1) {
            head = new CircleNode(1);
            head.next = head;
        } else {
            head = new CircleNode(1);
            head.next = head;
            for (int i = 2; i < n +1 ; i++) {
                CircleNode cur = head;
                CircleNode addNode = null;
                while(true) {
                    if(cur.next == head) {
                        addNode = new CircleNode(i);
                        cur.next = addNode;
                        addNode.next = head;
                        break;
                    }
                    cur = cur.next;
                }
            }

        }
    }
    // 打印当前链表
    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        CircleNode cur = head;
        // 当前与下一个节点相同时结束循环
        while(cur.next != head) {
            System.out.println(cur.toString());
            cur = cur.next;
        }
        System.out.println(cur.toString());


    }

    // 约瑟夫问题，丢手绢
    // 获取杀掉的顺序 N个人围成一圈，第一个人从K开始报数，报M的将被杀掉，下一个人接着从1开始报。如此反复，最后剩下一个，求最后的胜利者。
    // 解法一:  单向循环链表，使用双指针（防止断链), 1.借助2个辅助指针一个指向head一个指向链表尾部 2.出圈先同时移动2个指针k次 3.报数时同时让指针移动m-1次，此时front所在节点出圈 first = first.next; tail.next = first;
    // 解法二: 单向循环链表 : 单指针移动，获取出圈节点的前一个节点然后 front.next = front.next.next;，需要考虑m=1时候单向链表无法获取前一个节点
    // 解法三: 双向循环链表 : 单指针移动，找到出圈的节点再将环形链表链接 cur.pre.next = cur.next; cur.next.pre = cur.pre
    public void killOrder(int k , int m, int n) {
        if (head == null || k < 1 || m < 1 || k > n) {
            System.out.println("参数输入有误");
        }
        CircleNode front = head;
        CircleNode tail = head;
        // 找到尾部,
        while(tail.next != head) {
            tail = tail.next;
        }
        // 同时向前移动k - 1次
        for (int i = 0; i < k - 1; i++) {
            front = front.next;
            tail = tail.next;
        }

        // 报数时同时使指针移动，front节点即为出圈节点 , 自己也要报数，所以为移动m-1次
        while(front != tail) {
            for (int i = 0; i < m - 1; i++) {
                front = front.next;
                tail = tail.next;
            }
            System.out.println("出圈节点:" + front.toString());
            tail.next = front.next; // 连接首位
            front = front.next;  // front向前移动一位
        }
        System.out.println("最后出圈节点: " + front.toString());
    }

    // 约瑟夫问题，丢手绢
    // 获取杀掉的顺序 N个人围成一圈，第一个人从K开始报数，报M的将被杀掉，下一个人接着从1开始报。如此反复，最后剩下一个，求最后的胜利者。
    // 解法一:  单向循环链表，使用双指针（防止断链), 1.借助2个辅助指针一个指向head一个指向链表尾部 2.出圈先同时移动2个指针k次 3.报数时同时让指针移动m-1次，此时front所在节点出圈 first = first.next; tail.next = first;
    // 解法二: 单向循环链表 : 单指针移动，获取出圈节点的前一个节点然后 front.next = front.next.next;，需要考虑m=1时候单向链表无法获取前一个节点
    // 解法三: 双向循环链表 : 单指针移动，找到出圈的节点再将环形链表链接 cur.pre.next = cur.next; cur.next.pre = cur.pre
    public void killOrder2(int k , int m, int n) {

        if (head == null || k < 1 || m < 1 || k > n ) {
            System.out.println("参数输入有误");
        }
        // 当m=1时只需要遍历链表
        if (m == 1) {
            CircleNode cur = head;
            while(cur.next != head) {
                System.out.println("当前出圈为: " + cur.toString());
                cur = cur.next;
            }
        } else {
            CircleNode front = head;
            CircleNode temp = null;
            // 向前移动k - 1次
            for (int i = 0; i < k - 1; i++) {
                front = front.next;
            }

            // 报数时同时使指针移动，找到出圈前一个节点
            while(front.next != front) {
                for (int i = 0; i < m - 2; i++) {
                    front = front.next;
                }
                System.out.println("出圈节点:" + front.next.toString());
                front.next = front.next.next;
                front = front.next;
            }
            System.out.println("最后出圈节点: " + front.toString());
        }
    }
}

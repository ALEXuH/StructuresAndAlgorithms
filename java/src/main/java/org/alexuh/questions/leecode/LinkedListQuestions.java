package org.alexuh.questions.leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/24
 *
 * 链表相关问题
 *
 */
public class LinkedListQuestions {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        removeNthFromEnd(node1, 2);


    }

    /**
     *
     * 203. 移除链表元素
     *
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     *
     *  notice: 注意删除头部节点(单链表删除需要找到删除节点的上一个节点)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode cur = newHead;
        while(cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next; // cur不变继续遍历next也就是上个next.next
            } else {
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * 解法：使用虚拟头节点node，对node.next和node.next.next进行交换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode cur = newHead;
        while (cur.next != null && cur.next.next != null) {
            ListNode temp = cur.next;
            ListNode temp1 = cur.next.next.next;
            cur.next = cur.next.next;
            cur.next.next = temp;
            temp.next = temp1;
            cur = cur.next.next;

        }
        return newHead.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     *
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
     *
     *
     * 解法：双指针，慢指针: 快指针:慢指针先向前走n步，当快指针的next是空说明慢指针就是要删除的位置
     *  notice : 考虑到可能删除第一个节点，所以要使用虚拟节点 （删除需要找到删除节点的前一个节点）
     * 1.获取链表倒数n+1个位置节点cur
     * 2.cur.next = cur.next.next
     *
     * @param head
     * @param n
     * @return
     */
    public static  ListNode removeNthFromEnd(ListNode head, int n) {
        // 考虑到可能删除第一个节点，所以使用虚拟节点
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode bNode = head;
        for (int k = 0; k < n-1; k++) {
            bNode = bNode.next;
        }
        ListNode fNode =  newHead;
        while(bNode.next != null) {
            bNode = bNode.next;
            fNode = fNode.next;
        }
        fNode.next = fNode.next.next;
        return newHead.next;
    }

    /**
     * 142. 环形链表 II
     *给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 解法：双指针
     * 1.慢指针每次移动一次，快指针移动2次，如果有环一定会在环中相遇
     * 2.根据数学推理可知,设环中相遇点为h2,将h1指向head节点2个指针相遇的点就是环形链表入口
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 找到环相遇点
            if (fast == slow) {
                ListNode h1 = head;
                while(h1 != slow) {
                    h1 = h1.next;
                    slow = slow.next;
                }
                return h1;
            }
        }
        return null;
    }

    /**
     *面试题 02.07. 链表相交
     *给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null
     *
     * 解法：hash集合
     * 1.遍历链表a放入集合
     * 2.遍历链表 b判断是否在集合中
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;
        Set<ListNode> nodes =  new HashSet<>();
        while(temp != null) {
            nodes.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null) {
            if (nodes.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

}


 class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


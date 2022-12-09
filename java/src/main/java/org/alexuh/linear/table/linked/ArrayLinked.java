package org.alexuh.linear.table.linked;


import java.util.Stack;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/20
 * 单向链表
 * 1.以节点方式存储,每个节点包含data和下一个节点的指针
 * 2.各个节点可以是不连续的内存
 * 3.链表分为带head和没有head的链表
 *
 * crud: 从头遍历链表找到需要的位置
 * 单链表翻转
 */
public class ArrayLinked {

    // 头节点
    public HeroNode head = new HeroNode(0, "", "");

    // 添加到链表最后
    public void add(HeroNode hero) {
        // 查找到最后一个节点
        HeroNode temp = head;
        while(true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
    }
    // 按英雄顺序添加
    public void addOrder(HeroNode hero) {
        HeroNode temp = head;
        while(true) {
            // 查找到最后节点
            if (temp.next == null) {
                break;
            }
            // 比较编号获取添加位置的前一个节点
            if (temp.next.num > hero.num) {
                hero.next = temp.next;
                break;
            } else if (temp.next.num == hero.num){
                throw new RuntimeException("相同编号不能添加");
            }
            temp = temp.next;
        }
        temp.next = hero;
    }
    // 删除
    public void del(int num) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        HeroNode temp = head;
        while(true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.num == num) {
                temp.next = temp.next.next;
                // 如果为最后一位 temp.next == null
                if (temp.next == null) {
                    break;
                }
            }
            temp = temp.next;
        }
    }
    // 修改
    public void update(int num, String name , String nickname) {
        // 查找到最后一个节点
        HeroNode temp = head;
        while(true) {
            if (temp.next == null) {
                if (temp.num == num) {
                    temp.name = name;
                    temp.nickname = nickname;
                }
                break;
            }
            if (temp.num == num) {
                temp.name = name;
                temp.nickname = nickname;
            }
            temp = temp.next;
        }
    }
    // 查询
    public void search(int num) {
        // 查找到最后一个节点
        HeroNode temp = head.next;
        while(true) {
            if (temp.next == null) {
                if (temp.num == num) {
                    System.out.println(temp.toString());
                }
                break;
            }
            if (temp.num == num) {
                System.out.println(temp.toString());
            }
            temp = temp.next;
        }
    }
    // 打印当前链表
    public void list() {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp.next == null) {
                System.out.println(temp.toString());
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    // 清除链表
    public void clear() {
        this.head.next = null;
    }

    // 问题1：获取单链表的节点个数
    public static int getLinkedLenght(HeroNode hero) {
        if (hero.next == null) {
            return 0;
        }
        HeroNode temp = hero.next;
        int len = 0;
        while(temp.next != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }
    // 问题2：获取链表倒数第k个数
    // 解法1： 首先遍历一遍获取单链表长度length，转化为链表第length-k个节点，需要遍历2次
    // 解法2： 对每一个节点进行测试看遍历k个元素后是不是刚好到达链表尾部
    // 解法3：利用解法2，使用双指针（快慢指针），一个指针比另一个先走k步，当快指针为null时移动到链表尾部，前一个指针的位置即为所求值

    public HeroNode getKFromEnd(HeroNode head, int k) {
        // 快指针先走k步
//        while(c1 != null && k > 0) {
//            c1 = c1.next;
//            k--;
//        }
        HeroNode c1 = head;
        for (int i = 0; i < k; i++) {
            c1 = c1.next;
        }
        // 双指针同时走
        HeroNode c2 = head;
        while(c1 != null) {
            c1 = c1.next;
            c2 = c2.next;
        }
        return c2;
    }

    //  单链表翻转: 链表反转 情况1:不带head头,head = newHeadNode.next  情况2: 带head头,head.next = newHeadNode.next;
    public void reserveLinked() {
        HeroNode newHeadNode = new HeroNode(0, "","");
        HeroNode cur = head; // 当前遍历到的节点
        HeroNode next; // 记录遍历的下一个节点
        while(cur != null) {
            next = cur.next;
            cur.next = newHeadNode.next;
            newHeadNode.next = cur;
            cur = next;
        }
        System.out.println(newHeadNode.next);
        // 无固定head头,只需要重置head
        head = newHeadNode.next;
        // 有head头节点将头节点的下一个指向反转后的链表
        //head.next = newHeadNode.next;
    }

    // 反向遍历链表
    public void reserverList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while(temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while(stack.size() > 0) {
            System.out.println(stack.pop().toString());
        }
    }

}

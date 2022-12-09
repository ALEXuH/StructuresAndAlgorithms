package org.alexuh.linear.table.search.hashtable;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 * 链表
 *
 */
public class LinkedListDemo {

    Emploee emo = null; // head节点

    public LinkedListDemo() {
    }

    // 添加雇员

    public void add(Emploee emploee) {
        if(emo == null) {
            emo = emploee;
        } else {
            Emploee temp = emo;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = emploee;
        }
    }
    // 打印雇员
    public void list() {
        if (emo == null) {
            System.out.println("当前没有雇员");
        } else {
            Emploee temp =  emo;
            while (temp.next != null) {
                System.out.println("当前雇员信息: "+temp.toString());
                temp = temp.next;
            }
            System.out.println("当前雇员信息: "+temp.toString());
        }
    }
    // 查找雇员
    public void search(int id) {
        if (emo == null) {
            System.out.println("当前没有雇员");
        } else {
            Emploee temp =  emo;
            while(true) {
                if(temp.next == null) {
                    if (temp.id == id) {
                        System.out.println("雇员信息:" + temp.toString());
                    }
                    break;
                }
                if (temp.id == id) {
                    System.out.println("雇员信息:" + temp.toString());
                }
                temp = temp.next;
            }
        }
    }
}

package org.alexuh.linear.table.search.hashtable;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 * hash表
 *
 * 定义散列函数 （v % size 取模）
 * 注意链表开始要初始化
 *
 */
public class HashTableDemo {
    public int size = 16;
    public LinkedListDemo[] emploeeList;

    public HashTableDemo() {
        emploeeList = new LinkedListDemo[size];
        // 初始化每个链表
        for(int i = 0; i < emploeeList.length; i++) {
            emploeeList[i] = new LinkedListDemo();
        }
    }

    public HashTableDemo(int size) {
        this.size = size;
        emploeeList = new LinkedListDemo[size];
        // 初始化每个链表
        for(int i = 0; i < emploeeList.length; i++) {
            emploeeList[i] = new LinkedListDemo();
        }
    }

    // 散列函数（取模）
    public int getCap(int id) {
        return id % this.size;
    }

    // 添加雇员
    public void add(Emploee emo) {
        emploeeList[getCap(emo.id)].add(emo);
    }
    // 打印雇员
    public void list() {
        for(int i = 0; i < size; i++) {
            emploeeList[i].list();
        }
    }
    // 查找雇员
    public void search(int id) {
        emploeeList[getCap(id)].search(id);
    }
}

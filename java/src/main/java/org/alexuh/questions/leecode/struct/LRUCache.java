package org.alexuh.questions.leecode.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhiceng@Gmail.com on 2022/12/14
 *
 * LRU缓存:最近最少使用淘汰算法
 *
 * 实现方式
 * 哈希表+双向链表:
 * 使用双向链表来维护顺序,头部是最近使用的，尾部是长时间未使用的,用户访问数据时将热点数据移动到链表头，达到缓存容量时删除链表尾部数据
 * 使用哈希表来快速定位链表获取value值 map<key, DoubleLinkedNode>
 *
 */

class DoubleLinkedNode {
    int key;
    int value;
    DoubleLinkedNode pre;
    DoubleLinkedNode next;

    // 创建虚拟头尾节点
    public DoubleLinkedNode() {}

    public DoubleLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
public class LRUCache {
    private Map<Integer, DoubleLinkedNode> cache = new HashMap<>(); // 缓存中所有数据，保存key+链表
    DoubleLinkedNode head = new DoubleLinkedNode();  // 虚拟头节点
    DoubleLinkedNode tail = new DoubleLinkedNode(); // 虚拟尾节点
    int size = 0;  // 现有大小
    int capacity; // 缓存容量

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        int a = 0;
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }

    public LRUCache(int capacity) {
        this.capacity = capacity; // 初始化容量
        head.next = tail;  // 双向链表头尾连接
        tail.pre = head;
    }

    // 获取缓存数据
    public int get(int key) {
        // key不存在时返回-1，存在是移动节点到头节点然后在返回
        DoubleLinkedNode node = cache.get(key);
        if (node != null) { // 存在移动到头节点
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = cache.get(key);
        // 存在node更新然后移动到头节点
        // 不存在node直接添加在头节点，然后看是否容量满了,满了在移除链表尾部节点
        if(node == null) {
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            addToHead(newNode); // 新建节点添加到头
            cache.put(key, newNode); // 加入缓存
            size++;
            if (size > capacity) { // 大于容量移除尾节点
                int delKey = delTailNode();
                cache.remove(delKey); // 移除缓存
                size--;
            }
        } else { // 存在直接更新,然后移动到头节点
            node.value = value;
            moveToHead(node);
        }
    }

    // 热点数据添加到头节点
    private void addToHead(DoubleLinkedNode node) {
        node.next = head.next;  // 连接node和head节点
        node.pre = head;
        head.next.pre = node;  // 处理head.next的上一个节点
        head.next = node;
    }
    // 移动最近使用的数据到头节点: 先删除在添加
    private void moveToHead(DoubleLinkedNode node) {
        delNode(node);
        addToHead(node);
    }
    // 删除节点数据
    private void delNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    // 移除尾部数据
    private int delTailNode() {
        int key = tail.pre.key;
        delNode(tail.pre); // 移除虚拟尾节点的上一个
        return key;  // 返回最后节点的key用于删除
    }
}



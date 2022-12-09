package org.alexuh.nolinear.table.tree.operate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 *
 * 二叉树节点
 *
 */
public class HeroNode {
    public int id;
    public String name;
    public HeroNode left;  // 二叉树左节点
    public HeroNode right;  // 二叉树右节点

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // 前序遍历:
    public void preOrder() {
        System.out.println(this.toString()); // 先输出当前父节点

        if(this.left != null) {             // 如果左节点不为空递归向左遍历
            this.left.preOrder();
        }

        if(this.right != null) {         // 如果右节点不为空递归向右遍历
            this.right.preOrder();
        }
    }

    //  中序遍历
    public void inOrder() {
        if (this.left != null) {  // 先递归遍历左节点
            this.left.inOrder();
        }

        System.out.println(this.toString()); // 输出父节点

        if(this.right != null) {          // 递归遍历右节点
            this.right.inOrder();
        }
    }

    // 后序遍历
    public void followOrder() {
        if(this.left != null) {  // 先递归遍历左边
            this.left.followOrder();
        }

        if(this.right != null) {  // 递归遍历右边
            this.right.followOrder();
        }

        System.out.println(this); // 输出父节点

    }

    // 层序遍历
    public void bfsOrder() {
        Queue<HeroNode> queue= new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 输出一层从左到右
            for (int i = 0; i < size; i++) {
                HeroNode temp = queue.remove();
                System.out.println(temp);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
    }


    public Queue<HeroNode> queue= new LinkedList<>();

    //  层序遍历递归
    public void bfsOrder1(HeroNode node) {
        System.out.println(node);
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
        while(!queue.isEmpty()) {
            bfsOrder1(queue.remove());
        }
    }

    // 前序查找 查找与遍历类似,需要给定temp用于接受递归返回的查找值
    public HeroNode preSearch(int id) {
        if (this.id == id) {  // 先判断当前的父节点
            return this;
        }

        HeroNode temp = null;     // 查找到的值
        if (this.left != null) {   // 递归向左寻找
            temp = this.left.preSearch(id);
        }
        if (temp != null) {     // 如果左遍历找到则结束查找
            return temp;
        }
        if(this.right != null) { // 向右递归查找
            temp = this.right.preSearch(id);
        }
        return temp;
    }

    // 中序列查找
    public HeroNode inSearch(int id) {
        HeroNode temp = null;
        if (this.left != null) {  // 先递归向左找，如果找到直接返回
            temp = this.left.inSearch(id);
        }
        if (temp != null) {
            return temp;
        }

        if(this.id == id) { // 判断父节点找到直接返回
            return this;
        }

        if(this.right != null) {
            temp = this.right.inSearch(id);
        }
        return temp;
    }

    // 后序查找
    public HeroNode followSearch(int id) {
        HeroNode temp = null;
        if (this.left != null) { // 先递归遍历左边
            temp = this.left.followSearch(id);
        }
        if (temp != null) {
            return temp;
        }

        if (this.right != null) {  // 递归右边
            temp = this.right.followSearch(id);
        }
        if (temp != null) {
            return temp;
        }

        if(this.id == id) {  // 判断父节点是不是符合条件
            return this;
        } else {
            return null;
        }
    }

    // 删除节点: 1.如果删除叶子节点直接删除 2.如果删除父节点则删除子树
//    1.树是单向的,所以要找删除的节点只能找要删除的父节点,所以要判断左右节点是不是要删除的节点,将其置为空
//    2.先判断左节点是否为空，非空在判断是否符合删除的条件,符合就this.next = null,左节点置为空
//    3.同理判断右节点
//    4.递归左子树
//    5.递归右子树
    public void delNode(int id) {
        if(this.left != null && this.left.id == id) {
            this.left = null;
        }

        if(this.right != null && this.right.id == id) {
            this.right = null;
        }

        if (this.left != null) {
            this.left.delNode(id);
        }

        if (this.right != null) {
            this.right.delNode(id);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

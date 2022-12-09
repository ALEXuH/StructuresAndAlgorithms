package org.alexuh.nolinear.table.tree.operate;

import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 *
 * 二叉树 遍历/查找/删除
 *
 * 删除节点规则：1.假设root为空,则无需删除.假设root为要删除的节点root=null
 */
public class BinaryTree {

    private HeroNode root; // 根节点

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("当前树为空");
            return;
        }
        root.preOrder();
    }

    // 中序遍历
    public void inOrder() {
        if (root == null) {
            System.out.println("当前树为空");
            return;
        }
        root.inOrder();
    }

    // 后序遍历
    public void followOrder() {
        if (root == null) {
            System.out.println("当前树为空");
            return;
        }
        root.followOrder();
    }

    // 层序遍历
    public void bfsOrder() {
        if (root == null) {
            System.out.println("当前树为空");
            return;
        }
        root.bfsOrder();
    }


    // 层序遍历返回heroNode的id每一层的二维数组
    public List<List<Integer>> bfsOrderArray() {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<HeroNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                HeroNode node = queue.remove();
                temp.add(node.id);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    // 层序遍历递归
    public void bfsOrder1() {
        if (root == null) {
            System.out.println("当前树为空");
            return;
        }
        root.bfsOrder1(root);
    }


    // 前序查找
    public HeroNode preSearch(int id) {
        if (root == null) {
            System.out.println("当前树为空");
            return null;
        }
        return root.preSearch(id);
    }

    // 中序遍历
    public HeroNode inSearch(int id) {
        if (root == null) {
            System.out.println("当前树为空");
            return null;
        }
        return root.inSearch(id);
    }

    // 后序遍历
    public HeroNode followSearch(int id) {
        if (root == null) {
            System.out.println("当前树为空");
            return null;
        }
        return root.followSearch(id);
    }


    // 删除节点
    // 1.假设root为空,则无需删除.假设root为要删除的节点root=null
    public void delNode(int id) {
        if (this.root == null) {
            System.out.println("这是一颗空树");
        } else {
            if (this.root.id == id) {
                this.root = null;
            } else {
                this.root.delNode(id);
            }
        }
    }

}

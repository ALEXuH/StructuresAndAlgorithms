package org.alexuh.nolinear.table.tree.search;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/5
 *
 * 平衡二叉搜索树 （AVL树）
 *
 * 定义：
 * 1.是一棵二叉排序树
 * 2.左右两个子树的高度差的绝对值小于等于1
 * 3.左右两个子树都是平衡二叉树
 *
 */
public class BalancedBinarySearchDemo {

    private EmpNode root;  // 根节点

    public EmpNode getRoot() {
        return root;
    }

    public void setRoot(EmpNode root) {
        this.root = root;
    }

    // 添加节点同时保持为一个平衡二叉搜索树
    public void addNodeBalanced(EmpNode node) {
        if (root == null) {
            root = node;
        } else {
            if (node == null) {
                System.out.println("当前节点为空");
            }else {
                this.root.addNodeBalanced(node);
            }
        }
    }

    // 中序遍历: 会从小到大排序
    public void inOrder() {
        if (root == null) {
            System.out.println("该树为空");
        } else {
            root.inOrder();
        }
    }



}

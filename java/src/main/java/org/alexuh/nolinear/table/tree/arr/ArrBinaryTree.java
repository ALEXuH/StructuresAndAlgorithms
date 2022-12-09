package org.alexuh.nolinear.table.tree.arr;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/3
 *
 *1.必须要完全二叉树  (除最后一层所有节点都达到最大数,且左边叶子节点连续)
 * 2.第n个元素的左子节点2*n + 1     （n和数组相同从开始计算)
 * 3.第n个元素的右子节点2*n + 2
 * 4.第n个元素的父节点为 (n-1) / 2
 *
                 0(0)
      1(1)                2 (2)
 3(3)       4(4)       5(5)     (6)

 arr [0,1,2,3,4,5,6]

 如上数组和树,()中代表树中元素的编号

 如树的第1个元素（编号为1),根据公式计算出左子节点n=3,右子节点n=4,父节点n=0, 在数组中arr[3] = 3, arr[4] = 4,arr[0] = 0
 如数组中index为2的元素,根据公式得出左子节点编号=5,右子节点编号=6,父节点编号=0,符合树中括号的编号
 */
public class ArrBinaryTree {

    private int[] arr; // 数组方式存储的二叉树

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载方法从头也就相当与从root开始遍历
    public void preOrder() {
        preOrder(0);
    }

    //  前序遍历
    public void preOrder(int index) {
        // 先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("当前树为空");
            return;
        }
        // 遍历当前的父节点
        System.out.println(arr[index]);
        // 递归遍历左子树
        if (2*index + 1 < arr.length) {
            preOrder(2*index + 1);
        }
        // 递归遍历右子树
        if (2*index + 2 < arr.length) {
            preOrder(2*index + 2);
        }
    }

    public void inOrder(int index) {
        // 先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("当前树为空");
            return;
        }
        // 递归遍历左子树
        if (2*index + 1 < arr.length) {
            inOrder(2*index + 1);
        }
        // 遍历当前的父节点
        System.out.println(arr[index]);
        // 递归遍历右子树
        if (2*index + 2 < arr.length) {
            inOrder(2*index + 2);
        }
    }

    public void followOrder(int index) {
        // 先判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.println("当前树为空");
            return;
        }
        // 递归遍历左子树
        if (2*index + 1 < arr.length) {
            followOrder(2*index + 1);
        }
        // 递归遍历右子树
        if (2*index + 2 < arr.length) {
            followOrder(2*index + 2);
        }
        // 遍历当前的父节点
        System.out.println(arr[index]);
    }


}

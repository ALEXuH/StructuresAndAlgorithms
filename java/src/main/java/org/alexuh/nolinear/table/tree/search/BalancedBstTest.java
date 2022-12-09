package org.alexuh.nolinear.table.tree.search;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/5
 * 平衡二叉树测试
 *
 * 二叉搜索树                   平衡二叉树 (经过旋转的二叉搜索树)
 *     5                                  8
 *  3      8       =======>         5           9
 *       7    9                  3      7            10
 *              10
 *
 */
public class BalancedBstTest {

    public static void main(String[] args) {
        int[] arr = {5,3,8,7,9,10};
        BalancedBinarySearchDemo tree = new BalancedBinarySearchDemo();
        // 构建二叉搜索树
        for(int i : arr) {
            System.out.println("添加节点" + i); // 添加到10时节点高度失横开始旋转
            tree.addNodeBalanced(new EmpNode(i));
        }
        System.out.println("-------");
        tree.inOrder();  // 中序遍历输出结果按顺序排列
        System.out.println("---------验证是否旋转");
        System.out.println(tree.getRoot().value);
        System.out.println(tree.getRoot().left.value);
        System.out.println(tree.getRoot().right.value);
        System.out.println(tree.getRoot().left.right.value);


    }
}

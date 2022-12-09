package org.alexuh.nolinear.table.tree.search;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/4
 * 二叉搜索树
 * 添加 / 遍历 / 删除 测试
 *
 *
 *
 * int[] arr = {1,43,4,4242,46,7,2,53,54};
 *
 *               1(root)
 *                    43
 *                 4      4242
 *              2    7  46
 *                         53
 *                            54
 *
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = {1,43,4,4242,46,7,2,53,54};
        BinarySearchDemo tree = new BinarySearchDemo();
        // 构建二叉搜索树
        for(int i : arr) {
            tree.addNode(new EmpNode(i));
        }
        tree.preOrder();
        System.out.println("-------");
        tree.inOrder();  // 中序遍历输出结果按顺序排列

        System.out.println("================");
        // 删除节点
        // 删除叶子节点
        tree.del(54);
        // 删除只有右节点的左子节点
        tree.inOrder();
        System.out.println("-------------");
        tree.del(46);
        tree.inOrder();
        System.out.println("-------------");
        // 删除有左右两个节点的
        tree.del(4);
        tree.inOrder();

    }
}

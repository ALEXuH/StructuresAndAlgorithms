package org.alexuh.nolinear.table.tree.operate;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 *
 * * 二叉树 遍历/查找/删除 测试
 *
 */
public class Test {

    public static void main(String[] args) {
        // 构建如下二叉树
        //        1
        //     2    3
        //         5   4
        BinaryTree tree = new BinaryTree();
        HeroNode h1 = new HeroNode(1, "aaa");
        HeroNode h2 = new HeroNode(2, "bbb");
        HeroNode h3 = new HeroNode(3, "ccc");
        HeroNode h4 = new HeroNode(4, "ddd");
        HeroNode h5 = new HeroNode(5, "eee");
        tree.setRoot(h1); // 设置根节点
        h1.left = h2;
        h1.right = h3;
        h3.left = h5;
        h3.right = h4;

        // 前中后序遍历
        tree.preOrder(); // 前序遍历 1 2 3 5 4
        System.out.println("-----------");
        tree.inOrder(); // 中序遍历 2 1 5 3 4
        System.out.println("-----------");
        tree.followOrder(); // 后序遍历 2 5 4 3 1
        System.out.println("---------层序遍历"); // 1 2 3 5 4
        tree.bfsOrder();
        System.out.println("----层序遍历返回数组");
        List<List<Integer>> temp = tree.bfsOrderArray();
        System.out.println(Arrays.toString(temp.toArray()));
        System.out.println("---------"); // 1 2 3 5 4
        tree.bfsOrder1();

        System.out.println("===============");
        // 前中后序查找
        System.out.println(tree.preSearch(3));
        System.out.println(tree.inSearch(4));
        System.out.println(tree.followSearch(5));

        System.out.println("===============删除节点");
        tree.delNode(5);  // 删除叶子节点
        tree.preOrder();
        System.out.println("----------");
        tree.delNode(3); // 删除父节点
        tree.preOrder();

    }
}

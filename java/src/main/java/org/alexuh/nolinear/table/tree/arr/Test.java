package org.alexuh.nolinear.table.tree.arr;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/3
 */
public class Test {

    public static void main(String[] args) {
//                       0(0)
//              1(1)                2 (2)
//        3(3)       4(4)       5(5)     (6)
        int[] arr = {0,1,2,3,4,5,6};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder(0);  // 前序遍历 0134256
        System.out.println("-------");
        tree.inOrder(0);  // 中序遍历 3140526
        System.out.println("-------");
        tree.followOrder(0); // 后序遍历  3415620
    }

}


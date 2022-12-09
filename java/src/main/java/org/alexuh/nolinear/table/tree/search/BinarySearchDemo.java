package org.alexuh.nolinear.table.tree.search;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/4
 *
 * 二叉搜索树
 * 对于该树的任意一个非叶子节点,该节点的左节点的值比它小,右节点的值比它大(对于相同的可以在左节点叶可以在右节点)
 *
 * 中序遍历: 会从小到大排序
 *
 */
public class BinarySearchDemo {

    private EmpNode root;

    public BinarySearchDemo(EmpNode root) {
        this.root = root;
    }

    public BinarySearchDemo() {
    }

    public EmpNode getRoot() {
        return root;
    }

    public void setRoot(EmpNode root) {
        this.root = root;
    }

    // 添加节点
    public void addNode(EmpNode node) {
        // 根节点为空直接置为根节点
        if (root == null) {
            root = node;
        } else {
            if(node != null) { //防止空指针
                // 递归找到要添加的位置
                root.addNode(node);
            }
        }
    }

    // 前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("该树为空");
        } else {
            root.preOrder();
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

    /**
     * 删除节点
     * @param v
     *              1(root)
     *                    43
     *                 4      4242
     *              2    7  46
     *                         53
     *                            54
     *
     * 1.删除的是叶子节点  （还要考虑target为根节点 parent==null）
     *  1.1 target节点是左节点parent.left = null;
     *  1.2 target节点是右节点parent.right = null;
     * 2.非叶子节点且该节点下只有一个节点  （还要考虑target为根节点）
     *  2.1 如果target只有左节点且是parent的左节点 parent.left = target.left
     *  2.2 如果target只有左节点且是parent的右节点 parent.right = target.left
     *  2.1 如果target只有右节点且是parent的左节点 parent.left = target.right
     *  2.2 如果target只有右节点且是parent的右节点 parent.right = target.right
     * 3.target左右都有节点
     *  3.1 方法一：找右子树最小的值（根据树定义可以往左右一直找找到最后即为最小值）提上来然后删除最小值节点 (右边最小值肯定大于target.left) int least = v; target.v = v
     *  3.2 方法二: 找左子树的最大值
     *
     */
    public void del(int v) {
        if(root == null) {
            System.out.println("树为空");
            return;
        }
        // 获取要删除的节点和父节点
        EmpNode target = getTargetNode(v);
        EmpNode parent = getParentNode(v);

        if (target == null) {
            System.out.println("没有找到需要删除的节点");
            return;
        }

        // 1情况 叶子节点
        if(target.left == null && target.right == null) {
            // 说明要删除的root节点 root==target
            if (parent == null) {
                root = null;
            } else {
                if(parent.left != null && parent.left.value == target.value) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (target.left != null && target.right != null) {  // 情况3 2个子节点
            int least = getRightTreeLagestValue(target);
            del(least);// 删除最小节点并将值赋值给需要删除的节点
            target.value = least;
        } else {            // 情况2 有一个叶子节点
            if(target.left != null) {
                if (parent == null) {       // 考虑要删除的是根节点
                    root = target.left;
                } else {
                    if (parent.left != null && parent.left.value == target.value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                }
            } else {
                if(parent == null) {  // 考虑要删除的是根节点
                    root = target.right;
                } else {
                    if (parent.left != null && parent.left.value == target.value) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                }
            }
        }
    }

    // 找二叉搜索树给定右子树节点的最小值: 向左一直找
    private int getRightTreeLagestValue(EmpNode target) {
        int least = target.value;
        while(target.left != null) {
            least = target.left.value;
            target = target.left;
        }
        return least;
    }


    private EmpNode getParentNode(int v) {
        if (root == null) {
            System.out.println("树为空");
            return null;
        } else{
            return root.getParentNode(v);
        }
    }

    private EmpNode getTargetNode(int v) {
        if (root == null) {
            System.out.println("树为空");
            return null;
        } else {
            return root.getTargetNode(v);
        }
    }
}

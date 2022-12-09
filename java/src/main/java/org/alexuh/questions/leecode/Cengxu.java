package org.alexuh.questions.leecode;


import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/19
 *
 * 二叉树层序遍历（广度优先搜索）
 * 核心: 使用队列
 *
 * 层序遍历相关问题 (图论中的广度优先搜索)  8道Leecode题目
 */
public class Cengxu {
}

 // 二叉树
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode next;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
          this.left = left;
          this.right = right;
     }
}

// 多叉树
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class Solution {

    public static void main(String[] args) {

    }

    // 层序遍历返回treeNode的val每一层的二维数组
    public List<List<Integer>> bfsOrderArray(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                temp.add(node.val);
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



    /**
     *     1
     *   2     5
     * 3  4      6
     *
     *
     * 107:
     * 返回自底向上层次遍历
     *
     * 解法: 正常层序遍历后翻列表
     *
     * [[3,4,6],[2,5],[1]]
     * @param root
     * @return
     */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i=0; i < size; i++) {
                TreeNode node = queue.remove();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(temp);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = list.size() - 1 ; i >= 0; i--) {
            result.add(list.get(i));
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 199:
     *  二叉树的右视角图
     *  给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     *  解法: 层序遍历，取最后一个遍历到的值
     * @param root
     * @return
     */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i == size - 1) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    /**
     *
     * 637:
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Double v = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                v += temp.val;
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(v / size);
        }
        return result;
    }

    /**
     *
     * 429:
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node temp = queue.remove();
                values.add(temp.val);
                for(Node node : temp.children) {
                    queue.add(node);
                }
            }
            result.add(values);
        }
        return result;
    }

    /**
     * 515:
     *
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (max < node.val) {
                    max = node.val;
                }
                if (node.left != null)  {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 117. 填充每个节点的下一个右侧节点指针 II
     *
     * @param root
     * @return
     */
    public TreeNode connect(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                temp.add(node);
                if (node.left != null)  {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            for (int i = 0; i < size - 1; i++) {
                temp.get(i).next = temp.get(i+1);
            }
        }
        return root;
    }

}
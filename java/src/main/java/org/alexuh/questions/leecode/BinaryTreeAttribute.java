package org.alexuh.questions.leecode;

import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/19
 *
 * 二叉树相关问题
 *
 * 1.前序遍历
 * 2.中序遍历
 * 3.后序遍历
 * 4.层序遍历
 *
 */
public class BinaryTreeAttribute {
}

/**
 * 二叉树定义:
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class SolutionAttribute {
    /**
     *
     * 101:
     * 判断是否是中心轴对称二叉树
     *
     * 解法: 类似层序遍历,层序遍历是从左到右,这个是先添加左子树的左节点和右子树的右节点
     * 然后在将左子树的右节点和右子树的左节点放如队列两两拿出来比较
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left != null && right != null && left.val == right.val) {
                // 先添加左子树的左节点和右子树的右节点
                queue.add(left.left);
                queue.add(right.right);
                // 然后在将左子树的右节点和右子树的左节点放如
                queue.add(left.right);
                queue.add(right.left);
            } else if (left == null && right == null) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 104:
     * 给定一个二叉树，找出其最大深度
     *
     * 解法: 后序遍历
     *
     *              1
     *           2       3
     *        4    5   7
     *                9
     * 树的最大深度 = 树的最大高度 = 层序遍历的层数
     *
     * 解法1：后序遍历
     * 解法2: 层序遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        int depth = 1 + Math.max(leftDepth, rightDepth);
        return depth;
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return depth;
    }

    /**
     * 559:
     * 给定一个 N 叉树，找到其最大深度。
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Node temp = queue.remove();
                for(Node node : temp.children) {
                    queue.add(node);
                }
            }
        }
        return depth;
    }

    /**
     * 111. 二叉树的最小深度
     * notice: 最小深度是从根节点到最近叶子节点的最短路径，叶子节点（左右节点都为空）
     * 当左子树为空应该取1+min(right),单右子树为空应该取1+min(left)
     *
     * 解法：递归法
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth1(root.left);
        int rightDepth = minDepth1(root.right);
        if (root.right != null && root.left == null) {
            return 1+rightDepth;
        }
        if (root.right == null && root.left != null) {
            return 1+leftDepth;
        }
        return 1+Math.min(leftDepth, rightDepth);
    }

    /**
     * 111. 二叉树的最小深度
     *
     * 解法：层序遍历，当节点的左节点右节点都为空时就是最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return depth;
    }

    /**
     * 222. 完全二叉树的节点个数
     *
     * 解法: 层序遍历
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size  = queue.size();
            depth += size;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return depth;
    }

    /**
     * 110. 平衡二叉树
     *
     * 给定一个二叉树，判断它是否是高度平衡的二叉树(一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 )
     *
     * 解法：后序遍历,计算高度和子树相减高度
     *
     * @param root
     * @return
     */
    public int isBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = isBalanced(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = isBalanced(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        int height;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            height = 1+Math.max(leftHeight, rightHeight);
        }
        return height;
    }

    /**
     * 257. 二叉树的所有路径
     *
     * 解法：非递归,广度优先遍历(层序遍历)
     * 使用2个队列一个用来层序遍历，另一个用来保存走过的路径，当节点的左右子节点都为空时说明当前路径走完
     *
     * @param root
     * @return
     */
    public  List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<StringBuilder> pathQueue = new LinkedList<>();
        nodeQueue.add(root);
        pathQueue.add(new StringBuilder().append(root.val));

        while (!nodeQueue.isEmpty()) {
            StringBuilder path = pathQueue.remove();
            TreeNode cur = nodeQueue.remove();
            // 找到叶子节点记录该路径
            if (cur.left == null && cur.right == null) {
                result.add(path.toString());
            }
            if (cur.left != null) {
                nodeQueue.add(cur.left);
                pathQueue.add(new StringBuilder().append(path.toString()).append("->").append(cur.left.val));
            }
            if (cur.right != null) {
                nodeQueue.add(cur.right);
                pathQueue.add(new StringBuilder().append(path.toString()).append("->").append(cur.right.val));
            }

        }
        return result;
    }


    /**
     * 404. 左叶子之和
     *
     *解法：前序遍历，左叶子（父节点的左节点且节点左右子节点为空）
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        int sumLeft = sumOfLeftLeaves(root.left);
        int sumRight = sumOfLeftLeaves(root.right);
        return sum + sumLeft + sumRight;
    }

    /**
     *
     * 513. 找树左下角的值
     *
     * 解法: 层序遍历,每层的第一个值
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int v = root.val;
        while(!queue.isEmpty()) {
            int size = queue.size();
            v = queue.peek().val; // 当前层的第一个值
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return v;
    }

    /**
     * 112. 路径总和
     *
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * 如果存在，返回 true ；否则，返回 false
     *
     *解法: 广度优先，双队列计算，当到达叶子节点时判断值是否相同
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();  // 广度优先遍历队列
        Queue<Integer> pathSumQueue = new LinkedList<>(); // 保存节点的sum值
        nodeQueue.add(root);
        pathSumQueue.add(root.val);

        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int temp = pathSumQueue.remove();

            // 判断叶子节点
            if (node.left == null && node.right == null && temp == targetSum) {
                return  true;
            }
            // 添加左子节点
            if (node.left != null) {
                nodeQueue.add(node.left);
                pathSumQueue.add(temp + node.left.val);
            }
            // 添加有右子节点
            if (node.right != null) {
                nodeQueue.add(node.right);
                pathSumQueue.add(temp + node.right.val);
            }
        }
        return false;

    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     *
     * 解法：3个队列，一个遍历使用，一个保存sum值，一个保存路径
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();  // 广度优先遍历队列
        Queue<Integer> pathSumQueue = new LinkedList<>(); // 保存节点的sum值
        Queue<List<Integer>> pathQueue = new LinkedList<>(); // 保存路径
        nodeQueue.add(root);
        pathSumQueue.add(root.val);
        List<Integer> v = new ArrayList<>();
        v.add(root.val);
        pathQueue.add(v);

        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int temp = pathSumQueue.remove();
            List<Integer> paths = pathQueue.remove(); // 左右都需要使用下面需要自己拷贝使用

            // 判断叶子节点
            if (node.left == null && node.right == null && temp == targetSum) {
                result.add(paths);
            }
            // 添加左子节点
            if (node.left != null) {
                nodeQueue.add(node.left);
                pathSumQueue.add(temp + node.left.val);
                List<Integer> l = new ArrayList<>();
                l.addAll(paths);
                l.add(node.left.val);
                pathQueue.add(l);
            }
            // 添加有右子节点
            if (node.right != null) {
                nodeQueue.add(node.right);
                pathSumQueue.add(temp + node.right.val);
                List<Integer> r = new ArrayList<>();
                r.addAll(paths);
                r.add(node.right.val);
                pathQueue.add(r);
            }
        }
        return result;
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     *
     * 解法:广度优先 + 双队列（一个存储遍历节点，一个存储sum）
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        int size = 0;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int temp = sumQueue.remove();
            if (temp == targetSum) {
                size++;
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                sumQueue.add(temp + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                sumQueue.add(temp + node.right.val);
            }

        }
        return size;

    }

    public static  void dfs(TreeNode root, int targetSum, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            return;
        }
        dfs(root.left, targetSum, list);
        dfs(root.right, targetSum, list);
        //list.remove(root.val);
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
     * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     *
     * 解法:1.取后序数组的最后一个作为切割点切割中序数组找到该节点的左右子树
     *     2.然后根据左右子树的值分别取出的左右子树在后序数组最后的位置，在切割中序数组
     *     3.递归处理左右区间
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return null;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 解法： 二叉搜索树中序遍历是有序的,先转成数组然后比较
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfsIsValidBST(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i+1) <= list.get(i) ) {
                return false;
            }
        }
        return true;
    }

    // 中序遍历
    public void dfsIsValidBST(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfsIsValidBST(root.left, list);
        list.add(root.val);
        dfsIsValidBST(root.right, list);
    }


    /**
     * 530. 二叉搜索树的最小绝对差
     *
     * 中序遍历
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfsIsValidBST(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min,list.get(i+1) - list.get(i));
        }
        return min;
    }


    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 解法: 递归后序遍历
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p ,q);
        TreeNode right = lowestCommonAncestor(root.right, p ,q);
        // 如果left和right都不为空则当前节点为最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 如果left不为空说明找到来pq中的一个就一直向上返回这个值，直到右子树也找到到pd也就符合上面
        // 的条件就是最小公共祖先来（类似于快速排序找左边最大的右边最小的）
        if (left != null && right == null) {
            return left;
        }
        if (left == null && right != null) {
            return right;
        }
        return null;
    }

    /**
     *
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
     * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * 解法: 根据前序在中序中找切割点找到当前节点的左右子树，递归这个过程
     *
     * 1.递归结束条件 如果数组大小为0则结束
     * 2.取前序数组第一个作为切割点，在中序数组中找到该节点的值，获取该值的左右子树
     * 3.切割中序数组
     * 4.切割后序数组
     * 5.递归以上过程
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if(preLeft > preRight) {
            return null;
        }
        return null;

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderDfs(root, list);
        return list;
    }

    public void inOrderDfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderDfs(root.left, list);
        list.add(root.val);
        inOrderDfs(root.right, list);
    }

    /**
     * 114. 二叉树展开为链表
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同
     *
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrderDfs(root, list);
        for (int i = 0; i < list.size()-1; i++) {
            list.get(i).right = list.get(i+1);
            list.get(i).left = null;
        }
    }

    public void preOrderDfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrderDfs(root.left, list);
        preOrderDfs(root.right, list);
    }

    /**
     *
     * 617. 合并二叉树
     *
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
     * 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；
     * 否则，不为 null 的节点将直接作为新二叉树的节点
     *
     * 解法: 前序遍历，同时遍历2个树
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode node = new TreeNode(root2.val += root1.val);
        node.left=mergeTrees(root1.left, root2.left);
        node.right=mergeTrees(root1.right, root2.right);
        return node;
    }

    /**
     * 543. 二叉树的直径
     *
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过也可能不穿过根结点。
     *
     * 解法：最大直径也就是左子树右子树最大深度相加的最大值
     * @param root
     * @return
     */
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeDfs(root);
        return max;
    }

    public int diameterOfBinaryTreeDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = diameterOfBinaryTreeDfs(root.left);
        int rightDepth = diameterOfBinaryTreeDfs(root.right);
        max = Math.max(leftDepth + rightDepth, max);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     *
     *
     给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * 解法：根据二叉搜索树性质得知 node=右子树之和 反向中序遍历先右后左, 左子树的node = 右子树之和+父节点的右子树之和
     * @param root
     * @return
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

}

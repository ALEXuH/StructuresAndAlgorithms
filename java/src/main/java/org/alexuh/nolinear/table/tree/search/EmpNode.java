package org.alexuh.nolinear.table.tree.search;


/**
 * Created by xuzhiceng@Gmail.com on 2022/11/4
 */
public class EmpNode {

    public int value; // 节点权值
    public EmpNode left; // 左节点
    public EmpNode right; // 右节点

    public EmpNode(int value) {
        this.value = value;
    }

    public void addNode(EmpNode node) {
        // 如果要添加节点值小于当前节点则需要递归向左寻找直到找到最后叶子节点
        if(node.value < this.value) {
            if(this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else { // 如果要添加节点值大于当前节点则需要递归向右寻找直到找到最后叶子节点
            if(this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
    }

    public void preOrder() {

        System.out.println(this.value);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }

    }

    //  中序遍历
    public void inOrder() {

        if (this.left != null) {
            this.left.inOrder();
        }

        System.out.println(this.value);

        if (this.right != null) {
            this.right.inOrder();
        }

    }

    //  查找父节点
    public EmpNode getParentNode(int v) {
        // 当查找到相同value时返回否则递归向左或者右查找
        if((this.left != null && this.left.value == v) || (this.right != null && this.right.value == v)) {
            return this;
        } else {
            // 值小向左查找。值大递归向右查找,都没找到说明不存在该节点
            if(this.left != null && this.value > v) {
                return this.left.getParentNode(v);
            } else if (this.right != null && this.value < v) {
                return this.right.getParentNode(v);
            } else {
                return null;
            }
        }
    }

    // 查找要删除的节点
    public EmpNode getTargetNode(int v) {
        if (this.value == v) {
            return this;
        } else {
            if (this.left != null && this.value > v) {
                return this.left.getTargetNode(v);
            } else if (this.right != null && this.value < v) {
                return this.right.getTargetNode(v);
            } else {
                return null;
            }
        }
    }

    public void addNodeBalanced(EmpNode node) {
        // 先添加成普通二叉搜索树 左节点小右节点大
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNodeBalanced(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNodeBalanced(node);
            }
        }


        // 判断树左右子树高度差进行左旋转或者右旋转,左子树高右旋转,右子树高左旋
        if (getHeight(this.right) - getHeight(this.left) > 1) {
            // 双旋,假设右子节点的左子树高度大于右子树,直接左旋还是有高度差,所以先对右节点进行右旋
            if (getHeight(this.right.left) - getHeight(this.right.right) > 0) {
                this.right.rightRotation();
            }
            this.leftRotation();
        }
        
        if (getHeight(this.left) - getHeight(this.right) > 1) {
            // 双旋
            if(getHeight(this.left.left) - getHeight(this.left.right) > 0) {
                this.left.leftRotation();
            }
            this.rightRotation();
        }
    }

    // 右旋 左子树高
    private void rightRotation() {
        System.out.println("开始右旋");
        // 设置新节点
        EmpNode newNode = new EmpNode(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;

        // 修改当前节点值
        this.value = this.left.value;

        // 连接新节点形成平衡二叉树
        this.left = this.left.left;
        this.right = newNode;
    }

    //  左旋 右边子树高
    private void leftRotation() {
        System.out.println("开始左旋");
        // 设置新节点
        EmpNode newNode = new EmpNode(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        // 修改当前节点值
        this.value = this.right.value;
        // 连接新节点形成平衡二叉树
        this.left = newNode;
        this.right = this.right.right;

    }

    // 递归获取以当前为节点的树的高度
    public int getHeight(EmpNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left) + 1, getHeight(node.right) + 1);
    }

}

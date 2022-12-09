package org.alexuh.nolinear.table.tree.heap;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/3
 *
 *
 * 完全二叉树和数组的转化+大顶堆
 * 2.第n个元素的左子节点2*n + 1     （n和数组相同从开始计算)
 * 3.第n个元素的右子节点2*n + 2
 * 4.第n个元素的父节点为 (n-1) / 2
 *
 *                 0(0)
 *          1(1)                2 (2)
 *    3(3)       4(4)       5(5)     (6)
 *
 *   arr [0,1,2,3,4,5,6]
 *
 * 大顶堆：1.是一颗完全二叉树 2.每个节点的值都大于等于左右子节点的值 arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]（不关心左右子节点的值的大小）
 *
 * 思想：
 * 1.将待排序的序列构建成大顶堆,此时根节点为最大值然后将根节点与尾节点交换
 * 2.交换后小的值可能会交换到上面,此时从根节点(0)开始进行大顶堆维护
 * 3.重复上面的步骤最后得到一个有序序列
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {1,7,45,3,54,8,10};
        System.out.println("原序列");
        System.out.println(Arrays.toString(arr));

        heapSort(arr);
        System.out.println("排序后的序列");
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr) {
        int len = arr.length;
        // 建立大顶堆（自下而上）, 从最后一个叶子节点的父节点 n/2-1  推导: 最后节点编号（叶子节点） (n-1) 且节点的父节点公式(n-1)/2 => 最后一个非叶子节点 =(n-1-1) / 2= n/2 - 1
        // 遍历并调整每个节点满足大顶堆定义
        for(int i = len / 2 -1; i >=0; i--) {
            heapify(arr, i, len);
        }
        System.out.println("第一次构建的大顶堆");
        System.out.println(Arrays.toString(arr));
        // 交换根尾节点并维护大顶堆
        for(int i = len - 1; i >=0; i--) {
            swap(arr, 0 , i); // 交换root节点和尾部节点
            len--;
            heapify(arr, 0, len);  // 交换过后从root节点开始进行大顶堆维护
        }
    }


    /**
     * 构建和维护大顶堆
     * @param arr
     * @param i  从第几个索引开始维护
     * @param len
     */
    public static void heapify(int[] arr, int i, int len) {
        // 获取最大的索引，继续递归
        int large = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < len && arr[left] > arr[large]) {
            large = left;
        }
        if (right < len && arr[right] > arr[large]) {
            large = right;
        }

        // 如果最大值不是当前节点则进行交换然后以当前的最大值索引继续进行递归
        // 当自下而上时候（第一次构建大顶堆时候),只递归一次或两次,假设是叶子节点（最后一层节点）为最大,交换后在往下扫描时没有节点了, 假设是中间的节点,由于自下而上进行大的都交换到上面,当该节点left是最大时large=left，这时候继续往下找已经交换过所以不会进行交换
        // 自上而下（维护大顶堆）时候会一直进行递归直到都最大的都交换到父节点满足大顶堆定义
        if (large != i) {
            swap(arr,i,large);
            heapify(arr,large, len);
        }
    }

    // 数组交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

package org.alexuh.linear.table.search;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/1
 * 插值查找 (类似二分查找)
 *
 *思想：
 * 与二分查找相同，将mid优化成自适应，使之更靠向查找数
 *
 * 对于分布均匀二分查找效果更好，插值查找性能会比二分查找好很多，反之非常不均匀的情况下，插值查找未必效率很高
 *
 * mid推导过程：
 * 1.二分查找 mid=(high + low ) / 2  => mid = low + 1/2 (high - low)
 * 2.使1/2 变为自适应取概率大的一边  (key - arr[low]) / arr[high] - arr[low]
 * 3.最终推导式子 mid = low + (key-arr[low]) / (arr[high] - arr[low]) (high - low)
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        // 查找单个值
        int[] arr = {1,2,2,3,4,5,5,5,6,7,8,9,9,9,10};
        System.out.println(insertValueSearch(arr, 1, 0, arr.length - 1));
        System.out.println(insertValueSearch(arr, 9, 0, arr.length - 1));
        System.out.println(insertValueSearch(arr, 11, 0, arr.length - 1));
    }

    public static int insertValueSearch(int[] arr, int findValue, int left, int right) {
        // 未查找到或者判断不再范围之间结束递归
        if (left > right || arr[left] > findValue || arr[right] < findValue) {
            return -1;
        }
        int mid = left + (findValue - arr[left]) * (arr[right] - arr[left]) / (right - left);

        if (arr[mid] > findValue) {
            return insertValueSearch(arr, findValue, left, mid - 1);
        } else if (arr[mid] < findValue) {
            return insertValueSearch(arr, findValue, mid + 1, right);
        } else {
            return mid;
        }

    }
}

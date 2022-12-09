package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/31
 *
 * 归并排序   需要额外空间
 * 思想（分治法）
 *1.将待排序的数据分成若干组，每组只有一个元素
 * 2.将若干组两两进行合并，保证合并后的组是有序的, (合并步骤：给定i，j左右指针比较大小将较小的放入临时数组，直到
 * 有一方没有元素后，将另一方剩余的元素填充进临时数组  (1432 7249) 如8个数进行最后一次排序)
 * 3.重复以上步骤
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.generateIntArr(10, true);
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0 , arr.length - 1);
        SortUtils.printArr(arr);

        System.out.println(SortUtils.getCurrentTime());
        int[] arr1 = SortUtils.generateIntArr(80000, false);
        int[] temp1 = new int[arr1.length];
        mergeSort(arr1, temp1, 0 , arr1.length - 1); // 14ms
        System.out.println(SortUtils.getCurrentTime());
    }

    // 分 + 合
    public static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间索引
            // 左递归分解
            mergeSort(arr, temp, left, mid);
            // 右递归分解
            mergeSort(arr, temp, mid + 1, right);
            // 合并
            merge(arr, temp, left, mid, right);

        }
    }

    // 归并核心合并过程
    public static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;  // 左边序列初始索引
        int j = mid + 1; // 右边序列初始索引
        int t = 0;        // 临时数组初始索引

        // 先将左右2边数据按合并规则填充到临时数组中（比较i j所在索引元素大小，将较小的放到临时数组中）
        while(i <= mid && j <= right) {
            // 如果左边元素较小，则将其放入临时数组中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        // 将一边剩下的数据填充进数组
         while(i <= mid) {  // 左边剩余
             temp[t] = arr[i];
             i++;
             t++;
         }

         while(j <= right) { // 右边剩余
             temp[t] = arr[j];
             j++;
             t++;
         }
        // 将temp数组拷贝到原数组中
         t = 0;
         //int tempLeft = left; // 第一组合并tempLeft=0，right = 1, 第二组合并 tempLeft=2 right =3 ,左边第三组合并 tl=0， right=3
         while(left <= right) {
             arr[left] = temp[t];
             left++;
             t++;

         }


    }

}

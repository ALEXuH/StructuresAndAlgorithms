package org.alexuh.linear.table.sort;


/**
 * Created by xuzhiceng@Gmail.com on 2022/10/26
 * 思想
 * 1.选定pivot中心轴（基准值）
 * 2.大于基准值的数字放在右边,小于基准值的放在左边
 * 3.对左右子序列重复进行前面步骤
 *
 * 优化：递归消耗较多资源，在数组元素较少时使用选择排序
 */
public class QuickSort {

    public static void main(String[] args) {

        // 方法一：hoare法
        int[] arr = SortUtils.generateIntArr(10, true);
        quickSort(arr, 0 , arr.length - 1);
        SortUtils.printArr(arr);

        System.out.println(SortUtils.getCurrentTime());
        quickSort(SortUtils.generateIntArr(80000, false), 0, 80000 - 1);  // 15ms
        System.out.println(SortUtils.getCurrentTime());

        // 方法二：挖坑法
        int[] arr1 = SortUtils.generateIntArr(10, true);
        quickSort1(arr1, 0 , arr1.length - 1);
        SortUtils.printArr(arr1);

        System.out.println(SortUtils.getCurrentTime());
        quickSort1(SortUtils.generateIntArr(80000, false), 0, 80000 - 1);  // 15ms
        System.out.println(SortUtils.getCurrentTime());
    }

    // 原作者hoare方法
    public static void quickSort(int[] arr, int left, int right) {
        // 递归终止条件，如果左指针等于右指针，则说明只有一个元素
        if (left >= right) {
            return;
        }
        // 选取基准值
        int i = left;
        int j = right;
        int pivot = arr[left]; // 选取第一位为基准值
        while(i < j) {
            // 必须右指针先走，方便当i=j退出循环式，当前指针所在位置为小于等于基准数的数，可以与low进行交换恢复基准数
            // 右边指针向前找，找到小于等于基准值的 （等于的不进行交换，第一个值不会被交换）
            while(i < j && arr[j] >= pivot) {
                j--;
            }
            // 左指针向后找，找到大于等于基准值的
            while(i < j && arr[i] <= pivot) {
                i++;
            }
            // 找到后进行交换
            swap(arr, i , j);
        }
        // 当i=j，交换结束，交换基准值
        swap(arr, j, left);  // 右边先走，最后遇到的是小于等于基准数的并且第一个数（基准值）不进行交换，所以可以进行交换
        quickSort(arr, left, j);
        quickSort(arr, j+1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 挖坑法
    public static void quickSort1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = arr[left];

        while(i < j) {
            // 先找到右边比基准值小的数
            while(i < j && pivot <= arr[j]) {
                j--;
            }
            // 将找到的较小值填到i的坑里
            arr[i] = arr[j];
            // i继续向前找找到大于基准值的填坑到右边
            while(i < j && pivot >= arr[i]) {
                i++;
            }
            // 将找到的较大值填到右边坑里
            arr[j] = arr[i];
        }
        // 当i=j时，填坑基准值
        arr[i] = pivot;
        quickSort1(arr, left, j);
        quickSort1(arr, j+1, right);
    }
}

package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/24
 * 冒泡排序：相邻之间进行交换，最大的往后排
 *
 * 思想:
 * 依次比较相邻的元素，元素较大的往后移动进行元素之间的交换，第一轮从第一个开始，第二轮从第二个元素开始，经过n轮完成排序，像水底气泡一样往上冒（最大值会往后）
 * 优化
 * 如果一趟比较下来没有进行交换，说明序列有序可以提前返回,减少排序次数，添加flag进行标识
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.generateIntArr(10, true);
        bubble(arr);
        SortUtils.printArr(arr);

        // 优化，如果没有进行交换则结束排序，减少排序次数
        int[] arr1 = SortUtils.generateIntArr(10, true);
        bubbleOptimize(arr1);
        SortUtils.printArr(arr1);

        System.out.println(SortUtils.getCurrentTime());
        bubble(SortUtils.generateIntArr(80000, false));  // 8s
        System.out.println(SortUtils.getCurrentTime());
    }

    public static void bubble(int[] arr) {

        int len = arr.length;
        int temp;
        // 如果后面一位小于前面一位则进行交换
        for (int j = 0; j < len - 1; j++) {
            for (int i = 0; i < len - 1 - j ; i++) {
                if(arr[i+1] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }

    public static void bubbleOptimize(int[] arr) {

        int len = arr.length;
        int temp;
        boolean flag = false;
        // 如果后面一位小于前面一位则进行交换

        for (int j = 0; j < len - 1; j++) {

            for (int i = 0; i < len - 1 - j ; i++) {
                if(arr[i+1] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    flag = true;
                }
            }
            // 进行了交换
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }

    }
}

package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/26
 * 希尔排序
 * j =0          temp i 4
 * 2   3 4 5     1        5 6 3 9 2
 *               2
 *
 *
 * -5 + 5
 */
public class ShellSort {

    public static void main(String[] args) {
       // SortUtils.printArr(shellSort(SortUtils.generateIntArr(10, true)));
        System.out.println(SortUtils.getCurrentTime());
        shellSort(SortUtils.generateIntArr(80000, false));  // 17ms
        System.out.println(SortUtils.getCurrentTime());
    }

    public static int[] shellSort(int[] arr) {
        int len = arr.length;
        int temp;
        // 一直分组直到步长为1
        for (int step = len / 2; step >=1; step /= 2) {
            for (int i = step; i < len; i++) {
                temp = arr[i];
                int j = i - step;// 从第一位开始比较
                // 如果当前数大于temp前面 一个数需要进行移动
                while(j >=0 && arr[j] > temp) {
                    arr[j+step] = arr[j];
                    j-=step;
                }
                arr[j+step] = temp; // 上面插入后还进行了j-step
            }
        }
        return arr;
    }

}

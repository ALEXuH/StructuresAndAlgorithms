package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/24
 * 选择排序：选择最小的数往前排 交换次数比冒泡少
 *
 * 思路：
 * 假定当前数位最小值，遍历后面数据，如果发现更小的数则进行交换
 * 如从 从数组中第一个开始，假定arr[0]为最小值，遍历后面的数据如果有比arr[0]更小的则进行交换
 * 依次类推直到最后
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.generateIntArr(10, true);

        SortUtils.printArr(select(arr));

        System.out.println(SortUtils.getCurrentTime());
        select(SortUtils.generateIntArr(80000, false)); // 3s
        System.out.println(SortUtils.getCurrentTime());
    }

    public static int[] select(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 0; i < len; i++) {
            int sort = arr[i];
            int cur = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < sort) {
                    sort = arr[j];
                    cur = j;
                }
            }
            //  找到最小值 交换cur和i的值
            if (cur != i) {
                temp = arr[cur];
                arr[cur] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

}

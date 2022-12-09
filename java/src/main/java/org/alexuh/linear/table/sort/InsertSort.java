package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/24
 * 简单插入排序：
 *              i j tmp          j-1  j  tmp
 * [5 31 101 220  10]  [5 31 101 220 220]
 *                                  原来为10（tmp）
 * 把n个元素看成有序和无序列表, 开始有序表只有一个元素，无序表有n-1个元素，
 * 每次从无序表中取出一个元素将它与前面的有序列表值进行比较（扑克牌插入），
 * 如果找到值比需要插入的值小说明找到合适的位置，插入在有序表的合适位置
 */
public class InsertSort {

    public static void main(String[] args) {
        SortUtils.printArr(insertSort(SortUtils.generateIntArr(10, true)));
        System.out.println(SortUtils.getCurrentTime());
        insertSort(SortUtils.generateIntArr(80000, false)); // 400ms
        System.out.println(SortUtils.getCurrentTime());
    }

    public static int[] insertSort(int[] arr) {
        // 将第一个数看成有序列表，从第二个数开始遍历
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            // 保存要插入的数据
            int tmp = arr[i];
            int j = i ;
            // 找到比它小的数进行后移，假设第一比较数tmp<arr[j] 第一次直接覆盖i tmp的位置
            while(j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 如过找到比它大的数进行插入
            arr[j] = tmp;
        }
        return arr;
    }

}

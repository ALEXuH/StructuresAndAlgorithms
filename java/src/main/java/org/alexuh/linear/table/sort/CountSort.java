package org.alexuh.linear.table.sort;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/1
 *
 * 计数排序  o(N)
 * 特点
 * 1.排序元素必须是整数
 * 2.元素取值在一定范围内效率最好
 *
 * 思想
 * 1.找出愿数组中最大值max，并初始化一个新数组C = int[max],长度为max+1
 * 2.遍历原数组，统计数组的每个元素v出现的个数，并存入C[v]
 * 3.创建结果数组，找到C中元素大于0的元素，将它的索引值填入result数组中，每处理一次count-1直到count=0
 * 4.返回结果result
 *
 *
 *  优化（解决空间浪费）
 *  找出最大最小值 max/min 利用max-min的差值给定计数数组的长度 max-min+1
 *  计数索引就为num-min
 *
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.generateIntArr(10, true);
        SortUtils.printArr(countSort(arr));


        System.out.println(SortUtils.getCurrentTime());
        countSort(SortUtils.generateIntArr(80000, false));  // 7ms
        System.out.println(SortUtils.getCurrentTime());
    }

    public static int[] countSort(int[] arr) {
        // 找出最大值初始化计数数组
        int max = 0;
        for (int v : arr) {
            if (v > max) {
                max = v;
            }
        }
        int[] countArr = new int[max+1];
        // 填充计数数组
        for (int v : arr) {
            countArr[v]++;
        }
        // 初始化结果数组并填充值
        int[] result = new int[arr.length];
        int t = 0;
        for (int i = 0; i < countArr.length; i++) {
            while(countArr[i] > 0) {
                result[t] = i;
                countArr[i]--;
                t++;
            }
        }
        // 返回result数组
        return result;
    }

}

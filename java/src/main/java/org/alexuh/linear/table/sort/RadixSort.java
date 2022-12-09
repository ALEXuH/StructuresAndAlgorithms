package org.alexuh.linear.table.sort;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/1
 * 基数排序
 *
 * O(n * k) k 为数字位数
 * 空间复杂度O(rn) 为基数，如10进制则为10
 *
 * 思想:
 * 1.将所有待比较的数值统一为相同的位数，较短的数值补0
 * 2.从最低位开始依次进行一次放置（非比较只是将数据放入对应的桶中）,将数据放入基数（10进制为10）个桶中
 * 3.当最低位到最高位都完成一轮即得到有序序列
 *
 *
 */
public class RadixSort {
    public static void main(String[] args) {
       // int[] arr = SortUtils.generateIntArr(10, true);
        int[] arr1 = new int[10];
        arr1[0] = 12;
        arr1[1] = 220;
        arr1[2] = 22;
        arr1[3] = 16;
        arr1[4] = 32;
        arr1[5] = 4;
        arr1[6] = 2;
        arr1[7] = 10;
        arr1[8] = 12;
        radixSort(arr1);
        SortUtils.printArr(arr1);
    }

    public static void radixSort(int[] arr) {
        //1.获取数组中最大的数获取它的位数
        int max = arr[0];
        for(int i=1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length(); // 最大数位数
        int s = 1;
        for (int k = 0; k < maxLength; k++) {
            // 初始化桶，10进制排序,定义一个二维数组表示10个桶，防止桶不够放所以每个桶里的大小为arr.len
            int[][] bucket = new int[10][arr.length];
            // 定义一个数组，记录每个桶中放了多少数据(用于遍历二维数组)
            int[] bucketElementCount = new int[arr.length];
            // 将元素放如桶中
            for (int i = 0; i < arr.length; i++) {
                // 获取i位数的值，由于是10进制
                int t = arr[i] / s  % 10;
                // 放入桶中 (假设为1，放入第一个桶中，同时bucketElementCount对应桶中的数值加1（当前指针）)
                bucket[t][bucketElementCount[t]] = arr[i];
                bucketElementCount[t] ++;
            }
            int t = 0;
            // 按照桶的顺序依次取出数据，放入到原来数组中
            for (int i = 0; i < bucket.length; i++) {
                // 如果桶中有数据放入原数组
                if(bucketElementCount[i] != 0){
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        arr[t++] = bucket[i][j];
                    }
                }
                // 将记录桶索引重置为0进行下一轮
                bucketElementCount[i] = 0;
            }
            s*=10;
            System.out.println(String.format("比较右边第%s位:第%s轮排序结果" + Arrays.toString(arr),k+1,k+1));
        }

    }
}

package org.alexuh.linear.table.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/24
 */
public class SortUtils {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 获取当前时间
    public static  String getCurrentTime() {
        Date date = new Date();
        return sdf.format(date);
    }

    // 随机生成n位的int数组
    public static int[] generateIntArr(int n, boolean flag) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * n);
        }
        // 打印数组
        if (flag) {
            System.out.println("-----------原数组");
            printArr(arr);
        }
        return arr;
    }

    public static void main(String[] args) {
        //System.out.println((int)(Math.random() * 80000));
        generateIntArr(10, true);
        generateIntArr(10, false);
    }

    // 打印数组
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("---------");
    }

}

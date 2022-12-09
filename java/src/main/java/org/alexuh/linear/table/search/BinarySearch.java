package org.alexuh.linear.table.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/1
 *
 * 二分查找 递归/非递归/查询多个相同值
 *
 * 二分查找/折半查找算法(要求数据已经排序好）
 * 思路：
 * 1.定义左右指针left,right,取中间值mid
 * 2.判断arr[mid]=findValue,如果相等则说明查找到，如果不等并且arr[mid] < findValue,继续向右边递归查找，
 * 如果不等并且arr[mid] > findValue,继续向左边边递归查找
 * 3.如果查找到arr[mid]=findValue或者left>right 则结束递归
 */
public class BinarySearch {


    public static void main(String[] args) {
        // 查找单个值
        int[] arr = {1,2,2,3,4,5,5,5,6,7,8,9,9,9,10};
        System.out.println(binarySearch(arr, 10, 0, arr.length - 1));
        System.out.println(binarySearch(arr, 9, 0, arr.length - 1));
        System.out.println(binarySearch(arr, 11, 0, arr.length - 1));

        System.out.println("--------");
        // 非递归
        System.out.println(binarySearch(arr, 10));
        System.out.println(binarySearch(arr, 9));
        System.out.println(binarySearch(arr, 11));
        System.out.println("--------");
        // 查找多个相同的值
        System.out.println(binarySearchList(arr, 1, 0, arr.length - 1));
        System.out.println(binarySearchList(arr, 2, 0, arr.length - 1));
        System.out.println(binarySearchList(arr, 9, 0, arr.length - 1));
    }

    // 查找单个 递归
    public static int binarySearch(int[] arr, int v, int left, int right) {
        // 没有查找到结束递归
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] > v) {
            return binarySearch(arr, v, left, mid - 1);
        } else if (arr[mid] < v) {
            return binarySearch(arr, v, mid+1, right);
        } else {
            return mid;  // 查找到返回值
        }
    }

    // 非递归方式二分查找
    public static int binarySearch(int[] arr, int v) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        int index = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            // 中间值小于查找值向右查找
            if (arr[mid] < v) {
                left = mid + 1;
            } else if (arr[mid] > v) {
                right = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    // 查找多个相同的值 (查找到第一个相同后可以扫描2边，数组是有序的)
    public static List<Integer> binarySearchList(int[] arr, int v, int left, int right) {
        // 没有查找到结束递归
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if (arr[mid] > v) {
             return binarySearchList(arr, v, left, mid - 1);
        } else if (arr[mid] < v) {
             return binarySearchList(arr, v, mid+1, right);
        } else {
            // 如果查找到则向左边扫描和右边扫面，符合条件的加到数组中（已经排序的，相同的肯定在左右两边）
            List<Integer> list = new ArrayList<Integer>();
            // 先扫描左边
            for (int i = mid - 1; i >= 0 ; i--) {
                if (arr[i] != v) {
                    break;
                }
                list.add(i);
            }
            // 添加mid值
            list.add(mid);
            // 扫描右边
            for (int i = mid + 1; i <= right; i++) {
                if(arr[i] != v) {
                    break;
                }
                list.add(i);
            }
            return list;
        }
    }


}

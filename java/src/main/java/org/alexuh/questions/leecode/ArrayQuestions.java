package org.alexuh.questions.leecode;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/24
 *
 * 数组问题
 *
 * 1.单指针
 * 2.双指针
 *
 */
public class ArrayQuestions {

    public static void main(String[] args) {
        //int[] a = new int[]{-5,-4,0, 1,2,3,5};
        int[][] a = generateMatrix(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(a[i]));
        }

    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度
     *
     * 解法: 快慢指针, 慢指针：原来数组的位置 快指针：新数组的元素位置
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int len = nums.length;
        for (int j = 0; j < len; j++) {
            if (val != nums[j]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 977. 有序数组的平方
     *
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
     *
     * 解法1：先计算在排序
     * 解法2：平方后最大的都在最前和最后 如(-5,-4,-3,0,3,4,5),双指针比较最前和最后的填入新数组
     *
     * @param nums
     * @return
     */
    public  int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        // 相等时需要平方赋值一下
        while (left <= right) {
            int vl = nums[left] * nums[left];
            int vr = nums[right] * nums[right];
            if (vl <= vr) {
                result[index--] = vr;
                right--;
            } else {
                result[index--] = vl;
                left++;
            }
        }
        return result;
    }

    /**
     * 209. 长度最小的子数组
     *给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0
     *
     * 解法：滑动窗口
     * 1.设置start和end窗口开始和结束位置,
     * 2.找到连续相加大于等于target的记录此时的长度，然后将start+1 往右滑动继续找最小的连续长度
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;     // 滑动窗口前
        int end = 0;      // 滑动窗口后
        int minLength = Integer.MAX_VALUE; // 符合条件连续子串长度
        int len = nums.length;
        int sum = 0;

        while (end < len) {
            sum += nums[end];
            // 当符合条件时向右滑动窗口start+1并sum减去star的值，不满足的推出循环继续end++
            while(sum >= target) {
                sum -= nums[start];  // 类似与回溯
                minLength = Math.min(minLength, end- start + 1);
                start++;
            }
            end++;
        }
        // 没有时返回0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 76. 最小覆盖子串
     *
     * 解法：滑动窗口
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        return null;
    }

    /**
     *59. 螺旋矩阵 II
     *给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     *
     * 解法: 依次处理上右下左的边（边界都是左闭右开）
     * notice: 注意n为奇数时候要单独给中间的位置赋值
     *
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 1; // 定义要填充的数字
        int loop = n / 2; // 需要循环次数转几圈
        int offset = 1; // 边界控制每次需要减去1也就是offset+1
        int start = 0; // 起始位置
        int i = 0,j = 0; // 当前二维数组的位置

        while(loop > 0) {
            // 处理上面边
            for(j = start; j < n - offset; j++) {
                result[start][j] = count++;
            }
            // 处理右边边 j所在位置已经是边界位置
            for (i = start; i < n - offset; i++) {
                result[i][j] = count++;
            }
            // 处理下面边 i已经处于最后一行，j处于最后一列，所以需要j向前减
            for(j = j; j > offset-1; j--) {  // 第一圈要在index=1的位置，第二圈要在index=2的位置
                result[i][j] = count++;
            }
            // 处理左边边 j处于最左边边界,i处于最后一行,所以i要向前递减
            for(i=i; i > offset  - 1; i--) {
                result[i][j] = count++;
            }
            loop--;
            start++;
            offset++;
        }
        // 如果为奇数中间单独赋值
        if (n % 2 == 1) {
            result[n/2][n/2] = count;
        }
        return result;
    }

}
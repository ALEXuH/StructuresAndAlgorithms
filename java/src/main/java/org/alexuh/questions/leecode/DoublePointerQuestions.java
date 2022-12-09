package org.alexuh.questions.leecode;

/**
 * Created by xuzhiceng@Gmail.com on 2022/12/2
 *
 * 双指针问题
 *
 * 一般为字符串 数组
 *
 */
public class DoublePointerQuestions {

    public static void main(String[] args) {
    }

    /**
     * 392. 判断子序列
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 思路：给定两个指针 i j
     * 当 s[i] = s[j] 时i++ 当i走到最后时说明是子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length() - 1; // 当i遍历到最后上面还i++了所以只需要i=len
    }

    /**
     * 42. 接雨水
     * https://leetcode.cn/problems/trapping-rain-water/description/
     * <p>
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 解法：双指针
     * <p>
     * 计算每一列能接的雨水数量
     * 1.找到这一列左右的最大高度的柱子lheight,rheight, 木桶效应，存储雨水只能选最短的
     * 所以这一列可存储雨水为 min(lheight, rheight) - curHeight
     * <p>
     * notice： 第一列和最后一列旁边都没有柱子不能存储雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        // 遍历每一列计算每一列能存的雨水
        for (int i = 0; i < height.length; i++) {
            // 第一列和最后一列不能存水
            if (i == 0 || i == height.length - 1) {
                continue;
            }
            int leftHeight = height[i];
            int rightHeight = height[i];

            // 找左边最长柱子
            for (int j = 0; j < i; j++) {
                leftHeight = Math.max(leftHeight, height[j]);
            }
            // 找右边最长柱子
            for (int k = i + 1; k < height.length; k++) {
                rightHeight = Math.max(rightHeight, height[k]);
            }
            // 计算结果(木桶效应，存储雨水只能选最短的,所以这一列可存储雨水为 min(lheight, rheight) - curHeight)
            int v = Math.min(leftHeight, rightHeight) - height[i];
            if (v > 0) {
                sum += v;
            }

        }
        return sum;
    }


    /**
     * 84. 柱状图中最大的矩形 https://leetcode.cn/problems/largest-rectangle-in-histogram/
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <p>
     * 解法1：双指针
     * 计算每一列能够得到的最大面积
     * <p>
     * 1.因为都是相邻的所以要往左右分别找到 大于等于当前高度的的位置 right ，left
     * 宽度为 right-left+1  h=curHeight
     * s = (right-left+1) * curHeight
     * <p>
     * <p>
     * 解法2：单调栈
     * 找到两边比当前元素小的位置leftIndex，rightIndex 使用从栈底到栈顶递增栈（左边本来就小才能找到左边小的）
     * s = (leftIndex - rightIndex -1) * h
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int sum = 0;
        // 遍历每一列计算每一列最大面积
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int left = i;
            int right = i;
            // 获取左边大于等于当前高度的位置
            while (left >= 0 && h <= heights[left]) {
                left--;
            }
            left++; // 上面多减去1
            // 获取左边大于等于当前高度的位置
            while (right < heights.length && h <= heights[right]) {
                right++;
            }
            right--; // 上面多加列1
            // 计算面积
            sum = Math.max(sum, (right - left + 1) * h);
        }
        return sum;
    }

}
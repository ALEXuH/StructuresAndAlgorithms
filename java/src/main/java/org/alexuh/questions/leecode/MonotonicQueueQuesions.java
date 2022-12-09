package org.alexuh.questions.leecode;

import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/12/2
 *
 * 单调栈相关问题
 *
 * 单调栈：通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置
 * 或者寻找同时寻找两边比自己大或者小的元素的位置（最大面积和接雨水）
 *
 */
public class MonotonicQueueQuesions {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(new MonotonicQueueQuesions().nextGreaterElement(new int[]{4,1,2}, new int[]{1,2,3,4})));
        System.out.println(new MonotonicQueueQuesions().trap(new int[]{4,2,0,3,2,5}));
    }

    /**
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer
     * ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     *
     * 思路：查找当前元素右边较大的值的位置，使用单调栈并且递减
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        // 单调栈，栈中存放数组的索引
        for (int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int v = stack.pop();
                result[v] = i - v;
            }
            stack.push(i);
        }
        return result;
    }


    /**
     *496. 下一个更大元素 I
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     *
     * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出：[-1,3,-1]
     * 解释：nums1 中每个值的下一个更大元素如下所述：
     * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
     * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();
        // 无重复元素所以可以使用map保存num2中的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = -1; //找不到-1
            for(int j = map.get(nums1[i]); j < nums2.length; j++) {
                while(!stack.isEmpty() && nums2[j] > stack.peek()) {
                    // 找到比栈底元素更大的值，栈要弹空
                    stack.pop();
                    if (stack.isEmpty() && result[i] == -1) {
                        result[i] = nums2[j];
                    }
                }
                stack.push(nums2[j]);
            }
            stack.clear();
        }
        return result;
    }


    /**
     * 503. 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
     * 如果不存在，则输出 -1 。
     *
     * 输入: nums = [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     *
     * 解法：单调栈
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        return null;
    }

    /**
     * 84. 柱状图中最大的矩形
     * * 解法2：单调栈
     * * 找到两边比当前元素小的位置leftIndex，rightIndex 使用从栈底到栈顶递增栈（左边本来就小才能找到左边小的）
     * * s = (leftIndex - rightIndex -1) * h
     *
     * @param tempHeights
     * @return
     */
    public int largestRectangleArea1(int[] tempHeights) {
        // 存储索引位置 并且栈底到栈顶递增，栈顶的下面一个元素是左边最小的,新加的元素i如果小于栈顶则i是右边最小元素位置
        Stack<Integer> stack = new Stack<>();
        // notice: 开头和结尾没有左边最小的和右边最所以前后都添加一个0方便计算第一个和最后一个柱子
        int[] heights = new int[tempHeights.length + 2];
        for (int i = 0; i < tempHeights.length; i++) {
            heights[i+1] = tempHeights[i];
        }
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            // 单调栈 找到右边小的和左边小的计算中间的面积
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int midIndex = stack.pop(); // 弹出栈顶
                int rightIndex = i;
                int midHeight = heights[midIndex]; // 高度
                int leftIndex = stack.peek();
                result = Math.max(result, (rightIndex - leftIndex - 1) * midHeight); // 计算面积
            }
            stack.push(i);
        }
        return result;
    }


    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 存储元素位置信息
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        // 求每一行能接的雨水，找到该列左边和右边最高的leftIndex,rightIndex,
        // 高 = min(height[rightIndex], height[leftIndex]) - currHeight
        // 宽 = rightIndex - leftIndex -1
        // 雨水= w * h
        for (int i = 0; i < height.length; i++) {
            //  找到左右两边大的元素
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int currIndex = stack.pop(); // 当前列高度
                int currHeight = height[currIndex];
                // 如果没有左边的高度则是敞开的不能装水
                if (!stack.isEmpty()) {
                    int leftIndex = stack.peek(); // 左边第一个最大高度的位置
                    int leftHeight = height[leftIndex];
                    int rightIndex = i; // 右边第一个最大高度位置
                    int rightHeight = height[rightIndex];
                    int h = Math.min(leftHeight, rightHeight) - currHeight;
                    int w = rightIndex - leftIndex - 1;
                    sum += w*h;
                }
            }
            stack.push(i);
        }
        return sum;
    }

}
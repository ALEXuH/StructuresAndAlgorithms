package org.alexuh.questions.leecode;

import java.util.*;

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
        String s = "aA ";
        System.out.println(s.charAt(1) - 'a');
        System.out.println(s.charAt(0) - 'a');
        //System.out.println(new DoublePointerQuestions().checkInclusion("ab", "eidbaooo"));
        System.out.println(new DoublePointerQuestions().minWindow("ADOBECODEBANC","ABC"));
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

    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;     // 滑动窗口前
        int right = 0;      // 滑动窗口后
        int minLength = Integer.MAX_VALUE; // 符合条件连续子串长度
        int len = nums.length;
        int sum = 0;

        while (right < len) {
            sum += nums[right]; // 右指针元素加入窗口

            // 当符合条件时向右滑动窗口start+1并sum减去star的值，不满足的推出循环继续end++
            // 1 1 1 1 100 target=100类似于这种就需要一直减
            while(sum >= target) {
                sum -= nums[left]; // 移除左指针元素
                minLength = Math.min(minLength, right - left + 1);
                left++;  // 左指针窗口➡️移动
            }

            right++;  // 右指针右移窗口
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return new ArrayList<>();
        }
        int[] target = new int[26]; // 存储目标26个字母
        int[] window = new int[26];  // 窗口数据:存储窗口中的数据
        for (int i = 0; i < p.length(); i++) {
            target[p.charAt(i) - 'a']++; // 统计出现频次
        }
        List<Integer> result = new ArrayList<>();
        // 开窗
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            window[s.charAt(right)-'a']++; // 入窗: 数据放入窗口
            while(right - left + 1 >= p.length()) { // 满足需要判断的条件,这里也可以使用if,当满足len条件后，left右移肯定就不符合条件了
                if (Arrays.equals(window, target)) { // 判断是否满足题目要求
                    result.add(left);
                }
                window[s.charAt(left)- 'a']--; // 移除窗口左指针数据
                left++; // 滑动左指针: 右移左指针
            }
            right++; // 滑动右指针: 右移右指针

        }
        return result;
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口: 字符不能使用字母数组统计法
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>(); // 存放窗口数据
        // 开窗
        int left = 0;
        int right = 0;
        int maxLenght = 0;
        while (right < s.length()) {
            while(set.contains(s.charAt(right))) { // 直到频次等于0
                set.remove(s.charAt(left));  // 移除左指针数据
                left++;  // 滑动左指针: 右移动左指针
            }
            set.add(s.charAt(right)); // 入窗: 将右指针元素放入窗口
            maxLenght = Math.max(maxLenght, right - left + 1); // 统计结果
            right++;  // // 滑动右指针: 右移右指针
        }
        return maxLenght;

    }

    /**
     * 567. 字符串的排列
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // 滑动窗口
        if ((s1 == null || s2 == null) || (s1.length() == 0 && s2.length() == 0)) {
            return true;
        }
        int[] target = new int[26];  // 匹配串数据: 字母出现频次
        int[] window = new int[26]; // 存储窗口数据: 滑动窗口中如果出现频次和target相同则符合题意
        for (int i = 0; i < s1.length(); i++) { // 初始化匹配串
            target[s1.charAt(i) - 'a']++;
        }
        // 开窗
        int left = 0;
        int right = 0;
        while (right < s2.length()) {
            window[s2.charAt(right) - 'a']++; // 入窗: 数据放入窗口
            while(right - left + 1  >= s1.length()) { // 窗口里长度符合条件,也可以使用if,窗口减1肯定小于之前的长度
                if (Arrays.equals(target, window)) { // 并且字母出现频次相同则存在
                    return true;
                }
                window[s2.charAt(left) - 'a']--; // 移除窗口左指针元素
                left++;                         // 滑动左指针
            }
            right++;            // 滑动右指针
        }

        return false;
    }

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // 滑动窗口: 使用map存储匹配串和窗口串中字符出现频次,如果窗口中的都大于等于则符合题目，然后不断滑动寻找最小
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        // 窗口容器和目标匹配容器
        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            target.put(t.charAt(i),target.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 开窗
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int leftResult = 0;
        int rightResult = 0;
        while (right < s.length()) {
            window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
            while(checkTargetAndWindow(target, window)) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    leftResult = left;
                    rightResult = right;
                }
                if (window.containsKey(s.charAt(left))) {
                    if (window.get(s.charAt(left)) == 1) {
                        window.remove(s.charAt(left));
                    } else {
                        window.put(s.charAt(left), window.getOrDefault(s.charAt(left), 0) - 1);
                    }
                }
                left++;
            }
            right++;
        }
        if (minLength == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(leftResult, rightResult + 1);
        }
    }

    private boolean checkTargetAndWindow(Map<Character, Integer> target, Map<Character, Integer> window) {
        for(char key : target.keySet()) {
            if (window.getOrDefault(key, 0) < target.get(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        // 思路: 要想找到下一个较大的排列需要从后往前将后面的大数于小数进行交换，为了满足增加幅度尽可能的小,
        // 在找到一个数num[left] < nums[right], 说明此时[right,end] 为降序,此时只需要从[right, end]中找到第一个nums[i] < nums[k]进行交换,
        // 交换之后,反转[j,end]

        // 6  3(i)    5(j)    4    3  2   找到left right
        // 6  4(i)    5(j)   3(k)  3  2  找到[right, end]中第一个较大的进行交换
        // 6  4        2      3    3  5  此时[j,end] 为升序为了尽可能小将[j,end]反转

        // 双指针从后面遍历,找到前面小后面大的数据进行交换
        if (nums == null || nums.length < 2) {
            return;
        }
        // 双指针从后向前遍历
        int left = nums.length - 2;
        int right = nums.length - 1;
        while (left > 0 && nums[left] >= nums[right]) {
            left--;
            right--;
        }
        // 如果是最后一个排列(最大的排列如 3 2 1)直接反转返回
        if (left == 0 && nums[left] >= nums[right]) {
            reserve(nums, 0, nums.length - 1);
            return;
        }
        // 如果不是最后一个排列,则a[left] < a[right] 则从[left, end] 范围内肯定为降序,只要从后向前找找到第一个满足a[i] < a[k]的交换即可
        System.out.println(right);
        int k = nums.length - 1;
        while(nums[left] >= nums[k]) {
           k--;
        }
        swap(nums, left, k);

        // 反转 [right, end]
        reserve(nums, right, nums.length - 1);

    }

    private void reserve(int[] nums, int i, int i1) {
        while (i < i1) {
            swap(nums, i , i1);
            i++;
            i1--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 287. 寻找重复数
     * n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 由题意可知 index[0,n] values[1,n] 所以 num[num[index]] 一直不会越界，可以抽象成一个环形的链表
        // 环形链表求解交点: 1.使用快慢指针找到在环中的相交点 2.将slow=0,继续从交点处寻找到相同的

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while(true) {
            // 找到在环中相交的点
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                slow = 0;
                while (nums[slow] != nums[fast]) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return nums[slow];
            }
        }

    }

    /**
     * 75. 颜色分类
     * @param nums
     */
    public void sortColors(int[] nums) {
        // 方法一：计数排序 方法二: 单指针扫描2次  方法三: 双指针
        // 和删除数组元素类似(单指针)，使用单指针将需要的往前覆盖，本题不能直接覆盖需要交换

        // 单指针2次交换: 先把0交换到前面，然后把1交换的前面使用2次循环
        int cur = 0;
        // 将0交换到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) { // 如果找到0交换和当前index的位置
                int temp = nums[i];
                nums[i] = nums[cur];
                nums[cur] = temp;
                cur++; // 当前指针后移,因为i>=cur
            }
        }
        //将1交换到前面,cur此时位置为非0处
        for(int i = cur; i < nums.length; i++) {
            if (nums[i] == 1) { // 找到1进行交换
                int temp = nums[i];
                nums[i] = nums[cur];
                nums[cur] = temp;
                cur++;
            }
        }

    }

}
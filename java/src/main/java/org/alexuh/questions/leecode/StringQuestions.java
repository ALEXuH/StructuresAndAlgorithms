package org.alexuh.questions.leecode;


import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/26
 *
 * 字符串相关问题
 *
 */
public class StringQuestions {

    public static void main(String[] args) {
        //System.out.println(new StringQuestions().reverseWords("the sky is blue"));
       // System.out.println(new StringQuestions().repeatedSubstringPattern("abac"));
        System.out.println(Arrays.toString(StringQuestions.topKFrequent(new int[]{5,3,1,1,1,3,73,1}, 2)));
    }

    /**
     *
     * 344. 反转字符串
     *
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出
     *
     * 解法: 双指针
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     *541. 反转字符串 II
     *
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     *
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * @param s
     * @param k
     * @return
     */
//    public String reverseStr(String s, int k) {
//        char[] sChar = s.toCharArray();
//        for (int i = 0; i < s.length(); i+=k*2) {
//            // 如果剩余字符串小于2k但是大于或者等于k翻转前k个
//            if (i + k <= sChar.length) {
//
//            }
//        }
//
//    }

    /**
     * 剑指 Offer 05. 替换空格
     *请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 解法：遍历一遍遇到空格填充%20
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(sChar[i]);
            }
        }
        return  sb.toString();
    }

    /**
     * 151. 反转字符串中的单词
     *
     *给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     *
     * 解法
     * 1.去除多余的空格
     * 2.翻转整个字符串
     * 3.翻转每个单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        // 1.去除多余的空格
        // 2.翻转整个字符串
        char[] cs = removeSpaceAndReserveString(s);
        // 3.翻转每个单词
        int start = 0;
        int end = 0;
        for (int i = 0; i < cs.length; i++) {
            // 遇到空格或者到最后一位 翻转start - end - 1的字符串并将start置为end后一位，end在下面也会+1
            if (cs[i] == ' ') {
                reverseString(cs, start, end - 1);
                start = end + 1;
            }
            // i到最后一位 只需要翻转start - end
            if (i == cs.length - 1) {
                reverseString(cs, start, end);
            }
            end++;
        }
        StringBuilder sb = new StringBuilder();
        for (char v : cs) {
            sb.append(v);
        }
        return sb.toString();
    }

    private char[] removeSpaceAndReserveString(String s) {
        char[] sChar = s.toCharArray();
        int end = s.length() - 1;
        // 找到尾部非空的位置
        while(sChar[end] == ' ') {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < end+1; i++) {   // end为最后的索引位置，全部遍历需要加1
            // 去除前面空格：如果sb为空并且字符为空  去除中间多余的空格：如果sb的最后一为空格并且字符也为空格
            if (sb.length() == 0 && sChar[i] == ' ' || sChar[i] == ' ' && sb.charAt(sb.length() - 1) == ' '){
                continue;
            }
            sb.append(sChar[i]);
        }
        return reverseString(sb.toString().toCharArray(), 0 , sb.length() - 1);
    }

    public char[] reverseString(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
        return s;
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     *
     *解法：局部翻转 + 整体翻转
     *
     * abcdefg n=2
     *
     * 1.分别翻转前n和剩余的字符串  bagfedc
     * 2.翻转整个字符串   cdefgab
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] sc = s.toCharArray();
        reverseString(sc, 0 , n - 1);  // 翻转前n
        reverseString(sc, n , sc.length-1);  // 翻转n-末尾
        reverseString(sc, 0, sc.length -1 ); // 翻转整个字符串
        StringBuilder sb = new StringBuilder();
        for (char c : sc) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     *
     * 28. 找出字符串中第一个匹配项的下标
     *给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
     * 如果 needle 不是 haystack 的一部分，则返回  -1 。
     *
     * 解法：kmp字符串匹配
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int[] next = getNext(needle); // 获取前缀表
        char[] hc = haystack.toCharArray();
        char[] nc = needle.toCharArray();
        int j = 0;  // 模式串位置
        // 匹配字符串hasstack只向后移动，needle改变
        for (int i = 0; i < hc.length; i++) {
            while(j > 0 && hc[i] != nc[j]) {
                j = next[j - 1];
            }
            // 如果相等向后继续匹配
            if (hc[i] == nc[j]) {
                j++;
            }
            // 如果模式串匹配完则说明找到了位置
            if (j == nc.length) {
                return i - j + 1;
            }
        }
        return -1;
    }

    private int[] getNext(String needle) {
        char[] nc = needle.toCharArray();
        int[] next = new int[nc.length];
        next[0] = 0;
        int i = 0;  // 前缀的尾
        // j 后缀的尾 类似于j是匹配串位置 i是模式串位置
        for (int j = 1; j < nc.length; j++) {
            while (i > 0 && nc[i] != nc[j]) {
                i = next[i - 1];
            }
            if (nc[i] == nc[j]) {
                i++;
            }
            next[j] = i;
        }
        return next;
    }

    /**
     *
     * 459. 重复的子字符串
     *
     * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     *输入: s = "abab"
     * 输出: true
     * 解释: 可由子串 "ab" 重复两次构成。
     *
     * 解法: Kmp
     *
     * 假设为重复子串构成那么 len % (len - next[len-1]) == 0 长度能够整除一个周期的长度（len - 最大前缀长度）
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int[] next = getNext(s);
        System.out.println(Arrays.toString(next));
        if (next[s.length() - 1] != 0 && s.length() % (s.length() - next[s.length()-1]) == 0) { // 最后为0肯定不是重复子串构成并且能够整除一个周期
            return true;
        }
        return false;
    }

    /**
     *239. 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *  解法：维护一个优先队列 (带排序的队列 排序复杂度logn),遍历数据需要n次所以复杂度为nlogn
     *  (核心：利用优先队列进行排序同时存储索引用来判断边界得知当前队列头（最大值）是否是当前窗口的最大值)
     *
     * 1.维护一个大顶堆的优先队列，使得大的元素在队列头,里面存储 [value, index]数据和在nums里的位置
     * 2.先初始化队列将前k个数放入，获取第一个最大值
     * 3.遍历元素继续放入下一个元素，队列头不一定是这个滑动窗口里的，所以需要判断位置是否小于当前滑动窗口的左边界，
     * 如果小于说明是上一个滑动窗口的移出队列，直到大于左边界，此时队列头部就是当前滑动窗口的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 初始化优先队列 [value, index] 元素值和nums索引
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < k; i++) {
            q.add(new int[]{nums[i], i});
        }
        int len = nums.length;
        int[] result = new int[len - k + 1]; // 存放结果的数组
        int index = 1;
        if (!q.isEmpty()) {
            result[0] = q.peek()[0];
        }
        // 遍历数组将元素放入优先队列中获取当前滑动窗口的最大值
        for (int i = k; i < len; i++) {
            q.add(new int[]{nums[i], i});
            while (q.peek()[1] <= i - k) {  // 如果索引小于单前滑动窗口的左边界则弹出该元素
                q.poll();
            }
            result[index++] = q.peek()[0];
        }
        return result;

    }

    /**
     *
     * 347. 前 K 个高频元素
     *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     *
     * 解法:
     * 1.使用map哈希表统计次数
     * 2.使用优先队列（大顶堆）排序
     *
     * @param nums
     * @param k
     * @return
     */

    public static int[] topKFrequent(int[] nums, int k) {
        // hash表统计次数
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // 优先队列排序 [num, cut]  cut出现频次
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for(int key : map.keySet()) {
            pq.add(new int[]{key, map.get(key)});
        }
        // 出列获取前k频次
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }
        return result;
     }

}

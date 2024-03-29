package org.alexuh.questions.leecode;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by xuzhiceng@Gmail.com on 2022/12/6
 *
 * leecode热门100题
 *
 */
public class Leecode100 {

    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1); // 新链表头节点
        ListNode cur = head;
        int cary = 0; // 进位数

        while(l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            // 求链表存储值和下一位进位值 如 a+b =  13 ,存储值3 进位值1 如 a+b = 5 存储值5 进位值0
            int sum = a + b + cary;
            cary = sum / 10; // a+b < 20 进位数只需要除10
            int v = sum % 10; // 需要存储到链表的值
            // 生成链表
            cur.next = new ListNode(v);
            cur = cur.next;
            // 移动l1,l2链表指针
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 判断最后的一次进位，如果不为0，则在添加一次节点
        if (cary != 0) {
            cur.next = new ListNode(cary);
        }
        return head.next;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 解法: 滑动窗口 和单调栈模版类似 while(不符合条件) start++; 左窗口滑动
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int len = s.length();
        int maxlen = 0;
        HashSet<Character> set = new HashSet<>();

        while (end < len) {
            // 判断s[end]是否存在start - end -1中，不存在end继续向右移动 直到存在可以收获结果（计算长度），并将star左滑动直到存在时
            while (set.contains(s.charAt(end))) {
                start++;
                set.remove(s.charAt(start));
            }
            set.add(s.charAt(end));
            maxlen = Math.max(maxlen, end -start-1); // 计算最大值
            // 不存在s[end]继续向右移动
            end++;
        }
        return maxlen;
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // dp[i][j]: 左上角到[i,j]的路径上最小和
        int m = grid[0].length;
        int n = grid.length;
        int[][] dp = new int[m][n];
        // 只能向下或者向右 ，初始化第一行第一列
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++) {
            dp[0][i] += dp[0][i-1] + grid[0][i];
        }
        // 遍历f[1][1] 开始
        for(int i=1; i < m; i++) {
            for(int j= 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m-1][n-1];
    }


    /**
     * 560. 和为 K 的子数组
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数
     *
     * 解法1: 枚举法
     * @param nums
     * @param k
     * @return
     */

    public int subarraySum(int[] nums, int k) {
        // 枚举法
       int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count ++;
                }
            }
        }
        return count;
    }


    /**
     * 240. 搜索二维矩阵 II
     *编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * 解法: 对每一行使用查找算法（二分查找/插值查找）
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 每一行进行二分查找
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            boolean flag = binarySerchRow(matrix[i], target);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean binarySerchRow(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 234. 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // 放到数组中然后使用双指针判断
        ListNode cur = head;
        List<Integer> values = new ArrayList<>();
        while(cur != null) {
            values.add(cur.val);
            cur = cur.next;
        }
        // 双指针判断是否回文
        int left = 0;
        int right = values.size()-1;
        while (left <= right ) {
            if (values.get(left) != values.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    /**
     * 148. 排序链表
     *
     * 解法: 使用优先队列
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 将链表放入优先队列排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        ListNode cur = head;
        while(cur != null) {
            queue.add(cur);
            cur = cur.next;
        }
        // 生成链表
        ListNode temHead = new ListNode();
        ListNode cur1 = temHead;
        while(!queue.isEmpty()) {
            cur1.next = queue.remove();
            cur1 = cur1.next;
        }
        // 防止环形链表
        cur1.next = null;
        return temHead.next;

    }

    /**
     *
     * 152. 乘积最大子数组
     *
     * 有正负数，可以维护一个最大最小值,再来乘nums[i]
     *
     * f[i] 前面i个元素乘机最大值
     * 最后状态：
     * 1.不乘 num[i]
     * 2.乘 num[i]
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]: 前i个元素最大乘机
        // int[] dp = new int[nums.length];
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 维护一个最小值，状态1: 不乘，自己成为最大最小值  状态2: 乘，因为正负数关心 可能是乘前面最大也可能是乘前面最小的
            dp[i][0] =  Math.min(Math.min(dp[i-1][0] * nums[i], dp[i-1][1] * nums[i]), nums[i]);
            // 维护一个最大值
            dp[i][1] =  Math.max(Math.max(dp[i-1][0] * nums[i], dp[i-1][1] * nums[i]), nums[i]);
            result = Math.max(result, dp[i][1]);
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return result;
    }

    /**
     * 283. 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // 一次遍历遇到非0元素就往前移
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) { // 遇到0的时候跳过指针继续下移，index还在之前位置,下一次非0可以直接覆盖0位置元素
                continue;
            }
            nums[index] = nums[i];
            index++;
        }
        // 后面填充0，非0的都前移了
        for (int i = index; i < nums.length ; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 维护一个只有k个数的 优先队列(默认队列头到尾 升序排列)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int v : nums) {
            pq.add(v);
            // 如果size > k 弹出头
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // 升序排列，队列头就是第k大的元素
        return pq.peek();
    }

    /**
     * 581. 最短无序连续子数组
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 判断是否已经排序，已经排序直接返回0不存在满足题目的
        if (isSorted(nums)) {
            return 0;
        }
        // 满足题意的进行排序后前后缀也不会改变，所以可以先对数组进行排序，排序后使用双指针取前后缀相同的
        int[] tempNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tempNums); // 排序

        System.out.println(Arrays.toString(tempNums));
        // 获取前后缀相同的位置
        int left = 0;
        while (tempNums[left] == nums[left]) {
            left++;
        }
        int right = nums.length-1;
        while (tempNums[right] == nums[right]) {
            right--;
        }
        return right - left + 1;

    }

    private boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome(String s) {
        //  中心扩散法，以中间一个点或者2个点扩散,获取最长回文串
        if(s == null || s.length() == 0) {
            return "";
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = longestPalindromea(s, i, i);
            int len2 = longestPalindromea(s, i, i+1);
            int len  = Math.max(len1, len2);
            if (len > right - left) {
                left = i - (len - 1) / 2;
                right = i+ len / 2;
            }
        }
        System.out.println(left  + " " + right);
        return s.substring(left, right + 1);
    }

    public int longestPalindromea(String s, int left, int right) {
        while(left >= 0 && right <= s.length()-1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left + 1;
    }

    /**
     * 516. 最长回文子序列
     *
     *解法：动态规划
     *
     *子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列
     *
     *  最后状态:
     *  1. i,j位置相等 f[i][j] = f[i+1][i-1] + 2
     *  2. 不相等取[i][j-1] 或者[i-1][j]最大的
     *
     *  递推公式
     *  if (s[i] = s[j])  f[i][j] = f[i+1][i-1] + 2
     *  if (s[i] != s[j])  f[i][j] = max(f[i][j-1], f[i-1][j])
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        // 子序列可以删除其中字符不能使用中心扩散法
        // f[i][j]: 表示 [i,j]范围内最长子序列长度
        int[][] dp = new int[s.length()][s.length()];
        // 初始化，单个字符都是回文
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        // 遍历顺序可知f[i][j] 都从下推在往左推
        // [1, 2, 3, 3, 4]
        // [0, 1, 2, 2, 3]
        // [0, 0, 1, 1, 3]
        // [0, 0, 0, 1, 1]
        // [0, 0, 0, 0, 1]
        for (int i = s.length() - 1; i >= 0 ; i--) { // 从后遍历
            for (int j = i+1; j < s.length(); j++) {  // [i,j] j肯定需要小于i
                dp[i][j] = 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2; // 状态1: i,j位置相同
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]); // i,j位置处不相同，取2个范围内最大的
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][s.length()-1];

    }

    /**
     *
     * 22. 括号生成
     *
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        // 保证生成有效括号只需要当前右括号的数量 小于等于左括号的数量就可以最终生成有效括号
        // 解法: 递归 + 回溯
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesisBackTrack(n, 1 , 1, result, sb);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    /**
     *
     * @param n   几对括号
     * @param open  左括号数量
     * @param close 右括号数量
     * @param result 收集的结果
     * @param sb
     */
    public void generateParenthesisBackTrack(int n, int open, int close, List<String> result, StringBuilder sb) {
        //  递归结束括号对都用完了，收集结果
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        }
        // 当(数量小于等于n （几对）可以继续添加(
        if (open <= n) {
            sb.append("(");
            generateParenthesisBackTrack(n, open + 1, close, result, sb); // 递归
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
        // 当)括号数量小于左括号说明还是有效括号可以继续添加
        // notice: 等于的时候在添加就不符合题意了
        if (close < open) {
            sb.append(")");
            generateParenthesisBackTrack(n, open, close + 1, result, sb); // 递归
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }

    }

    /**
     * 23. 合并K个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 解法: 先合并2个，返回之后继续和下一个进行合并
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoKLists(ans, lists[i]);
        }
        return ans;
    }

    // 合并2个升序链表
    public ListNode mergeTwoKLists(ListNode node1, ListNode node2) {

        if (node1 == null || node2 == null) {
            return node1 == null? node2: node1;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        ListNode cur1 = node1;
        ListNode cur2 = node2;

        //
        while(cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        // 把最后剩下的拼接上去
        cur.next = (cur1 == null ? cur2 : cur1);
        return head.next;
    }

    /**
     * 448. 找到所有数组中消失的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int v : nums) {
            set.add(v);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 394. 字符串解码
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        // 使用栈匹配[]
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] == ']') {
                StringBuilder sb1 = new StringBuilder();
                // 获得字符串
                while (!stack.isEmpty() && stack.peek() != '[') {
                     sb.append(stack.pop());
                }
                // 获取重复次数
                StringBuilder sb2 = new StringBuilder();
                while(!stack.isEmpty() ) {
                    if (stack.peek() != '[') {
                        sb2.append(stack.pop());
                    }
                }
                // 生成数据
                int v = Integer.parseInt(sb2.reverse().toString());
                for (int j = 0; j < v; j++) {
                    sb.append(sb1);
                }
            } else {
                stack.push(sChar[i]);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 二分查找多个
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int[] result = new int[]{-1, -1};
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else { //
                // 数组已经排序，如果找到等于target的可以分别扫描左边右边获取区间
                int l = mid;
                int r = mid;
                while (l >=0 && nums[l] == target) {
                    l--;
                }
                while (r <= right && nums[r] == target) {
                    r++;
                }
                result[0] = l+1;
                result[1] = r-1;
                break; // 找到目标值需要跳出循环
            }
        }
        return result;

    }

    /**
     * 416. 分割等和子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // 01背包,容量为 sum/2的背包能装最大重量
        int sum = 0;
        for(int v : nums) {
            sum += v;
        }
        // dp[i]: 背包容量为i能装最大重量
        int[] dp = new int[sum / 2];
        // 初始化
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) { // 遍历物品
            for (int j = sum / 2; j >= nums[i]; j--) {  // 遍历背包 01背包要逆序遍历
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        for (int i = sum / 2; i > 0; i--) { // 遍历背包
            for (int j = 0; j < nums.length; j++) {  // 遍历背包 01背包要逆序遍历
                if (i >= nums[j]) {
                    dp[i] = Math.max(dp[i], dp[i - nums[j]] + nums[j]);
                }
            }
        }
        // 获取容量为sum/2的最大重量
        int v = dp[sum / 2 -1];
        System.out.println(Arrays.toString(dp));
        // 如果最大值不等于sum/2那么不能使得两个子集相同，等于则存在
        if (v != sum / 2) {
            return false;
        } else {
            return true;
        }

    }

    /**
     *
     * 494. 目标和
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {

        return 0;
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        // 有n中不同物品且每个只能使用一次 => 01背包问题: 逆序遍历背包
        // 背包容量(目标和): 由题意可知总重量为sum, 可以将石头看成两堆,target = sum/2
        // 题目转化成: 背包容量为sum/2,能装的最大重量
        int sum = 0;
        for(int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum / 2;
        // dp[i]: 容量为i的背包对于给定物品最大能装的重量
        int[] dp = new int[target+1];
        // 初始化都为0
        // 遍历顺序从大到小 保证一次使用
        for(int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]); // 递推公式 max(dp[j], dp[j-nums[i]] + nums[i])
            }
        }
        int left = dp[target]; // 左边一堆石头重量
        int right = sum - left; // 右边一堆石头重量
        int v = Math.abs(right - left); // 粉碎后重量
        return v;

    }

    /**
     *33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        // 解法: 只要num.len >=0,那么对于排序旋转后的数组进行二分，一部分肯定是排序的，另一部分是没有排序的.
        // 如果target不在排序的里面那就可能在没有排序的数组里。

        // 对于null和len=1的进行判断
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        // 二分查找
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while(left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 检查target是不是在排序好的区间里如果不是就是在另外的一个区间
            // 如果左区间为排序好的区间并且 target < nums[mid] && target >= nums[left]说明元素在左区间，调整右指针
            // 如果右区间为排序好的区间并且 target > nums[mid] && target <= nums[right]说明元素在该区间，调整左指针
            if (nums[left] <= nums[mid]) { // 如果是左边排序好
                if (target < nums[mid] && target >= nums[left]) { // 并且target落在左区间
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {         // 如果是右边排序好
                if (target > nums[mid] && target <= nums[right]) { // 并且target落在右区间
                    left = mid + 1;
                } else {
                    right = mid -1;
                }
            }

        }
        return -1;
    }

    public void rotate(int[][] matrix) {
//        // 由图像规律可得第一行在图像的倒数第一列，第二行在图像的倒数第二列 所以旋转后 m[i][j] = m[][]
//        n = 2
//        (0,0)  -> (0,2)
//        (0,1)  -> (1,2)
//        (0,2) -> (2, 2)
    }

    /**
     * 49. 字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //  字母异位词，里面字母相同，所以排序后一定相同，可以使用map将排序后的作为key
        Map<String, List<String>> map = new HashMap<>();
        for (String v : strs) {
            char[] charArray = v.toCharArray();
            Arrays.sort(charArray);            // 排序数组
            String key = new String(charArray); // 转成数组排序在转回字符串
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(v);
            map.put(key, list);
        }
        List<List<String>> result = new ArrayList<>();
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    /**
     * 56. 合并区间
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 思路:
        // 先按左端点进行排序,[1,3] [1,6] [2,4] [2,3] [3,6]
        // 将第一个区间加入merge数组,如果后面一个数组的左端点小于等于在数组中的右端点,则说明区间重合，此时取两个区间右端点最大值
        // 如果后面一个数组左端点大于，meger数组中的右端点则说明区间不重合,将这个区间放入数组中
        // 一次进行上面的合并

        if (intervals == null || intervals.length == 0) {
            return new int[1][1];
        }
        // 1.左端点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 存储结果数组
        List<int[]> merge = new ArrayList<>();
        // 遍历合并
        for(int[] v : intervals) {
            int l = v[0]; // 左端点
            int r = v[1]; // 右端点
            if (merge.size() == 0 || merge.get(merge.size() - 1)[1] < l) { // meger列表为空或者前一个的右端点小于当前的左端点，区间不重合
                merge.add(v);
            } else { // 区间重合，选取右端点最大的
                merge.get(merge.size() - 1)[1] = Math.max(r, merge.get(merge.size() - 1)[1]);
            }
        }
        // 转化成数组
        return merge.toArray(new int[merge.size()][]);
    }

    /**
     * 406. 根据身高重建队列
     */

    public int[][] reconstructQueue(int[][] people) {
        // 对升高进行降序对排名进行升序排列，然后利用list.add(index, ele)的插入法
        // 排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0]; // 升高降序
                } else {
                    return o1[1] - o2[1]; // 排名升序
                }
            }
        });

        List<int[]> result = new ArrayList<>();
        for(int[] v : people) {
            result.add(v[1], v); // 插入法
        }
        return result.toArray(new int[result.size()][]);

    }

    /**
     * 128. 最长连续序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 解法: 类似于接雨水双指针解法(往2边走找较大的),初步: 初始化一个map,遍历每一个数，使用双指针两边走看是否在集合中，左右指针最长的就是结果
        // 优化: 只需从最小的一位数开始如 5 7 4 6 , 当遍历到5时发现有4直接跳过继续往下遍历，直到4就可以开始统计以4开始的连续序列长度
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        Set<Integer> set = new HashSet<>();
        for(int v : nums) {
            set.add(v);
        }
        for(int v : nums) {
            int l = 1;
            if (set.contains(v - 1)) { // 存在比自己小1的继续寻找，直到找到最小的开始统计
                continue;
            }
            while(set.contains(v+1)) {
                v++; // notice: v要加1不然就一直循环
                l++;
            }
            maxLength = Math.max(maxLength, l);
        }
        return maxLength;
    }

    //4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0.0;
    }

    /**
     * 238. 除自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        // 解法: 当前数组值 = 左边的乘机 * 右边的乘机
        int[] left = new int[nums.length];
        int v = 1;
        // 左边乘机
        for (int i = 0; i < nums.length; i++) {
            left[i] = v;  // 第一位左边乘机为1
            v *= nums[i];
        }
        System.out.println(Arrays.toString(left));
        // 右边乘机 和输出一起
        int[] result = new int[nums.length];
        int r = 1;
        for(int i=nums.length - 1; i >= 0 ;i--) {
            result[i] = r*left[i]; // 左边乘机 * 右边乘机
            r *= nums[i]; // 右边乘机: 最后一位右边乘机为1
        }
        return result;
    }

    /**
     * 72. 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        // 解法: 动态规划,最后状态插入,删除,替换
        // dp[i][j]: 表示word1 [0,i]和word2 [0,j]的最短编辑距离
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化，空字符串->word1,都是只需要添加也就是dp[i][0] = i
        for(int i = 0; i < m+1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n+1; i++) {
            dp[0][i] = i;
        }
        // 遍历顺序从左到右，从上到下
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                // 当前遍历位置i-1 j-1
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // 状态1: 如果最后一位相同不需要操作即可
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // 状态2: world1需要删除一个字符 dp[i-1][j] + 1
                    // 状态3：word1需要新增一个字符  dp[i][j-1] + 1
                    // 状态4: word1需要替换一个字符 dp[i-1][j-1] + 1
                    // dp[i][j] = min(状态2，状态3,状态4)
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        // 解法: dfs访问所有节点,遇到1的陆地就标记与它周围相邻的陆地这周围标记的就是一个岛屿,依次遍历所有网格所有的点获得所有的岛屿
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // 遇到1的陆地结果加1并将周围陆地都标记为已访问
                if (grid[i][j] == '1') {
                    res++;
                    numIslandsDfs(grid,i, j);
                }
            }
        }
        return res;
    }

    public void numIslandsDfs(char[][] grid, int row, int col) {
        // 递归返回条件1：上下左右边界判断超出即返回，和二叉树dfs类似
        if (col < 0 || row < 0 || row > grid.length - 1 || col > grid[0].length - 1) {
            return;
        }
        // 递归返回条件2: 如果非陆地也就是 grid[row][col] != 1
        if (grid[row][col] != '1') {
            return;
        }
        // 和二叉树先序遍历相同，先访问当前节点，标记为已访问，然后访问上下左右节点
        grid[row][col] = '2'; // 访问的标记为2
        // 访问上下左右
        numIslandsDfs(grid, row+1, col);
        numIslandsDfs(grid, row-1, col);
        numIslandsDfs(grid, row, col+1);
        numIslandsDfs(grid, row, col-1);
    }


    /**
     * 96. 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // 直接记忆公式  dp[i] += dp[j-1] * dp[i-j];
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    /**
     * 124. 二叉树中的最大路径和
     */
    int maxValue = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // 后序遍历+递归
        maxPathSumDfs(root);
        return maxValue;
    }

    public int maxPathSumDfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左子树最大值
        int left = Math.max(maxPathSumDfs(node.left), 0);
        // 右子树最大值
        int right = Math.max(maxPathSumDfs(node.right), 0);
        // 计算路径最大值
        int pathValue = node.val + left + right;
        // 更新最大值
        maxValue = Math.max(maxValue, pathValue);
        return node.val + Math.max(left, right);
    }

    /**
     * 139. 单词拆分
     * 
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //  dp[i]: 表示前i个字符[0,i-1]能否被字典里的值组成
        // 假设可以最后状态 0 j i  如果[0-j]能被组成并且[j,i]也被包含说明[0,i]=true
        boolean[] dp = new boolean[s.length() + 1];
        // 初始化: 空字符串能被字典里组成，一个单词都不出
        Set<String> words = new HashSet<>();
        words.addAll(wordDict);
        dp[0] = true;
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 存在的话直接返回从i开始进行下一轮判断
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 394. 字符串解码
     *
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     *
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     *
     * @param s
     * @return
     */
    public String decodeString1(String s) {
        // 思路:利用栈如果匹配到右括号]就解码到左括号所在位置,然后将解码出来的字符在压入栈(题目中有嵌套类型如3[a2[c]])
        //Character.isLetter() [a,z] isDigit [0,9]

        if (s == null || s.length() == 0) {
            return "";
        }
        // 初始化参数
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i)); // 不为]所有字符字节入栈
            } else {  // 匹配到]
                // 获取[之前的字符
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                stack.pop(); // 弹出左括号[
                // 获取数字
                StringBuilder num = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    num.append(stack.pop());
                }
                // 将字符和数字翻转之后根据num次数在压入栈中
                int size = Integer.parseInt(num.reverse().toString());
                for (int j = 0; j < size ; j++) {
                    for (int k = sb.length() - 1; k >= 0; k--) {
                        stack.push(sb.charAt(k));
                    }
                }
            }
        }

        // 获取栈的所有数据，翻转即为结果
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * 32. 最长有效括号
     *
     * dp
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        // dp[i]: 表示以s[i] 为结尾的字符的最长有效括号
        int[] dp = new int[s.length()];
        // 初始化都为0    ()()(())
        // 最终状态: 1.如果s[i]='(', dp[i] = 0 (一定要包含s[i]最后一位以(结尾则没有有效括号)
        //         2. 如果s[i] = ')', 如果s[i-1]='(', 则长度=dp[i-2] + 2     ()()[i]
        //         3. 如果s[i] = ')',说明前面可能有已经好的有效括号，3.1: j的位置是左括号 v = i和前面匹配到的左括号 + 括号中有效的 + 括号外(包含j-1的) =2+dp[i-1] + dp[j-1]
        //                                                     3.2: 如果j的位置是右括号则这次没有匹配到直接跳过
        //                                ()()([j] () ) [i] ;  j = i - dp[i-1]-1

        int len = s.length();
        int maxLen = 0;
        for(int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {  //  s[i] =')'
                if (s.charAt(i-1) == '(' ) { // s[i-1] = '(',前一位正好为( 组成一对 再加上包含i-2的最长有效数
                    dp[i] = 2 + (i-2>=0 ? dp[i-2] : 0);  // () i=1时边界情况
                } else {  // s[i-1] = ')',v = i和前面匹配到的左括号 + 括号中有效的 + 括号外(包含j-1的) =2+dp[i-1] + dp[j-1]
                    if (i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(' ) { // 如果s[j] = '（' 和s[i]正好可以组成一对  j=i-dp[i-1]-1
                        dp[i] = 2 + dp[i-1] + (i - dp[i-1]-2>=0 ? dp[i - dp[i-1]-2] : 0);
                    }
                }

            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return maxLen;
    }

    /**
     * 207. 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 思路: 拓扑排序,验证是否存在回路
        return true;
    }


    public  int removeDuplicates(int[] nums) {
//        if (nums == null) {
//            return 0;
//        }
//        if (nums.length < 2) {
//            return nums.length;
//        }
//        int index = 2;
//        for (int i = 2;  i < nums.length; i++) {
//            System.out.println(nums[i]);
//            if (nums[i-2] == nums[i]) { // 3位数都相同继续
//                index = i;
//                continue;
//            }
//            nums[index] = nums[i];
//            //index++;
//
//            Character.toLowerCase(nums[i]);
//        }
//        return index;
        //StringBuilder sb = new StringBuilder("dsa");
        return 0;
    }

    public  boolean isPalindrome(String s) {
        // 解法: 双指针两边遍历 遇到字母转成小写后进行比较是否相同
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) {
                left++;
            }
            while(left < right && !Character.isLetter(s.charAt(left))) {
                right--;
            }
            if ( Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        // 遍历完说明时回文
        return true;
    }

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        UUID.randomUUID().toString();



//        Collections.synchronizedList(new ArrayList<>());
//
//        Executor pool = Executors.newFixedThreadPool(10); // corePoolSize = n; maxPoolsize=n; queue.size = Integer.MAX_VALUE
//        Executor pool = Executors.newSingleThreadExecutor(); // corePoolSize = 1; maxPoolsize=1; queue.size = Integer.MAX_VALUE
//        Executor pool = Executors.newCachedThreadPool(); // corePoolSize = 0; maxPoolsize=Integer.MAX_VALUE; queue.size = 1
//
//        HashMap<String, Integer> map = new HashMap()
//
//        Map<String, String> table = new Hashtable<>();
//        table.put()
//
//        for(int i = 0; i <= 20; i++) {
//            new Thread(() -> {
//
//            }, String.valueOf(i)).start();
//        }

        int[][] grid = new int[3][3];
        grid[0] = new int[]{1,3,1};
        grid[1] = new int[]{1,5,1};
        grid[2] = new int[]{4,2,1};
        //0L, TimeUnit.MILLISECONDS,
              //  new LinkedBlockingQueue<Runnable>()
        Executor pool = new ThreadPoolExecutor(
                10,  // 线程池的常驻核心线程数
                20,               // 线程池可以容纳的最大线程数
                0L,             // 多余的空闲线程存活时间
                TimeUnit.MILLISECONDS, // 上面存活时间单位ms
                new LinkedBlockingQueue<>(20), //阻塞队列存放提交未被执行的任务
                Executors.defaultThreadFactory(), // 创建线程的线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 默认拒绝策略: 任务无法提交抛出异常
                );

        // System.out.println(new Leecode100().minPathSum(grid));
        //System.out.println(new Leecode100().subarraySum(new int[]{1, 1, 1}, 2));
       // new Leecode100().moveZeroes(new int[]{0,1,0,3,12});
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.add(1);
//        pq.add(3);
//        pq.add(35);
//        System.out.println(pq.peek());
        //new Leecode100().mergeKLists(new ListNode[]{new ListNode(1)});
        //System.out.println(new Leecode100().mergeKLists(new ListNode[]{new ListNode(1)}).val);

//        char a = '3';
//        System.out.println(a);
//        System.out.println(Integer.valueOf(String.valueOf(a)));
//        StringBuilder sb = new StringBuilder();
//        sb.append('2');
//        sb.append('3');
//        sb.append('4');
//        System.out.println(sb.toString());
        //new Leecode100().search(new int[]{4,5,6,7,0,1,2}, 0);
//        List<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(0, 11);
//        list.add(1, 12);
//        System.out.println(Arrays.toString(list.toArray()));

        //System.out.println(new Leecode100().longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(Character.isLetter(']'));
        System.out.println(Integer.parseInt(String.valueOf('1')));
        StringBuilder sb = new StringBuilder();
        sb.append('1');
        sb.append('2');
        System.out.println(sb.toString());
        System.out.println("sda".substring(0, 0));
        System.out.println("sda".substring(0, 1));
        int[] a = new int[]{};
        new Leecode100().isPalindrome("race a car");

    }
}



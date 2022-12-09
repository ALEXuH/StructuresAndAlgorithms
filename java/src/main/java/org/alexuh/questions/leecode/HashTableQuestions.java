package org.alexuh.questions.leecode;

import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/25
 *
 * 哈希表相关题目
 *
 */
public class HashTableQuestions {
    public static void main(String[] args) {
        //isHappy(19);
//        int[] a = new int[]{-1,0,1,2,-1,-4};
//        threeSum(a);
        System.out.println(1000000000 + 1000000000 + 1000000000 + 1000000000);
        int[] b = new int[]{1000000000,1000000000,1000000000,1000000000};
        System.out.println(fourSum(b, -294967296));
    }


    /**
     * 242. 有效的字母异位词
     *给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 异位词：排序后字符串相同或者理解为每个字字母出现的频次相同
     *
     * 解法1: 排序字符串进行比较
     * 解法2: 初始化26个字母的hash表记录s里的字母频次, 在遍历t中的相应减1，如果最后都为0则是异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] frequence = new int[26];
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        for (int i = 0; i < sChar.length; i++) {
            frequence[sChar[i] - 'a']++;       // ASCALL 码 a=97
        }
        for (int i = 0; i < tChar.length; i++) {
            frequence[tChar[i] - 'a']--;       // ASCALL 码 a=97
        }
        for (int i = 0; i < frequence.length; i++) {
            if (frequence[i] != 0) {
                return false;
            }
        }
        return true;

    }

    /**
     *
     * 349. 两个数组的交集
     *
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     *输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     *
     * 解法：使用hashset去重的hash表
     * 1.将数组a遍历写入hashset
     * 2.遍历nums如果包含在hasset里输出结果到结果的hashset
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> numsSet = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        for (int i : nums1) {
            numsSet.add(i);
        }
        for (int i : nums2) {
            if (numsSet.contains(i)) {
                resultSet.add(i);
            }
        }
        return resultSet.stream().mapToInt(x -> x).toArray();
    }


    /**
     * 202. 快乐数
     *
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」 定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     *
     *notice: 获取数的每个位置的数：n % 10 就会获取最后一位，每次在把n/10 就可以一直获取最后一位继而得出各个位置的数
     *
     * 解法：题目中说会陷入循环说明如果出现过2次一样的数说明就不是快乐数了，可以创建一个hashset保存计算的数用作判断
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> resultSet = new HashSet<>();
        int temp = calculate(n);
        while(!resultSet.contains(temp)) {
            if (temp == 1) {
                return true;
            }
            resultSet.add(temp);
            temp = calculate(temp);
        }
        return false;
    }

    public static int calculate(int n) {
        int sum = 0;
        while(n > 0) {
            int temp = n % 10 ; // 123 % 10  12 % 10  1 % 10
            sum += temp * temp;
            n = n / 10;
        }

        return sum;
    }

    /**
     * 1. 两数之和
     *给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 解法：使用hash表记录出现过的数据,在判断是否存在target-cur 的值出现
     *
     * a + b = target
     * b = target - a;
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     *
     * 454. 四数相加 II
     *
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足
     *
     * 解法：和两数之和类似，可以先统计3数之和在确定最后一个数
     * 优化：分成2组 （ab） （bc） 和上面思想相同效率更高一点map里数量少
     *
     * a+b+c+d = target  (a+b+c) + d
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        // 先计算3数之和的map
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                for (int k = 0; k < nums3.length; k++) {
                    int sum = nums1[i] + nums2[j] + nums3[k];
                    if (map.get(sum) != null) {
                        map.put(sum, map.get(sum)+ 1);
                    } else {
                        map.put(sum, 1);
                    }
                }
            }
        }
        // 根据最后获取需要的数
        int res = 0;
        for (int i = 0; i < nums4.length; i++) {
            if (map.get(0-nums4[i]) != null) {
                res += map.get(0-nums4[i]);
            }
        }
        return res;
    }

    /**
     *15. 三数之和
     *
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     *
     *解法1: hash表2层循环，一个hash表存一个数组的数据，两层for循环算出需要的值，然后从hash表中获取，去重比较麻烦（不建议使用）
     *
     * 解法2： 双指针,固定一个i也就是a值,数组先进行了排序，所以是左小右大，num[i] + num[left] + num[right] > 0,只需要将右指针左移动
     * 当num[i] + num[left] + num[right] = 0 找到了值并进行去重处理 num[i] + num[left] + num[right] < 0 .将左指针右移动
     *
     * notice： i left right 去重逻辑
     *
     *  i   left                   right
     *  -4   -2    -2  0  1  2  3   4
     *
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        // 先对数组进行升序排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 第一个就大于0后面已经没有符合条件的了
            if (nums[i] > 0) {
                return result;
            }
            // 去重i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i+1;  // 左指针
            int right = len - 1;  // 右指针
            while(left < right) {
                int v = nums[i] + nums[left] + nums[right];
                if (v > 0) {
                    right--;
                } else if (v < 0) {
                    left++;
                } else {  // 等于0符合条件的
                    result.add(Arrays.asList(nums[i], nums[left], nums[right])); // 将三元组添加到结果集
                    // 去重b
                    while(left < right && nums[left] == nums[left+1]) {
                        left++;       // 下面会在加一次
                    }
                    // 去重c
                    while(left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    // 同时移动指针
                    right--;
                    left++;

                }
            }
        }
        return result;
    }

    /**
     * 18. 四数之和
     *
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
     * （若两个四元组元素一一对应，则认为两个四元组重复）：
     *
     * 解法：和三数之和类似，四数之和确定2个值num[i],num[k], 后面操作和三数一样先排序，双指针移动，做好四个数的去重
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 剪枝操作 如果第一个数大于目标值则直接返回,[-5,-4,-3,0,1,2] 如果为负数不能直接返回 -5 -4 = -9
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }
            // 去重num[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i+1; j < len; j++) {
                // 去重num[j]
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                // 左右指针
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int v = nums[i] + nums[j] + nums[left] + nums[right];
                    if (v > target) {  // 大于目标值右指针左移
                        right--;
                    } else if (v < target) {
                        left++;    // 小于目标值左指针右移
                    } else {   // 等于目标值放入结果集并移动左右指针
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 去重num[left] nums[right]
                        while(left < right && nums[left] == nums[left+1]) {
                            left++;
                        }
                        while(left < right && nums[right] == nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
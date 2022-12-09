package org.alexuh.questions.leecode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/29
 * 贪心算法
 *
 */
public class Greedquestions {

    public static void main(String[] args) {
        //System.out.println(new Greedquestions().findContentChildren(new int[]{1,2,3}, new int[]{3}));
        System.out.println(new Greedquestions().canJump(new int[]{3,2,1,0,4}));
    }

    /**
     * 455. 分发饼干
     *对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     *
     * 解法: 贪心算法要保证饼干发完,可以小饼干满足小胃口的人
     * 1.先排序s g然后判断
     *
     * @param g 胃口
     * @param s 饼干
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int gIndex = 0;
        // 遍历饼干
        for(int v : s) {
            // 如果当前饼干满足不了当前学生，胃口是从小到大排序的后面更大更满足不了此时应该使用下一块饼干
            if(gIndex <= g.length - 1 && g[gIndex] <= v) {
                count++;
                gIndex++;
            }
         }
        return count;
    }

    /**
     * 55. 跳跃游戏
     *给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标
     *
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * 解法: 计算每个点的覆盖范围看是否能覆盖到最后一个点（i+num[i] >= nums.size）
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int cover = 0;
        int len = nums.length;
        // 在覆盖范围内在取最大跳跃值
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= len - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     *1005. K 次取反后最大化的数组和
     *给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     *
     * 解法：贪心算法，要达到k次后的最大值可以保证每次修改都是最大值,
     *      假设有负数只需要修改绝对值最大的，假设没有负数可以修改最小的正整数
     *
     * 想到使用优先队列，让它自己从小到大排列，每次取队列头计算然后放进去重复k次
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 优先队列（让数据从小到大排序）
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        for (int v  : nums) {
            pq.add(v);
            sum += v;
        }
        // k次计算
        for (int i = 0; i < k; i++) {
            if (!pq.isEmpty()) {
                int temp = pq.remove();
                sum = sum - temp + (-1*temp); // 注意移除元素是先减去这个元素在加上添加的*-1的元素
                pq.add(temp*-1);
            }
        }
        // 返回结果
        return sum;
    }

    /**
     * 860. 柠檬水找零
     *在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     * 输入：bills = [5,5,5,10,20]
     * 输出：true
     *
     * 解法：
     * 分析情况: 假设5 10 20 美元张数为five ten twenty
     * 情况1：付5美元 five+1
     * 情况2：付10美元 ten+1 five-1
     * 情况3：付20美元 twenty+1  ten-1 && five -1 或者 five - 3 贪心策略优先使用10美元和5美元组合
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        int twenty = 0;

        for(int v : bills) {
            if (v == 5) {
                five++;
            }
            if (v == 10) {
                ten++;
                if (five == 0) {
                    return false;  // 没有5美元不能找零10美元了
                }
                five--;
            }
            if (v == 20) {
                twenty++;
                if (ten >= 1 && five >= 1) { // 对于优先找10美元的
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {                 // 无法找零20美元
                    return false;
                }
            }
        }
        return true;

    }
}


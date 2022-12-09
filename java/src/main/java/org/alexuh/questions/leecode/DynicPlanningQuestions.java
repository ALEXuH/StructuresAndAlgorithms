package org.alexuh.questions.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/29
 *
 * 动态规划
 *
 * 1.背包问题: 1.01背包 2.完全背包
 * 2.打家结社问题: 1.普通数组 2.环型数组 3.二叉树
 * 3.股票问题: 1.买卖一次 2.买卖多次 3.买卖多次有手续费 4.买卖多次有冻结期 5.买卖2次
 * 4.序列问题: 1.最长递增子序列 2.最长连续递增增子序列 3.最长公共子序列 4.最长重复子数组 5.最大子数组和 6.最大数组和 7.不相交的
 * 5.其他: 1.整数拆分 2.剪绳子 3.斐波那契数量 4.爬楼梯 5.mn格子路径问题 6.青蛙跳石头
 *
 */
public class DynicPlanningQuestions {
    public static void main(String[] args) {
        //new DynicPlanningQuestions().climbStairs(1);
        //new DynicPlanningQuestions().uniquePaths(3, 7);
//        int[] weight = {1, 3, 4};
//        int[] value = {15, 20, 30};
//        int bagWight = 4;
//        new DynicPlanningQuestions().testWeightBagProblem(weight, value, bagWight);
        //new DynicPlanningQuestions().maxUncrossedLines(new int[]{1,4,2}, new int[]{1,2,4});
//
        //int[] prices = {7,1,5,3,6,4};
        //new DynicPlanningQuestions().maxProfit(prices);
    }

    /**
     * 509. 斐波那契数
     *斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     *
     * 0 1 1      2
     *     n=2   n=3
     *
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        // dp数组dp[n] 表示第n个数的值
        int[] dp = new int[n+1];
        // 考虑n小于2的时候
        if(n < 2) {
            return n;
        }
        // dp初始化
        dp[0] = 0;
        dp[1] = 1;

        // 顺序从小到大计算
        for(int i = 2; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2]; // 递推公式
        }
        // 返回结果
        return dp[n];
    }

    /**
     * 70. 爬楼梯
     *假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 思路:
     * 最后一步状态 可能是 f(n-1)也可能是f(n-2) ->
     * 转化成子问题到f(n-1)和f(n-2)有多少种方法，继而推出 f(n) = f(n-1) + f(n-2)
     *
     * dp[]确定为有i个阶梯时共有dp[i]种方法可以到达
     * for循环的是n阶梯个数
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 当阶梯为i时有dp[i]中方法到达
        int[] dp = new int[n+1];
        // 初始化dp数组
        dp[0] = 0;
        dp[1] = 1;
        if(n < 2) {
            return dp[n];
        }
        dp[2] = 2;
        // 顺序从小到达推出n
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2]; // 递推公式
        }
        System.out.println(dp);
        return dp[n];
    }

    /**
     * 62. 不同路径
     *
     *一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 思路分析：
     * 确定最后一步状态: 在位置[m-1][n] 或者[m][n-1]
     * 分解成字问题：到[m-1][n]和到[m][n-1]有多少条路径 且f[m][n] = f[m-1][n] + f[m][n-1]
     * 初始条件: 只能向下或者右可以确定第一行和第一列的值为n
     * 顺序从小到大（都是-1需要先计算小的）
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 从坐标(0,0)到坐标(m,n) dp[m][n]表示从左上角过来到m,n点有多少中不同路径
        int[][] dp = new int[m][n];
        // 初始化第一行第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 按照从小到大的顺序遍历,i,j都从1开始
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        for(int[] v : dp) {
            System.out.println(Arrays.toString(v));
        }
        return dp[m-1][n-1];
    }

    /**
     * 63. 不同路径 II
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 思路和上面没有障碍的路径相同
     * notice：
     * 1.遇到障碍就设为0
     * 2.第一行和第一列的初始化,只能下或者右所以遇到1了自己和后面的都要初始化成0
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 获取行列初始化dp数组 dp[i][j] 表示到（i,j）有多少中不同路径
        if (obstacleGrid == null || obstacleGrid.length == 0 ){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化dp数组,对于第一行或者第一列遇到障碍自己包括后面的都设为0
        for(int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1){
                break;
            }
            dp[0][i] = 1;
        }
        // 顺序从小到大并从1开始,遇到障碍设为0，默认是0也可以直接返回
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 遇到障碍设为0，默认是0可以直接返回
                if (obstacleGrid[i][j] == 1){
                    continue;
                }
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        for(int[] v : dp) {
            System.out.println(Arrays.toString(v));
        }
        return dp[m-1][n-1];
    }

    public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight){
        // 定义dp数组: dp[i] 表示背包能装ikg时候的最大价值
        int[] dp = new int[bagWeight+1];
        //初始化 背包重量为0时能价值也为0
        dp[0] = 0;
        // 顺序遍历: 先物品后背包 背包要倒叙遍历保证物品只取一次
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) { // 倒叙遍历
                dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    /**
     *198. 打家劫舍
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     *
     * 思路分析
     * 一；确定最后一步状态：
     * 设f[i] 表示前 i间房能偷盗的最大金额
     * 1.偷第i间房间,只能间隔偷所以  f(i-2) + nums[i]
     * 2.不偷第i房间,f[i-1] 表示偷前i-1间房间最大值
     *
     * 获得递推公式 f[i] = max(f[i-2] + nums[i], f[i-1])
     *转化成子问题求f[i-2]和f[i-1] 也就是前i-2和i-1能偷的最大值
     *
     *二: 转移方程
     * f[i] = max(f[i-2] + nums[i], f[i-1])
     *
     * 三: 初始值
     * f[0] = num[0]  偷一间房
     * f[1] = max(num[0], num[1])  偷2间房相邻所以只能取最大值
     *
     *四: 顺序
     * 由递推公式得知
     *f[2] = max(f[0] + num[i] , f[1])
     * f[0] -> f[1] -> f[2] -> f[3] ...
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 定义dp数组:dp[i]:偷前i间房间的最大金额
        int[] dp = new int[nums.length];
        // 初始化dp,相邻的不能偷所以，一间房直接取第一个，2间房取最大的
        dp[0] = nums[0];
        if (nums.length <=1 ) {
            return nums[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);
        // 遍历数组从2开始
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }


    /**
     * 213. 打家劫舍 II
     *你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 解法：转化成第一种非环形类型
     *
     * 最后状态：
     * 状态1: 偷最后一间房间,也就是不能偷第一间房，也就是偷的范围是. [1,i]
     状态2: 不偷最后一间房间,也就是能偷第一间房，也就是偷的范围是. [0,i-1]
     *
     * 分别求解2个范围取最大值
     * @param nums
     * @return
     */
    public int robTwo(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 比较范围[1,i]  [0,i-1]
        System.out.println(robOne(nums, 1, nums.length - 1));
        System.out.println(robOne(nums, 0, nums.length-1-1));
        return Math.max(robOne(nums, 1, nums.length - 1), robOne(nums, 0, nums.length-2));

    }

    public int robOne(int[] nums, int start, int end) {
        int[] dp = new int[end+1];
        // 初始化dp,相邻的不能偷所以，一间房直接取第一个，2间房取最大的
        dp[start] = nums[start];

        if (end - start + 1 == 1 ) {
            return nums[start];
        }

        dp[start+1] = Math.max(nums[start], nums[start+1]);
        // 遍历数组从2开始
        for (int i = start+2; i < end+1; i++) { // 注意要初始化的后一位开始
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[end];
    }

    /**
     * 337. 打家劫舍 III
     *
     * 最后状态：
     * 状态1: 偷当前节点，不能偷子节点
     * 状态2: 不偷当前节点,可以偷子节点
     *
     * 使用后序遍历
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] v = robBack(root); // 获得偷根节点的最大金额和不偷根节点的最大金额
        return Math.max(v[0], v[1]);
    }

    public int[] robBack(TreeNode root) {
        if(root == null) {
            return new int[]{0,0}; // v[0] 表示不偷的最大金额，v[1]代表偷当前节点的最大金额
        }
        int[] leftValue = robBack(root.left); // 左
        int[] rightValue = robBack(root.right); // 右
        // 中间逻辑
        // 不偷当前节点，1.都不偷 2.2个都偷  3.偷左不偷右 4.偷右不偷左
        int val = Math.max(Math.max(leftValue[0] + rightValue[0], leftValue[1] + rightValue[1]), Math.max(leftValue[0] + rightValue[1], leftValue[1]  + rightValue[0]));
        // 偷当前节点 当前节点值+2个不偷子节点的最大金额
        int val1 = root.val + leftValue[0] + rightValue[0];
        return new int[]{val, val1};
    }

    /**
     *剑指 Offer II 103. 最少的硬币数目
     *给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     */

    public int coinChange(int[] coins, int amount) {
        // 定义dp数组: dp[i]表示拼出总金额为i所需要的最少枚硬币，无法拼出则为-1
        int[] dp = new int[amount+1];
        // 初始化，求最少可以都初始化成最大的
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // 金额为0时只能0枚硬币
        // 求解顺序 f[0] f[1] ..
        for (int i = 1; i < amount+1; i++) { // 遍历金额从小到大
            for (int j = 0; j < coins.length; j++) { // 遍历硬币不同面额
                if(i >= coins[j] && dp[i-coins[j]] != Integer.MAX_VALUE) { // 处理边界
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    /**
     * 青蛙跳石头
     *
     * @param A
     * @return
     */
    public boolean canJump(int[] A) {
        // 定义dp数组: dp[i] 表示是否能跳到石头i
        boolean dp[] = new boolean[A.length];
        // 初始化
        dp[0] = true; // 本来在第一块石头肯定能跳到
        for (int i = 1; i < A.length; i++) { // 循环石头
            for (int j = 2; j < A.length; j++) { // 给定要跳到的目标
                if (dp[i] && A[i] >= j-i) { // 如果能跳到i并且A[i]大于两块石头的距离
                    dp[j] = true;
                }
            }
        }
        return dp[A.length - 1];
    }

    /**
     * 剪绳子问题
     * 题目：给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
     * 每段绳子的长度记为k[1],…,k[m]。请问k[1]x…xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，
     * 我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。（2 <= n <= 60）
     *
     */
    public int cutRope(int target) {
        // 定义dp数组:dp[i] 表示绳子长i时的最大乘机
        int[] dp = new int[target + 1];
        // 初始化 当为2时只能剪一次1*1 当为3时最大1*2 当为4时 1*3=3
        if (target == 0 || target == 1) {
            return 0;
        }
        if(target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target == 4) {
            return 4;
        }
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        // 遍历顺序 dp[2] dp[3] dp[4] dp[5] ...
        for(int i = 5; i < target+1; i++) { // 遍历绳子长度
            for(int j = 1; j < i; j++) { //遍历最后一段绳子长度 最后一段j<i,小于当前总长度
                dp[i] = Math.max(dp[i], dp[i-j] * j);
            }
        }
        return dp[target];

    }


    /**
     * 343. 整数拆分
     *给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     *
     * 思路：假设f[n]是整数n的最大乘机
     *
     * 确定最终状态：最后一定有一个值j f[i] = (i-j) * j  (j < i)
     * 分解成子问题：求n-a的最大乘机
     * 递推公式：f[i] = max(f[i-j] * j, (i-j)*j) i是整数1-n j是小于i的整数
     * 初始化值：因为k>=2 必须要拆分 0,1,2只有2能拆 所以 f[0] =0; f[1] = 0; f[2] = 1*1 = 1; f[3] = 1*2 = 2;
     * 遍历顺序：f[3] f[4] f[5] ...
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // 初始化dp数组
        int[] dp = new int[n+1];
        if (n <=3) {
            return n-1;
        }
        // 初始化
        dp[2] = 1;
        dp[3] = 2;
        //遍历顺序f[3] f[4] f[5] ..
        for (int i = 4; i < n+1; i++) { // 遍历整数i
            for(int j = 1; j < i; j++) { // 遍历拆分后的j
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[i-j] * j)); // 递推公式
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];

    }


    /**
     *
     * 121. 买卖股票的最佳时机
     *
     * 贪心算法:
     *
     * 只买卖一次可以维护一个最小值，把每天的减去最小值就可以获得当天卖出的最大金额
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0)  {
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    /**
     * 一次买卖
     *
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     *
     * 动态规划 ：
     * f[i][j], j:0,持有股票，j:1 不持有股票
     * f[i][0]表示前i天持有股票所得最多现金，
     * f[i][1]表示前i天不持有股票的所得最多现金
     *
     * 1.最后状态
     *   1.持有股票 max(状态1.1, 状态1.2)
     *     1.1: 之前就持有股票, 就保持现状，昨天持有股票所得最多现金为: f[i-1][0]
     *     1.2: 买入当天的股票,因为只能买入一次,所持有现金肯定为: -prices[i]
     *   2.不持股票 max(2.1, 2.2)
     *     2.1: 之前持有股票,就需要卖出当天的股票,所得现金为: f[i-1][0] + prices[0]
     *     2.2: 之前就不持股票，保持现状  f[i-1][1]
     * 2.递推公式
     * f[i][j] = {max(f[i-1][0],-prices[i]), max(f[i-1][1],f[i-1][0] + prices[0])}
     *
     * 3.初始化
     * 第一天持有：f[0][0] = -prices[i]
     * 第一天不持有:f[0][1] = 0
     *
     * 4.遍历顺序 f[0][0] f[0][1]  f[1][0] f[1][1] ...从小到大
     *
     * @param prices
     * @return
     */
    public int maxProfitDp(int[] prices) {
        // 定义dp数组
        // f[i][j], j:0,持有股票，j:1 不持有股票
        // f[i][0]表示前i天持有股票所得最多现金，
        // f[i][1]表示前i天不持有股票的所得最多现金
        int[][] dp = new int[prices.length][2];
        if (prices.length == 0) {
            return 0;
        }
        // 初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        // 遍历顺序 f[0][0] f[0][1]  f[1][0] f[1][1] ...并从1开始
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(-prices[i], dp[i-1][0]);   // 持有股票所得最多现金
            dp[i][1] = Math.max(dp[i-1][1], prices[i] + dp[i-1][0]);  // 不持有股票所得最多现金
            result = Math.max(dp[i][0], dp[i][1]);
        }
        return result;
    }

    /**
     *
     * 多次买卖
     *
     * 122. 买卖股票的最佳时机 II
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *  动态规划 ：
     *  f[i][j], j:0,持有股票，j:1 不持有股票
     *  f[i][0]表示前i天持有股票所得最多现金，
     *  f[i][1]表示前i天不持有股票的所得最多现金
     *
     *  持有股票: f[i][0] = max(-prices[i]+f[i-1][1], f[i-1][0])
     *  不持有股票: f[i][1] = max(f[i-1][1], f[i-1][0] + prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfitTwo(int[] prices) {
        // 定义dp数组
        // f[i][j], j:0,持有股票，j:1 不持有股票
        // f[i][0]表示前i天持有股票所得最多现金，
        // f[i][1]表示前i天不持有股票的所得最多现金
        int[][] dp = new int[prices.length][2];
        if (prices.length == 0) {
            return 0;
        }
        // 初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        // 遍历顺序 f[0][0] f[0][1]  f[1][0] f[1][1] ...并从1开始
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(-prices[i]+dp[i-1][1], dp[i-1][0]);   // 持有股票所得最多现金
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);  // 不持有股票所得最多现金
            result = Math.max(dp[i][0], dp[i][1]);
        }
        return result;
    }

    /**
     * 多次有手续费买卖 （和多次买卖相同，区别在需要在卖出时候减去手续费）
     *
     * 714. 买卖股票的最佳时机含手续费
     *给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        // dp[i][j]: dp[i][0] 表示前i天持有股票时获取最大利润。dp[i][1]: 表示前i天不持有股票时获取的最大利润
        int[][] dp = new int[prices.length][2];
        // 初始化
        dp[0][0] = -prices[0]; // 第一天持有股票
        dp[0][1] = 0;  // 第一天不持有股票
        // 遍历顺序从小到大，从1开始
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);  // 持有股票状态 1:之前就持有保持之前状态 2: 之前不持有，需要在这次买入，并加上之前不持有时最大利润
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i] - fee); // 不持有股票状态 1: 本身就不持有保持之前状态 2.之前持有股票，需要在这次卖出也就是之前持有的最大利润加上当前的价格然后在减去手续费
            result = Math.max(dp[i][0], dp[i][1]);
        }
        System.out.println(Arrays.toString(dp[prices.length-1]));
        return result;

    }

    /**
     *
     * 2次买卖
     *
     * 123. 买卖股票的最佳时机 III
     *
     *
     *给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 限定2次买入
     *
     * dp[i][j]: 代表前i天状态j最大持有金额
     * 最后状态j
     * 0: 无操作
     * 1: 第一次买入
     * 2: 第一次卖出
     * 3: 第二次买入
     * 4: 第二次卖出
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        // dp[i][j]: 代表前i天状态 j最大持有金额
        int[][] dp = new int[prices.length][5];
        // 初始化，对第一天的第一次买入和第二次买入进行赋值
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        // 遍历顺序 f[0][0-4] f[1][0-4] ... 从1开始
        for (int i = 1; i < prices.length; i++) {
            dp[i][0]= dp[i-1][0];  // 无操作,保持之前状态即可
            dp[i][1]= Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);  // 第一次买入时候状态，1.之前已经买入了第一次 2.之前没有买过，第一次购买
            dp[i][2]= Math.max(dp[i-1][2], dp[i-1][1] + prices[i]); // 第一次卖出状态: 1.之前已经第一次卖出过了,保持状态 2.之前买入过第一次，需要在这次卖出
            dp[i][3]= Math.max(dp[i-1][3], dp[i-1][2] - prices[i]); // 第二次买入状态: 1.之前已经是二次买入状态了，保持之前的状态即可 2.之前已经卖出一次，需要在当前i买入
            dp[i][4]= Math.max(dp[i-1][4], dp[i-1][3] + prices[i]); // 第二次卖出状态: 1.之前已经是第二次卖出状态了，保持之前的状态即可 2. 之前已经完成了第二次的买入，需要在当前卖出
        }
        System.out.println(Arrays.toString(dp[prices.length-1]));
        return dp[prices.length-1][4];
    }

    /**
     * 多次买卖含有冷冻期
     *
     * 309. 最佳买卖股票时机含冷冻期
     *
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *  dp[i][j]: 代表前i天状态j最大持有金额
     *  最后状态j
     *  0: 持有股票
     *  0: 持有股票
     *
     * 状态一:持有股票：1.之前就持有股票   f[i-1][0]
     *         2.之前不持有股票需要买入今天的股票
     *           2.1. 之前一次没买过，第一次买入 -price[i]
     *           2.2  经过冷冻期1天或者n天，最大金额肯定是最近刚卖出那一次也就是刚经过冷冻期的前一天不持有股票的时候,
     *                冷冻期之前的最大金额是冷冻期前一天已经卖出了不持有股票状态也就是f[i-2][1]
     *                所以刚经过冷冻期在买当前股票最大利润为 f[i-2][1] - prices[i]
     *
     *状态二: 不持有股票：1.之前就不持有股票 f[i-1][1]
     *                 2.之前持有股票需要今天卖出 f[i-1][0] + prices[i]
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp[i][j]: 代表前i天状态j最大持有金额
        // 0: 持有股票 1: 不持有股票
        int[][] dp = new int[prices.length][2];
        // 初始化
        dp[0][0] = -prices[0]; // 第一天持有股票最多的现金
        dp[0][1] = 0; // 第一天不持有股票最多的现金
        // 遍历顺序由小到大 从1开始
        for (int i = 1; i < prices.length; i++) {
            // 持有股票 1.之前就持有 2.之前不持有需要今天买入,今天买入需要超过冷冻期或者没有买过。
            // 2.1 第一次购买 -prices[i]  2.2 超过冷冻期最大利润就是前一天就是冷冻期才有可能买卖多次获取最大利润 f[i-2][1] - prices[i]
            dp[i][0] = Math.max(dp[i-1][0], Math.max(-prices[i], (i-2 < 0? 0 : dp[i-2][1]) - prices[i]));
            // 不持有股票 1.本身就不持有 2.持有需要当前卖出
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(Arrays.toString(dp[prices.length-1]));
        return Math.max(dp[prices.length-1][0], dp[prices.length-1][1]);
    }




    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 和硬币类似：硬币是最后状态是不同币值，序列最后状态是j也就是数组中的某一个位置
     *
     * 思路：
     * 1
     * 最后状态:
     *  状态1: 取i, 总有前一个元素j（j < i) 可以删除不连续所以j是0-i中间的任意一个数, if(nums[i] > nums[j]); f[i] = f[j] + 1;
     *  状态2：不取最后一个元素也就是不取i
     * 子问题: 求f[j] 也就是0-j位置的最长子序列长度
     *
     * 2.
     * 递推公式： f[i] = max(f[j] + 1, f[i])
     *
     * 3初始化
     * 子序列最短是自己也就是1 所以都初始化为0
     *
     * 4.遍历顺序 f[0] f[1] f[2] ...
     * 取其中最大的
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // dp[i]: 前i个元素元素最长递增子序列
        int[] dp = new int[nums.length];
        int result = 0; // 存放最大结果
        // 初始化
        Arrays.fill(dp, 1);
        //遍历顺序由小到大 f[0] f[1] ...
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 递推公式
                }
            }
            result = Math.max(result, dp[i]); // 获取最长子序列值
        }
        return result;
    }


    /**
     * 674. 最长连续递增序列
     *给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     *
     * 思路和最长序列相同
     * 区别：连续的话j也就是i（最后一位）的前一位 i-1
     *
     * 状态1：取i ， if(nums[i] > nums[i-1])； f[i] = f[i-1] + 1
     * 状态2：不取i
     *
     * 递推公式: f[i] = max(f[i-1] + 1, f[i])
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        // dp[i]:前i个元素最长连续子序列
        int[] dp = new int[nums.length];
        // 初始化
        Arrays.fill(dp, 1);
        // 遍历顺序 f[0] f[1] ..
        int result = 1; // notice：最小默认1
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1; // 递推公式
            }
            result = Math.max(result, dp[i]); // 取最大序列值
        }
        return result;
    }

    /**
     * 718. 最长重复子数组
     *给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     *
     *
     * 思路：设f[i][j] 为A中0-iB中0-j的公共最长子数组长度
     *最后状态
     * 状态1：取i j所在位置，因为时连续的所以最后的前面一步可以直接使用i-1,j-1。 if(nums[i] == nums[j]); f[i-1][j-1] + 1
     * 状态2: 不取
     *
     * 递推公式: f[i][j] = max(f[i-1][j-1] + 1, f[i][j])
     *
     * 初始化:看第一位是否相同 f[0][0] = if(nums[i] = nums[j]) 1 else 0
     *
     * 遍历顺序: f[0][0], f[1][1] f[2][2]
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j]: 数组a前i个元素和数组B前j个元素最长连续重复子数组长度
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        if (nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        // 初始化都为0
        int result = 0;
        // 遍历顺序 f[1][1] f[2][2]
        for (int i = 1; i < nums1.length+1 ; i++) {
            for (int j = 1; j < nums2.length+1; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        for(int[] a : dp) {
            System.out.println(Arrays.toString(a));
        }

        Map<Integer, Integer> map = new HashMap<>();
        return result;

    }

    /**
     * 1143. 最长公共子序列
     *给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 思路： 设f[i][j] 为 [0-i-1] 和[0-j-1]的最大公共序列长 -1是方便代码实现
     * 最后状态
     * 状态1: 取最后一组 i，j if(text1[i] == text2[j]); f[i][j] = f[i-1][j-1] + 1
     * 状态2: 不取最后一组看 max(f[i-1][j], f[j][j-1])
     *
     * 递推公式
     * if nums[i]==nums[j]; f[i][j] = max(f[i-1][j-1] + 1, f[i][j])
     * else max(f[i-1][j], f[j][j-1])
     *
     * 初始化都为0
     *
     *
     * 遍历顺序 f[0][0] f[1][1]
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        // 定义dp[i][j]: 为 [0-i-1] 和[0-j-1]的最大公共序列长 -1是方便代码实现
        int[][] dp = new int[t1.length + 1][t2.length+1];
        // 初始化都为0
        // 遍历顺序dp[0][0] dp[1][1]
        int result = 0;
        for (int i = 1; i < t1.length+1; i++) {
            for (int j = 1; j < t2.length+1; j++) {
                if (t1[i-1] == t2[j-1]) {
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;

    }


    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     *
     * 思路：
     * 确定状态：设f[i] 为前i个元素最大值
     * 状态1：加入i元素 ， 因为是连续的前一个最大是f[i-1] f[i] = f[i-1] + nums[i]
     * 状态3: 不加入i元素，自己成为头序列 nums[i]
     *
     * 递推公式
     * f[i] = max(f[i-1] + nums[i], nums[i])
     *
     * 初始化:
     * f[0] = nums[0]
     *
     * 遍历顺序 f[1] f[2] f[3] ...
     *
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // dp[i]: 为前i个元素最大值
        int[] dp = new int[nums.length];
        // 初始化
        if (nums.length == 0) {
            return 0;
        }
        dp[0] = nums[0];
        // 遍历顺序 f[0] f[1]
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]); // 递推公式
            result = Math.max(result, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return result;
    }

    /**
     * 1035. 不相交的线
     *在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
     *
     * 问题转化成求最大公共子序列
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // dp[i][j]:表示数组nums1 前i-1个元素和nums2的前j-1个元素最大公共子序列
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        // 初始化 都为0 第一位是没有值的
        dp[0][0] = 0;
        int result = 0;
        // 遍历顺序f[0][0],f[1][1]， 从[1,len]是数组的[0-len-1]
        for (int i = 1; i < nums1.length+1; i++) {
            for (int j = 1; j < nums2.length+1; j++) {
                if (nums1[i-1] == nums2[j-1]) { // notice: 当i=1是 i-1=0都从第一位开始
                    dp[i][j] = dp[i-1][j-1] + 1; // 状态1: 最后一位相同时递推公式
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //状态2: 不相同时取[0，i-1] [0,j] 和[0-i] [0-j-1] 最大值
                }
                result = Math.max(result, dp[i][j]); // 获取最大值
                System.out.println(dp[i][j]);
            }
        }

        return result;

    }



}

//f[i][j] a为i b为j时最长重复子数组 if(a[0]= b[0]) f[0][0] = 1  else f[0][0] = 0;
//
//f[i][j] = max(f[i-1][j-1] + 1, f[i]f[j])
//
//取j j
//不取 i j
//
//
//
//// 0 0 0 0 j 0 0 0 i
////
////         第i天卖
////price[i] -price[j] + f[j]      f[j] <= min
////
////不卖
////f[i-1]
////
////最后状态
////
////最后一天卖
////         第一次卖   f[i] = prices[i] - min   result
////         第二次卖   f[i] = price[i] -prices[j] + f[j]  f[j] <= 买卖一次的最大利润
////
////最后一天不卖
////f[i-1]
////
////      最大利润
////         for (int i = 2; i < prices.length; i++) {
////        min = Math.min(min, prices[i]);
////        dp[i] = Math.max(dp[i - 1], prices[i] - min);
////        }
//// 确定的不确定的状态个数 物品1 物品2 物品3 。。。
//
//
// A     B
//
//        f[i][j]
//最后状态  nums[i] = nums[j]  f[i-1][j-1] + 1
//
//取 i j
//
//不连续的


// f[i]
// 最后状态: 连与不连
// 连：f[i][j] = f[i-1]f[j-1] + 1  不连 max(f[i-1][j], f[i][j-1])
// 怎么判断能连？单层for循环 j= [i - j.length]


//1 2 3 4 5 6
//
//持有股票状态:
//不持有股票状态
//        1.两天前卖出两股票，非冷冻期
//        2。之前买了股票，今天卖出
//冷冻期状态（卖出才能进入冷冻期间必定不持有股票）:
//
//f[i][0] = max(f[i-1][0], f[i-1][1] - prices[i])   // 持有股票状态; 1。之前就持有 2。之前没有持有，现在买入有2中情况 2.1: 前一天是冷冻状态 2.2 前一天是已经卖出的状态（可能是前几天卖的）
//f[i][1] =  max(f[i-1][1], f[i-1][0] + prices[i])           // 不持有股票，1.本身就没有股票状态 2.之前有，今天需要卖掉
//f[i][2] = f[i-1][1]   // 冷冻期指的是卖出股票 第二天，也就可以看前一天不持有股票所获的的最大现金
//
//状态
//
//刚刚卖出不持有股票 处于冷冻期
//持有股票 1.之前持有股票 2.当天买入，（不持有股票或者刚刚买入）
//不持有股票 1.之前就不持有股票 2.当天卖出
//
//        0: 持有
//        1: 不持有
//
//        1. 买入今天股票 1.第一次买入 -prices[i]
//        2. 经过了冷冻期最大利润肯定为f[n-2][1] - prices[i]
//
//状态1, 持有股票: 1.之前就持有 2.之前没有需要买入今天股票    f[i] = max(f[i-1][0], max(-prices[i],f[i-2][1] - prices[i]))
//状态2, 不持有股票: 1.之前就不持有保持当前 2.之前持有需要当天卖出 f[i] = max(f[i-1][1], f[i-1][0] + prices[i])
//
//
//f[i] =  f[i-nums[j] +   for [nums[j], j = [0,len]]
//
//f[i] 表示
//
//f[i] 表示背包总容量为i,放进物品后最大的重量 , 如果 总容量等于最大重量说明相同  (target)sum/2
//
//f[i] = max(f[i-nums[j]] + nums[j],nums[j])
//f[i] = max(f[i-nums[j]] + nums[j],nums[j])
//
//只取一次
//背包模版
//
//最后一步
//
//f[i] 表示最后剩下最小的石头重量
//
//f[i] =
//
//f[i][j] 表示
//
//假设 f[s] 为目标数为s的所有方法数量
//
//最后状态   f[s] = f[s-ak] + ak  +  f[s+a1] -a1
//
//       s =  ak+ (s-ak)
//        分解成字问题: 求target = s-ak的数量
//
//f[s] = f[s-a1] + a1
//
//硬币问题 {1,2,5}
//
//f[i] 为目标和为i的最少硬币个数
//
//最后状态 for
//        {
//            f[i-1] + 1  // 最后一枚硬币为1
//            f[i-2] + 1 // 最后一枚硬币为2
//            f[i-3] + 1 // / 最后一枚硬币为3
//        }
//
// 递推公式 f[i] = max(f[i], f[i-nums[j] + 1])
//
// if (nums[i] > nums[j] && i != j)
//f[i] = f[j] + 1
//
//最后肯定有一个nums[i]  序列长度为 f[j] + 1
//
//
// 1 4 9 16
//
//        f[i]为返回 和为 n 的完全平方数的最少数量 。
//
//
//
//        最后状态
//for {
//    f[i] = f[i-1] + 1
//        f[i] = f[i-4] + 1
//        }




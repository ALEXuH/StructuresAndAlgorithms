package org.alexuh.alg;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/9
 * 01背包 ： 一个物品1个
 * 完全背包：一件物品无限个
 *
 */
public class DynamicPlanning {

    public static void main(String[] args) {
        int[] a = {1, 2, 5};
        int m = 11;
        System.out.println(coinChange(a, m));

        // 物品重量和价格
        int[] weight = {1,4,3};
        int[] value = {1500, 3000, 2000};
        int m1 = 4;
        System.out.println(bagChange(weight, value, m1));
    }

    // 硬币问题

    /**
     *
     * @param A 硬币数组{1,2,7}
     * @param M 要拼多少钱 27
     * @return
     */
    public static int coinChange(int[] A, int M) {
        // 定义dp数组
        int[] f = new int[M + 1];
        int n = A.length;
        // 初始化值
        f[0] = 0;
        // for循环+递推公式求解
        for (int i = 1; i <= M; i++) {
            f[i] = Integer.MAX_VALUE;
            for(int j=0; j < n; j++) {
                if (i >= A[j] && f[i-A[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - A[j]] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(f));
        if (f[M] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return f[M];
        }
    }

    public static int bagChange(int[] weight, int[] value, int m) {
        // 定义dp数组 f[i][j] 表示任取 0-i之间的物品放在容量为j的背包中的最大价值
        int[][] dp = new int[weight.length][m + 1];
        // 初始化值 假设j=0也就是重量为0价值叶都为0,假设i=0也就是只放第一件物品,
        // 如果当前物品重量小于j也就是v[i],大于j也就是0, 当前题目物品1等于1w所以都初始化为第一件物品的价格
        for(int j = 0; j < m+1; j++) {
            if (j >= weight[0]) {  // weight[0] = 1
                dp[0][j] = value[0];
            }
        }
        // 打印初始化的dp数组
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
            //System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println("==========");

        // for循环+递推公式求解
        // 递推公式max{f[i-1][j],f[i-1][m-w[i]] + v[i] } 放入i和不放i件物品

        // 从第一件物品开始遍历 i=1
        for(int i = 1; i < weight.length; i++) {  // 递归物品
            for (int j = 0; j<=m; j++) {
                if(j < weight[i]) {
                    // 背包容量小于i 就等于不放这个物品即表上面的
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
            //System.out.println(Arrays.toString(dp[i]));
        }
        // 获取最大值 i行m列
        int max = dp[0][m];
        for (int i = 0; i < weight.length; i++) {
            max = Math.max(max, dp[i][m]);
        }
        System.out.println(max);
        return max;
    }

}

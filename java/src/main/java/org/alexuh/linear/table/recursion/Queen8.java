package org.alexuh.linear.table.recursion;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/22
 *     // 八皇后问题 递归回溯
 *     // 8 * 8 的棋盘 要求任意两个皇后不能处于同一行同一列或者同一个斜线
 *     // 思路：1.放置第一个皇后在第一行第一列
 *     // 2.第二行的皇后放在第二行第一列判断与前面的皇后是否ok，不ok则放在第二列第三列，知道找到一个合适的
 *     //3. 重复以上步骤，当第8个皇后也放到一个不冲突的位置，即得到一个解
 *     // 4. 当得到解时，开始回溯，栈回退到上一个皇后继续寻找其他解
 *
 *      // [1,4,2,3,1,3,4,8]  以数组表示摆放位置如1表示 第一行第二列，4表示第二行第5列
 */
public class Queen8 {

    int num = 4; // 共有多少个皇后
    int[] arr = new int[num]; // 放置皇后结果

    static int count = 0; // 解法次数

    static int judgeCount = 0;

    public static void main(String[] args) {
         Queen8 qu = new Queen8();
         qu.check(0);
         System.out.println("一共有解法: " + count);
         System.out.println("判断当前摆放皇后是否符合要求: "+ judgeCount);
    }

    // 放置皇后 n=0
    public void check(int n) {
        if (n == num) {
            count++;
            for (int i = 0; i < num; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
            return;
        }
        // 依次放入皇后
        for (int i = 0; i < num; i++) {
            arr[n] = i; // 放置在第n+1行第i列
            // 判断是否ok,不冲突继续放下一行的
            if(judge(n)) {
                check(n+1);
            }
            // 如果冲突就继续执行arr[n] = i,即放置在其他列
        }

    }

    //  n为放置第几个皇后，当放置第n个皇后，判断是否ok
    public  boolean judge(int n) {
        // 判断当前放置的皇后是否冲突
        // arr[i] arr[n] 判断是否列相同
        // Math.abs(n-i) == Math.abs(arr[n] - arr[i]) 判断是否在斜线,对角线
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

}

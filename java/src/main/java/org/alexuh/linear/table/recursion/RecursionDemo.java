package org.alexuh.linear.table.recursion;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/22
 */
public class RecursionDemo {

    public static void main(String[] args) {
        test(3); // 1 1 2 3
        System.out.println(cal(3));

        System.out.println("--------------迷宫地图输出");
        int[][] migong = migong();
        System.out.println("--------------");
        System.out.println("--------------新地图输出，2即为路径");
        setWay(migong, 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(migong[i][j]);
            }
            System.out.println();
        }


    }
    // 1.调用test(3) 方法入栈
    // 2.在test3中遇到test2将test2也入栈，如此重复
    // 3.到test(1)时满足条件退出递归调用，执行下面打印代码
    // 4.递归调用依次返回，执行下面的代码
    public static void test(int n) {
        if (n > 1) {
           test(n-1);
        } else {
            System.out.println(n); // 最后一个栈n=1 if中条件不成立进入该else循环
        }
        System.out.println(n);
    }

    // 阶乘
    public static int  cal(int n ) {
        if (n == 1) {
            return 1;
        } else {
            System.out.println("n值为：" + n);
            return cal(n - 1) * n;
        }
    }

    // 迷宫问题
    // 8 * 7 的迷宫，四边为墙且第四行前3列为墙，求（1,1 到 (6,5) 的路线
    // 1为墙 2为可以走 3表示该点走过但是不通  走策略 下 -> 右 -> 上 -> 左
    public static boolean setWay(int[][] map, int i ,int j){
        if (map[6][5] == 2) { // 通路已经走过
            return true;
        } else {
            if(map[i][j] == 0) { // 没走过
                map[i][j] = 2;
                // 走策略 下 -> 右 -> 上 -> 左
                if(setWay(map,i + 1, j)) {
                    return true;
                } else if (setWay(map, i , j + 1)) {
                    return true;
                } else if (setWay(map, i-1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                }  else {
                    map[i][j] = 3;// 此路不通是死路
                    return false;
                }
            } else {  // 如果走过或者为墙 1 2 3 则表示此路不通
                return false;
            }
        }
    }

    // 利用二维数组模拟迷宫 8行7列
    public static int[][] migong() {
        int[][] v = new int[8][7];
        // 四周置为1
        for (int i = 0; i < 8; i++) {
            v[i][0] = 1;
            v[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            v[0][i] = 1;
            v[7][i] = 1;

        }
        // 设置挡板
        v[4][1] = 1;
        v[4][2] = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(v[i][j]);
            }
            System.out.println();
        }
        return v;

    }


}

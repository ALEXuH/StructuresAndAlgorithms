package org.alexuh.nolinear.table.graph;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/11
 *
 *弗洛伊德算法O(n^3):
 *
 * 求图各个顶点到各顶点的最短距离
 *
 * 动态规划
 * 假设i k j   f(ij) 表示ij之间的距离，设k为ij最短路径所需要经过的顶点 f(ij) = Min (f(ij), f(ik) + f(kj))  [递推公式]
 *
 */
public class Floyed {
    public static void main(String[] args) {
        int MAX = Integer.MAX_VALUE;    // 无法到达时距离设为 Integer.MAX_VALUE
        int[][] graph ={
                {0,1,12,MAX,MAX,MAX},
                {MAX,0,9,3,MAX,MAX},
                {MAX,MAX,0,MAX,5,MAX},
                {MAX,MAX,4,0,13,15},
                {MAX,MAX,MAX,MAX,0,4},
                {MAX,MAX,MAX,MAX,MAX,0}
        };

        floyed(graph);

    }

    // graph 为直接模拟的图的邻接矩阵,不能直接到达的设为MAX
    public static int[][] floyed(int[][] graph) {

        // 1.获取图的邻接矩阵
        int[][] dist = graph;
        // 定义pre 保存到达目标顶点的中间顶点 默认每一行都初始化为当前行的索引 即以下
        //000
        //111
        //222
        int[][] pre = new int[dist.length][dist.length];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(pre[i],i);
        }

        int vertexNum = graph.length;
        int len;

        //  遍历中间节点  i k j
        for (int k = 0; k < vertexNum; k++) {
            for (int i = 0; i < vertexNum; i++) { // 遍历源节点
                for (int j = 0; j < vertexNum; j++) { // 遍历目标节点
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) { // 默认设置为max防止溢出
                        len = dist[i][k]  + dist[k][j];
                        if (len < dist[i][j]) {
                            dist[i][j] = len;
                            pre[i][j] = k; // 保存中间顶点
                        }
                        // 不需要保存中间顶点可直接使用动态规划的递归公式简写
                        //dist[i][j] = Math.min(dist[i][k]  + dist[k][j], dist[i][j]);

                    }
                }
            }
        }
        //   输出顶点到各个顶点的最短距离
        for (int i = 0; i < dist.length; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }

        System.out.println("-------");

        // 输出到各个顶点最短距离的前缀顶点的索引
        for (int i = 0; i < pre.length; i++) {
            System.out.println(Arrays.toString(pre[i]));
        }
        return dist;
    }
}

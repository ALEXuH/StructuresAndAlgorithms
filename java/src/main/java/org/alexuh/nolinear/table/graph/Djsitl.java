package org.alexuh.nolinear.table.graph;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/11
 *
 * 迪杰斯特拉算法 O(n^2)（贪心算法，对每到达的每一个顶点都取最小值）:
 *
 * 如果求每个点到各个顶点的最小路径 n * n^2 = O(n^3) 时间复杂度就与弗洛伊德算法一样
 *
 * 最优解线路 => A到该解的到每个沿途顶点距离是最短的 => 求出A到各个顶点的最小距离 => 推出到目的顶点的最小路径和距离

 * 编程思想:
 * 每次选取一个离出发点最近且未标记的节点(给定visit[]标识是否访问/dist[]表示出发点到各个顶点的最短距离(无法直接到达设为max))，调整出发点到以这个节点B为中心的周边节点的最短距离[A经过点B到下一个顶点C的最短距离]（扩散/广度优先思想）。这个过程持续 n - 1 次，直到所有节点都遍历完毕
 *
 *
 */
public class Djsitl {
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
        int start = 0;  // 选择出发点
        System.out.println("---------");
        System.out.println(Arrays.toString(djstl(graph,start)));
    }

    public static int[] djstl(int[][] graph, int start) {
        int vertxeNum = graph.length;
        boolean[] isVisit = new boolean[vertxeNum]; // 标记顶点是否访问过
        int[] dist = new int[vertxeNum]; // start到各个顶点的最小距离

        // 1.初始化dist表 2.标记开始节点
        for(int i = 0; i < vertxeNum; i++) {
            dist[i] = graph[start][i];
        }
        isVisit[start] = true;
        // 除了第一个节点后面每个节点需要查找一次
        for(int i = 1; i < vertxeNum; i++) {
            int min = Integer.MAX_VALUE;
            int p = 0;
            // 找出一个未标记的且距离出发出发点最近的顶点B
            for (int j = 0; j < vertxeNum; j++) {
                if(!isVisit[j]  && dist[j] < min ) { // 1.不能等于start顶点距离为0 2.是未访问过的顶点
                    min = dist[j];
                    p = j;
                }
            }

            // 标记顶点已经访问
            isVisit[p] = true;
            // 更新start经过B顶点在到达其他顶点的距离
            for (int j = 0; j < vertxeNum; j++) {
                if (j != p && graph[p][j] != Integer.MAX_VALUE && dist[j] > graph[p][j] + dist[p]) {
                    dist[j] = graph[p][j] + dist[p];
                }
            }

            // 打印dist当前到各个顶点路径的最小值
            //B [0, 1, 10, 4, 2147483647, 2147483647]
            //C [0, 1, 8, 4, 17, 19]
            //E  [0, 1, 8, 4, 13, 19]
            //F [0, 1, 8, 4, 13, 17]
            //[0, 1, 8, 4, 13, 17]
            System.out.println(Arrays.toString(dist));

        }
        return dist;

    }

}

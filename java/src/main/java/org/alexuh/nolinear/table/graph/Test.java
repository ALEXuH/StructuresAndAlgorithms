package org.alexuh.nolinear.table.graph;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/7
 *
 *       A  B  C  D  E  F
 *  A   [0, 1, 1, 0, 0, 0]
 *  B   [1, 0, 0, 1, 0, 0]
 *  C   [1, 0, 0, 0, 0, 0]
 *  D   [0, 1, 0, 0, 0, 0]
 *  E   [0, 0, 0, 0, 0, 1]
 *  F   [0, 0, 0, 0, 1, 0]
 *
 * ABCD 连通  EF 连通
 *
 */
public class Test {

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        String[] list = {"A", "B", "C", "D","E", "F"};
        // 添加顶点
        for(String v : list) {
            graph.addVertex(v);
        }
        // 添加边
        graph.addBorder(0, 1, 1); // A - B
        graph.addBorder(0, 2, 1); // A - C
        graph.addBorder(1, 3, 1); // B - D

        // 添加非连通图的边
        graph.addBorder(4, 5, 1); // E - F

        graph.printGraph();

        System.out.println("深度优先遍历-------");
        // 深度优先遍历
        graph.dfsTraverse(); // A B D C E F

        System.out.println("广度优先遍历---------");
        // 广度优先遍历
        graph.bfdTraverse(); // A B C D E F


    }

}

package org.alexuh.nolinear.table.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/10
 */
public class GraphApply {

    public static void main(String[] args) {
        // prim(普里姆)算法
        Graph graph = new Graph(6);
        String[] list = {"A", "B", "C", "D","E", "F"};
        // 添加顶点
        for(String v : list) {
            graph.addVertex(v);
        }
        // 添加边
        graph.addBorder(0, 1, 10); // A - B
        graph.addBorder(0, 2, 2); // A - C
        graph.addBorder(0, 3, 3); // A - D
        graph.addBorder(1, 3, 1); // B - D
        graph.addBorder(1, 5, 5); // B - F
        graph.addBorder(4, 5, 21); // E - F
        graph.addBorder(1, 4, 6); // B - E

        GraphApply applay = new GraphApply();
        applay.prim(graph, 0); // 从第1个顶点开始

        System.out.println("--------------克鲁斯卡尔");
        // 克鲁斯卡尔
        applay.kruskal(graph);

    }

    /**
     * 普里姆算法 (选择最小边的点)
     * @param graph
     * @param start
     */
    public void prim(Graph graph, int start) {
        int numVertices = graph.getNumVertex();
        boolean[] isVisit = new boolean[numVertices];

        isVisit[start] = true;

        // 记录最小权值的2个顶点的索引
        int h1 = -1;
        int h2 = -1;
        // 顶点集合
        List<String> vertexList = graph.getVertexList();

        int min = Integer.MAX_VALUE;
        // 需要寻找n-1条边
        for(int k = 0; k < numVertices - 1; k++) {
            // 寻找访问过的和未访问过的顶点之间的最小权值的边和顶点
            for(int i=0; i<numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    // 0表示不连通没有边需要加上graph.getWeight(i, j) != 0
                    if (isVisit[i] && !isVisit[j] && graph.getWeight(i, j) < min && graph.getWeight(i, j) != 0) {
                        min = graph.getWeight(i, j);
                        h1 = i;
                        h2 = j;
                    }

                }
            }
            System.out.printf("当前在找第%s条边 %s %s", k+1, h1, h2);
            // 1.找到过后重置上一轮的顶点和min值用于找下一条边
            // 2.标记已访问点
            System.out.printf("顶点 %s -> %s 权值为 %s%n", vertexList.get(h1), vertexList.get(h2),min);
            isVisit[h2] = true;
            min = Integer.MAX_VALUE;

        }
    }

    /**
     * 克鲁斯卡尔算法 (选择最小边)
     * @param graph
     */
    public void kruskal(Graph graph) {

        int[] end = new int[graph.getNumVertex()];
        List<Edge> result = new ArrayList<>();
        // 1.获取排序后的边的集合
        List<Edge> edges = graph.getEdges();
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                int diff = o1.weight - o2.weight;
                if (diff > 0) {
                    return 1;
                } else if (diff < 0) {
                    return -1;
                }
                return 0;
            }
        });
        System.out.println(edges);
        // 2.遍历边并判断是否形成回路
        for (Edge edge: edges) {
            // 获取顶点索引然后获取它的根节点 (重点为end方法)
            int n = graph.getEnd(end, graph.getVertexIndex(edge.start));
            int m = graph.getEnd(end, graph.getVertexIndex(edge.end));
            // 没有形成回路说明找到一条边
            if(m != n) {
                end[n] = m;   //标记统一，小的转成大的
                System.out.printf("顶点 %s -> %s 权值为 %s%n", edge.start, edge.end, edge.weight);
                result.add(edge);
            }
        }

    }

}

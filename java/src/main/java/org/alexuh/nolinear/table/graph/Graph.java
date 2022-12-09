package org.alexuh.nolinear.table.graph;

import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/7
 * 图：邻接矩阵实现
 *    A  B  C
 *  A 0 0   1
 *  B 1 0   1
 *  C 0 1   1
 */
public class Graph {

    private List<String> vertexList; // 存储顶点
    private int[][] matrix; // 邻接矩阵
    private int numBorder; // 边的个数
    private int numVertex; // 顶点个数
    private boolean[] isVisit; // 标记顶点是否已访问

    private Queue<Integer> queue = new LinkedList<>();

    public Graph(int n) {
        vertexList = new ArrayList<>();
        matrix = new int[n][n];
        isVisit = new boolean[n];
        numBorder = 0;
        numVertex = 0;
    }

    //  添加节点
    public void addVertex(String vex) {
        vertexList.add(vex);
        numVertex++;
    }

    // 添加边和边的权值
    public void addBorder(int i, int j, int weight) {
        matrix[i][j] = weight;
        matrix[j][i] = weight;
        numBorder++;
    }

    // 获取边的个数
    public int getNumBorder() {
        return numBorder;
    }
    // 获取顶点个数
    public int getNumVertex() {
        return numVertex;
    }
    // 获取顶点之间边的权值
    public int getWeight(int i, int j) {
        return matrix[i][j];
    }
    // 打印图(显示二维数组)
    public  void printGraph() {
        for (int[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    // DFS 深度遍历优先 （递归 + 回溯）
    public void dfs(int v) {
        // 标记为已访问
        isVisit[v] = true;
        // 输出节点
        System.out.println("访问节点: " + vertexList.get(v));

        // 往下递归找出所有未访问的顶点
        for (int i = 0; i < numVertex; i++) {
            if (!isVisit[i] && matrix[v][i] == 1) {
                dfs(i);
            }
        }
    }

    // 对所有顶点都深度遍历，防止非连通图的没有访问到
    public void dfsTraverse() {
        for (int i = 0; i < numVertex; i++) {
            if (!isVisit[i]) {
                dfs(i);
            }
        }
    }


    // 广度优先遍历 BFS （队列）
    public void bfs(int v) {
        // 标记顶点已访问
        isVisit[v] = true;
        // 输出顶点值
        System.out.println("访问节点: " + vertexList.get(v));
        // 找出当前顶点的所有相邻顶点进行标记并添加到队列中
        for (int i = 0; i < numVertex; i++) {
            if (!isVisit[i] && matrix[v][i] == 1) {
                isVisit[i] = true;
                queue.add(i);
            }
        }
        // 递归遍历队列中的顶点
        while(queue.size() > 0) {
            bfs(queue.remove());
        }
    }

    // 非递归广度优先
    public void bfs1(int v) {
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("输出当前顶点");
        queue.add(v);
        int temp;
        while(queue.size() > 0) {
            temp =  queue.remove();
            isVisit[temp] = true;
            for(int i = 0; i < numVertex; i++) {
                if (!isVisit[i] && matrix[temp][i] == 1) {
                    queue.add(i);
                }
            }
        }
    }

    // 对所有顶点都深度遍历，防止非连通图的没有访问到
    public void bfdTraverse() {
        for (int i = 0; i < numVertex; i++) {
            bfs(i);
        }
    }

    public List<String> getVertexList() {
        return vertexList;
    }

    /**
     * 获取顶点的根节点（并查集）
     * @param end 表示第i个节点的根节点是end[i]
     * @param i
     * @return
     */
    public int getEnd(int[] end, int i) {
        while(end[i] != 0) {
            i = end[i];
        }
        return i;
    }

    /**
     * 获取边
     * @return
     */
    public List<Edge> getEdges() {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                if(matrix[i][j] != 0) {
                    result.add(new Edge(vertexList.get(i), vertexList.get(j), matrix[i][j]));
                }
            }
        }
        return result;
    }

    public int getVertexIndex(String v) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).equals(v)) {
                return i;
            }
        }
        return -1;
    }
}

// 边的实力
class Edge {
    // 边的2个顶点
    public String start;
    public String end;
    public int weight; // 边的权值

    public Edge(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", weight=" + weight +
                '}';
    }
}

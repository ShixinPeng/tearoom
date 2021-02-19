package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author shixinpeng
 * @description 无向图-邻接表
 * @ClassName: Graph
 * @date 2020/2/29
 *
 */
public class Graph {
    /**
     * 定点的个数
     */
    private int v;
    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new  LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 无向图中添加一条边
     public void addEgde(int s, int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    public void printGraph(){
        for (int i = 0; i < v; i++) {
            System.out.println(" " + i + "=>" + adj[i].toString());
        }
    }

    /**
     *  Breadth-First-Search
     *  广度优先算法
     * @title bfs
     * @author shixin peng
     * @date 2020-03-01 15:02
     * @param s
     * @param t
     * @return void
     * @throws
     */
    public void bfs(int s,int t){
        if (s == t){
            return;
        }
        // 用来记录已经被访问的顶点
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 用来存储已经被访问、但相连的顶点还没有被访问的顶点（待访问子顶点的顶点队列）
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 记录搜索路径
        int[] prev = new int[v];
        // 初始化prev 搜索路径
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        // 待查找队列不为空
        while (queue.size() !=0){
            // w = 获取当前查找的顶点(关注点)
            int w = queue.poll();
            // 遍历当前查找的顶点所有邻接顶点
            for (int i = 0; i < adj[w].size(); i++) {
                // 依次获取操作顶点的邻接顶点
                int q = adj[w].get(i);
                // 判断邻接顶点是否已经操作过
                if (!visited[q]){
                    // 如果没有操作过，则记录当前的路径
                    // 这里prev表示方式为prev[邻接顶点] = 被连接上的顶点（已经搜索到的顶点、可以理解为上级线人）
                    prev[q] = w;
                    // 如果邻接顶点为终止顶点，则打印已经存储的路径
                    if (q==t){
                        // 输入路径
                        printPath(prev,s,t);
                        return;
                    }
                    // 如果邻接顶点不为终止顶点，则：
                    // 1：记录当前操作顶点已经操作完毕
                    visited[q] = true;
                    // 2：记录需要操作的顶点
                    queue.add(q);
                }

            }
        }

    }

    private void printPath(int[] prev,int s,int t) {

        if (prev[t] != -1 && t != s){
            printPath(prev,s,prev[t]);
        }
        System.out.print(t + " ");
    }


    /**
     * 全局变量或者类成员变量
     * 用于控制在深度优先查找中，找到路径后跳出循环
     */
    boolean found = false;

    /**
     *  深度优先算法
     * @title dfs
     * @author shixin peng
     * @date 2020-03-08 14:48
     * @param
     * @return void
     * @throws
     */

    public void dfs(int s,int t){
        // 重置标识
        found = false;
        // 访问过的顶点记录
        boolean[] visited = new boolean[v];

        // 查找路径存储数组
        int[] prev = new int[v];
        // 初始化查找路径
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        // 查找路径

    }

    private void recurDfs(int w,int t,boolean[] visited,int[] prev) {
        // 寻找成功，停止寻找
        if (found == true) return;

        visited[w]
    }

    public static void main(String[] args) {



    }

    public static void bfsTest() {
        Graph graph = new Graph(5);
        graph.addEgde(1,2);
        graph.addEgde(2,3);
        graph.addEgde(3,4);
        graph.addEgde(1,3);

        graph.printGraph();

        graph.bfs(1,4);
    }
}

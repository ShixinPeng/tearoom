package graph;

import java.util.LinkedList;

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

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEgde(1,2);
        graph.addEgde(1,3);
        graph.addEgde(1,4);

        graph.printGraph();

    }
}

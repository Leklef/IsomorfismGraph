package Algoritm;

import Struct.Graph;

/**
 * Created by lenar on 06.05.17.
 */
public class Ullmann {

    private Graph g1;
    private Graph g2;

    private Graph[] agraph;

    private int nagraph = 0;

    public Ullmann(Graph g1, Graph g2) {
        this.g1 = g1;
        this.g2 = g2;
        agraph = new Graph[g1.size()*g1.size()];
    }

    public void add_Graph(Graph g) {
        for (int i=0;i < g.size(); i++)
            for(int j=0; j < g.size(); j++)
                agraph[nagraph].matrix[i][j] = g.matrix[i][j];
    }

    public Graph transpose(Graph g) {
        Graph t = new Graph(g.size());

        for (int i = 0; i < g.size(); i++)
            for (int j = 0; j < g.size(); j++)
                t.matrix[j][i] = g.matrix[i][j];
        return t;
    }

    public Graph mul(Graph graph1, Graph graph2) {
        Graph m = new Graph(graph1.size());

        for (int i = 0; i < graph1.size(); i++)
            for (int j = 0; j < graph1.size(); j++)
                for (int z = 0; z < graph1.size(); z++)
                    m.matrix[i][j] = m.matrix[i][j] + (graph1.matrix[i][z] * graph2.matrix[z][j]);
        return m;
    }

    public Graph cut(int k, int m, Graph g) {
        Graph c = new Graph(g.size());

        for (int i = 0; i < k; i++)
            for (int j = 0; j < m; j++)
                c.matrix[i][j] = g.matrix[i][j];
        return c;
    }

    public boolean compare (Graph graph1, Graph graph2) {
        for (int i = 0; i < graph1.size(); i++)
            for (int j = 0; j < graph1.size(); j++)
                if (graph1.matrix[i][j] != graph2.matrix[i][j]) return false;
        return true;
    }
}

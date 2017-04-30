/**
 * Created by lenar on 30.04.17.
 */
public class ConnectedGraph {

    Node[] V;

    public ConnectedGraph() {}

    public ConnectedGraph(BitMatrix matrix) {
        int n = matrix.getSize();
        this.V = new Node[n];

        for (int i = 0; i < n; i++)
            V[i] = new Node(i);
        for (int i = 0; i < n; i++){
            int childrenSize = 0;
            for (int j = 0; j < n; j++)
                if (matrix.getBit(i, j))
                    childrenSize++;

            Node[] C = new Node[childrenSize];
            int k = 0;

            for (int j = 0; j < n; j++)
                if (matrix.getBit(i, j)){
                    C[k] = V[j];
                    k++;
                }
            V[i].addChildren(C);
        }
    }

    public static AbstractTree BFS(ConnectedGraph G, int m, int size) {

        int[] miniMap1 = new int[size];

        for (int a = 0; a < G.V.length; a++)
            miniMap1[G.V[a].data] = a;

        return BFS(G, m, miniMap1);

    }

    public static AbstractTree BFS(ConnectedGraph G, int m, int[] miniMap) {
        int n = G.V.length;
        AbstractTree tree = new AbstractTree(n);
        boolean[] visited = new boolean[n];
        int[] queue = new int[n];

        visited[m] = true;
        queue[0] = m;
        tree.setLevel(m, 0);

        int l = 1;
        int i = 0;

        while (i < l) {
            int r = queue[i];
            i++;
            int k = tree.getLevel(r);

            for (int j = 0; j < G.V[r].children.length; j++) {
                Node s = G.V[r].children[j];
                int b = miniMap[s.data];

                if (!visited[b]) {
                    visited[b] = true;
                    queue[l] = b;
                    tree.setLevel(b, k + 1);
                    l++;
                }
            }
        }

        tree.setHeight();
        tree.setWidth();

        return tree;
    }

}

import java.io.IOException;

/**
 * Created by lenar on 30.04.17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Graph Isomorphism!");

        BitMatrix adjMatrix = AdjMatrix.readAdj("input7"); //AdjMatrix.makeRandom(14);
        BitMatrix adjMatrixPerm = AdjMatrix.readAdj("input8"); //AdjMatrix.makeRandom(14);
        adjMatrix.printMatrix();
        System.out.println();
        adjMatrixPerm.printMatrix();

//        ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
//        ConnectedGraph G2 = new ConnectedGraph(adjMatrixPerm);

        Graph gr1 = new Graph(adjMatrix);
        Graph gr2 = new Graph(adjMatrixPerm);

        //System.out.println(ConnectedGraph.areIsomorphic(G1,G2,G1.V.length));
        System.out.println(Graph.findIsomorphism(gr1, gr2));
        System.out.println("End program");
    }
}

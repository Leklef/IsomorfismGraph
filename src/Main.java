import java.io.IOException;

/**
 * Created by lenar on 30.04.17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Graph Isomorphism!");

        BitMatrix adjMatrix= AdjMatrix.readAdj("input1");
        BitMatrix adjMatrixPerm = AdjMatrix.readAdj("input2");

        ConnectedGraph G1 = new ConnectedGraph(adjMatrix);
        ConnectedGraph G2 = new ConnectedGraph(adjMatrixPerm);

        System.out.println(ConnectedGraph.areIsomorphic(G1,G2,G1.V.length));
        System.out.println("End program");
    }
}

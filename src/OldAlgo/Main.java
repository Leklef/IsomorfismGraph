package OldAlgo;

import java.io.IOException;

/**
 * Created by lenar on 30.04.17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to OldAlgo.Struct.Graph Isomorphism!");

        BitMatrix adjMatrix = AdjMatrix.readAdj("supinput1"); //OldAlgo.Util.AdjMatrix.makeRandom(14);
        BitMatrix adjMatrixPerm = AdjMatrix.readAdj("supinput2"); //OldAlgo.Util.AdjMatrix.makeRandom(14);

//        OldAlgo.ConnectedGraph G1 = new OldAlgo.ConnectedGraph(adjMatrix);
//        OldAlgo.ConnectedGraph G2 = new OldAlgo.ConnectedGraph(adjMatrixPerm);

        Graph gr1 = new Graph(adjMatrix);
        Graph gr2 = new Graph(adjMatrixPerm);

        //System.out.println(OldAlgo.ConnectedGraph.areIsomorphic(G1,G2,G1.V.length));
        System.out.println(Graph.findIsomorphism(gr1, gr2));
        System.out.println("End program");
    }
}

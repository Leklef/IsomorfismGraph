import Algoritm.Ullmann;
import Struct.Graph;
import Util.GraphInput;

import java.io.IOException;

/**
 * Created by lenar on 01.05.17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GraphInput i1 = new GraphInput("input1.txt");
        GraphInput i2 = new GraphInput("input1.txt");
        Graph g1 = new Graph(i1.readInput().matrix);
        Graph g2 = new Graph(i2.readInput().matrix);
        Ullmann u = new Ullmann(g1, g2);
        u.isIsomorfed();

        for (int i=0; i<g1.size(); i++) {
            System.out.print("-" + (i + 1) + " ");
            for (int j = 0; j < g1.size(); j++) {
                if (g1.matrix[i][j] == 1)
                    System.out.print((j+1)+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        for (int i=0; i<g1.size(); i++) {
            System.out.print("-" + (i + 1) + " ");
            for (int j = 0; j < g1.size(); j++) {
                if (g2.matrix[i][j] == 1)
                    System.out.print((j+1)+" ");
            }
            System.out.println();
        }
    }
}

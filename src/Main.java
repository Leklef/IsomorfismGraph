import Util.AdjMatrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lenar on 01.05.17.
 */
public class Main {

    private static Graph g1;
    private static Graph g2;

    public static void main(String[] args) throws IOException {
        boolean iso;

        g1 = new Graph("input400.1");
        g2 = new Graph("input400.2");

        long totalTime = System.nanoTime();
        iso = g1.UllmanIsomorphicTest(g2);
        long endTime = System.nanoTime();
        long duration = endTime - totalTime;

        if(iso)
            System.out.println(g1.getName()+" IS isomorphic to "+g2.getName());
        else
            System.out.println(g1.getName()+" IS NOT isomorphic to "+g2.getName());
        System.out.println("Number of Nodes: "+g1.nodeCount()+ "\nNumber of Edges: "+g1.edgeCount());
        System.out.println("Completion Time:" + duration/ Math.pow(10, 9) + " seconds.");

        TotalAlgo my = new TotalAlgo(g1.getAdjacencyMatrix(), g2.getAdjacencyMatrix());

        //other algorithm implementation
        totalTime = System.nanoTime();
        iso = my.isIsomorphic();
        endTime = System.nanoTime();
        duration = endTime - totalTime;

        if(iso)
            System.out.println(g1.getName()+" IS isomorphic to "+g2.getName());
        else
            System.out.println(g1.getName()+" IS NOT isomorphic to "+g2.getName());
        System.out.println("Completion Time:" + duration/ Math.pow(10, 9) + " seconds.");

        //int[][] m1 = AdjMatrix.toArray(AdjMatrix.makeRandom(400));
////        int[][] m2 = AdjMatrix.toArray(AdjMatrix.makeRandom(800));
////
        //writeMatrix("input400.1.txt", m1);
//        writeMatrix("input800.2.txt", m2);
//
//        for (int i = 0; i < m1.length; i++) {
//            for (int j = 0; j < m1.length; j++) {
//                System.out.print(m1[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        System.out.println();
//
//        for (int i = 0; i < m1.length; i++) {
//            for (int j = 0; j < m1.length; j++) {
//                System.out.print(m2[i][j] + " ");
//            }
//            System.out.println();
//        }

//        java.awt.Toolkit.getDefaultToolkit().beep();

    }

    public static void writeMatrix(String filename, int[][] matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    bw.write(matrix[i][j] + " ");
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {}
    }
}

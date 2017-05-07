package Util;

import java.io.IOException;

/**
 * Created by lenar on 02.05.17.
 */

public class GraphInput {

    private String filename;

    public GraphInput(String name){
        filename = name;
    }

    public GraphResult readInput(){
        return readInput(filename);
    }

    public GraphResult readInput(String name){
        filename = name;
        GraphResult retVal = null;

        try {
            int numVert;
            int numEdges=0;
            int[][] matrix;
            boolean visited[];
            int degree[];
            String graphName = name;
            matrix = AdjMatrix.toArray(AdjMatrix.readAdj(filename));

            numVert = matrix.length;
            visited = new boolean[numVert];
            degree = new int[numVert];

            for (int i=0; i < numVert; i++) {
                for (int j = 0; j < numVert; j++) {
                    if (matrix[i][j] == 1) {
                        numEdges++;
                        degree[i] = degree[i];
                    }
                }
                visited[i] = false;
            }
            numEdges = numEdges/2;
            retVal = new GraphResult(matrix, visited, degree, numVert, numEdges,graphName);



        } catch(java.io.FileNotFoundException fnfe){
            System.out.println("Could not open file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public class GraphResult {

        public int[][] matrix;
        public boolean[] visited;
        public int[] degrees;
        public int numVert;
        public int numEdges;
        public String name = "";

        public GraphResult(int matrix[][], boolean visited[], int degrees[], int numVert, int numEdges, String name) {
            this.matrix = matrix;
            this.visited = visited;
            this.numVert = numVert;
            this.numEdges = numEdges;
            this.degrees = degrees;
            this.name = name;
        }

        public void printMatrix() {
            for(int i=0; i<numVert; i++) {
                for(int j=0; j<numVert; j++)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
        }

    }
}


package Struct;

/**
 * Created by lenar on 06.05.17.
 */
public class Graph {

    public int [][] matrix;

    public Graph(int [][] matrix) {
        this.matrix = matrix;
    }

    public Graph(int nodes) {
        this.matrix = generateMatrix(nodes);
    }

    public int size() { return matrix.length; }

    private int[][] generateMatrix(int nodes) {
        int[][] m = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                m[i][j] = 0;
        return m;
    }

}

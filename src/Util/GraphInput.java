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
            int[][] m = AdjMatrix.toArray(AdjMatrix.readAdj(filename));
            retVal = new GraphResult(m);

        } catch(java.io.FileNotFoundException fnfe){
            System.out.println("Could not open file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public class GraphResult
    {
        public int[][] matrix;

        public GraphResult(int[][] matrix)
        {
            this.matrix = matrix;
        }
    }
}


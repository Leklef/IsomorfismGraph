import Util.GraphInput;

/**
 * Created by lenar on 07.05.17.
 */
public class Graph {

    private String name = "";
    private boolean[] visited;
    private int[][] vertAdj;
    private int numNodes;
    private int numEdges;
    private int[] nodeDegrees;
    private int visitCounter = 0;
    private GraphInput.GraphResult original = null;

    public Graph() { //O(0)
        init(0);
    }

    public Graph(int nodes) //O(n^2)
    {
        init(nodes);
    }

    public Graph(String filename) //O(edges)
    {
        GraphInput graph = new GraphInput(filename);
        original = graph.readInput(); //O(e) e = edges
        if(original == null)
            init(0); //O(0)
        else
        {
            visited = original.visited;
            vertAdj = original.matrix;
            numNodes = original.numVert;
            numEdges = original.numEdges;
            name = original.name;
            nodeDegrees = original.degrees;
        }
    }

    private void init(int nodes) //O(1)
    {
        numNodes = nodes;
        numEdges = 0;
        visited = new boolean[numNodes];
        vertAdj = new int[numNodes][numNodes];
        nodeDegrees = new int[numNodes];
        visitCounter = 0;
    }

    public int nodeCount() //O(1)
    {
        return numNodes;
    }

    public int edgeCount() //O(1)
    {
        return numEdges;
    }

    public boolean isVisited(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        return visited[node];
    }

    public boolean visit(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        visited[node] = true;
        visitCounter++;
        return true;
    }

    public boolean unvisit(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        visited[node] = false;
        visitCounter--;
        return true;
    }

    public int numVisited() //O(1)
    {
        return visitCounter;
    }

    public String getName() //O(1)
    {
        return name;
    }

    public int[] countDegrees() //O(n)
    {
        int[] deg = new int[numNodes];

        for(int i=0; i<numNodes; i++)
            deg[nodeDegrees[i]]++;

        return deg;
    }

    public int getDegree(int node) //O(1)
    {
        if(numNodes==0 || node<0 || node>numNodes-1)
            return  0;
        return nodeDegrees[node];
    }

    public int[][] getAdjacencyMatrix() //O(1)
    {
        return vertAdj;
    }

    public boolean compareMatrix(Graph toCheck) //O(.5n^2)
    {
        int[][] check = toCheck.getAdjacencyMatrix();
        for(int i=0; i<numNodes; i++)
            for(int j=i+1; j<numNodes; j++)
                if(check[i][j]!=vertAdj[i][j])
                    return false;
        return true;
    }

    public boolean compareMapping(int[][] second, int[] map) //O(.5n^2)
    {
        for(int i=0; i<numNodes; i++)
            for(int j=i+1; j<numNodes; j++)
                if(vertAdj[i][j]!=second[map[i]][map[j]])
                    return false;
        return true;
    }

    public boolean UllmanIsomorphicTest(Graph toCompare)
    {
        if ((numNodes != toCompare.nodeCount()) || (numEdges != toCompare.edgeCount()))
            return false;

        boolean areIsomorph = false;
        int[][] degreeMap = new int[numNodes][numNodes];

        for (int i=0; i<numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if(this.getDegree(i)==toCompare.getDegree(j))
                    degreeMap[i][j] = 1;
                else
                    degreeMap[i][j] = 0;
            }
        }
        java.util.Set<Pair> degMatching = new java.util.HashSet<Pair>();
        areIsomorph = backTrack(degreeMap, 0, degMatching);
        return areIsomorph;
    }

    private boolean backTrack(int[][] degMap, int i, java.util.Set<Pair> degMatch)
    {
        boolean areIsomorph = false;
        if (i > numNodes-1)
        {
            if (degMatch.size() == numNodes)
                areIsomorph = true;
        }
        else
        {
            for (int j=0; j<numNodes && !areIsomorph; j++)
            {
                if (degMap[i][j] == 1)
                {
                    int[][] tempMap = new int[numNodes][numNodes];
                    Pair newPair = new Pair(i, j);
                    degMatch.add(newPair);

                    for (int k = 0; k<numNodes; k++)
                        System.arraycopy(degMap[k], 0, tempMap[k], 0, numNodes);
                    for (int k = i+1; k < numNodes; k++)
                        tempMap[k][j] = 0;

                    if (forwardCheck(tempMap))
                        areIsomorph = backTrack(tempMap, i+1, degMatch);

                    degMatch.remove(newPair);
                }
            }
        }
        return areIsomorph;
    }

    private boolean forwardCheck(int[][] degMap)
    {
        for (int[] row : degMap)
        {
            int sum=0;
            for(int num : row)
                sum += num;

            if (sum == 0)
                return false;
        }
        return true;
    }


}

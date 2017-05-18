import Util.AdjMatrix;
import Util.BitMatrix;
import Util.GraphInput;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    //число вершин
    public int nodeCount() //O(1)
    {
        return numNodes;
    }

    //число граней
    public int edgeCount() //O(1)
    {
        return numEdges;
    }

    //посещена ли вершина под таким-то номером
    public boolean isVisited(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        return visited[node];
    }

    //сделать вершины посещенной
    public boolean visit(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        visited[node] = true;
        visitCounter++;
        return true;
    }

    //сделать вершины непосещенной
    public boolean unvisit(int node) //O(1)
    {
        if(numNodes==0 || node >= numNodes)
            return false;
        visited[node] = false;
        visitCounter--;
        return true;
    }

    //получить число посещенных вершин
    public int numVisited() //O(1)
    {
        return visitCounter;
    }

    public String getName() //O(1)
    {
        return name;
    }

//    public int[] countDegrees() //O(n)
//    {
//        int[] deg = new int[numNodes];
//
//        for(int i=0; i<numNodes; i++)
//            deg[nodeDegrees[i]]++;
//
//        return deg;
//    }

    public int[] countDegrees() //O(n)
    {
        int[] deg = new int[numNodes];

        for(int i=0; i<numNodes; i++)
            for (int j=0; j < numNodes; j++) {
                deg[i] += vertAdj[i][j];
            }


        return deg;
    }

    //получение степень вершины в графе
    public int getDegree(int node) //O(1)
    {
        if(numNodes==0 || node<0 || node>numNodes-1)
            return  0;
        return nodeDegrees[node];
    }

    //получение матриц графа
    public int[][] getAdjacencyMatrix() //O(1)
    {
        return vertAdj;
    }

    //сравнение матриц двух графов
    public boolean compareMatrix(Graph toCheck) //O(.5n^2)
    {
        int[][] check = toCheck.getAdjacencyMatrix();
        for(int i=0; i<numNodes; i++)
            for(int j=i+1; j<numNodes; j++)
                if(check[i][j]!=vertAdj[i][j])
                    return false;
        return true;
    }

    //сравнение карт двух графов
    public boolean compareMapping(int[][] second, int[] map) //O(.5n^2)
    {
        for(int i=0; i<numNodes; i++)
            for(int j=i+1; j<numNodes; j++)
                if(vertAdj[i][j]!=second[map[i]][map[j]])
                    return false;
        return true;
    }

    //Проверка на изоморфизм по алгоритму Ульманна
    public boolean UllmanIsomorphicTest(Graph toCompare) throws IOException {
        if ((numNodes != toCompare.nodeCount()) || (numEdges != toCompare.edgeCount()))
            return false;

        if (!checkDegreesMatrix(toCompare)) {
            return false;
        }

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
        Set<Pair> degMatching = new HashSet<Pair>();
        areIsomorph = backTrack(degreeMap, 0, degMatching);
        return areIsomorph;
    }

    // рекурсивная процедура поиска матриц перестановок
    private boolean backTrack(int[][] degMap, int i, Set<Pair> degMatch)
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

    //поиск вперед которая исключает варианты, проверка которых логически необоснованна
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

    //проверка на разность степеней вершин графа
    private boolean checkDegreesMatrix(Graph g2) throws IOException {
        int[] deg1 = countDegrees();
        int[] deg2 = g2.countDegrees();

        boolean check = false;
        boolean ch2 = false;
        for (int i = 0; i < deg1.length; i++) {
            check = false;
            for (int j = 0; j < deg2.length; j++) {
                if (deg1[i]==deg2[j]) {
                    check = true;
                }
            }
            for (int j = 0; j < deg2.length; j++) {
                if (deg2[i]==deg1[j]) {
                    ch2 = true;
                }
            }
            if (!check || !ch2) {
                return false;
            }
        }

        return true;
    }

}

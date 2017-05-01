/**
 * Created by lenar on 30.04.17.
 */
public class ConnectedGraph {

    Node[] V;

    public ConnectedGraph() {}

    public ConnectedGraph(BitMatrix matrix) {
        int n = matrix.getSize();
        this.V = new Node[n];

        for (int i = 0; i < n; i++)
            V[i] = new Node(i);
        for (int i = 0; i < n; i++){
            int childrenSize = 0;
            for (int j = 0; j < n; j++)
                if (matrix.getBit(i, j))
                    childrenSize++;

            Node[] C = new Node[childrenSize];
            int k = 0;

            for (int j = 0; j < n; j++)
                if (matrix.getBit(i, j)){
                    C[k] = V[j];
                    k++;
                }
            V[i].addChildren(C);
        }
    }

    public static AbstractTree BFS(ConnectedGraph G, int m, int size) {

        int[] miniMap1 = new int[size];

        for (int a = 0; a < G.V.length; a++)
            miniMap1[G.V[a].data] = a;

        return BFS(G, m, miniMap1);

    }

    public static AbstractTree BFS(ConnectedGraph G, int m, int[] miniMap) {
        int n = G.V.length;
        AbstractTree tree = new AbstractTree(n);
        boolean[] visited = new boolean[n];
        int[] queue = new int[n];

        visited[m] = true;
        queue[0] = m;
        tree.setLevel(m, 0);

        int l = 1;
        int i = 0;

        while (i < l) {
            int r = queue[i];
            i++;
            int k = tree.getLevel(r);

            for (int j = 0; j < G.V[r].children.length; j++) {
                Node s = G.V[r].children[j];
                int b = miniMap[s.data];

                if (!visited[b]) {
                    visited[b] = true;
                    queue[l] = b;
                    tree.setLevel(b, k + 1);
                    l++;
                }
            }
        }

        tree.setHeight();
        tree.setWidth();

        return tree;
    }

    public static Map areIsomorphic(ConnectedGraph G1, ConnectedGraph G2, int size) {
        int n = G1.V.length;
        Map map = new Map(n);
        int numOp = 0;

        if (n != G2.V.length) {
            System.out.println("False. Graphs are of different size! ");
            return null;
        }

        long totalTime = System.nanoTime();
        AbstractTree[] trees1 = new AbstractTree[n];
        AbstractTree[] trees2 = new AbstractTree[n];

        int[] miniMap1 = new int[size];

        for (int a = 0; a < n; a++){
            miniMap1[G1.V[a].data] = a;
        }

        int[] miniMap2 = new int[size];

        for (int a = 0; a < n; a++){
            miniMap2[G2.V[a].data] = a;
        }

        for (int i = 0; i < n; i++) {
            trees1[i] = ConnectedGraph.BFS(G1, i, miniMap1);
            trees2[i] = ConnectedGraph.BFS(G2, i, miniMap2);
        }
        long endTime = System.nanoTime();
        long duration = endTime - totalTime;
        System.out.println("BFS took " + duration/ Math.pow(10, 9) + " seconds.");

        boolean[] matched = new boolean[n];
        int mismatched = -1;

        for (int i = 0; i < n; i++) {
            int length = map.length;
            for (int j = 0; j < n; j++) {
                if (numOp > 200){
                    System.out.println("Number of operations is " + numOp);
                }
                if (!matched[j] && j > mismatched) {
                    boolean match = true;
                    match = checkConditions(map, trees1[i], trees2[j], match);

                    if (match) {
                        map.add(length, i, j);
                        matched[j] = true;
                        numOp++;
                        mismatched = -1;
                        break;
                    }
                }
            }
            if (map.length == length) {
                if (i - 1 < 0) {
                    System.out.println("False. Graphs are non-isomorphic! ");
                    return null;
                }
                mismatched = map.getValue(i - 1);
                matched[mismatched] = false;
                numOp++;
                map.pop();
                i = map.length - 1;
            }
        }

        System.out.println("True. Graphs are isomorphic! ");
        return map;
    }

    public static boolean checkConditions(Map map, AbstractTree tree1, AbstractTree tree2, boolean match) {
        if (tree1.height != tree2.height)
            match = false;
        else {
            for (int k = 0; k < tree1.height; k++)
                if (tree1.width[k] != tree2.width[k]) {
                    match = false;
                    break;
                }
            if (match)
                for (int k = 0; k < map.length; k++) {
                    int key = map.getKey(k);
                    int value = map.getValue(k);
                    int keyLevel = tree1.getLevel(key);
                    int valueLevel = tree2.getLevel(value);
                    if (keyLevel != valueLevel) { // Check to see if shortest distance is preserved.
                        match = false;
                        break;
                    }
                }
        }
        return match;
    }

    public static boolean checkAllEdges(ConnectedGraph G1, ConnectedGraph G2, Map map) {
        int n = map.length;

        if (checkEdges(G1, G2, map, n))
            return false;

        Map map2 = new Map(n);
        for (int i = 0; i < n; i++) {
            map2.add(i, map.getValue(i), map.getKey(i));
        }

        if (checkEdges(G2, G1, map2, n))
            return false;

        System.out.println("Success! Isomorphism confirmed.");
        return true;
    }

    private static boolean checkEdges(ConnectedGraph G1, ConnectedGraph G2, Map map, int n) {
        for (int i = 0; i < n; i++) {
            int key = map.getKey(i);
            int value = map.getValue(i);

            if (G1.V[key].children.length != G2.V[value].children.length) {
                System.out.println("Error. Map sets correspondence between nodes with different number of children! ");
                return true;
            }

            for (int j = 0; j < G1.V[key].children.length; j++) {
                int key1 = G1.V[key].children[j].data;
                for (int l = 0; l < G1.V.length; l++){
                    if (G1.V[l].data == key1){
                        key1 = l;
                        break;
                    }
                }
                int value1 = map.mapKey(key1);

                value1 = G2.V[value1].data;

                boolean flag = false;

                for (int k = 0; k < G2.V[value].children.length; k++) {
                    if (G2.V[value].children[k].data == value1) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Error. An edge was not mapped! ");
                    return true;
                }
            }
        }
        return false;
    }

}

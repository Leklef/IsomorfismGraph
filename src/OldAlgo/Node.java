package OldAlgo;

/**
 * Created by lenar on 30.04.17.
 */
public class Node {

    int data;
    Node[] children;

    public Node() {}

    public Node(int data) {
        this.data = data;
    }

    public void addChildren(Node[] c) {
        this.children = c;
    }

}

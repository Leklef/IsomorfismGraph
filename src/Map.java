/**
 * Created by lenar on 30.04.17.
 */
public class Map {
    int lenght;
    int[][] map;

    public Map(int n){
        lenght = 0;
        map = new int[n][2];
    }

    public void add(int i, int key, int value){
        map[i][0] = key;
        map[i][1] = value;
        lenght++;
    }

    public int getKey(int i){ return map[i][0]; }

    public int getValue(int i){ return map[i][1]; }

    public int mapKey(int key) {
        for (int i = 0; i < lenght; i++)
            if (map[i][0] == key)
                return map[i][1];
        return -1;
    }

    public void pop() { lenght--; }
}

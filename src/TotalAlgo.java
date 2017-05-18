import java.util.Arrays;

/**
 * Created by lenar on 18.05.17.
 */
public class TotalAlgo {

    private int[][] m1;
    private int[][] m2;

    private boolean[] find;

    private boolean[] isVisited;

    public TotalAlgo(int[][] m1, int[][] m2) {
        this.m1 = m1;
        this.m2 = m2;
        find = new boolean[m1.length];
        isVisited = new boolean[m1.length];
    }

    public int[] shift(int arr[]) {
        int b[] = new int[arr.length];
        b[0] = arr[arr.length - 1];
        System.arraycopy(arr, 0, b, 1, arr.length - 1);
        return b;
    }

    public boolean isEqualArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++)
            if (arr1[i] != arr2[i]) {
                return false;
            }
        return true;
    }

    public boolean isIsomorphic() {
        this.backtrack();
        for (int k = 0; k < isVisited.length; k++) {
            if (!find[k]) {
                return false;
            }
        }

        return true;
    }

    public void backtrack() {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                for (int z = 0; z < m1.length; z++) {
                    if (isVisited[j] && !find[i]) {}
                    else {
                        m2[j] = shift(m2[j]);
                        if (isEqualArray(m1[i],m2[j])) {
                            find[i] = true;
                            isVisited[j] = true;
                        }
                    }
                }
            }
        }
//        if (isVisited[j] && !find[i]) {
//            ++j;
//        }
//        for (int k=0; k < m1.length; k++) {
//            m2[j] = shift(m2[j]);
//            if (isEqualArray(m1[i], m2[j])) {
//                find[i] = true;
//                isVisited[j] = true;
//                k=0;
//                j=0;
//                ++i;
////                backtrack(i++, 0);
//            }
//        }
    }
}

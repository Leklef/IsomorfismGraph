import java.util.BitSet;

/**
 * Created by lenar on 30.04.17.
 */
public class BitMatrix {

    BitSet[] matrix;

    public BitMatrix() {}

    public BitMatrix(int n) {
        this.matrix = new BitSet[n];
        for (int i = 0; i < n; i++)
            this.matrix[i] = new BitSet(n);
    }

    public void setBit(int i, int j, boolean value) { this.matrix[i].set(j, value); }

    public boolean getBit(int i, int j) { return this.matrix[i].get(j); }

    public int getSize() { return this.matrix.length; }

    public static BitMatrix transpose(BitMatrix matrix) {
        int n = matrix.getSize();
        BitMatrix transpose = new BitMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                transpose.setBit(j, i, true);
        return transpose;
    }

}

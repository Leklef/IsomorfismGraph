import java.util.Random;

/**
 * Created by lenar on 30.04.17.
 */
public class PermMatrix extends BitMatrix {

    public static BitMatrix makeRandom(int n){
        BitMatrix matrix= new BitMatrix(n);
        Random random= new Random();
        int[] list= new int[n];

        for (int i= 0; i < n; i++)
            list[i]= i;
        for (int i= 0; i < n; i++){
            int index= random.nextInt(n-i)+i;
            int temp= list[index];
            list[index]= list[i];
            list[i]= temp;
        }
        for (int i= 0; i < n; i++)
            matrix.setBit(i, list[i], true);

        return matrix;
    }

    public static BitMatrix multiply(BitMatrix A, BitMatrix B){
        int n= B.getSize();
        BitMatrix product= new BitMatrix(n);

        for (int i= 0; i < n; i++)
            for (int j= 0; j < n; j++)
                for (int k= 0; k < n; k++)
                    if (A.getBit(i, k) && B.getBit(k, j)){
                        product.setBit(i, j, true);
                        break;
                    }
        return product;
    }
}

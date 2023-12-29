import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int c, N = 0, tmp, nN;
        while ((c = System.in.read()) > 32) N = (N << 3) + (N << 1) + (c & 15);


        for (int M = 1; M < N; M++) {
            nN = tmp = M;
            while(tmp > 0){
                nN += (tmp % 10);
                tmp /= 10;
            }
            if(nN == N){
                System.out.println(M);
                System.exit(0);
            }
        }
        System.out.println(0);
    }
}
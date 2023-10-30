import java.io.*;

public class Main {
    /*  DP - 피보나치
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a1 = 1, a2 = 2;

        if (N == 1) System.out.println(a1);
        else if (N == 2) System.out.println(a2);
        else {
            int an;
            for (int i = 3; i <= N; i++) {
                an = (a1 + a2) % 15746;
                a1 = a2;
                a2 = an;
            }
            System.out.println(a2);
        }
    }
}
import java.io.*;

public class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        int[] ans = new int[16];

        for (int i = 0; i < 8; i++) {
            ans[2 * i] = A[i] & 15;
            ans[2 * i + 1] = B[i] & 15;
        }

        for (int i = 15; i >= 2; i--) {
            for (int j = 0; j < i; j++) {
                ans[j] = (ans[j] + ans[j + 1]) % 10;
            }
        }

        System.out.println(ans[0]+""+ans[1]);
    }
}

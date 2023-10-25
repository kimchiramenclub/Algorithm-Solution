import java.io.*;
import java.util.*;

class Main {
    /*
     **/
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] sum = new int[B + 1];
        int idx = 1;
        Loop:
        for (int i = 1; ; i++) {
            for (int j = 1; j <= i; j++) {
                sum[idx] = sum[idx - 1] + i;
                if (idx++ == B) break Loop;
            }
        }
        System.out.println(sum[B] - sum[A - 1]);
    }
}
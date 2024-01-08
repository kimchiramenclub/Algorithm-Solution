import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[] notPrime = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            if (!notPrime[i]) {
                if (i >= M) sb.append(i).append('\n');
                int multiple = i;
                while ((multiple += i) <= N) {
                    notPrime[multiple] = true;
                }
            }
        }

        System.out.print(sb);
    }
}
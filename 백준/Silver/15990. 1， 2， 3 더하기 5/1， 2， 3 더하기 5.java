import java.io.IOException;

public class Main {
    /*
        DP
        - 마지막이 1, 2, 3 인 경우로 숫자를 나눠가며 부분적으로 풀이
        ex) 5 -> 1 + 4 -> 1 + 3 + 1
        나눌 때, 1인 경우 2 or 3만 올 수 있게, 2인 경우 1 or 3만 올 수 있게 하는 식으로
    */

    static final int MOD = 1_000_000_009;
    static int[][] dp = new int[100_001][3];
    static int max = 3;

    static int DP(int N) {
        if (N > max) {
            for (int i = max + 1; i <= N; i++) {
                dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % MOD; // 마지막이 1인 케이스
                dp[i][1] = (dp[i - 2][0] + dp[i - 2][2]) % MOD; // 마지막이 2인 케이스
                dp[i][2] = (dp[i - 3][0] + dp[i - 3][1]) % MOD; // 마지막이 3인 케이스
            }
            max = N;
        }

        int sum = 0;
        for(int val : dp[N]) sum = (sum + val) % MOD;
        return sum;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        dp[1] = new int[]{1, 0, 0}; // 마지막이 1인 케이스 : 1 = 1
        dp[2] = new int[]{0, 1, 0}; // 마지막이 2인 케이스 : 2 = 2
        dp[3] = new int[]{1, 1, 1}; // 마지막이 1,2,3인 케이스 : 3 = 1 + 2, 2 + 1, 3

        while (T-- > 0) {
            sb.append(DP(readInt())).append('\n');
        }
        System.out.print(sb);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
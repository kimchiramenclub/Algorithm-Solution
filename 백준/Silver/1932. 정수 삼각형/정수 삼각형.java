import java.io.IOException;

class Main {
            /*  DP
                - 삼각형의 밑단부터 시작해서, [i][j] vs [i][j+1] 중 max 값이 [i-1][j]에 더해지게 함.
            */

    static int[][] dp;

    static int solution(int N) {

        // 삼각형의 밑단부터 dp
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j <= i-1; j++) {
                dp[i-1][j] += Math.max(dp[i][j], dp[i][j+1]);
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) throws IOException {
        int N = readInt();
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) dp[i][j] = readInt();
        }
        
        System.out.println(solution(N));
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

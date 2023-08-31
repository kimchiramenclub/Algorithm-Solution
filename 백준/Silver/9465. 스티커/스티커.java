import java.io.*;
import java.util.*;

class Main {
    /*  dp
        - 스티커를 1번째 열부터 순서대로 뽑는 거라고 가정
        - 갯수를 '최대'로 선택하는 방법은 대각선으로 움직이면서 뽑는 것.
            ㄴ 선택지는 4가지가 있음. 1행,2행, 1열 + 건너뛰고 1행,2행
            ㄴ 건너뛰기는 대각선 통해서 오는 선택지보다 그게 나을때만 가능.
            ㄴ i열 1행(or 2행)으로 건너뛰는 기준? : i-2열 2행까지 총합 > i-1열 2행까지 총합
               (i-2열 1행 총합은 신경 쓸 필요가 없음. 그게 크더라도 대각선 숫자를 더하면서 오면 되기 때문)
               즉, 대각선 규칙을 깰만큼 그 성과가 클 때만 가능.
     */


    static int solution(int N, int[][] tc) {
        if(N == 1) return Math.max(tc[0][0], tc[0][1]);

        int[][] dp = new int[N][2];

        // 초기값 대입
        dp[0][0] = tc[0][0];    dp[0][1] = tc[0][1];
        dp[1][0] = dp[0][1] + tc[1][0];
        dp[1][1] = dp[0][0] + tc[1][1];

        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]) + tc[i][0];
            dp[i][1] = Math.max(dp[i-1][0], dp[i-2][0]) + tc[i][1];
        }

        return Math.max(dp[N-1][0], dp[N-1][1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] tc = new int[N][2];

            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) tc[k][j] = Integer.parseInt(st.nextToken());
            }
            sb.append(solution(N, tc)).append("\n");
        }

        System.out.println(sb);
    }
}
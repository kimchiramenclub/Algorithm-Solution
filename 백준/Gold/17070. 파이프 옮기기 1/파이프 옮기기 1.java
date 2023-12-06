import java.io.*;
import java.util.*;

public class Main {
    /*
            그래프탐색, DP
            - 민 후에만 회전 가능. 즉, 벽 체크를 먼저 하고(방향에 따라), 이후에 move(회전 포함)
            - 파이프 방향에 따라 번호 부여 (가로 : 0, 대각선 : 1, 세로 : 2)

            문제 : BFS 방식을 쓰면 중복도 생기고, queue의 node가 지수로 증가해서 시간초과
            수정 : DP 방식으로 변경
                - x = 1, y = 1 ~ N으로 진행하면서 dp 저장
                  이걸 x = 1 ~ N까지 반복하면서, x=N, y=N 칸으로 이동
     */

    static int dp(int N, int[][] graph) {
        int[][][] dp = new int[N + 2][N + 2][3];

        // 초기값
        dp[1][2][0] = 1;

        for (int x = 1; x <= N; x++) {
            for (int y = 3; y <= N; y++) {
                if (graph[x][y] == 0) {

                    // 가로 배치
                    dp[x][y][0] = dp[x][y - 1][0] + dp[x][y - 1][1];
                    // 대각선 배치
                    if (graph[x - 1][y] == 0 && graph[x][y - 1] == 0) {
                        dp[x][y][1] = dp[x - 1][y - 1][0] + dp[x - 1][y - 1][2] + dp[x - 1][y - 1][1];
                    }
                    // 세로 배치
                    dp[x][y][2] = dp[x - 1][y][2] + dp[x - 1][y][1];
                }
            }
        }

        return (dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();

        int[][] graph = new int[N + 2][N + 2];  // True면 벽이 아닌 그래프
        fillEdge(N, graph);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = readInt();
            }
        }

        System.out.println(dp(N, graph));
    }

    static void fillEdge(int N, int[][] graph) {
        Arrays.fill(graph[0], 2);
        Arrays.fill(graph[N + 1], 2);
        for (int i = 0; i <= N + 1; i++) graph[i][0] = graph[i][N + 1] = 2;
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
        }
        return buf[idx++];
    }
}
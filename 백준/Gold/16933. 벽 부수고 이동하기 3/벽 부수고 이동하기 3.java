import java.io.*;
import java.util.*;

class Main {
    /*       BFS
            - visited 배열을 1개만 쓰면, 벽을 부수고 이동한 애가 먼저 다른 길을 이동해버리고, 마지막 벽을 못 뚫고 -1이 나올 수 있음.
            그러므로 벽을 뚫기 전엔 0, 벽을 1번이라도 뚫은 후에는 1을 쓰는 3차원 visited 배열을 만들어야함.
            - 벽을 K번까지 뚫을 수 있음. visited 3차원 배열을 [2] -> [K+1]로 늘릴 수 있지만, 개선 가능성? 가지치기?
            - visited[1000][1000][10] 이것보다, visited[10][1000][1000] 이렇게 두는 게 시간, 공간 상 효율적이다!

            낮,밤 개념 + 제자리 이동
            - 낮,밤은 Point class에 boolean isDay를 추가해서 체크
            - 제자리 머무르기는

            수정
            - https://www.acmicpc.net/source/48015586 참고
            - visited 배열을 int로 해서 벽 부순 횟수를 저장하고, 값 체크를 통해 가지치기를 실행하도록
            - Point class 대신 int[] 사용

            문제
            - 벽 있는 지 체크 조건이 좀 이상함. 다 통로더라도 이미 다 방문했으면 벽으로 취급.. 흠
    * */

    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean hasAdjacentWall;

    static int findPath(int N, int M, int K) {
        Queue<int[]> move = new ArrayDeque<>();
        move.offer(new int[]{0, 0, 0, 1, 1}); // 시작점 삽입.   {Row, Column, K, Day/Night , Count}
        map[0][0] = 0;

        while (!move.isEmpty()) {
            int[] P = move.poll();
            P[3] = (P[3] == 1) ? 0 : 1; // 낮/밤 전환
            hasAdjacentWall = false; // '밤'일 때 주변에 벽 있어서 제자리 이동이 필요할 지 체크용

            // 도착지에 도착했다면, 이동한 길이 return
            if (P[0] == N - 1 && P[1] == M - 1) return P[4];

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nX = P[0] + dx[i];
                int nY = P[1] + dy[i];

                // 재방문 X, map 안에서 이동하도록
                // 이동 경우의 수 가지치기. 더 적게 벽을 부수고 이동한 기록이 있다면, pass
                if (nX >= 0 && nX <= N - 1 && nY >= 0 && nY <= M - 1) {
                    if (map[nX][nY] > 0 && P[2] < map[nX][nY]) { // 통로가 뚫려있다면 이동
                        move.offer(new int[]{nX, nY, P[2], P[3], P[4] + 1});
                        map[nX][nY] = P[2];
                        continue;
                    }

                    hasAdjacentWall = true;

                    if (P[3] == 0 && P[2] < K && -P[2] > map[nX][nY]) { // 벽이 막혔지만 '낮'이고, 뚫을 기회가 있다면 뚫기
                        move.offer(new int[]{nX, nY, P[2] + 1, P[3], P[4] + 1});
                        map[nX][nY] = -P[2];
                    }
                }
            }

            // 제자리 이동
            if (P[3] == 1 && hasAdjacentWall) { // 밤인 경우에만 제자리 이동 가능 (낮에 벽 부수고 이동 위해서)
                move.offer(new int[]{P[0], P[1], P[2], P[3], P[4] + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int K = readInt();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) map[i][j] = isWall();    // 벽이면 -2000001, 뚫린 길이면 2000001
        }

        System.out.println(findPath(N, M, K));
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 벽이면 음수, 아니면 양수 리턴
    static int isWall() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32) ;
        return c == '1' ? -2000001 : 2000001;
    }
}
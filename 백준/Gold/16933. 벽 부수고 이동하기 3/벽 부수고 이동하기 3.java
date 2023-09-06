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
    * */

    static boolean[][] map;
    static boolean[][][] visited;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean hasAdjacentWall;

    static int findPath(int N, int M, int K) {
        Queue<Point> move = new ArrayDeque<>();
        move.offer(new Point(0, 0, 0, 1, false)); // 시작점 삽입
        visited[0][0][0] = true; // 시작점 방문처리

        while (!move.isEmpty()) {
            Point P = move.poll();
            P.toggleDay();  // 낮/밤 전환
            hasAdjacentWall = false; // '밤'일 때 주변에 벽 있어서 제자리 이동이 필요할 지 체크용

            // 도착지에 도착했다면, 이동한 길이 return
            if (P.row == N - 1 && P.col == M - 1) return P.cnt;

            // 상하좌우 이동
            for (int[] dir : direction) {
                int nX = P.row + dir[0];
                int nY = P.col + dir[1];

                // 재방문 X, map 안에서 이동하도록
                // 이동 경우의 수 가지치기. 더 적게 벽을 부수고 이동한 기록이 있다면, pass
                if ((nX >= 0 && nX <= N - 1 && nY >= 0 && nY <= M - 1) && !hasBetterPath(nX, nY, P.broke)) {
                    if (map[nX][nY]) { // 통로가 뚫려있다면 이동
                        move.offer(new Point(nX, nY, P.broke, P.cnt + 1, P.isDay));
                        visited[P.broke][nX][nY] = true;
                        continue;
                    }

                    hasAdjacentWall = true;

                    if (P.isDay && P.broke < K) { // 벽이 막혔지만 '낮'이고, 뚫을 기회가 있다면 뚫기
                        move.offer(new Point(nX, nY, P.broke + 1, P.cnt + 1, true));
                        visited[P.broke + 1][nX][nY] = true;
                    }
                }
            }

            // 제자리 이동
            if (!P.isDay && hasAdjacentWall) { // 밤인 경우에만 제자리 이동 가능 (낮에 벽 부수고 이동 위해서)
                move.offer(new Point(P.row, P.col, P.broke, P.cnt + 1, false));
            }
        }

        return -1;
    }

    // 더 적게 벽을 부수고 이동한 기록이 있으면 true 리턴  , 현재가 가장 최적의 이동이면 false 리턴
    static boolean hasBetterPath(int X, int Y, int brokeWallCount){
        for(int i =0; i<= brokeWallCount; i++){
            if(visited[i][X][Y]) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int K = readInt();

        map = new boolean[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) map[i][j] = !isWall();    // 벽이면 false, 뚫린 길이면 true
        }

        System.out.println(findPath(N, M, K));
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 벽이면 true, 아니면 false 리턴
    static boolean isWall() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32) ;
        return c == '1';
    }
}

// 이동 관련 상태를 저장하는 class
class Point {
    int row, col, broke, cnt;
    boolean isDay = false;

    Point(int row, int col, int broke, int cnt, boolean isDay) {
        this.row = row;     // 행
        this.col = col;     // 열
        this.broke = broke; // 벽 부순 횟수
        this.cnt = cnt;     // 이동한 횟수
        this.isDay = isDay; // 낮 / 밤
    }

    void toggleDay() {
        this.isDay = !this.isDay;
    }
}
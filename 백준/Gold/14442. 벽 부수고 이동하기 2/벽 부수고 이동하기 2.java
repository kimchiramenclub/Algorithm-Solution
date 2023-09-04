import java.io.IOException;
import java.util.*;

class Main {
    /*       BFS
            - visited 배열을 1개만 쓰면, 벽을 부수고 이동한 애가 먼저 다른 길을 이동해버리고, 마지막 벽을 못 뚫고 -1이 나올 수 있음.
            그러므로 벽을 뚫기 전엔 0, 벽을 1번이라도 뚫은 후에는 1을 쓰는 3차원 visited 배열을 만들어야함.

            - 벽을 K번까지 뚫을 수 있음. visited 3차원 배열을 [2] -> [K+1]로 늘릴 수 있지만, 개선 가능성? 가지치기?
            - visited[1000][1000][10] 이것보다, visited[10][1000][1000] 이렇게 두는 게 시간, 공간 상 효율적이다!
    * */

    static boolean[][] map;
    static boolean[][][] visited;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static int findPath(int N, int M, int K) {
        Queue<Point> move = new ArrayDeque<>();
        move.offer(new Point(0,0,0,1)); // 시작점 삽입
        visited[0][0][0] = true; // 시작점 방문처리

        while (!move.isEmpty()) {
            Point P = move.poll();

            // 도착지에 도착했다면, 이동한 길이 return
            if (P.row == N-1 && P.col == M-1) return P.cnt;

            for (int[] dir : direction) {
                int nX = P.row + dir[0];
                int nY = P.col + dir[1];

                // 재방문 X, map 안에서 이동하도록
                if((nX >= 0 && nX <= N-1 && nY >= 0 && nY <= M-1) && !visited[P.broke][nX][nY]){
                    if(map[nX][nY]) {
                        move.offer(new Point(nX, nY, P.broke, P.cnt + 1)); // 통로가 뚫려있다면 이동
                        visited[P.broke][nX][nY] = true;
                    }
                    else if(P.broke < K) {
                        move.offer(new Point(nX, nY, P.broke+1, P.cnt + 1)); // 벽이 막혔지만 뚫을 기회가 있다면 뚫기
                        visited[P.broke][nX][nY] = true;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int K = readInt();

        map = new boolean[N][M];
        visited = new boolean[K+1][N][M];

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

class Point{
    int row, col, broke, cnt;

    Point(int row, int col, int broke, int cnt){
        this.row = row;
        this.col = col;
        this.broke = broke; // 벽 부순 횟수
        this.cnt = cnt;
    }
}

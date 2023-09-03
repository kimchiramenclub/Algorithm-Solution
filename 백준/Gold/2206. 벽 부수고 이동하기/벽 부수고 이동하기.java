import java.io.IOException;
import java.util.*;

class Main {
    /*       BFS
            - visited 배열을 1개만 쓰면, 벽을 부수고 이동한 애가 먼저 다른 길을 이동해버리고, 마지막 벽을 못 뚫고 -1이 나올 수 있음.
            그러므로 벽을 뚫기 전엔 0, 벽을 1번이라도 뚫은 후에는 1을 쓰는 3차원 visited 배열을 만들어야함.
    * */
    
    static boolean[][] map;
    static boolean[][][] visited;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static int findPath(int N, int M) {
        Queue<Point> move = new ArrayDeque<>();
        move.offer(new Point(1,1,0,1)); // 시작점 삽입
        visited[1][1][0] = true; // 시작점 방문처리

        while (!move.isEmpty()) {
            Point P = move.poll();

            // 도착지에 도착했다면, 이동한 길이 return
            if (P.row == N && P.col == M) return P.cnt;

            for (int[] dir : direction) {
                int nX = P.row + dir[0];
                int nY = P.col + dir[1];

                // 재방문 X, map 안에서 이동하도록
                if(!visited[nX][nY][P.broke] && (nX >= 1 && nX <= N && nY >= 1 && nY <= M)){
                    if(map[nX][nY]) {
                        move.offer(new Point(nX, nY, P.broke, P.cnt + 1)); // 통로가 뚫려있다면 이동
                        visited[nX][nY][P.broke] = true;
                    }
                    else if(P.broke == 0) {
                        move.offer(new Point(nX, nY, 1, P.cnt + 1)); // 벽이 막혔지만 뚫을 기회가 있다면 뚫기
                        visited[nX][nY][1] = true;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        map = new boolean[N + 2][M + 2];
        visited = new boolean[N + 2][M + 2][2];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) map[i][j] = !isWall();    // 벽이면 false, 뚫린 길이면 true
        }

        System.out.println(findPath(N, M));
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

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
import java.io.*;
import java.util.*;

public class Main {

    /* 그래프 탐색
        - 1 일 경우에만 값 갱신. 0일 때는 pass, >1 일 때도 pass(방문한 곳)
        - 방문하지 못한 곳은 마지막에 이중 for문 돌려서 -1로? 정말로 거리가 1인 부분은 pass 해야함.

        문제
        - 최초에 이동한 거리는 1이라서, 값이 1(미방문)일 때만 방문하는 로직에 걸려버림.
        수정
        - 그냥 visited를 쓰자...

    */
    public static int[][] map;
    public static boolean[][] visited;

    public static void solution(int N, int M, int[] node) {
        bfs(N, M, node);
        // 도달 못한 곳 -1 처리
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                if (map[y][x] == 1 && !visited[y][x]) map[y][x] = -1;
            }
        }
    }

    public static void bfs(int N, int M, int[] node) {
        map[node[0]][node[1]] = 0; // 최초 좌표(2)

        Queue<int[]> move = new LinkedList<>();
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        move.offer(node);

        do {
            int[] coord = move.poll();

            // 상하좌우 이동
            for (int[] dir : direction) {
                int y = coord[0] + dir[0];
                int x = coord[1] + dir[1];

                // 방문 여부 체크
                if (map[y][x] == 1 && !visited[y][x]) {
                    visited[y][x] = true;
                    // 방문하지 않은 땅(1)이면 방문하고, 거리를 1 늘려줌.
                    map[y][x] = map[coord[0]][coord[1]] + 1;
                    move.offer(new int[]{y, x});
                }
            }
        } while (!move.isEmpty());

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2]; // boundary를 0으로 감싸기
        visited = new boolean[N + 2][M + 2];
        int[] node = new int[2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    node[0] = i;
                    node[1] = j;
                }
            }
        }

        solution(N, M, node);
        // 출력부
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) System.out.print(map[y][x] + " ");
            System.out.println();
        }

    }
}
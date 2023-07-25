import java.io.*;
import java.util.*;

class Main {
    /*  그래프 탐색
        - 벽 조건을 체크하며 상하좌우 이동
        - 방문했거나 벽을 만나면 return
        - 한 블록을 다 방문했다면, count++
        - int[] 큐를 사용해서 색약, 정상 사람 방문 동시 처리 [y, x, p] - p : 적약 여부. 정상 - 1, 적약 - 0
    * */

    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] solution(int N, char[][] colors, boolean[][] nVisited, boolean[][] wVisited) {
        return bfs(N, colors, nVisited, wVisited);
    }

    public static int[] bfs(int N, char[][] colors, boolean[][] nVisited, boolean[][] wVisited) {
        Queue<int[]> area = new LinkedList<>();

        int nC = 0; // 정상인 구역 갯수
        int wC = 0; // 색약인 구역 갯수
        char nColor = '\u0000'; // 정상인의 체크 중인 구역 색
        char wColor = '\u0000'; // 색약인의 체크 중인 구역 색

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 정상인 새로운 구역 방문
                if (!nVisited[i][j]) {
                    nColor = colors[i][j];
                    area.offer(new int[]{i, j, 1});
                    nVisited[i][j] = true;
                    nC++;
                }
                // 색약인 새로운 구역 방문
                if (!wVisited[i][j]) {
                    wColor = colors[i][j];
                    area.offer(new int[]{i, j, 0});
                    wVisited[i][j] = true;
                    wC++;
                }
                while (!area.isEmpty()) {
                    int[] C = area.poll(); // 구역 검색 시작 위치

                    // 상하좌우 체크
                    for (int[] dir : direction) {
                        int y = C[0] + dir[0];
                        int x = C[1] + dir[1];
                        // 정상인일 때
                        if (C[2] == 1 && !nVisited[y][x] && nColor == colors[y][x]) {
                            area.offer(new int[]{y, x, 1});
                            nVisited[y][x] = true;
                        }
                        // 색약인일 때
                        else if(C[2] == 0 && !wVisited[y][x]){
                            //적+녹색 구역
                            boolean isRoG = (wColor == 'R' || wColor == 'G') && (colors[y][x] == 'R' || colors[y][x] == 'G');
                            // 청색 구역
                            boolean isB = wColor == colors[y][x];
                            // 적색+녹색 구역  아니면 청색 구역이 이어질 때
                            if(isRoG || isB){
                                area.offer(new int[]{y,x,0});
                                wVisited[y][x] = true;
                            }
                        }
                    }
                }
            }
        }
        return new int[]{nC, wC};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        char[][] colors = new char[N + 2][N + 2]; // 벽 보정
        boolean[][] nVisited = new boolean[N + 2][N + 2];
        boolean[][] wVisited = new boolean[N + 2][N + 2];

        for (int i = 1; i <= N; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= N; j++) colors[i][j] = tmp.charAt(j - 1); // charAt의 index가 0부터 시작이므로 보정
        }

        int[] ans = solution(N, colors, nVisited, wVisited);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
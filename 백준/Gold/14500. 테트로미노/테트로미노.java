import java.io.*;
import java.util.*;

class Main {
    /*  브루트포스
        - O(n * m(각 테트로미노 모델))
        - ㅗ 모형 빼고는 다 4칸 이동한 모양이라 DFS 모델로 커버 가능.
        - BFS는 안 되는 이유가 , 상하좌우로 다 이동하게 하면 갔다가 다시 돌아오는 방문을 체크하기 힘듬.
        - ㅗ 모형은 십자가 모형으로 체크. 십자가 4칸이 다 채워지면 4칸 중 3칸의 값을 체크, 3칸만 채워지면 바로 total 체크
            3칸 이상 채워지지 않으면 check X

        문제 : 첫번째 테스트케이스 값이 계속 20이 나옴
        수정 : 방문한 좌표를 프린트해가며 디버깅 한 결과, 맨 처음 시작 좌표를 방문 처리 안해서 그런게 밝혀짐.
                시작 좌표 방문 처리 코드 추가
    */

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited; // DFS로 도형을 그릴 때 방문한 칸을 재방문하지 않게 방지
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] cross = new int[4]; // ㅗ 모양 체크 용 배열
    static int answer = 0;

    static void solution(int N, int M, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // ㅗ 모양 제외한 테트로미노 모델
                visited[i][j] = true; // 시작좌표 방문처리
                checkDFSModel(i, j, 1, map[i][j]);
                visited[i][j] = false;
                // ㅗ 모양
                checkCrossModel(i, j, map[i][j]);
            }
        }
    }

    static void checkDFSModel(int x, int y, int move, int total) {
        // 탈출 조건
        if (move >= 4) {
            answer = Math.max(answer, total);
            return;
        }

        for (int[] dir : direction) {
            int nX = x + dir[0];
            int nY = y + dir[1];
            // 경계 조건을 만족하면, 테트로미노 도형을 만들기 위해 다음 칸 이동
            if (nX >= 0 && nX < N && nY >= 0 && nY < M && !visited[nX][nY]) {
                visited[nX][nY] = true; // 방문 처리
                checkDFSModel(nX, nY, move + 1, (total + map[nX][nY]));
                visited[nX][nY] = false; // 다른 모양의 테트로미노에 겹치지 않게
            }
        }
    }

    static void checkCrossModel(int x, int y, int total) {
        Arrays.fill(cross, 0); // 배열 초기화
        int nX = 0;
        int nY = 0;
        int filled = 0;
        int tot = total;

        for (int i = 0; i < 4; i++) {
            nX = x + direction[i][0];
            nY = y + direction[i][1];
            // 경계조건을 만족하면, cross 배열에 값 대입
            if (nX >= 0 && nX < N && nY >= 0 && nY < M) {
                cross[i] = map[nX][nY];
                filled++; // 값이 있으면 + 1
               tot += cross[i];
            }
        }

        // ㅗ 모양이 딱 만들어진다면
        if (filled == 3) answer = Math.max(answer, tot);
            // 십자가 모양이라면, 상하좌우로 돌아가며 한 칸씩 제외하고 ㅗ 모양 값을 체크
        else if (filled == 4) {
            for (int i = 0; i < 4; i++) answer = Math.max(answer, tot - cross[i]);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(N, M, map);
        System.out.println(answer);
    }
}
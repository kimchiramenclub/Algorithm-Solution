import java.io.*;
import java.util.*;

public class Main {

    /* DFS, BFS 문제

       - 익은 토마토들 주변 상하좌우로 매 턴마다 방문
       - 안 익었다 - 방문 x | 익었다 - 방문 o
       - -1은 벽 처리

       1. 맨 처음 토마토 익은 위치들부터 큐에 넣고 시작
       2. 날짜 별로 큐에 넣은 위치들을 count하고, 다음 날엔 그만큼 poll 해가면서 또 큐에 넣음
       3. 큐에 들어간 게 없는 날이 종료 날.
       4. 다 익지 못했는 지를 총 count로 체크

       수정 : 메모리, 시간 더 효율적이게 수정해보기

     */

    public static byte[][][] tomato;
    // 메모리 사용량 줄이기 위해 ArrayDeque 사용, primitive int 배열 사용
    public static Queue<int[]> ripe = new ArrayDeque<>();

    public static int solution(int ripedMax) throws IOException {
        return bfs(ripedMax);
    }

    public static int bfs(int ripedMax) {
        int date = 0; // 날짜
        int riped = ripe.size(); // 총 익은 토마토 갯수

        int[][] direction = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0},{0, 0, -1},{0, 0, 1}};

        do{
            int count = ripe.size(); // 전 날 익은 토마토 갯수

            // 전 날 익은 토마토들이 전파
            for(int i=0;i<count;i++){
                int[] T =  ripe.poll(); // 익은 토마토 위치
                // 상하좌우 전파
                for(int[] dir : direction){
                    int x = T[0] + dir[0];
                    int y = T[1] + dir[1];
                    int z = T[2] + dir[2];
                    if(tomato[x][y][z] == (byte)0){
                        ripe.offer(new int[]{x, y, z});
                        tomato[x][y][z] = (byte)1; //방문처리
                        riped++;
                    }
                }
            }

            if(ripe.size() > 0) date++; //익은 토마토가 하나라도 있다면 다음 날짜로
            else {
                if(riped == ripedMax) return date; // 모든 토마토가 익었다면 날짜 return
                else return -1; // 익지 않은 토마토가 있다면 -1 return
            }
        }while(true);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        tomato = new byte[N + 2][M + 2][H + 2]; // 창고를 벽으로 두르기
        int wallCount = 0; // 토마토가 없는 위치 갯수
        for (byte[][] array2D : tomato) for (byte[] array1D : array2D) Arrays.fill(array1D, (byte) -1);


        // 토마토 창고를 벽으로 두르고, 토마토 채우기
        for(int h = 1; h <= H; h++) {
            for (int n = 1; n <= N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 1; m <= M; m++) {
                    tomato[n][m][h] = Byte.parseByte(st.nextToken());
                    // 최초 익은 토마토 위치 입력
                    if (tomato[n][m][h] == (byte) 1) ripe.offer(new int[]{n, m, h});
                    else if (tomato[n][m][h] == (byte) -1) wallCount++;
                }
            }
        }

        System.out.println(solution(N*M*H - wallCount));
    }
}
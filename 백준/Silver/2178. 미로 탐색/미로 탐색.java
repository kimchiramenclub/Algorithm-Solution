import java.io.*;
import java.util.*;

class Main {
    /*  BFS
        - 이동 횟수 정보도 배열에 포함시켜서 출력
        - 항상 도착 가능
        - 방문 배열 필요 X
    */

    static int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};

    static int solution(int N, int M, boolean[][] maze){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1,1,1});
        maze[1][1] = false; // 방문처리

        while(!queue.isEmpty()){
            int[] Q = queue.poll();
            // 미로 탈출 조건
            if(Q[0] == N && Q[1] == M) return Q[2];

            for(int[] dir : direction){
                int nX = Q[0] + dir[0];
                int nY = Q[1] + dir[1];
                if(maze[nX][nY]){
                    queue.offer(new int[]{nX,nY,Q[2]+1});
                    maze[nX][nY] = false; // or, maze[nX][nY] = !maze[nX][nY];
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] maze = new boolean[N+2][M+2];

        for(int i=1;i<=N;i++){
            String tmp = br.readLine();
            for(int j=1;j<=M;j++){
                maze[i][j] = tmp.charAt(j - 1) == '1'; // 1이면 true, 0이면 false
            }
        }

        System.out.println(solution(N, M, maze));
    }
}
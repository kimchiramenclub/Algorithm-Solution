import java.io.*;
import java.util.*;

class Main {
    /*  그래프 탐색
        - BFS + Priority Queue
        - visited 배열 필요 없을듯?
    */

    static PriorityQueue<Integer> houseNum = new PriorityQueue<>(); // 단지 아파트 갯수 정렬해서 출력하는 큐
    static Queue<int[]> house = new ArrayDeque<>(); // 단지 내 집 좌표
    static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방향 이동

    static void solution(int N, boolean[][] map){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(map[i][j]){
                    map[i][j] = false; // 방문처리
                    house.offer(new int[]{i,j});
                    int count = 1; // 단지 내 아파트 갯수

                    // BFS
                    while(!house.isEmpty()){
                        int[] H = house.poll();
                        // 4방향 이동
                        for(int[] dir : direction){
                            int nX = H[0]+dir[0];
                            int nY = H[1]+dir[1];
                            if(map[nX][nY]) { // 만약 상하좌우에 집이 있다면
                                map[nX][nY] = false; // 방문처리
                                house.offer(new int[]{nX,nY});
                                count++;
                            }
                        }
                    }
                    houseNum.offer(count);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N+2][N+2];

        for(int i=1;i<=N;i++){
            String tmp = br.readLine();
            for(int j=1;j<=N;j++){
                if(tmp.charAt(j-1) == '1') map[i][j] = true; // 집이 있는 경우, true로
            }
        }
        solution(N, map);

        // 우선순위
        bw.write(houseNum.size()+"\n");
        while(!houseNum.isEmpty()) {
            bw.write(houseNum.poll()+"\n");
        }
        bw.flush();
    }
}
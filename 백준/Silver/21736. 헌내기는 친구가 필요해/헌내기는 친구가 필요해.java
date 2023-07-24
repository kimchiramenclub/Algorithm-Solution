import java.io.*;
import java.util.*;

public class Main {
    /*  그래프 탐색
        - 벽 조건을 체크하며 상하좌우 이동
        - P를 찾으면 count++
        - 방문했거나 벽을 만나면 return (방문할 때 X로 바꿔서 다시 안 가도록)
    * */
    public static int count = 0;
    public static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};

    public static int solution(char[][] campus, int[] index){
        dfs(campus, index);
        return count;
    }

    public static void dfs(char[][] campus, int[] index){
        // 방문 체크 + P 체크
        int y = index[0];
        int x = index[1];
        if(campus[y][x] == 'P') count++;
        else if(campus[y][x] == 'X') return;
        campus[y][x] = 'X';

        for(int[] dir : direction){
            dfs(campus, new int[]{y+dir[0],x+dir[1]}); // 상하좌우 방문
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] campus = new char[N+2][M+2]; // 벽 처리를 위해 +2
        for(char[] c : campus) Arrays.fill(c, 'X'); // 벽을 'X'로 채워 방문 처리
        int[] index = new int[2];


        for(int i=1;i<=N;i++){
            String tmp = br.readLine();
            for(int j=1;j<=M;j++) {
                campus[i][j] = tmp.charAt(j-1); // 입력문은 charAt의 index가 0부터 시작이므로 보정
                if(campus[i][j] == 'I') index = new int[]{i,j};
            }
        }

        int ans = solution(campus, index);
        if(ans == 0) System.out.println("TT");
        else System.out.println(ans);
    }
}
import java.io.*;
import java.util.Arrays;

public class Main {
    /*  구현
        - 거꾸로 채우는 게 구현이 빠름
        - 테두리 만날 때마다 방향전환
    * */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder Point = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] table = new int[N + 2][N + 2];
        fillBorder(N, table);

        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int x = 1, y = 1;
        int move = 0;
        
        for (int i = N * N; i > 0; i--) {
            table[x][y] = i;

            if(i == K) Point.append(x+" "+y);
            if(table[x+dir[move][0]][y+dir[move][1]] > 0) move = (move + 1) % 4;

            x += dir[move][0];
            y += dir[move][1];
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++) sb.append(table[i][j]).append(' ');
            sb.append('\n');
        }
        
        sb.append(Point);
        System.out.print(sb);
    }

    static void fillBorder(int N, int[][] table) {
        Arrays.fill(table[0], 1); // Top border
        Arrays.fill(table[N + 1], 1); // Bottom border

        for (int i = 1; i <= N; i++) {
            table[i][0] = 1; // Left border
            table[i][N + 1] = 1; // Right border
        }
    }
}
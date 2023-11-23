import java.io.*;
import java.util.*;

public class Main {
    /*  DFS, backtracking

     * */

    static char[][] map;
    static boolean[] visited = new boolean[26];
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int ans = 0;

    static void backTrack(int r, int c, int move) {
        visited[map[r][c] - 'A'] = true;

        for (int[] d : direction) {
            if (!visited[map[r + d[0]][c + d[1]] - 'A']) backTrack(r + d[0], c + d[1], move + 1);
        }

        visited[map[r][c] - 'A'] = false;
        if(ans < move) ans = move;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        map = new char[R + 2][C + 2];

        for (int r = 1; r <= R; r++) {
            String line = br.readLine();
            for (int c = 1; c <= C; c++) {
                map[r][c] = line.charAt(c - 1);
            }
        }
        fillEdge(R, C, map[1][1]);

        backTrack(1, 1, 1);
        System.out.println(ans);
    }

    static void fillEdge(int R, int C, char val) {
        Arrays.fill(map[0], val);
        Arrays.fill(map[R + 1], val);
        for (int r = 0; r <= R + 1; r++) map[r][0] = val;
        for (int r = 0; r <= R + 1; r++) map[r][C + 1] = val;
    }
}
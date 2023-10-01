import java.io.*;

class Main {
    /*  지뢰 찾기
        - 한 줄에 N개의 문자이므로, 지뢰 최대 갯수는 9
        - 지뢰가 연달아 있을 때, 지뢰 칸 값이 -1로 유지되도록 주의
     * */

    // 인접 8칸 이동
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 2][N + 2];    // Array index bound 방지 위해 N+2로 0,N+1은 경계로

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();    // 한 줄 입력
            for (int j = 1; j <= N; j++) {
                if (line.charAt(j - 1) == '.') continue; // .이라면(지뢰가 아니라면) pass

                map[i][j] = -1; // 지뢰면 -1 입력
                for (int[] d : dir) {   // 지뢰 주변 8칸에 지뢰 숫자만큼 up
                    int nX = i+d[0];
                    int nY = j+d[1];
                    if(map[nX][nY] >= 0) map[nX][nY] += (line.charAt(j - 1) & 15);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] < 0) sb.append('*');          // 지뢰
                else if (map[i][j] >= 10) sb.append('M');   // too many 지뢰
                else sb.append(map[i][j]);                  // 주변 지뢰 갯수
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
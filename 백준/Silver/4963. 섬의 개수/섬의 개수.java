import java.io.*;

public class Main {
    /*
     * */

    static boolean[][] land;

    static void dfs(boolean[][] land, int x, int y) {
        land[x][y] = false;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (land[x + dx][y + dy]) dfs(land, x + dx, y + dy);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int w, h;

        while ((w = readInt()) != 0 && (h = readInt()) != 0) {
            land = new boolean[h + 2][w + 2];

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) if (readInt() == 1) land[i][j] = true;
            }

            int count = 0;
            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (land[i][j]) {
                        count++;
                        dfs(land, i, j);
                    }
                }
            }

            sb.append(count).append('\n');
        }

        System.out.print(sb);
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
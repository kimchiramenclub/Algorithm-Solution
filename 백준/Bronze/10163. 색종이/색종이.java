import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int[][] map = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        int N = readInt();

        int xMax = 0;
        int yMax = 0;
        for(int i=1;i<=N;i++){
            int x1 = readInt();
            int y1 = readInt();
            int x2 = x1 + readInt() - 1;
            int y2 = y1 + readInt() - 1;

            for(int x = x1; x <= x2; x++) Arrays.fill(map[x], y1, y2+1, i);
            if(x2 > xMax) xMax = x2;
            if(y2 > yMax) yMax = y2;
        }

        checkArea(N, xMax, yMax);
    }

    static void checkArea(int N, int x, int y){
        StringBuilder sb = new StringBuilder();
        int[] area = new int[N+1];

        for(int i = 0;i<=x;i++){
            for(int j=0;j<=y;j++){
                area[map[i][j]]++;
            }
        }

        for(int i=1;i<=N;i++) sb.append(area[i]).append('\n');
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
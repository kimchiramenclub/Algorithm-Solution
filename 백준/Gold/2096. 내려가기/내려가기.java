import java.io.IOException;

public class Main {
    /*  DP

     * */

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[][] minDP = new int[2][3];
        int[][] maxDP = new int[2][3];

        for (int i = 0; i < 3; i++) minDP[0][i] = maxDP[0][i] = readInt();

        int[] lane = new int[3];
        int next = 0, prev = 1;
        int minL, minR,minAll, maxL, maxR, maxAll;
        
        for (int i = 1; i < N; i++) {
            next ^= 1;
            prev ^= 1;

            lane[0] = readInt();  lane[1] = readInt();  lane[2] = readInt();

            minL = minDP[prev][0] <= minDP[prev][1] ? minDP[prev][0] : minDP[prev][1];
            minR = minDP[prev][1] <= minDP[prev][2] ? minDP[prev][1] : minDP[prev][2];
            minAll = minL <= minR ? minL : minR;
            
            minDP[next][0] = minL + lane[0];
            minDP[next][2] = minR + lane[2];
            minDP[next][1] = minAll + lane[1];

            maxL = maxDP[prev][0] <= maxDP[prev][1] ? maxDP[prev][1] : maxDP[prev][0];
            maxR = maxDP[prev][1] <= maxDP[prev][2] ? maxDP[prev][2] : maxDP[prev][1];
            maxAll = maxL <= maxR ? maxR : maxL;

            maxDP[next][0] = maxL + lane[0];
            maxDP[next][2] = maxR + lane[2];
            maxDP[next][1] = maxAll + lane[1];
        }

        int min = Math.min(minDP[next][0], Math.min(minDP[next][1], minDP[next][2]));
        int max = Math.max(maxDP[next][0], Math.max(maxDP[next][1], maxDP[next][2]));

        System.out.println(max+" "+min);
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
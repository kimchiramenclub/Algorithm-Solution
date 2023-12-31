import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        int[][] array = new int[N][2];

        for (int i = 0; i < N; i++) {
            array[i][0] = readInt();
            array[i][1] = readInt();
        }
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });

        for(int i=0;i<N;i++) sb.append(array[i][0]+" "+array[i][1]+"\n");
        System.out.println(sb);
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int sign = 1, n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        if (c == '-') sign = -1;
        else n = c & 15;
        while (47 < (c = read()) && c < 58) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}

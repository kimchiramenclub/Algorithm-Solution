import java.io.IOException;

public class Main {

    static int[] arr;

    static void checkCycle(int idx, boolean[] visited){
        if(!visited[idx]){
            visited[idx] = true;
            checkCycle(arr[idx], visited);
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int T = readInt();
        arr = new int[1001];

        while (T-- > 0) {
            int N = readInt();
            boolean[] visited = new boolean[N + 1];
            int num = 0;

            for (int i = 1; i <= N; i++) arr[i] = readInt();
            for (int i = 1; i <= N; i++) {
                if(!visited[i]){
                    checkCycle(i, visited);
                    num++;
                }
            }
            sb.append(num).append('\n');
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
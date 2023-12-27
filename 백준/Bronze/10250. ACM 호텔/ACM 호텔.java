import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = readInt();

        while (T-- > 0) {
            int H = readInt(), W = readInt(), N = readInt();
            int answer = 100;
            answer = answer * (N % H == 0 ? H : N % H); // 층 계산
            answer += (N % H == 0 ? N / H : N / H + 1);  // 호 계산
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
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

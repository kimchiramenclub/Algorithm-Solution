import java.io.*;

public class Main {

    static byte[] buf = new byte[6];
    static String code;

    static void printCode(int N, int[][] codes) throws IOException {
        StringBuilder sb = new StringBuilder();
        System.in.read();

        int code;
        for (int i = 1; i <= N; i++) {
            code = checkCode(readInt(), codes);

            if (code < 0) {
                System.out.println(i);
                return;
            } else {
                sb.append((char)codes[code][0]);
            }
        }

        System.out.println(sb);
    }

    static int checkCode(int code, int[][] codes) {
        int diff;

        for (int i = 0; i < codes.length; i++) {
            diff = Integer.bitCount(code ^ codes[i][1]);
            if(diff < 2) return i;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int N = (System.in.read() & 15);
        int[][] codes = {{65, 0}, {66, 15}, {67, 19}, {68, 28}, {69, 38}, {70, 41}, {71, 53}, {72, 58}};

        printCode(N, codes);
    }

    static int readInt() throws IOException {
        int n = 0;
        for (int i = 0; i < 6; i++) {
            n = (n << 1) + (System.in.read() == 49 ? 1 : 0);
        }
        return n;
    }
}
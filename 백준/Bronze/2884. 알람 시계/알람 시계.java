import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int H = readInt(), M = readInt(), tmp = M - 45;
        M = (tmp) >= 0 ? M - 45 : M + 15;
        H = (tmp) >= 0 ? H : (H != 0 ? H - 1 : 23);

        System.out.println(H + " " + M);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
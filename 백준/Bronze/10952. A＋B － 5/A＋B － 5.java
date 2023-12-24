import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int A, B;
        while ((A = readInt()) != 0 && (B = readInt()) != 0) {
            sb.append((A+B)).append('\n');
        }
        System.out.print(sb);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
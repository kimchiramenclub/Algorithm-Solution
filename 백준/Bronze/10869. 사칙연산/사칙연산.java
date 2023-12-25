import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt();
        System.out.println((A+B));
        System.out.println((A-B));
        System.out.println((A*B));
        System.out.println((A/B));
        System.out.print((A%B));
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
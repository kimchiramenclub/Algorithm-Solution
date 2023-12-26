import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int Y = readInt();
        if (Y % 4 == 0 && (!(Y % 100 == 0) || Y % 400 == 0)) System.out.println(1);
        else System.out.println(0);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
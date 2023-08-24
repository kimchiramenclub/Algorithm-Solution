import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        double answer = (double)readInt() / (double)readInt();

        System.out.println(answer);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 2) + (c & 15);
        return n;
    }
}
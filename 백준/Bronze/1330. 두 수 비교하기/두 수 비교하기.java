import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        int A = readInt();
        int B = readInt();
        int result = Integer.compare(A, B);
        if(result < 0) System.out.println("<");
        else if(result == 0) System.out.println("==");
        else System.out.println(">");
    }

    static int readInt() throws IOException {
        int c, sign = 1, n = 0;
        if((c = System.in.read()) == '-') sign = -1;
        else n = c & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 2) + (c & 15);
        return n * sign;
    }
}
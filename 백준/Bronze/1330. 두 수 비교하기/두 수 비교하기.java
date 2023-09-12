import java.io.IOException;

class Main {


    public static void main(String[] args) throws IOException {
        int A = readInt();
        int B = readInt();

        if(A < B) System.out.println("<");
        else if(A > B) System.out.println(">");
        else System.out.println("==");


    }

    static int readInt() throws IOException {
        int c, n = 0, sign = 1;
        if((c = System.in.read()) == '-') sign = -1;
        else n = c;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }
}

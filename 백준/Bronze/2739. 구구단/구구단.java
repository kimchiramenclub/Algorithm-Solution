import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int N = readInt();

        for(int i= 1;i<=9;i++){
            sb.append(N).append(" * ");
            sb.append(i).append(" = ");
            sb.append(N*i).append("\n");
        }

        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 2) + (c & 15);
        return n;
    }
}
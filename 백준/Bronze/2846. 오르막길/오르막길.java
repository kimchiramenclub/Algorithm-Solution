import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        
        int prev, min, max = 0;
        min = prev = readInt();
        int answer = 0;
        
        for (int i = 1; i < N; i++) {
            int curr = readInt();
            if (prev >= curr) {
                max = prev;
                answer = Math.max(answer, max - min);
                min = curr;
            } 
            prev = curr;
        }
        answer = Math.max(answer, prev - min);

        System.out.println(answer);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
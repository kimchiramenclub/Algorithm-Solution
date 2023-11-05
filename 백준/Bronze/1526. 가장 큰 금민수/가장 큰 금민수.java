import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int c, N = 0, digit = 0;
        while ((c = System.in.read()) > 32) {
            N = (N << 3) + (N << 1) + (c & 15);
            digit++;
        }

        for (int i = N; i >= 4; i--) {
            if (isGold(i) && (N=i)==i) break;
            
        }
        System.out.println(N);
    }

    static boolean isGold(int N) {
        do {
            if (N % 10 != 4 && N % 10 != 7) return false;
        } while ((N /= 10) > 0);
        return true;
    }
}
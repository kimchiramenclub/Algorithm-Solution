import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] remainder = new boolean[42];
        int num, count = 0;
        for (int i = 0; i < 10; i++) {
            num = (readInt() % 42);
            if(!remainder[num]){
                count++;
                remainder[num] = true;
            }
        }
        System.out.println(count);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
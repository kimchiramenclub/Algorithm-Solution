import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int num = readInt() * readInt() * readInt();
        int[] mod = new int[10];

        while(num > 0){
            mod[num % 10]++;
            num /= 10;
        }

        for(int m : mod) sb.append(m).append('\n');
        System.out.print(sb);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

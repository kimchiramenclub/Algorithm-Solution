import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int piece;
        for (int i = 1; i <= 6; i++) {
            piece = Integer.parseInt(st.nextToken());
            if (i == 1 || i == 2) sb.append((1 - piece));
            else if(i == 6) sb.append((8 - piece));
            else sb.append((2- piece));
            sb.append(' ');
        }

        System.out.println(sb);
    }
}

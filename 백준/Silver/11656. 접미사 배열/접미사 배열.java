import java.io.*;
import java.util.Arrays;

class Main {
    /*
     * */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String[] S = new String[str.length()];
        S[0] = str;
        sb.append(S[0]);

        for (int i = 1; i < S.length; i++) {
            S[i] = sb.substring(i, S.length);
        }
        Arrays.sort(S);

        sb.setLength(0);
        for (String s : S) sb.append(s).append('\n');
        System.out.println(sb);
    }
}
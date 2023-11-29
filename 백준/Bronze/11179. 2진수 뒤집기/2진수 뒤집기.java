import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String bits = Integer.toBinaryString(N);

        int ans = 0;
        for (int i = bits.length()-1; i >= 0; i--){
            ans = (ans << 1) + (bits.charAt(i) == '1' ? 1 : 0);
        }

        System.out.println(ans);
    }
}
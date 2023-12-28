import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int c, L = 0, ans = 0;
        while ((c = System.in.read()) > 32) L = (L << 3) + (L << 1) + (c & 15);

        String S = new BufferedReader(new InputStreamReader(System.in)).readLine();
        for (int i = 0; i < L; i++) ans += getHash(S.charAt(i), i);
        System.out.println(ans);
    }

    static int getHash(char C, int hashIdx){
        int num = (C - 96);
        while(hashIdx-- > 0) num = (num * 31) % 1234567891;
        return num;
    }
}
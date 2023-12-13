import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String S = new BufferedReader(new InputStreamReader(System.in)).readLine();
        StringBuilder sb = new StringBuilder();
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, -1);

        for (int i = 0; i < S.length(); i++){
            if(alphabet[(S.charAt(i) - 'a')] == -1) alphabet[(S.charAt(i) - 'a')] = i;
        }

        for(int i=0;i<26;i++) sb.append(alphabet[i]).append(' ');
        System.out.println(sb);
    }
}

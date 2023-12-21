import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S;

        while((S = br.readLine()) != null){
            sb.append(((S.charAt(0)& 15)+(S.charAt(2)&15))).append('\n');
        }
        System.out.print(sb);
    }
}

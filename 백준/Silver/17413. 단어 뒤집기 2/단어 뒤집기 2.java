import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder rev = new StringBuilder();

        String S = br.readLine();
        boolean isTag = false;

        for (int i = 0; i < S.length(); i++) {
            char s = S.charAt(i);

            if (!isTag) {
                if (s == '<' || s == ' ') {
                    if (s == '<') isTag = true;
                    sb.append(rev.reverse()).append(s);
                    rev.setLength(0);
                } else rev.append(s);
            }
            else{
                sb.append(s);
                if(s == '>') isTag = false;
            }
        }

        sb.append(rev.reverse());
        System.out.println(sb);
    }
}
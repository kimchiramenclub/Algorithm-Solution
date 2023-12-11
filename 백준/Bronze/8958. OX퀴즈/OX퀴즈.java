import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String quiz = br.readLine();
            int score = 0;
            int total = 0;

            for (int i = 0; i < quiz.length(); i++) {
                if (quiz.charAt(i) == 'O') {
                    score++;
                    total += score;
                } else score = 0;
            }
            sb.append(total).append('\n');
        }
        System.out.print(sb);
    }
}

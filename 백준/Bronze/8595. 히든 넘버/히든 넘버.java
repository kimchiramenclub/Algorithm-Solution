import java.io.*;

public class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        String word = br.readLine();

        long num = 0L, sum = 0L;
        char c;
        for (int i = 0; i < word.length(); i++) {
            if ((c = word.charAt(i)) >= 48 && c <= 57) {
                num = (num << 3) + (num << 1) + (c & 15);
            } else if (num > 0) {
                sum += num;
                num = 0L;
            }
        }
        
        if (num > 0) sum += num;

        System.out.println(sum);
    }
}
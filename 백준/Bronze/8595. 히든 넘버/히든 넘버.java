import java.io.*;

public class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        String word = br.readLine();
        
        long sum = 0L;
        int num = 0;
        char c;
        for (int i = 0; i < word.length(); i++){
            if(word.charAt(i) >= '0' && word.charAt(i) <= '9') {
                while ((c = word.charAt(i)) >= '0' && c <= '9') {
                    num = (num << 3) + (num << 1) + (c & 15);
                    if(++i >= word.length()) break;
                }
                sum += num;
                num = 0;
            }
        }

        System.out.println(sum);
    }
}

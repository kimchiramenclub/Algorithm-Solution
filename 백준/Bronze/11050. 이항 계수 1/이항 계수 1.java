import java.io.*;
import java.util.*;

public class Main {
    /*  이항계수
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 1;
        for(int i=1;i<=K;i++) answer = answer * (N-i+1) / i;
        
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /* 그리디 연습
        S > n(n+1)/2 의 최대 n
     */

    public static long solution(long S) {
        int count = 0;
        if(S==1) return 1;
        for(int i = (int)Math.sqrt(S);(long)i <= S;i++){
            if((long)i * (long)(i+1) / 2 > S) return i-1;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        System.out.println(solution(S));
    }

}

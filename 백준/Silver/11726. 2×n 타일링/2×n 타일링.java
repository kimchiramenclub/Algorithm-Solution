import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /*  동적계획법
        - 1 : 1
        - 2 : 2
        - 3 : 3  ㅣㅣㅣ ㅣ=  =ㅣ
        - 4 : 5 |||| ||= |=| =|| ==
        - 5 : 8   ||||| |||= * 4  |== * 3
        - 6 : 13   |||||| ||||=*5 ||==*6 ===
        피보나치 비슷
 */

    public static int solution(int n) {
        if(n==1) return 1;
        return fibo(n);
    }

    public static int fibo(int n){
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for(int i=2;i<=n;i++){
            dp[i] = (dp[i-1] + dp[i-2])%10007;
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(solution(n));
    }
}
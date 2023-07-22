import java.io.*;

public class Main {
    /*  동적계획법
        - 1 : 1
        - 2 : 3  || = ㅁ
        - 3 : 5   ||| =| * 2 ㅁ| *2
        - 4 : 11       |||| , ||= * 3 , ||ㅁ *3 , == , =ㅁ * 2, ㅁㅁ
        - 5 : 21        ||||| , |||= * 4, |== * 4, |||ㅁ *3, |=ㅁ * 6 , |ㅁㅁ *3

        = 과 ㅁ이 동일시돼서, dp[i] = dp[i-1] + 2*dp[i-2] 구조임.
    * */
    public static int[] dp;

    //Bottom-Up 방식
    public static int solution(int n){
        if(n>2){
            for(int i=3;i<=n;i++){
                dp[i] = (dp[i-1] + dp[i-2]*2) % 10007;
            }
        }
        return dp[n];
    }

    // Top-Down 방식
//    public static int solution(int n){
//        if(dp[n] == 0) dp[n] = (solution(n-1) + solution(n-2)*2) % 10007;
//        return dp[n];
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n+2]; // n=1일 때 초기값 케이스를 위해 +2
        dp[1] = 1; dp[2] = 3; // 초기값 대입

        System.out.println(solution(n));
    }
}
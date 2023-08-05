import java.io.*;

class Main {
    /*  브루트포스, 동적계획법
        - n에서 제곱수를 뺀 n-i^2 의 제곱수 최소 갯수 + 1
        ex) 17 - 1 = 16(1)   ---> 1+1 = 2
            17 - 4 = 13(2) (13 = 9+4)  ---> 2+1 = 3
    */

    static int solution(int n){
        int[] dp = new int[n+1];
        dp[0] = 0; // ex) 16처럼 16-4^2 = 0 인 경우, 제곱수 1개로 되므로 0

        for(int i=1;i<=n;i++){
            int max = (int)Math.sqrt(i);
            dp[i] = Integer.MAX_VALUE;
            // 최소 제곱수 합 갯수 구하기
            for(int j=1;j<=max;j++) dp[i] = Math.min(dp[i], dp[i-j*j]+1);
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }
}

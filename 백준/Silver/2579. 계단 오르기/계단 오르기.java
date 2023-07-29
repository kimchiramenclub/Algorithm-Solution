import java.io.*;

public class Main {
    /*  동적계획법
        1. 1~2 계단 점프    2. 1계단 점프는 연속 2번까지만     3. 마지막 계단은 꼭 밟아야함

        - 계단이 1개일 때, 2개일 때 .... 이렇게 마지막 계단까지 최댓값 찾기?
        - 2차원 배열을 만들어서,
            dp[i][0] : 이전 계단에서 1칸 점프한 최댓값.
            dp[i][1] : 이전 계단에서 2칸 점프한 최댓값.
            즉, i+1 계단에서는
            dp[i+1][0] = dp[i][1]+stairs[i+1]
                // i번째 계단에서 이전에 1칸 점프하고 또 1칸 점프는 안 되므로, dp[i][0] 사용불가
            dp[i+1][1] = dp[i-1][0]+stairs[i+1] or dp[i-1][1]+stairs[i+1]

         - 수정 : N=1일 때 runtime error 발생 안하게 수정
    * */

     public static int solution(int N, int[] stairs){
        // 각 계단의 점수 최댓값 저장 배열
        // dp[i][0] : 이전 계단에서 1칸 점프 했을 때 최댓값        dp[i][1] : 이전 계단에서 2칸 점프했을 때 최댓값
        int[][] dp = new int[N][2];
        // 초기값 저장
        dp[0][0] = stairs[0];
        // 런타임 에러 방지
        if(N > 1) {
            dp[1][0] = dp[0][0] + stairs[1];
            dp[1][1] = stairs[1];
        }

        for(int i=2;i<=N-1;i++){
            dp[i][0] = dp[i-1][1] + stairs[i]; // 1칸 뛰려면 이전에는 2칸 뛰었어야만 가능
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + stairs[i]; //2칸 뛰려면 이전 칸에서 1칸 뛰었든, 2칸 뛰었든 상관 X. 최댓값으로
        }
        return Math.max(dp[N-1][0], dp[N-1][1]); // N번재 계단에 1칸 뛰어서 온 점수, 2칸 뛰어서 온 점수의 MAX
     }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N];
        for(int i=0;i<N;i++) stairs[i] = Integer.parseInt(br.readLine());

        System.out.println(solution(N, stairs));
    }
}
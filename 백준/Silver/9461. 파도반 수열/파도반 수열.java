import java.io.*;

public class Main {
    /* 파도 수열
        1 1 1 2 2 3(+1) 4(+1) 5(+1) 7(+2) 9(+2)
        12(+3) 16(+4)
        - 5턴 전의 값이 다음 값을 구하는 데 쓰임.
        - 여러개의 testCase가 들어오므로 , Top-Bottom 형태로 구성
     */

    public static long[] dp = new long[101];

    public static long solution(int N) {
        if(dp[N] > 0) return dp[N];
        dp[N] = solution(N-1) + solution(N-5);
        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        // 초기값 대입
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for(int i=0;i<N;i++) bw.write(solution(Integer.parseInt(br.readLine()))+"\n");
        bw.flush();
        bw.close();
    }
}
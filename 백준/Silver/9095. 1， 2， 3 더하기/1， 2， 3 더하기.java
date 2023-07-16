import java.io.*;

public class Main {

    /*  동적계획법
        - Bottom-Top 방식
        1 : 1
        2 :  -1, -2 두개의 옵션. -1 -> 1(1) , -2 -> 0(1)    --> 2
        3 : -1, -2,-3       -1 -> 2(2가지) , -2 -> 1(1)   -3 -> 0(1)     --> 4
        4 : -1 -> 3(4가지 방법) , -2 -> 2(2가지) , -3 -> 1(1가지)   --> 7
 */
    public static void solution(int[] testCase) throws IOException  {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dp = new int[11];
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=10;i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int t : testCase){
           bw.write(dp[t]+"\n");
        }

        bw.flush();
        bw.close();
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] testCase = new int[T];
        for(int i=0;i<T;i++){
            testCase[i] = Integer.parseInt(br.readLine());
        }

        solution(testCase);
    }
}
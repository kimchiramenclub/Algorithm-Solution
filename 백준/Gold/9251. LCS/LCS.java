import java.io.*;

class Main {
    /*   LCS - Longest Common Subsequence
         - dp를 활용한 알고리즘
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        int lenA = A.length();
        String B = br.readLine();
        int lenB = B.length();
        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        System.out.println(dp[lenA][lenB]);
    }
}

import java.io.*;
import java.util.Arrays;

class Main {
    /*  그리디
        30의 배수 조건 : 1. 0이 적어도 1개 있을것  2. 전체 숫자 합 = 3의 배수
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String tmp = br.readLine();     // N이 10^5 길이의 큰 숫자이므로, String으로 읽음
        int[] N = new int[tmp.length()];  // N의 각 자리 숫자 배열  
        int sum = 0;    // 3의 배수 판별용 sum
        boolean hasZero = false;    // 0이 적어도 1개 있는지

        for (int i = 0; i < N.length; i++) {
            N[i] = tmp.charAt(i) & 15;
            sum += N[i];
            if (!hasZero && N[i] == 0) hasZero = true;
        }

        // 30의 배수가 없다면 (1. 0이 X    2. 전체 합 3의 배수 아님)  -1 출력
        if (!hasZero || sum % 3 != 0) sb.append(-1);
        else {
            Arrays.sort(N);
            for(int i=N.length-1;i>=0;i--) sb.append(N[i]); // 높은 숫자부터 출력
        }
        System.out.println(sb);
    }
}
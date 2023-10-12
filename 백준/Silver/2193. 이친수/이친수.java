import java.io.*;

class Main {
    /*  DP
        - 이친수는 0으로 시작하지 않는다. -> 앞자리가 1로 고정
        - 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
        N
        - 2 : 1 (10)
        - 3 : 2 (100, 101)
        - 4 : 3  (1000, 1001, 1010)
        - 5 : 5  (10000, 10001, 10010, 10100, 10101)
        - 6 : 8  (100000, 100001, 100010, 100100, 101000, 100101, 101001, 101010)
        피보나치 패턴
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long answer = 0;
        long[] fibNum = {1, 1};

        if(N <= 2) answer = fibNum[N-1];
        for (int i = 3; i <= N; i++) {
            answer = fibNum[1] + fibNum[0];
            fibNum[0] = fibNum[1];
            fibNum[1] = answer;
        }

        System.out.println(answer);
    }
}
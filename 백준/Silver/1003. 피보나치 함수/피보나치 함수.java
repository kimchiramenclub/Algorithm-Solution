import java.io.*;

public class Main {
    /* 0.25초라는 짧은 시간 -> 동적 계획법
      - Top Down 방식으로 구성 (보기 편해서)
      - answer, memo를 둘다 static으로

      fibo(2) -> *fibo(0) + *fibo(1) -> 1,0 + 0,1 -> 1,1
      fibo(3) -> fibo(1) + fibo(2) -> 0,1 + 1,1 -> 1,2
      fibo(4) -> fibo(2) + fibo(3) -> 1,1 + 1,2 -> 2,3
      이미 주어진 값인 fibo(0), fibo(1)의 덧셈을 통해,  새로운 값들인 fibo(2), fibo(3)을 구해 memo하는 방식으로
      fibo(3), fibo(4) , ... fibo(n)까지 순차적으로 구해나갈 수 있음.

      feat. https://st-lab.tistory.com/124  

    */
    public static int[][] answer;
        // 0부터 담당할 경우, 40까지 표현하려면 41칸이 필요함.
    public static int[][] memo = new int[41][2];

    public static void solution(int[] testCase) {
        // memo[0], memo[1] = 0 일 경우, 탐색되지 않았다고 할 것이므로, fiboCount(1)까지는 구해줌
        memo[0][0] = memo[1][1] = 1;
        memo[0][1] = memo[1][0] = 0;

        for(int i=0; i<testCase.length;i++){
            answer[i] = fiboCount(testCase[i], i);
        }
    }

    public static int[] fiboCount(int n, int index){
        // n이 탐색하지 않은 값일 때 (&& 조건이므로, fibo(0), fibo(1)은 자동으로 걸러짐)
        if (memo[n][0] == 0 && memo[n][1] == 0) {
            // 탐색하지 않은 경우, '0'의 갯수 구하기
            memo[n][0] = fiboCount(n-1, index)[0] + fiboCount(n-2, index)[0];
            // 탐색하지 않은 경우, '1'의 갯수 구하기
            memo[n][1] = fiboCount(n-1, index)[1] + fiboCount(n-2, index)[1];
        }
        // 탐색한 구간까지 내려가면, 새로운 값을 구하기 위해 이전 값을 리턴함.
        return memo[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] testCase = new int[N];
        answer = new int[N][2];

        for(int i=0; i<N; i++){
            testCase[i] = Integer.parseInt(br.readLine());
        }
        solution(testCase);

        // 출력
        for(int[] i : answer){
            for(int j : i){
                bw.write(j+" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}

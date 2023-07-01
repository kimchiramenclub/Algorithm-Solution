import java.io.*;
import java.util.*;

public class Main {

    /* 그리디 알고리즘
        - local optimal choice가 global optimum으로 이어진다는 알고리즘.
        - 최적의 선택을 하위 단계에서 해서, 전체적으로 최적의 선택을 이끌어냄
     */



    public static int solution(int N, int K, int[] values) {
        int count = 0;

        for(int i=N-1;i>=0;i--){
            if(values[i] <= K) {
                count = count + K/values[i];
                K = K % values[i];
            }
            if(K == 0) return count;
        }
        return 0;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] values = new int[N];
        for(int i=0;i<N;i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(N, K, values));
    }
}
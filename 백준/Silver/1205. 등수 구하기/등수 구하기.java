import java.io.*;
import java.util.*;

public class Main {

    /*  구현
        - 같은 점수일 때, 등수가 밀리는 것? -> 랭킹 리스트 우선순위에서 밀린다는 것
        - 같은 점수라면 등수는 같게 유지는 됨.
        - N < P 라면, 랭킹이 꽉 차지 않은 것.
        - 같은 점수여도 쭉 지나가지만, rank는 업데이트 안 되게

        N = 3, P = 4, newScore = 90
        100 90 90

        1. N = 0 -> 등록된 갯수 X : 1등
        2. N < P -> 실제 등수대로 바로 출력가능(맨 마지막 랭킹 점수와 같아도 밀려나지 않음)
        3. N = P -> 맨 마지막 랭킹과는 비교해야 함.
     */
    
    public static int solution(int N, int newScore, int P, int[] scores) {
        int rank = 1;

        if (N == 0) return 1; // 등록된 점수 X -> 무조건 1등

        for (int i = 0; i < N; i++) {
            if (newScore > scores[i]) return rank;
            else if (newScore == scores[i]) {
                // 2. N < P
                if (N < P) return rank;
                // N == P 인 경우, rank 갱신만 피하게 하고 후순위로 pass함.
            } else {
                rank = i + 2;
            }
        }

        // N개의 랭킹리스트를 다 돌고 랭킹 자리가 남아있다면
        if (N < P) return N + 1;
        
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] scores = new int[N];

        if (N > 0) st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, newScore, P, scores));
    }
}
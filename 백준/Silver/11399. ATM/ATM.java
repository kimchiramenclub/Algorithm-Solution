import java.io.*;
import java.util.*;

public class Main {

    /* 그리디 알고리즘
        - local optimal choice가 global optimum으로 이어진다는 알고리즘.
        - 최적의 선택을 하위 단계에서 해서, 전체적으로 최적의 선택을 이끌어냄

        - 1, 1+2, 1+2+3 ...  이런 식으로 시간이 뒤로 갈수록 합산됨.
        - 처음 호출된 시간이 더 반복 호출되므로, 작은 숫자가 앞에 호출될 수록 유리
        - 정렬해서 for문
     */



    public static int solution(int N, int[] times) {
        int totalSum = 0;
        int midSum = 0;

        Arrays.sort(times);
        for(int i=0;i<times.length;i++){
            midSum += times[i];
            totalSum += midSum;
        }
        return totalSum;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] times = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            times[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, times));
    }
}

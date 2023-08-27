import java.io.*;
import java.util.*;

class Main {
    /*  dp
        https://st-lab.tistory.com/137
        - 이 블로그의 '이분탐색'을 활용한 O(NlogN) 시간복잡도 풀이를 참고.
     */

    static int[] seq;

    static int solution(int N) {
        // LIS : Longest Increasing Subsequence
        // 최장 증가수열의 길이를 체크하기 위한 배열. 증가수열의 값들을 저장.
        int[] LIS = new int[N];


        LIS[0] = seq[0];   // 증가수열의 최초값 (즉, 수열의 최댓값)
        int idx = 1;       // 증가수열의 최소길이

        // seq을 돌면서 증가수열에 사용할 부품을 찾고, 부품 교체가 가능하면 교체(수열의 길이를 늘리기 위해)
        for (int i = 1; i < N; i++) {
            // 1. 증가수열의 마지막 값보다 크다면, 증가수열에 포함시키고 길이를 늘림.
            if (LIS[idx - 1] > seq[i]) LIS[idx++] = seq[i];
                // 2. 값이 커서 증가수열의 최초값(최댓값)을 대체할만 하다면, 최초값을 대체
            else if (LIS[0] < seq[i]) LIS[0] = seq[i];
                // 3. 값이 증가수열 길이 늘릴만큼 크지도, 최솟값보다 작지도 않다면, 중간 부품 교체 가능성 확인
            else {
                // idx값 idx-1로 (배열에서의 실제 index 값. 원래 내장 메서드랑 달리 inclusive로 할거라)
                int tmp = binarySearch(LIS, 0, idx-1, seq[i]);
                LIS[tmp < 0 ? -(tmp + 1) : tmp] = seq[i];
            }
        }

        return idx;
    }

    // 감소 배열에 대한 이분탐색
    static int binarySearch(int[] LIS, int low, int high, int value) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (LIS[mid] == value) return mid;
            else if (LIS[mid] > value) low = mid + 1;
            else high = mid - 1;
        }

        return -(low + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        seq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) seq[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N));
    }
}
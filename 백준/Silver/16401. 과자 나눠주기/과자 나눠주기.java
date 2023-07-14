import java.io.*;
import java.util.*;

public class Main {

    /*  이분탐색
        - 조카 1명에게 줄 수 있는 최대 과자 길이
        - 과자를 쪼갤 수는 있음. 조카 4 : 10 10 15 -> 7(10) 7(10) 7+7(15)
        - 나눠줄 과자 최대 길이를 이분 탐색으로 찾기.
        - 해답이 없다면 0
        - 과자 줄 수 있는 최대 길이 : (max) * N/M (M > N일 때)
            -> but 이진 탐색에서 이 정도로 high를 줄이는 게 그리 유의미하지 않은 것 같음.

        - 같은 길이의 과자를 나눠줄 수 없다면 0 return
 */

    public static int solution(int M, int N, int[] snack, int max) {
//        if(M > N) max = (int)((long)max * (long)M / (long)N);

        if(!checkSplit(M, snack, 1)) return 0; // 분배 자체가 불가능하면 0
        return binarySearch(M, snack, 1, max);
    }

    public static int binarySearch(int M, int[] snack, int low, int high) {
        // 탈출 조건. 9, 10 같은 값이 mid를 늘리지 않아 무한 루프 돌지 않도록 체크
        if(low > high) return high;

        int mid = (low + high) / 2; // int 최댓값이 21억이므로, 10억+10억은 overflow X
        if (checkSplit(M, snack, mid)) return binarySearch(M, snack, mid+1, high);
        else return binarySearch(M, snack, low, mid-1);
    }

    public static boolean checkSplit(int M, int[] snack, int mid){
        long slice = 0;
        for(int s : snack){
            slice += (s/mid);
            if(slice >= M) return true; // 나눠줄 수 있는 걸 확인하면 중간 탈출
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 조카 수
        int N = Integer.parseInt(st.nextToken()); // 막대 과자 수
        int[] snack = new int[N];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snack[i]);
        }

        System.out.println(solution(M, N, snack, max));
    }
}
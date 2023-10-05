import java.io.*;
import java.util.Arrays;

class Main {
    /*  해시맵
        - 물체의 최대 중량?
        - w/k <= 로프 한계 (사용한 로프 중 가장 작은 값)
        - k * 로프한계 : 이 값의 max 찾기
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) ropes[i] = Integer.parseInt(br.readLine());
        Arrays.sort(ropes); // 로프 ascending order

        int k = 0;  // 사용한 로프 갯수
        int wt = 0; // 물체 최대 무게
        for (int i = N - 1; i >= 0; i--) {  // 로프를 로프 한계가 큰 순으로 탐색
            k = N - i;
            wt = Math.max(wt, k * ropes[i]);
        }
        System.out.println(wt);
    }
}
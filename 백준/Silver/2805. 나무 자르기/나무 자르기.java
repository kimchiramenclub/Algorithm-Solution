import java.io.*;
import java.util.StringTokenizer;

class Main {
    /*  이분 탐색
        - H 이상인 값만 잘라서 가져감
        - 최대높이 H (가져가는 나무 최소화)
        - long 쓰는 게 좋을듯. 가져가는 나무 합은 int 범위 넘을 수 있음

        탈출 조건 + boundary?
    */


    static int solution(int N, int M, int[] trees, int max){
        return binarySearch(N, M, trees, 0, max);
    }

    static int binarySearch(int N, int M, int[] trees, int low, int high){
        // 탈출 조건
        if(low > high) return high;

        int mid = (low + high) / 2;
        long sum = 0;
        for(int i=0;i<N;i++){
            if(trees[i]-mid > 0) sum += (trees[i]-mid); // 나무 높이가 절단기보다 높으면 절단
            // M값을 넘는 순간 넘어가게 해서 시행횟수 down  + 음수값 방지
            if(sum >= M) return binarySearch(N, M, trees, mid+1, high);
        }
        return binarySearch(N, M, trees, low, mid-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] trees = new int[N];
        int max = 0;
        for(int i=0;i<N;i++){
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]); // 이분탐색의 초기 high 값
        }

        System.out.println(solution(N, M, trees, max));
    }
}

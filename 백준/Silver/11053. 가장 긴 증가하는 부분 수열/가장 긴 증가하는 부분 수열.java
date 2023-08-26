import java.io.*;
import java.util.*;

class Main {
    /*  dp
        https://st-lab.tistory.com/137
        - 이 블로그의 '이분탐색'을 활용한 O(NlogN) 시간복잡도 풀이를 참고.
     */

    static int[] seq;

    static int solution(int N){
        // LIS : Longest Increasing Subsequence
        // 최장 증가수열의 길이를 체크하기 위한 배열. 증가수열의 값들을 저장.
        int[] LIS = new int[N];


        LIS[0] = seq[0];   // 증가수열의 최초값 (즉, 수열의 최솟값)
        int idx = 1;       // 증가수열의 최소길이

        // seq을 돌면서 증가수열에 사용할 부품을 찾고, 부품 교체가 가능하면 교체(수열의 길이를 늘리기 위해)
        for(int i=1;i<N;i++){
            // 1. 증가수열의 마지막 값보다 크다면, 증가수열에 포함시키고 길이를 늘림.
            if(LIS[idx-1] < seq[i]) LIS[idx++] = seq[i];
            // 2. 값이 작아서 증가수열의 최초값(최솟값)을 대체할만 하다면, 최초값을 대체
            // ex) 최솟값이 30 -> 10 으로 변한다면, 더 긴 증가수열을 찾을 수도 있음.
            else if(LIS[0] > seq[i]) LIS[0] = seq[i];
            // 3. 값이 증가수열 길이 늘릴만큼 크지도, 최솟값보다 작지도 않다면, 중간 부품 교체 가능성 확인
            else{
                /* toIndex에 활용되는 idx는 실제 LIS[idx] 값은 배제하고 검색해줌.
                   그래서 만약 인덱스를 3~5로 하고 싶다면, 6을 넣어줘야 5까지 찾아줌. */
                /* binarySEarch 메서드는 배열이 정렬되어 있는 경우에만 사용 가능.
                 지금 LIS는 증가수열의 값들을 차례대로 넣고 있으므로, 정렬된 상태 */
                int tmp = Arrays.binarySearch(LIS, 0, idx, seq[i]);
                /* tmp < 0이라면, 증가수열에서 그 값을 찾지 못한 것.
                   그 때, binarySearch 메서드는 (-(insertion point) - 1)을 return함.
                   -(tmp+1) => 실제 insertion point!
                   ex) 찾는 값이 30이고,  20 40 이렇게 있다면, -(tmp+1) = 1   즉, 40을 더 작은 값인 30으로 대체한다는 것! */
                LIS[tmp < 0 ? -(tmp+1) : tmp] = seq[i];
            }
        }

        // 결국 증가수열의 길이를 늘리는 건 큰 값의 발견임. 그 전까지는 최초값, 중간 부품을 교체해가면서 가능성을 탐구하는 것
        /* ex) 10 50 5 30 40 60
           이 경우, 증가수열 길이 : 2 인 상태에서 5를 최솟값으로 변경, 50을 30으로 교체해서 40, 60이 수열에 포함되게 함. */
        return idx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        seq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N));
    }

    // 한 줄에 최대 1000개까지의 입력이 주어지므로, 비효율적
//    static int readInt() throws IOException {
//        int c, n = 0;
//        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 2) + (c & 15);
//        return n;
//    }
}
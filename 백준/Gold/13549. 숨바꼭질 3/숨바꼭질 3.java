import java.io.*;
import java.util.*;

public class Main {

    /* 0-1 BFS 문제   + 동적계획법
       - 0-1 너비 우선 탐색은 movement =0, 1 인 두 case가 있음.
       - Deque을 사용해서 movement = 0인 case를 우선하기 위해 앞에 투입, movement = 1인 case는 뒤에 투입

       문제 1 : 숨바꼭질 1 처럼 int visited를 쓰면, 제대로 카운트가 안됨.
       - 다시 boolean으로 돌리고, Deque를 int[]로 선언해서 movement 기억하게 함.
       문제 2 : 1 ->2 일때 1+1해서 하는 방법이 먼저 방문처리 해버려서, 1*2 방법이 인식이 안됨.
       - 방문 체크 없애버리진 않고, 일단 곱셈 방식이 for문에서 더 앞에 오게 바꿔봄(없애니까 메모리 초과됨)
     */

    public static Deque<int[]> cases = new LinkedList<>();
    public static boolean[] visited; // 방문 상태 체크

    public static int solution(int N, int K) {
        return bfs(N, K);
    }

    public static int bfs(int N, int K){
        cases.offer(new int[]{N,0});
        visited[N] = true;

        while(true){
           int[] T = cases.poll();
            if(T[0] == K) return T[1]; // 다음 턴에 답이 나온다면, 바로 return

            for(int i=0;i<3;i++){
               int next = 0;
               switch (i) {
                   case 0 : next = T[0] * 2; break;
                   case 1 : next = T[0] - 1; break;
                   case 2 : next = T[0] + 1; break;
               }
               if(next >= 0 && next < visited.length && !visited[next]){
                   visited[next] = true;
                   // movement = 0인 case를 우선하기 위해 덱의 앞으로 투입
                   if(i==0)  cases.offerFirst(new int[]{next, T[1]});
                   else cases.offerLast(new int[]{next, T[1]+1});
               }
           }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];

        System.out.println(solution(N,K));
    }
}
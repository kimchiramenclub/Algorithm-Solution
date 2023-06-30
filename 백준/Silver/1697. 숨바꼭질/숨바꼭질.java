import java.io.*;
import java.util.*;

public class Main {

    /* BFS 문제   + 동적계획법
       - 매 턴마다 경우의 수가 최대 3개씩 나옴.
       - 매 턴 나오는 곱셈, 덧셈의 경우의 수를 모두 큐에 넣고, 정답이 나오는 순간 탈출
       - K보다 큰 지, 작은 지를 판단해서 +,- , 곱셈 정하기
       - N이 K를 넘는 순간, 곱셈은 의미가 없음. /2 과정이 없기때문

       수정 : 모든 경우의 수를 매번 다 넣다보니까, 메모리 초과가 떴음.
       - 방문한 숫자는 다시 방문하지 않도록 visited 배열 사용
       - N > K인 경우에는 N은 계속 낮추는 방향밖에 없으므로, 딱히 visited 체크 필요 없음

       - if(N*2 <= 100000 && !visited[N*2]) cases.offer(N*2);  왜 이 케이스는 배제해도 되는 지
         먼저 곱셈을 하는 순간 간격이 2배로 멀어지므로, 선 곱셈보다는 선 뺄셈이 더 유리함.
         그래서 100000을 곱셈으로 넘어버리는 케이스는 배제해도 괜찮음.
         그리고 if (~~ && ~~) 조건은 앞 조건이 false일 경우 뒷 조건은 체크하지 않으므로, Array boundary exception이 일어나지 않음.

        - while문 안에 for(int i=0;i<turn;i++){ 이런 식으로 각 턴마다의 경우의 수를 모두 for문으로 돌리다보니 공간복잡도가 문제가 생김.
          차라리 Queue를 int[]로 바꾸어서 time도 저장하고, for문을 없애버리는 게 메모리 상 효율적

        - 방문 배열을 아예 int로 해서, 몇 턴만에 그 숫자에 도달했는 지 하는 것도 괜찮아보임. 그러면 Queue를 굳이 int[]로 할 필요 X

     */

    public static Queue<Integer> cases = new LinkedList<>();
    public static int[] visited; // 방문 상태 체크

    public static int solution(int N, int K) {
        if(N == K) return 0;
        return bfs(N, K);
    }

    public static int bfs(int N, int K){
        cases.offer(N);
        visited[N] = 0;

        while(true){
           N = cases.poll();

           for(int i=0;i<3;i++){
               int next = 0;
               switch (i) {
                   case 0 : next = N - 1; break;
                   case 1 : next = N + 1; break;
                   case 2 : next = N * 2; break;
               }
               if(next == K) return visited[N]+1; // 다음 턴에 답이 나온다면, 바로 return
               else if(next >= 0 && next < visited.length && visited[next] == 0){
                   cases.offer(next);
                   visited[next] = visited[N]+1;
               }
           }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        visited = new int[100001];

        System.out.println(solution(N,K));
    }
}
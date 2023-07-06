import java.io.*;
import java.util.*;

public class Main {

    /* 동적 계획법
        - 숨바꼭질 문제와 비슷한 문제
        - BFS 사용
     */
    public static Queue<Integer> cases = new LinkedList<>();
    public static HashMap<Integer, Integer> visited = new HashMap<>();

    public static int solution(int N){

        return bfs(N);
    }

    public static int bfs(int N){
        cases.offer(N);
        visited.put(N,0);

        while(!cases.isEmpty()){
            N = cases.poll();

            for(int i=0;i<3;i++){
                int next = 0;
                int count = visited.get(N);
                switch(i){
                    case 0 : if(N%3==0) next = N/3; break;
                    case 1 : if(N%2==0) next = N/2; break;
                    case 2 : if(N>=2) next = N-1; break;
                }
                if(next == 1) return count+1;
                else if(!visited.containsKey(next)){
                    cases.offer(next);
                    visited.put(next, count+1);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
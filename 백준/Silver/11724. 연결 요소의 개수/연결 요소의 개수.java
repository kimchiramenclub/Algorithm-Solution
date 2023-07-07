import java.io.*;
import java.util.*;

public class Main {

    /* 그래프 탐색
        - 한 연결요소를 다 탐색했을 때, 어떻게 판별할 지? 
        -> dfs로 바로 판별하지 않고, 1~N까지 index를 돌면서 연결요소를 세는 count 메서드 사용.
        
        수정 : BFS로도 해보기. DFS는 메모리 사용량이 너무 큼
     */
    public static boolean[] visited;
    public static LinkedList<LinkedList<Integer>> graph = new LinkedList<>();

    public static int solution(int N, int M, int[][] lines){
        // 그래프 표현
        for(int i=0;i<N+1;i++) graph.add(new LinkedList<>());
        for(int i=0;i<M;i++){
            graph.get(lines[i][0]).add(lines[i][1]);
            graph.get(lines[i][1]).add(lines[i][0]);
        }
        // 연결요소를 찾는 메서드
        return findCC();
    }

    public static int findCC(){
        int count = 0;

        for(int i=1;i<graph.size();i++){
            if(!visited[i]){
                dfs(i);
                count++;
            }
        }
        return count;
    }

    public static void dfs(int node){
        // 방문처리
        visited[node] = true;
        // 연결된 노드들 방문
        for(int adj : graph.get(node)){
            if(!visited[adj]) dfs(adj);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] lines = new int[M][2];
        visited = new boolean[N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, lines));
    }
}

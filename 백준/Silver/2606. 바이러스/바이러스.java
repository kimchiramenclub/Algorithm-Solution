import java.io.*;
import java.util.*;

public class Main {
    /* BFS
       - 간선 정보를 List + Array로 표현해보기
     */
    public static boolean[] visited;

    public static int solution(int M, int N, int[][] lines) {
        visited = new boolean[M+1]; // 방문 검사
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 그래프. 아래의 예시 구조와 같음
        // graph[1] = [2]      node 1 is connected to node 2
        // graph[2] = [1, 3]    node 2 is connected to nodes 1 and 3
        for(int i=0;i<M+1;i++) graph.add(new ArrayList<>());
        for(int i=0;i<N;i++){
            graph.get(lines[i][0]).add(lines[i][1]);
            graph.get(lines[i][1]).add(lines[i][0]);
        }
        return bfs(graph);
    }

    public static int bfs(ArrayList<ArrayList<Integer>> graph){
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int adj : graph.get(node)){
                if(!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(M, N, lines));
    }
}

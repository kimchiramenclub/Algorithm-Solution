import java.io.*;
import java.util.*;

public class Main {

    /* DFS, BFS 문제

       - 간선이 순서대로가 아니라 무작위로 있어서, 2차원 배열로 graph 구성
       - Stack, Queue를 사용하는 방식으로 DFS, BFS 구성

     */
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] graph;

    public static void solution(int N, int M, int V, int[][] links) throws IOException {
        graph = new boolean[N + 1][N + 1]; // 정점이 1부터 시작하므로, 보정

        for (int i = 0; i < M; i++) {
            graph[links[i][0]][links[i][1]] = true;
            graph[links[i][1]][links[i][0]] = true;
        }

        dfs(N, V);
        bw.write("\n");
        bfs(N, V);
    }

    public static void dfs(int N, int V) throws IOException {
        boolean[] visited = new boolean[N + 1];
        Stack<Integer> stack = new Stack<>();

        stack.push(V); // 시작 노드 입력

        while (!stack.isEmpty()) {
            boolean hasNearNode = false; // 인접한 노드가 있는 지
            int vertex = stack.peek();

            if (!visited[vertex]) {
                visited[vertex] = true; // 현재 노드 방문 처리
                bw.write(vertex + " ");
                // 1. 아래 for문에 넣으면 마지막 node는 방문할 곳이 없어서 for문이 돌지 않고, 출력 X
                // 2. if문을 씌워주지 않으면, 스택을 비우는 과정에서도 출력됨
            }

            for (int i = 1; i <= N; i++) {
                if (graph[vertex][i] && !visited[i]) {
                    hasNearNode = true;
                    stack.push(i);
                    break;
                }
            }
            if (!hasNearNode) stack.pop(); // 인접한 노드가 없다면, 되돌아가기 위해 pop
        }
    }

    public static void bfs(int N, int V) throws IOException {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            bw.write(vertex + " "); // 스택과 달리 FIFO 구조이므로, 넣은 순서대로 빠져서 조건이 필요 없음.
            visited[vertex] = true; // 현재 노드 방문 처리

            for (int i = 1; i <= N; i++) {
                if (graph[vertex][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int[][] links = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            links[i][0] = Integer.parseInt(st.nextToken());
            links[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(N, M, V, links);
        bw.flush();
        bw.close();
    }
}
import java.io.*;
import java.util.*;

class Main {
    /*      다익스트라 알고리즘 - 최단경로

            - 두 정점 사이에 복수의 간선 X. 1개만 존재 -> 2차원 배열 사용!
            - O((V+E)logV * 'V')  : X -> A,B,C... F   , A->X,  B->X, C ->X ... 이렇게 모든 정점에 대해서 한번씩 돌려야하기 때문
            - '방향'그래프 이기 때문에, 입력에서 받는 간선 정보가 단방향임.
            - 시작 마을 -> X -> 시작마을   == X -> 시작마을 -> X

            참고 :
            https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
            https://chb2005.tistory.com/78
    * */

    static List<Edge>[] graph;  // 방향 그래프

    // X : 시작 정점
    static void Dijkstra(int X, int[] dist) throws IOException {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[X] = 0;    // 시작정점 거리 0 입력
        pq.offer(new Edge(X, 0)); // 시작정점 입력

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.node;

            // 방문체크 + 지금까지 구한 최단거리가 현재 루트보다 짧다면, pass
            if (dist[node] < current.weight) continue;

            for (Edge next : graph[current.node]) {
                int nextWeight = current.weight + next.weight;  // 현재 정점까지의 가중치(거리) + 다음 정점까지의 가중치

                // A -> B -> C > A -> C 라면, 굳이 더 돌아가는 루트는 최소 거리 계산에 필요없으므로 배제.
                if (dist[next.node] > nextWeight) {    // 최소 거리라면 업데이트
                    dist[next.node] = nextWeight;
                    pq.offer(new Edge(next.node, nextWeight));     // 다음 정점으로 이동
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt(); // 정점 갯수 (마을)
        int M = readInt(); // 간선 갯수
        int X = readInt(); // 시작 정점 (파티 장소)

        // graph 선언
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        // [i][j] : i 정점으로부터 j 정점까지의 최단 거리를 전부 기록하는 2차원 배열
        int[][] distances = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(distances[i], Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            int u = readInt();  // 시작정점
            int v = readInt();  // 도착정점
            int w = readInt();  // 간선 길이 (거리)
            // 간선 정보 입력. '방향'그래프라서, 단방향 정보만 입력
            graph[u].add(new Edge(v, w));
        }

        // i 정점으로부터의 최단 거리를 기록하는 1차원 거리 배열 send
        // 다익스트라 알고리즘을 N번 돌림
        for (int i = 1; i <= N; i++) Dijkstra(i, distances[i]);

        // i -> X -> i 의 학생 이동거리 중 최고값 구함.
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, distances[i][X] + distances[X][i]);
        }
        System.out.println(max);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static class Edge implements Comparable<Edge> {
        public final int node;      // 정점 번호
        public final int weight;    // 가중치

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        // PriorityQueue의 정렬기준
        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}

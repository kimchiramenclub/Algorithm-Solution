import java.io.*;
import java.util.*;

class Main {
    /*      다익스트라 알고리즘 - 최단경로

            - 2번째 풀이. distance를 따로 뺌
            - 두 정점 사이에 복수의 간선 있을 수도 있음.
            - '방향'그래프 이기 때문에, 입력에서 받는 간선 정보가 단방향임.

            참고 :
            https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
            https://chb2005.tistory.com/78
    * */

    static List<Edge>[] graph;  // 방향 그래프
    static int[] dist;   // 정점 배열

    static void solution(int V, int E, int K) throws IOException {
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[K] = 0;    // 시작정점 거리 0 입력
        pq.offer(new Edge(K, 0)); // 시작정점 입력

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int node = current.node;

            // 방문체크 + 지금까지 구한 최단거리가 현재 루트보다 짧다면, pass
            if(dist[node] < current.weight) continue;

            for(Edge next : graph[current.node]){
                int nextWeight = current.weight + next.weight;  // 현재 정점까지의 가중치(거리) + 다음 정점까지의 가중치

                // A -> B -> C > A -> C 라면, 굳이 더 돌아가는 루트는 최소 거리 계산에 필요없으므로 배제.
                if(dist[next.node] > nextWeight){    // 최소 거리라면 업데이트
                    dist[next.node] = nextWeight;
                    // 현재까지 이동한 거리(가중치)를 수정하며 다음 정점으로 이동
                    pq.offer(new Edge(next.node, nextWeight));
                }
            }
        }

        // 시작 정점으로부터 해당 정점까지의 최단 경로 출력
        for(int i=1;i<=V;i++){
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(dist[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        int V = readInt(); // 정점 갯수
        int E = readInt(); // 간선 갯수
        int K = readInt(); // 시작 정점

        // graph 선언
        graph = new ArrayList[V+1];
        for(int i=0;i<=V;i++) graph[i] = new ArrayList<>();

        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0;i<E;i++){
            int u = readInt();  // 시작정점
            int v = readInt();  // 도착정점
            int w = readInt();  // 간선 간의 가중치
            // 간선 정보 입력. '방향'그래프라서, 단방향 정보만 입력
            graph[u].add(new Edge(v, w));
        }

        solution(V, E, K);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static class Edge implements Comparable<Edge>{
        public final int node;      // 정점 번호
        public final int weight;    // 가중치

        public Edge(int node, int weight){
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
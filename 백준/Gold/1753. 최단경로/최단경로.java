import java.io.*;
import java.util.*;

class Main {
    /*      다익스트라 알고리즘 - 최단경로

            - 두 정점 사이에 복수의 간선 있을 수도 있음.
            - '방향'그래프 이기 때문에, 입력에서 받는 간선 정보가 단방향임.

            참고 :
            https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
            https://chb2005.tistory.com/78
    * */

    static List<Edge>[] graph;  // 방향 그래프
    static Vertex[] vertices;   // 정점 배열

    static void solution(int V, int E, int K) throws IOException {
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        vertices[K].distance = 0;   // 시작정점까지의 거리 0으로
        pq.offer(vertices[K]); // 시작정점 입력

        while(!pq.isEmpty()){
            Vertex current = pq.poll(); // 현재 정점

            for(Edge e : graph[current.id]){
                int newDist = current.distance + e.weight;  // 이전 정점의 거리 + 가중치

                // A -> B -> C > A -> C 라면, 굳이 더 돌아가는 루트는 최소 거리 계산에 필요없으므로 배제.
                if(newDist < e.target.distance){    // 최소 거리라면 업데이트
                    e.target.distance = newDist;
                    pq.offer(e.target);     // 다음 정점으로 이동
                }
            }
        }

        // 시작 정점으로부터 해당 정점까지의 최단 경로 출력
        for(int i=1;i<=V;i++){
            if(vertices[i].distance == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(vertices[i].distance);
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
        // Vertex 선언
        vertices = new Vertex[V+1];
        for(int i=1;i<=V;i++) vertices[i] = new Vertex(i, Integer.MAX_VALUE);

        for(int i=0;i<E;i++){
            int u = readInt();  // 시작정점
            int v = readInt();  // 도착정점
            int w = readInt();  // 간선 간의 가중치
            // 간선 정보 입력. '방향'그래프라서, 단방향 정보만 입력
            graph[u].add(new Edge(vertices[v], w));
        }

        solution(V, E, K);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 초반에는 간선이 이어진 정점의 id를 제공하는 데 쓰이고, 나중에는 해당 정점까지의 distance를 의미함.
    // distance 정보는 '시작 정점 -> 해당 정점까지의 거리' 를 의미하므로, 시작정점이 '1'이라면 Vertex 1의 거리는 무조건 0
    // Vertex는 각 정점 마다 unique 하고, 해당 정점으로의 간선이 여러 개 존재하더라도 같은 vertex를 가져다 쓰는거임.
    static class Vertex implements Comparable<Vertex> {
        public final int id;  // 정점 번호
        public int distance;  // 해당 정점까지의 최단 경로

        public Vertex(int id, int distance) {   // 해당 정점까지의 거리 업데이트
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex other) {    // PriorityQueue의 정렬 기준
            return Integer.compare(this.distance, other.distance);
        }
    }

    // JAVA 14 버전 이후로는 record로 표현가능.
    // record Edge(Vertex target, int weight) {}
    // 프로그램에서 불변 데이터를 모델링하는 데 사용. 단순한 데이터 전달자 역할을 하는 클래스를 선언하는 보다 간결하고 명확한 방법
    static class Edge{
        public final Vertex target;
        public final int weight;

        public Edge(Vertex target, int weight){
            this.target = target;
            this.weight = weight;
        }
    }
}

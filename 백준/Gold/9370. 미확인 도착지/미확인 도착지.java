import java.io.*;
import java.util.*;

class Main {
    /*      다익스트라 알고리즘 - 미확인 도착지

            - 다익스트라 알고리즘을 총 3번 사용. 1. (s -> g or h)  2. (g->목) 3. (h->목)
            ex) g-h 도로가 INF에 가깝고, h-> 목적지 후보가 없는 극단적인 경우
                s->g  <<<< s->h 라고 하자. 그럼 얼핏 보면
                s-> g -> h -> 목적지 or s->g->h->g 가 타당해 보일 수 있지만,
                g->h->g = 2INF가 되므로, s->h->g-> 목적지 후보보다 커지게 됨.
                따라서, g->목, h->목 2가지 경우를 다익스트라로 체크해야 함.
            - s -> g 가 INF거나, g or h -> 목적지 후보가 INF 나오는 지 체크.

            문제 : 목적지 후보 중 적어도 1개는 도달 가능하다면, s -> g or h는 무조건 true 아닌가?
                 그렇다면, 다익스트라 2번으로 가능하지 않나? g-> , h-> 한 다음에 or 조건으로 목적지 후보에 도달 가능한지만 체크.
            수정 : '최단거리' 라는 조건은, s -> t == s -> (g or h) -> t 라는 뜻. 즉, g-h 도로를 지난 게 최단거리라는 것.
                   s->t 체크용으로 s -> 다익스트라도 돌려야 함.
* */
    
    static final int INF = 1_0000_0000;
    // X : 시작 정점
    static int[] Dijkstra(int N, int X, Node[] graph) throws IOException {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1]; // 시간 배열
        Arrays.fill(dist, INF);
        dist[X] = 0;   // 시작정점 시간
        pq.offer(new Node(X, 0, null)); // ? -> X 의 가상의 간선을 만들어서 offer. 그럼 X 정점부터 시작하게 됨.

        for (int i = 0; !pq.isEmpty(); i++) {
            Node current = pq.poll();

            // 방문체크 + 지금까지 구한 최단시간이 현재 이동시간보다 짧다면, pass
            // i = 0 일 경우에는 times[X] = 0  < 0 이 되므로, i라는 조건을 넣음.
            if (i > 0 && dist[current.nextIdx] < current.d) continue;

            for (Node edge = graph[current.nextIdx]; edge != null; edge = edge.node) {
                int newDist = current.d + edge.d;
                if (dist[edge.nextIdx] > newDist) {    // 최소 거리라면 업데이트
                    dist[edge.nextIdx] = newDist;
                    pq.offer(new Node(edge.nextIdx, newDist, null));     // 다음 정점으로 이동하는 가상의 간선을 만들어서 offer
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int T = readInt(); // 테스트 케이스

        for (int i = 0; i < T; i++) {
            int n = readInt(); // 정점 갯수 (교차로)
            int m = readInt(); // 간선 갯수 (도로)
            int t = readInt(); // 목적지 후보

            Node[] graph = new Node[n + 1];

            int s = readInt(); // 시작 정점
            int g = readInt(); // 꼭 지날 교차로 1
            int h = readInt(); // 꼭 지날 교차로 2
            int ghDistance = 0; // g <-> h 거리

            for (int j = 0; j < m; j++) {
                int u = readInt();  // 시작정점
                int v = readInt();  // 도착정점
                int w = readInt();  // 간선 길이 (거리)
                // 양방향 간선 입력. 간선이 다음 간선을 가리키는 linkedlist 구조로.
                graph[u] = new Node(v, w, graph[u]);
                graph[v] = new Node(u, w, graph[v]);
                // g-h 도로 거리 기억
                if(u == Math.min(g,h) && v == Math.max(g,h)) ghDistance = w;
            }

            // 목적지 후보 입력 & sort
            int[] dest = new int[t];
            for (int j = 0; j < t; j++) dest[j] = readInt();
            Arrays.sort(dest);

            int[] sDist = Dijkstra(n, s, graph); // s -> 목적지 다익스트라
            int[] gDist = Dijkstra(n, g, graph); // g -> 목적지 다익스트라
            int[] hDist = Dijkstra(n, h, graph); // h -> 목적지 다익스트라

            // s -> 목적지 최단거리가 g-h 도로를 지나가는 최단거리와 같은지 검증
            for (int j = 0; j < t; j++) {
                if(sDist[dest[j]] == INF) continue; // 아예 도달 불가능하다면 pass

                if(sDist[dest[j]] == (sDist[g]+ghDistance+hDist[dest[j]]) ||
                        sDist[dest[j]] == (sDist[h]+ghDistance+gDist[dest[j]])
                ) sb.append(dest[j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static class Node implements Comparable<Node> {
        int nextIdx;   // 도착 정점
        int d;   // 거리 가중치
        Node node;  // 특정 정점 (ex : 1)이 가지고 있는 간선들을 linkedlist로 이어주는 역할

        Node(int next, int dist, Node node) {
            this.nextIdx = next;
            this.d = dist;
            this.node = node;
        }

        @Override   // PriorityQueue 정렬용
        public int compareTo(Node node) {
            return this.d - node.d;
        }
    }
}
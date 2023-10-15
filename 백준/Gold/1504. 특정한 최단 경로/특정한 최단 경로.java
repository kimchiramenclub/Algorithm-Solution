import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    /*  다익스트라
        - 두 정점 사이에 간선이 하나만 존재.
        - int 범위 안 넘음.
        - 1 -> , N ->, v1 --> (v1--v2 == v2--v1) 이렇게 3번 다익스트라 메서드를 돌리고, 거리가 짧은 방향으로 이동
        - 양방향
     * */

    static int N;
    static final int INF = 240_0000;    // 1 -- v1 -- v2 -- N 이 경로의 최악 케이스가 INF보다 커지지 않게 값 설정

    static int[] Dijkstra(int start, Node[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0, null));  // ? -> start의 가상의 간선을 만들어서 offer. 그럼 시작 정점부터 출발

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.end;
            // 시작 정점은 제외하고, 거리 갱신 여부를 통해 방문 체크
            if (curIdx != start && dist[curIdx] < cur.wt) continue;

            for (Node node = graph[curIdx]; node != null; node = node.n) {
                if (dist[curIdx] + node.wt < dist[node.end]) {
                    dist[node.end] = dist[curIdx] + node.wt;    // 최단 거리 갱신
                    pq.offer(new Node(node.end, dist[node.end], null)); // 최단 거리 값을 가진 가상의 간선 offer
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        N = readInt(); // 정점 갯수
        int E = readInt(); // 간선 갯수
        Node[] graph = new Node[N + 1];

        for (int i = 0; i < E; i++) {
            int a = readInt();
            int b = readInt();
            int c = readInt();
            graph[a] = new Node(b, c, graph[a]);    // 양방향 간선
            graph[b] = new Node(a, c, graph[b]);
        }
        int v1 = readInt();
        int v2 = readInt();

        int[] distFromOne = Dijkstra(1, graph);
        int[] distFromV1 = Dijkstra(v1, graph);
        int[] distFromN = Dijkstra(N, graph);

        int path1 = distFromOne[v1] + distFromV1[v2] + distFromN[v2];   // 1 -- v1 -- v2 -- N
        int path2 = distFromOne[v2] + distFromV1[v2] + distFromN[v1];   // 1 -- v2 -- v1 -- N

        if (path1 >= INF && path2 >= INF) System.out.println(-1);    // 두 경로가 모두 불가능하다면
        else System.out.println(Math.min(path1, path2));
    }

    static class Node implements Comparable<Node> {
        int end, wt;
        Node n;

        Node(int end, int wt, Node n) {
            this.end = end;
            this.wt = wt;
            this.n = n;
        }

        @Override
        public int compareTo(Node node) {
            return this.wt - node.wt;
        }
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
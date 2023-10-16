import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    /*  다익스트라
        - 무조건 루트가 있음
     * */

    static int N;
    static final int INF = Integer.MAX_VALUE;

    static int Dijkstra(int start, int end, Node[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[start] = 0;
        pq.offer(new Node(start, 0, null));  // ? -> start의 가상의 간선을 만들어서 offer. 그럼 시작 정점부터 출발

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.end;
            // 시작 정점은 제외하고, 거리 갱신 여부를 통해 방문 체크
            if (curIdx != start && cost[curIdx] < cur.wt) continue;

            for (Node node = graph[curIdx]; node != null; node = node.n) {
                if (cost[curIdx] + node.wt < cost[node.end]) {
                    cost[node.end] = cost[curIdx] + node.wt;    // 최단 거리 갱신
                    pq.offer(new Node(node.end, cost[node.end], null)); // 최단 거리 값을 가진 가상의 간선 offer
                }
            }
        }
        return cost[end];
    }

    public static void main(String[] args) throws IOException {
        N = readInt(); // 정점 갯수
        int M = readInt(); // 간선 갯수
        Node[] graph = new Node[N + 1];

        for (int i = 0; i < M; i++) {
            int s = readInt();
            int e = readInt();
            int cost = readInt();
            graph[s] = new Node(e, cost, graph[s]);    // 단방향 간선
        }
        int A = readInt();
        int B = readInt();

        System.out.println(Dijkstra(A,B,graph));
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
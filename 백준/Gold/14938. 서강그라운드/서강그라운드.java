import java.io.IOException;
import java.util.*;

class Main {
    /*  다익스트라
        - 이동 범위에 한계가 있음
        - Node에 item도 필요
        - 양방향

        - 문제 : 방문배열 대신 priority queue를 써서 하는 경우, 최단거리가 반복 갱신 되는 경우가 있어 item이 원래보다 더 커짐.
          수정 : item visited 배열 만들어서 한번 item을 획득했으면 재획득하지 않게
     *게*/

    static int n;   // 지역갯수
    static int m;   // 수색범위
    static final int INF = Integer.MAX_VALUE;

    static int Dijkstra(int start, Node[] graph, int[] items) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];    // 거리 배열
        boolean[] visited = new boolean[n + 1];     // item 중복 합산 방지용 배열
        int item = 0;

        Arrays.fill(dist, INF);
        dist[start] = 0;
        visited[start] = true;
        item += items[start];

        // ? -> start의 가상의 간선을 만들어서 offer. 그럼 시작 정점부터 출발
        pq.offer(new Node(start, 0, null));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.end;

            for (Node node = graph[curIdx]; node != null; node = node.n) {
                int newDist = dist[curIdx] + node.wt;

                if (newDist <= m && newDist < dist[node.end]) { // 수색 범위 내이며, 최단거리를 갱신한다면
                    dist[node.end] = newDist;    // 최단 거리 갱신
                    if(!visited[node.end]) {    // 이미 방문한 지역이면, pass
                        item += items[node.end];    // 방문 지역의 item 합산
                        visited[node.end] = true;
                    }
                    pq.offer(new Node(node.end, newDist, null)); // 최단 거리 값을 가진 가상의 간선 offer
                }
            }
        }
        return item;
    }

    public static void main(String[] args) throws IOException {
        n = readInt(); // 지역 갯수
        m = readInt(); // 수색 범위
        int r = readInt(); // 길 갯수 (간선 갯수)
        Node[] graph = new Node[n + 1]; // 지역 graph
        int[] items = new int[n + 1];   // 각 지역 별 item 수

        for (int i = 1; i <= n; i++) items[i] = readInt();
        for (int i = 0; i < r; i++) {
            int s = readInt();
            int e = readInt();
            int l = readInt();
            graph[s] = new Node(e, l, graph[s]);    // 양방향 간선
            graph[e] = new Node(s, l, graph[e]);
        }

        int maxItem = 0;
        for (int i = 1; i <= n; i++) {
            maxItem = Math.max(maxItem, Dijkstra(i, graph, items));
        }
        System.out.println(maxItem);
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
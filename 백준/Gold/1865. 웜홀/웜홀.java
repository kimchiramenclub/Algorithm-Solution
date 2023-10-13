import java.io.IOException;

class Main {
    /*  벨만-포드 알고리즘 : 단방향 음수 가중치
        - 음의 사이클이 있기만 하면 YES, 없으면 NO

        - 시작 노드를 1~N으로 잡으면, 어떤 시작 노드에서 음의 사이클이 나타날 지 몰라서 벨만-포드 알고리즘 자체도 N번 돌려야함.
        - 이 문제에서는 음의 사이클 여부만 찾고 있음. 따라서, 실제 최단 거리가 어떻게 갱신되는지는 중요치 않음.
        - 가상 시작 노드 0을 잡고, 모든 노드로 0의 가중치로 도로가 연결되게 하면, 맨 처음에 모든 dist 값이 0으로 갱신됨.
        - 그럼에도 벨만-포드 알고리즘을 돌릴 시 갱신되는 값이 있다면, 음의 사이클이 있는 것.
     *  */

    static Node[] graph;

    static boolean bellmanFord(int N) {
        int[] dist = new int[N + 1];    // 가상 시작 노드 0에서 출발했다 가정하므로, 모든 가중치가 0
        boolean update = false;

        // N-1번 간선 순회 반복 작업 후, 마지막 음의 사이클 확인 작업
        for (int i = 1; i <= N; i++) {
            update = false;
            for (int s = 1; s <= N; s++) {
                for (Node node = graph[s]; node != null; node = node.n) {
                    if (dist[s] + node.weight < dist[node.end]) {
                        dist[node.end] = dist[s] + node.weight;
                        if (!update) update = true;
                    }
                }
            }
            if (!update) break; // 음의 사이클이 없다면, break
        }

        return update;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int TC = readInt();

        for (int i = 0; i < TC; i++) {
            int N = readInt();  // 노드 갯수
            int M = readInt();  // 간선 갯수
            int W = readInt();  // 웜홀 갯수

            graph = new Node[N + 1];

            // 도로 입력
            for (int j = 0; j < M; j++) {
                int S = readInt();
                int E = readInt();
                int T = readInt();
                graph[S] = new Node(E, T, graph[S]);
                graph[E] = new Node(S, T, graph[E]); // 양방향 도로
            }
            // 웜홀 입력
            for (int j = 0; j < W; j++) {
                int S = readInt();
                int E = readInt();
                int T = readInt();
                graph[S] = new Node(E, -T, graph[S]); // 웜홀은 단방향 도로
            }

            // 음의 사이클이
            sb.append(bellmanFord(N) ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    static class Node {
        int end, weight;
        Node n;

        Node(int end, int weight, Node n) {
            this.end = end;
            this.weight = weight;
            this.n = n;
        }
    }

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

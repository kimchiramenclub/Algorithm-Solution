import java.io.IOException;
import java.util.ArrayList;

class Main {
    /*  벨만-포드 알고리즘 : 단방향 음수 가중치
        - 음의 사이클이 있기만 하면 YES, 없으면 NO

        - 시작 노드를 1~N으로 잡으면, 어떤 시작 노드에서 음의 사이클이 나타날 지 몰라서 벨만-포드 알고리즘 자체도 N번 돌려야함.
        - 이 문제에서는 음의 사이클 여부만 찾고 있음. 따라서, 실제 거리가 어떻게 갱신되는지는 중요치 않음.
        - 가상 시작 노드 0을 잡고, 모든 노드로 0의 가중치로 도로가 연결되게 하면, 맨 처음에 모든 dist 값이 0으로 갱신됨.
        - 그럼에도 벨만-포드 알고리즘을 돌릴 시 갱신되는 값이 있다면, 음의 사이클이 있는 것.
     *  */

    static Node[] nodes;

    static boolean bellmanFord(int N) {
        int[] dist = new int[N + 1];
        boolean update = false;

        // N-1번 간선 순회 반복 작업 후, 마지막 음의 사이클 확인 작업
        for (int i = 1; i <= N; i++) {
            update = false;
            for (int j = 1; j <= N; j++) {
                for (Edge edge : nodes[j].edges) {
                    if (dist[edge.start] + edge.weight < dist[edge.end]) {
                        dist[edge.end] = dist[edge.start] + edge.weight;
                        if (!update) update = true;
                    }
                }
            }
            if (!update) break;
        }
        
        return update; // 음수 사이클이 없음
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int TC = readInt();

        for (int i = 0; i < TC; i++) {
            int N = readInt();  // 노드 갯수
            int M = readInt();  // 간선 갯수
            int W = readInt();  // 웜홀 갯수

            nodes = new Node[N + 1];
            for (int j = 1; j <= N; j++) nodes[j] = new Node();

            // 도로 입력
            for (int j = 0; j < M; j++) {
                int S = readInt();
                int E = readInt();
                int T = readInt();
                nodes[S].addEdge(S, E, T);
                nodes[E].addEdge(E, S, T); // 양방향 도로
            }
            // 웜홀 입력
            for (int j = 0; j < W; j++) {
                int S = readInt();
                int E = readInt();
                int T = readInt();
                nodes[S].addEdge(S, E, -T); // 웜홀은 단방향 도로
            }

            // 음의 사이클이
            sb.append(bellmanFord(N) ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    static class Node {
        ArrayList<Edge> edges = new ArrayList<>();

        void addEdge(int start, int end, int weight) {
            edges.add(new Edge(start, end, weight));
        }
    }

    static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
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

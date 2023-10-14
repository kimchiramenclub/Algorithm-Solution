import java.io.IOException;
import java.util.Arrays;

class Main {
    /*  벨만-포드 알고리즘 : 단방향 음수 가중치
        - 음의 사이클도 체크

        문제 : 출력초과
        - 언더플로우가 나서 그런거였음. N-1번 반복하다 보니, 6천만 * N까지 낮아질 수 있기 때문

        문제2 : N = 2이면서 음수 가중치일 때 오답
        - N번째 cycle을 없애버렸기 때문...
        - cycle N-1번만 돌리고 싶어서 그냥 출력부에 N > 2 조건을 달아놓음.
     *  */

    static Node[] graph;
    static final long INF = Long.MAX_VALUE;
    static long[] time;

    static boolean bellmanFord(int N) {
        Arrays.fill(time, INF);
        time[1] = 0;
        boolean update = false;

        // N-1 번 순회
        for (int i = 1; i <= N - 1; i++) {
            update = false;
            for (int s = 1; s <= N; s++) {
                for (Node node = graph[s]; node != null; node = node.n) {
                    if (time[s] != INF && time[s] + node.weight < time[node.end]) {
                        time[node.end] = time[s] + node.weight;
                        if (!update) update = true;
                    }
                }
            }
            if (!update) break;  // 순회 중에 update가 없으면, break
        }

        return !update; // N-1번까지 update가 있었으면, 음의 사이클이 확정됨. 굳이 N번째를 돌릴 필요는 없음
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int M = readInt();
        graph = new Node[N + 1];
        time = new long[N + 1];

        for (int i = 0; i < M; i++) {
            int A = readInt();
            int B = readInt();
            int C = readInt();
            graph[A] = new Node(B, C, graph[A]);
        }

        if (!bellmanFord(N) && N > 2) {
            sb.append(-1);
        } else{
            for (int e = 2; e <= N; e++) sb.append(time[e] == INF ? -1 : time[e]).append('\n');
            sb.setLength(sb.length() - 1);
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


    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int sign = 1, n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        if (c == '-') sign = -1;
        else n = c & 15;
        while (47 < (c = read()) && c < 58) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
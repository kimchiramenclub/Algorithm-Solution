import java.io.*;
import java.util.*;

class Main {
    /*      트리 지름 찾기
            - 골드 4 버전 문제와 입력만 다르고 같음
    * */

    static ArrayList<ArrayList<Edge>> tree;
    static boolean[] visited;
    static int total = 0;
    static int startNode;

    static int solution(int N) {
        findNode(1, 0);

        Arrays.fill(visited, false);
        total = 0;
        findCost(startNode, 0);

        return total;
    }

    // 루트인 1로부터 가중치 합이 가장 높은 node
    static void findNode(int node, int sum) {
        visited[node] = true;

        // 연결된 Node 탐색
        for (Edge E : tree.get(node)) {
            if (!visited[E.neighbor]) {
                findNode(E.neighbor, sum + E.distance);
            }
        }
        // 뿌리의 끝까지 갔다면, 가중치 합 갱신
        if (total < sum) {
            total = sum;
            startNode = node;
        }
    }

    static void findCost(int node, int sum) {
        visited[node] = true;

        // 연결된 Node 탐색
        for (Edge E : tree.get(node)) {
            if (!visited[E.neighbor]) {
                findCost(E.neighbor, sum + E.distance);
            }
        }
        // 트리의 지름만 구하면 되므로, 가중치 합 값만 갱신
        total = Math.max(total, sum);
    }

    public static void main(String[] args) throws IOException {

        int V = readInt();

        // 트리 초기화
        tree = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) tree.add(new ArrayList<>());
        // 방문 배열 선언
        visited = new boolean[V + 1];

        // 간선 정보 입력
        for (int i = 1; i <= V; i++) {
            int n = readInt(); // 정점 번호 pass

            while(true){
                int node = readInt();
                if(node == -1) break;   // -1이 나올 경우 break
                int distance = readInt();

                tree.get(n).add(new Edge(node, distance));
            }
        }
        // 출력
        System.out.println(solution(V));
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0, sign = 1;
        if((c = System.in.read()) == '-') sign = -1;
        else n = c & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }

    // 간선 정보를 저장하는 클래스
    static class Edge {
        int neighbor;   // parent, child 구분 없이 이어진 node 정보
        int distance;     // 거리

        Edge(int neighbor, int distance) {
            this.neighbor = neighbor;
            this.distance = distance;
        }
    }
}

import java.io.IOException;
import java.util.*;

class Main {
    /*      트리 지름 찾기
            - Double DFS
            - 첫 번째 DFS : 아무 노드를 정해서, 그 노드로부터 가장 가중치 합이 높은 노드를 찾음.
              ㄴ 어느 노드에서 시작하던 A노드까지의 가중치 합이 가장 크다면, A를 지름의 끝으로 두는 게 제일 큰 지름을 만드는데 필수
              두 번쨰 DFS : A로부터 가장 가중치 합이 높은 노드 찾기

              의문점 : 첫번째 DFS에서 최대 가중치 합이 같은 node가 여러 개 있을 경우?
              ex) 1 -> 2 -> 4 -> 7 이 루트랑
                  1 -> 3 -> 5 -> 9 이 루트가 가중치 합이 같다고 가정

                  그러면,
                  9 -> 5 -> 3 -> 6 -> 12
                  7 -> 4 -> 2 -> 1 ->  3 -> 6 -> 12 이렇게 7번 node의 경우가 가중치 합이 높아지지 않나?
              
              해결 : 이렇게 최대 가중치 합이 같은 node가 여러개 있을 경우, 지들끼리 지름을 형성하므로 어차피 최대 값이 나옴.
                    설령 최대 가중치 합이 같은 node가 3개 이상이여도, 어차피 하나만 잘 고르면 그 node가 가장 가중치 합이 높은 node를 찾아갈거임.    
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
                findNode(E.neighbor, sum + E.weight);
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
                findCost(E.neighbor, sum + E.weight);
            }
        }
        // 트리의 지름만 구하면 되므로, 가중치 합 값만 갱신
        total = Math.max(total, sum);
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();

        // 트리 초기화
        tree = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());
        // 방문 배열 선언
        visited = new boolean[N + 1];

        // 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            int parent = readInt();
            int child = readInt();
            int weight = readInt();
            tree.get(parent).add(new Edge(child, weight));  // child 정보 저장
            tree.get(child).add(new Edge(parent, weight));  // parent 정보 저장
        }
        // 출력
        System.out.println(solution(N));
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // 간선 정보를 저장하는 클래스
    static class Edge {
        int neighbor;   // parent, child 구분 없이 이어진 node 정보
        int weight;     // 가중치

        Edge(int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}

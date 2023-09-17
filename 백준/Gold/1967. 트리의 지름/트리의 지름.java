import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    /*      트리 지름 찾기
            - Double DFS
            - 첫 번째 DFS : 아무 노드를 정해서, 그 노드로부터 가장 가중치 합이 높은 노드를 찾음.
              ㄴ 어느 노드에서 시작하던 A노드까지의 가중치 합이 가장 크다면, A를 지름의 끝으로 두는 게 제일 큰 지름을 만드는데 필수
              두 번쨰 DFS : A로부터 가장 가중치 합이 높은 노드 찾기

    * */


    static ArrayList<ArrayList<Edge>> tree;
    static boolean[] visited;
    static int total = 0;
    static int A, B;

    static int solution(int N){
        findA(1, 0);

        Arrays.fill(visited, false);
        total = 0;
        findCost(A, 0);

        return total;
    }

    static void findA(int node, int sum){
        visited[node] = true;

        for(Edge E : tree.get(node)){
            if(!visited[E.neighbor]) {
                findA(E.neighbor, sum + E.weight);
            }
        }

        if(total < sum){
            total = sum;
            A = node;
        }
    }

    static void findCost(int node, int sum){
        visited[node] = true;

        for(Edge E : tree.get(node)){
            if(!visited[E.neighbor]) {
                findCost(E.neighbor, sum + E.weight);
            }
        }
        total = Math.max(total, sum);
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();

        // 트리 초기화
        tree = new ArrayList<>(N+1);
        for(int i=0;i<=N;i++) tree.add(new ArrayList<>());
        // 방문 배열 선언
        visited = new boolean[N+1];

        // 간선 정보 입력
        for(int i=0;i<N-1;i++){
            int parent = readInt();
            int child = readInt();
            int weight = readInt();
            tree.get(parent).add(new Edge(child, weight));
            tree.get(child).add(new Edge(parent, weight));
        }

        System.out.println(solution(N));
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static class Edge{
        int neighbor;
        int weight;

        Edge(int neighbor, int weight){
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}

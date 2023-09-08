import java.io.IOException;
import java.util.*;

class Main {
        /*     트리
               - 숫자의 순서만으로는 어느 쪽이 child, parent인지 알 수 없음.
               - tree 구조를 다 완성하고, 1부터 DFS 혹은 BFS로 돌기 시작해야 체크할 수 있음.
        * */

    static ArrayList<LinkedList<Integer>> tree;

    static void BFS(int N){
        StringBuilder sb = new StringBuilder();
        int[] parents = new int[N+1]; // 부모 노드 정보를 담는 array
        Queue<Integer> nodeQueue = new ArrayDeque<>();
        // 초기값 대입
        parents[1] = Integer.MAX_VALUE;
        nodeQueue.offer(1);
        // BFS
        while(!nodeQueue.isEmpty()){
            int parent = nodeQueue.poll();

            for(int child : tree.get(parent)){
                if(parents[child] != 0) continue;   // 이미 부모노드 정보가 있다면, pass
                parents[child] = parent;    // 부모 노드 정보 저장
                nodeQueue.offer(child);
            }
        }

        for(int i=2;i<=N;i++) sb.append(parents[i]).append("\n");
        System.out.println(sb);
    }

    // 트리 구조에서만 가능.
    // graph 구조처럼 loop가 있을 경우, parent가 먼저 설정되어 있어서 더 아래의 node까지 접근 못할 수 있음.
    static void DFS(int N){
        StringBuilder sb = new StringBuilder();
        int[] parents = new int[N+1]; // 부모 노드 정보를 담는 array
        Stack<Integer> nodeStack = new Stack<>();
        // 초기값 대입
        nodeStack.push(1);
        parents[1] = Integer.MAX_VALUE;
        // DFS
        while(!nodeStack.isEmpty()){
            int parent = nodeStack.pop();

            for(int child : tree.get(parent)){
                if(parents[child] == 0){    // 부모 노드 정보가 없는 경우에만
                    parents[child] = parent;    // 부모 노드 정보 저장
                    nodeStack.push(child);
                }
            }
        }

        for(int i=2;i<=N;i++) sb.append(parents[i]).append("\n");
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();

        tree = new ArrayList<>(N+1);
        for (int i = 0; i <= N; i++) tree.add(new LinkedList<>());
        // 트리 입력
        for (int i = 1; i < N; i++){
            int tmp1 = readInt();
            int tmp2 = readInt();
            tree.get(tmp1).add(tmp2);
            tree.get(tmp2).add(tmp1);
        }

        BFS(N);
    }

    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
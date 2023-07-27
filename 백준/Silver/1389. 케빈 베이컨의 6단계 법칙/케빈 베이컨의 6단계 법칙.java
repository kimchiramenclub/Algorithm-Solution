import java.io.*;
import java.util.*;

class Main {
    /*  플로이드-워셜 알고리즘
        - 쌍방향 그래프
        - 배열 형태로
        - 배열의 가로줄 합의 최소 index를 찾는 문제 (자신은 제외)
    */

    static int solution(int N, int[][] graph){
        for(int m=1;m<=N;m++){         // 중간노드
            for(int s=1;s<=N;s++){     // 시작노드
                for(int e=1;e<=N;e++){ // 끝노드
                    // s노드와 e노드의 연결 여부 갱신
                    if(s==e) graph[s][e] = 0;
                    graph[s][e] = Math.min(graph[s][e], graph[s][m]+graph[m][e]);
                }
            }
        }

        int sum = Integer.MAX_VALUE;
        int index = 0;
        for(int i=1;i<=N;i++){
            int tmp = Arrays.stream(graph[i]).sum() - graph[i][i]; // 각 노드의 케빈 베이컨 합(자기 자신 제외) 
            if(tmp < sum){
                sum = tmp;
                index = i;
            }
        }
        return index;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N+1][N+1];
        for(int i=1;i<=N;i++) Arrays.fill(graph[i], 100); // 최초에 연결되지 않는 간선 INF로 채움

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph[j][k] = 1;
            graph[k][j] = 1;
        }

        System.out.println(solution(N, graph));
    }
}
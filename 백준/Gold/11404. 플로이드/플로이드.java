import java.io.*;
import java.util.*;

class Main {
    /*  플로이드-워셜 알고리즘
        - 단방향 그래프
        - 배열 형태로
        - 정점 s와 정점 e 사이에 여러 간선이 존재 가능. 문제는 최소 비용의 간선만 고려
    */

    static void solution(int n, int[][] graph) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 플루이드 워셜
        for(int m = 1; m<=n; m++){
            for(int s=1;s<=n; s++){
                for(int e=1;e<=n;e++){
                    // 값이 둘다 INF일 때의 합을 막기 위함
                    if(graph[s][m] < Integer.MAX_VALUE && graph[m][e] < Integer.MAX_VALUE)
                    graph[s][e] = Math.min(graph[s][e], graph[s][m]+graph[m][e]);
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1; j<=n;j++){
                if(i == j || graph[i][j] == Integer.MAX_VALUE) bw.write("0 ");
                else bw.write(graph[i][j]+" ");
            }
            bw.write("\n");
        }

        bw.flush();
    }




    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;

       int n = Integer.parseInt(br.readLine()); // 도시의 수
       int m = Integer.parseInt(br.readLine()); // 버스의 수
       int[][] graph = new int[n+1][n+1];
       for(int i=1;i<=n;i++) Arrays.fill(graph[i], Integer.MAX_VALUE); // 간선 없는 구간 INF로 채우기

       for(int i=1;i<=m;i++){
           st = new StringTokenizer(br.readLine());
           int j = Integer.parseInt(st.nextToken());
           int k = Integer.parseInt(st.nextToken());
           // 버스 노선이 여러 개 가능해서, 가장 비용적은 노선만 기록
           graph[j][k] = Math.min(graph[j][k], Integer.parseInt(st.nextToken()));
       }

       solution(n, graph);
    }
}
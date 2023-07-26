import java.io.*;
import java.util.*;

class Main {
    /*  플로이드-워셜 알고리즘
        https://chanhuiseok.github.io/posts/algo-50/
        - for문을 3번 반복해서 (중간 노드 - 시작 노드 - 끝 노드), 모든 노드 간의 최단 거리를 구하는 알고리즘
        ex)  1->2->3->4->5
            - 2를 중간 노드로 해서, 1->2->3을 통해 1->3이 업데이트
            - 3을 중간 노드로 해서, 1->3->4를 통해 1->4가 업데이트
            5->4->3->2->1
            - 3->2->1 통해 3->1 업데이트
            - 4->3->1 통해 4->1 업데이트
        - 2->5->4->1->3 이런 식으로 순서가 복잡한 경우에도,
            k=1 :   4->1->3 : 4->3
            k=4 :   5->4->3 : 5->3
            k=5 :   2->5->3 : 2->3
            이렇게 구해짐. 퍼즐처럼 끼워맞춰 가기 때문에 존재만 한다면 무조건 구하게 되어있음.
    */

    static void solution(byte N, byte[][] graph) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int m=1;m<=N;m++){         // 중간노드
            for(int s=1;s<=N;s++){     // 시작노드
                for(int e=1;e<=N;e++){ // 끝노드
                    // s노드와 e노드의 연결 여부 갱신
                    if(graph[s][m]+graph[m][e]==2) graph[s][e]=1;
                }
            }
        }

        // 출력문
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++) bw.write(graph[i][j]+" ");
            bw.write("\n");
        }
        bw.flush();
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        byte N = Byte.parseByte(br.readLine());
        byte[][] graph = new byte[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            // 간선이 있다면 true, 없으면 false
            for(int j=1;j<=N;j++) graph[i][j] = Byte.parseByte(st.nextToken());
        }

        solution(N, graph);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    /*  dp
        - RGB 색 각각 고유 비용이 있음
        - 각 i단계에서 RGB 중 하나의 색을 칠했을 때, 그 선택지가 최소 비용이 되게 유지 (3가지 색칠은 다 값을 기억하도록)
    * */

    static int N;
    static int[][] RGB;

    static int solution(){
        int[][] minCost = new int[N][3]; // N개의 단계마다 각각 R(0),G(1),B(2) 로 칠했을 때의 최소 비용

        // 초기값
        minCost[0][0] = RGB[0][0];
        minCost[0][1] = RGB[0][1];
        minCost[0][2] = RGB[0][2];
        
        // 각 집마다 각 인덱스에 맞는 색깔을 칠했을 때 비용 + 이전 집까지의 색칠이 최솟값이 되는 비용
        for(int i=1;i<N;i++){
            minCost[i][0] = RGB[i][0] + Math.min(minCost[i-1][1], minCost[i-1][2]);
            minCost[i][1] = RGB[i][1] + Math.min(minCost[i-1][0], minCost[i-1][2]);
            minCost[i][2] = RGB[i][2] + Math.min(minCost[i-1][0], minCost[i-1][1]);
        }

        // 마지막 집의 색 중에 최솟값 리턴
        return Math.min(minCost[N-1][0], Math.min(minCost[N-1][1], minCost[N-1][2]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}

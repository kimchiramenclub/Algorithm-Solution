import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /* 브루트포스 : 완전탐색
       모든 경우의 수를 시행하면서 답을 탐색

       - 이중 for문 사용
       - 자신을 다른 사람들 모두와 비교하면서, 자기의 등수를 매김
    */


    public static int[] solution(int N, int[][] size) {
        int[] answer = new int[N];
        
        for(int i=0;i<N;i++){
            int rank = 1;
            for(int j=0;j<N;j++){
                if(i == j) continue; // 자기 자신은 pass
                // 자신보다 더 큰 사람이 나오면, rank를 낮춤
                if(size[i][0] < size[j][0] && size[i][1] < size[j][1]){rank++;}
            }
            answer[i] = rank;
        }
        return answer;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int[][] size;
        int[] answer;

        // 입력부
        int N = Integer.parseInt(br.readLine());
        size = new int[N][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            size[i][0] = Integer.parseInt(st.nextToken());
            size[i][1] = Integer.parseInt(st.nextToken());
        }
        answer = solution(N, size);

        // 출력부
        for(int i : answer){
            bw.write(i+" ");
        }
        bw.flush();
        bw.close();
    }
}
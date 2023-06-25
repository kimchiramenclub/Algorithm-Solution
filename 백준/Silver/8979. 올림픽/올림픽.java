import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /* 브루트포스 방식 사용
        순서를 알고 싶은 나라와 전체 나라 비교
    */

    public static int solution(int N, int K, int[][] medals) {
        int rank = 1;
        for(int i =0; i<N; i++){
            if(i == K) continue;
            else if(medals[i][1] > medals[K][1]) rank++;
            else if(medals[i][1] == medals[K][1] && medals[i][2] > medals[K][2]) rank++;
            else if(medals[i][1] == medals[K][1] && medals[i][2] == medals[K][2] && medals[i][3] > medals[K][3]) rank++;
        }
        return rank;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] medals = new int[N][4];

        //입력부
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            // 국가 번호를 먼저 받고, 배열에서 해당되는 국가번호 위치에 넣기. -1 : index 보정
            int num = Integer.parseInt(st.nextToken()) - 1;
            // 실제 국가번호 값 보정
            medals[num][0] = num + 1;
            for(int j=1;j<=3;j++){
                medals[num][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, K-1, medals)); // K - 1 : index 0 시작인 것 보정
    }
}
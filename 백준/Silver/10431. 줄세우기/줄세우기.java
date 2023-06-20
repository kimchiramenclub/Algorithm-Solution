import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /* 삽입정렬
       - 모든 case가 순서대로 있으면, 이동이 0, 복잡도는 O(n)
       - O(n^2) 방식
       - 이중 for문을 이용해서, j = i+1인 위치에서 더 작은 값이 나올 때까지 교체(tmp사용)해가며 앞으로 이동.
            for(j = i+1; j > 0; j--)
       - i index는 i < N-1 로, 마지막 index를 포함하지 않음.
    */

    public static int[][] solution(int[][] testCase) {
        int[][] answer = new int[testCase.length][2];
        int tmp = 0;

        for (int i = 0; i < answer.length; i++) {  // 시행 케이스 index
            answer[i][0] = i + 1; // 케이스 번호 삽입

            // 삽입 정렬
            for (int j = 1; j < 20; j++) {
                for (int k = j + 1; k > 1; k--) {
                    if(testCase[i][k] < testCase[i][k-1]) {
                        // 앞, 뒤 값 교체
                        tmp = testCase[i][k-1];
                        testCase[i][k-1] = testCase[i][k];
                        testCase[i][k] = tmp;
                        answer[i][1]++;
                    } else {break;}
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] testCase = new int[N][21];
        StringTokenizer st;

        // 테스트케이스에 값 입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<21;j++){
                testCase[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 답 출력
        for (int[] i : solution(testCase)) {
            for (int j : i) {
                bw.write(j + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
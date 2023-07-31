import java.io.*;
import java.util.*;

class Main {
    /*  브루트포스 + 비둘기집 원리

        - 16개의 모델에 각각 이진법으로 값을 부여
            INFP = 1111(2)   ESTJ = 0000(2)
        - 그럼 심리적 거리를 각 인덱스를 빼는 걸로 구할 수 있음.
    */

    static int solution(String[] MBTI) {
        // MBTI를 이진법으로 나타내서, 각 MBTI 당 학생 수를 알려주는 배열
        int[][] mBin = new int[16][2];
        for (int i = 0; i < 16; i++) mBin[i][1] = i; // MBTI 정보 저장
        char[] mb = {'I', 'N', 'F', 'P'};
        int sum;

        // MBTI 분류
        for (String M : MBTI) {
            sum = 0;
            for (int i = 0; i < 4; i++) {
                if (M.charAt(i) == mb[i])
                    sum += (int) Math.pow(2, 3 - i); // 2진법으로 MBTI 분류. INFP = 1111(15) ESTJ = 0000(0)
            }
            mBin[sum][0]++;
        }
        // 학생 수에 따라 오름차순 정렬
        Arrays.sort(mBin, (o1, o2) -> Integer.compare(o2[0], o1[0]));

        return checkDist(mBin);
    }

    // 브루트포스 메서드
    // dist : 심리적 거리
    static int checkDist(int[][] mBin) {
        int dist = Integer.MAX_VALUE; // 심리적인 거리

        // 첫 번째 학생 선정
        for (int i = 0; i <= 13; i++) {
            if (mBin[i][0] >= 3) return 0; // 3명을 같은 그룹에서 선정하면, 심리적 거리 = 0
            for (int j = i + 1; j <= 14; j++) {
                if (mBin[j][0] == 0) continue;
                if (mBin[i][0] + mBin[j][0] >= 3) {  // 두 그룹에서 모든 학생이 선정됐다면, 심리적 거리 = (두 그룹 간의 심리적 거리) * 2
                    dist = Math.min(dist, calDist(mBin[i][1], mBin[j][1]) * 2);
                }
                for (int k = j + 1; k <= 15; k++) {
                    if(mBin[k][0] > 0) dist = Math.min(dist,
                            calDist(mBin[i][1],mBin[j][1])+calDist(mBin[j][1],mBin[k][1])+calDist(mBin[k][1],mBin[i][1])
                            );
                }
            }
        }
        return dist;
    }

    // 거리 재는 메서드
    static int calDist(int n1, int n2) {
        int tmp = n1 ^ n2;   // MBTI의 XOR 연산을 통해, 심리적 거리를 2진법으로 얻어냄.
        int dist = 0;
        for (int i = 8; i >= 1; i /= 2) {
            dist += tmp / i;      // MBTI 항목별로 거리 합산
            tmp %= i;
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            String[] MBTI = new String[N];
            for (int j = 0; j < N; j++) {
                MBTI[j] = st.nextToken();
            }
            bw.write(solution(MBTI) + "\n");
        }

        bw.flush();
    }
}

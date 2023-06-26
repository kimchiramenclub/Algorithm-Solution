import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /* 브루트포스 문제
        - 고장 안 난 버튼으로 가장 가까운 채널 이동 ex) 5457 -> 5455++
        - -, + 버튼은 고장 X. 가장 가까운 채널 이동하고, N과의 차이로 몇 번 추가 이동할 지 알 수 있음.

        * 주의
        - 1000 1 0 -> 999 눌러야 하는데, 맨 앞자리 0은 안 누르는거나 다름없음. 답이 4가 되야함

       1. N의 각 자릿수마다 가장 가까운 숫자를 대입. 재귀로 작은 숫자 넣은 케이스, 큰 숫자 넣은 케이스 모두 체크
        2. 최종 자리까지 숫자를 대입한 후, 실제 N과의 차이가 가장 적은 케이스 체크.
        3. 채널 번호 치는 것보다 +,- 이동이 더 적은지 최종 체크.

        수정 : 예외상황이 너무 많아서, 그냥 모든 경우의 수를 돌려보게 바꾸기로.
     */
    public static boolean[] isBroke = new boolean[10];
    public static int N;
    public static int answer = Integer.MAX_VALUE; // answer 최솟값 설정을 위해 시작값을 더 큰 값으로 잡음.

    public static int solution(int M) throws IOException {
        int channel = 100;

        dfs(0,  0);
        // +,-만 눌러서 이동하는 게 빠른지, 가장 가까운 채널 입력이 빠른지 체크
        return Math.min(answer, Math.abs(N - channel));
    }

    public static void dfs(int index, int channel){
        if(index != 0){
            // 채널 입력 + (+,-) 버튼으로 이동 click 값
            int move = index + Math.abs(N - channel);
            answer = Math.min(move, answer);
        }
        // 고장나지 않은 버튼 하나하나 다 입력
        for(int i = 0;i<10;i++) {
            if (!isBroke[i]) {
                if(index < 6) dfs(index + 1, channel * 10 + i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if(M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) isBroke[Integer.parseInt(st.nextToken())] = true;
        }

        System.out.println(solution(M));
    }
}
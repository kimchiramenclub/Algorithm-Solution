package BackJoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back_1107 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] button = new boolean[12];
        int channel = 100; // 현재 채널

        Arrays.fill(button,true); // 리모콘 버튼

        for(int i=0;i<M;i++) {
            button[Integer.parseInt(st.nextToken())] = false; // 고장난 버튼은 false. 사용불가
        }

        // +, - 로만 옮기는 게 빠를 때 (97~103만 가능)
        // 숫자 버튼만 고장
        // 숫자로 먼저 제일 가깝게 간 후, + - '만' 하는 것보다 빠르면 +,-만 하는건 하다가 자동 exit 하게

        // 고장난 버튼으로 가장 가까운 숫자?

        // 1. 먼저 주어진 N을 몇의 자리인지 반복문으로 파악하고,
                // ex)  1 | 0 | 0  -- 1 * 10^  "2"

        // 2. i index를 이용해 리모콘 숫자 버튼을 N의 맨 앞자리부터 i만큼씩 멀어지면서 해당되는 버튼 살아있는 지 체크
            // ex) 5 -> 4,5 ok? -> 3, 6 ok?  (0~9라는 범위를 넘지 않게 조건)
            // 맨 앞이 "1"일 경우, 2는 몰라도 0은 입력이 줄어드는 것 같음.  ex) 100 -> 입력: 99  O   입력: 099 X
            // N을 반올림하면 덜 체크 필요할 것 같음.
                  // 자릿수만큼 반올림해서  5455 100의 자리에서 반올림 -> 5000


                // ex) 239 -> 200   1보다 2 유리


        // 3. 다음 자릿수도 차례대로 찾음.

        // 4. 조합한 숫자 vs N 해서 그 차이만큼 출력 숫자에 +, -  삼중 연산자

    }


}

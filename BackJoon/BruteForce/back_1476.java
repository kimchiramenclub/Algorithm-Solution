package BackJoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back_1476 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(st.nextToken()); // 0~14 (0 => 15)
        int S = Integer.parseInt(st.nextToken()); // 0~27 (0 => 28)
        int M = Integer.parseInt(st.nextToken()); // 0~18 (0 => 19)
//        int[] ESM = {1, 1, 1}; // E,S,M과 값이 같을 때까지 이용할 배열
        // 나머지의 원리를 이용해 더 쉽게 가능

        for(int year=1;year<=7980;year++) {
            if((year - E) % 15 == 0  && (year - S) % 28 == 0 && (year - M) % 19 == 0) {
//              year의 나머지 == E,S,M 이랑 같은지가 중요한 것이기 떄문에,  E,S,M을 빼주면 (year-E) % 는 항상 0이여야 함!
                System.out.println(year);
                break;
            }
        }

    }
}


// 나머지 속성 이용해서 하게 year-E% 15 == 0 이런식으로

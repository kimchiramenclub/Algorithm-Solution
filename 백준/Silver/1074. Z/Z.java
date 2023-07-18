import java.io.*;
import java.util.*;

public class Main {

    /* 분할 정복 - 재귀
       - 배열을 4분할하면서, (r,c)가 어디에 포함되었는 지 파악
       - 실제 배열 만들지 말고, low&high 값을 정해서 order값에 +
         ex) 4*4 크기의 1사분면에 없다 -> order+=16
 */
    public static int r;
    public static int c;
    public static int order = 0;

    public static int solution(int N, int r, int c){
        checkOrder(N-1, 0, (int) Math.pow(2, N),0,(int) Math.pow(2, N));
        return order;
    }

    // ad : allowed depth
    public static void checkOrder(int ad, int lowR, int highR, int lowC, int highC){
        // 탈출조건 : 더 이상 4분할이 불가능하다면 return
        if(ad < 0) return;

        int midR = (lowR+highR)/2;
        int midC = (lowC+highC)/2;
        int area = (int)Math.pow(2,2*ad); // 분할 배열의 크기
        int offset = (( r >= midR)? 2 : 0) + ((c >= midC)? 1 : 0); // 분할 배열에서 Z자로 몇 번째 배열에 있는 지
        order += area * offset; // 위치한 배열에 도달하기 전까지의 order

        if(r < midR && c < midC) checkOrder(ad-1, lowR, midR, lowC, midC); // Z자 1사분면 위치
        else if(r < midR) checkOrder(ad-1, lowR, midR, midC, highC); // Z자 2사분면 위치
        else if(c < midC) checkOrder(ad-1, midR, highR, lowC, midC); // Z자 3사분면 위치
        else checkOrder(ad-1, midR, highR, midC, highC); // Z자 4사분면 위치
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, r, c));
    }
}
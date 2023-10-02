import java.io.*;

class Main {
    /*  비트마스킹
        - 문제 설명은 길지만, 숫자를 2진법으로 표현해서 '1'인 부분 갯수를 세는 것.
        - Brian Kernighan 알고리즘
          ㄴ N의 비트 중 가장 오른쪽에 위치한 1부터 반대값들을 만들어서, 해당 비트를 &연산을 통해 0으로 만듬
          ex) 10101(21)
              10101(21) & 10100(20) => 10100  : 둘다 1인 앞의 1들만 살아남고, 맨 뒤에 있던 1은 1&0 이 되어 0으로 변환
              10100(20) & 10011(19) => 10000  : 맨 앞 1만 살아남고, n-1은 특성상 100... 을 011... 이런 식으로 바꾸므로, 다 0으로 변환
              10000(16) & 01111(15) => 00000
        or
        - Integer.bitCount()    : 해밍가중치를 사용하는 Counting bits set 알고리즘(상수 시간)
     * */

    public static void main(String[] args) throws IOException {
        int X = readInt();
        int cnt = 0;

        // Brian Kernighan 알고리즘
        while (X != 0) {
            X &= (X - 1);   // X = X & (X-1)
            cnt++;
        }
        System.out.println(cnt);
    }
    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

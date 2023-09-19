import java.io.IOException;

class Main {
    /*  배낭 문제
        DP 혹은 백트래킹

        - A, B, C, D, ... 이렇게 물건 순서대로 가면서 무게 경우의 수를 축적.
          ex) A : 0, 6(A무게) , B : 0, 10(A+B), 4(B무게)
        - 같은 무게일 경우, 최대 가치만 저장.
        - 초기 capacity = K 인 HashMap을 이용해서 저장.

        문제 : Hashmap에서 ConcurrentModificationException(for문 돌면서 바로바로 수정) 하는 error 생김.
        수정 : temporary Hashmap을 선언해가면서 사용
        수정2 : 그냥 array 사용해보게 수정

     */

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();

        int[] cases = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int W = readInt();  // 물품 무게
            int V = readInt();  // 물품 가치

            if(W > K) continue;

            for (int j = K; j > W; j--) {
                if (cases[j - W] != 0) {
                    cases[j] = Math.max(cases[j], cases[j - W] + V);
                }
            }
            cases[W] = Math.max(cases[W], V);
        }

        // Map을 iterate하면서 최대 가치 합 구함
        int maxValue = 0;
        for (int value : cases) maxValue = Math.max(maxValue, value);
        System.out.println(maxValue);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
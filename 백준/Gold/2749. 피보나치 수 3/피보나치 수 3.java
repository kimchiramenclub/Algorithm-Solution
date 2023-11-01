import java.io.*;

public class Main {
    /*  분할정복을 통한 거듭제곱 - 피보나치
        메모이제이션

        - n을 2진법으로 나타내서, 2진법 최댓값이 어디 위치하는 지 확인.
        - 배열에 저장해서, 해당되는 M 값들만 구해놓고, 곱해서 구하기
        - 피보나치의 행렬 표현 사용
        - 행렬에서 M^(m+n) = M^(m) * M^(n) 임을 이용
     * */


    static boolean[] bits = new boolean[60];
    static long[][][] memo;


    // 피보나치 메서드
    // ex) n=15 -> M^1 * M^2 * M^4 * M^8  이런 식으로 memo에 저장된 값들을 통해 거듭제곱을 더 빨리 계산
    static long fibonacci(long n, long mod, int powMax) {
        long[][] answer = new long[][]{{1, 0}, {0, 1}};

        for (int p = 0; p <= powMax; p++) {
            // 해당 index에 bit가 있다면, answer * M^(2^p)
            if (bits[p]) answer = multiply(answer, memo[p], mod);
        }

        //{{1,1},{1,0}}^n = {{F(n+1),F(n)},{F(n),F(n-1)}} 이므로, n값을 보정했으니 F(n) return
        return answer[0][1];
    }

    // n을 2진법으로 각 bit를 저장하고, 최대 bit의 위치를 return하는 메서드
    static int checkBit(long n) {
        int pow = 0;
        while (n >=(1L << pow)) {
            if ((n & (1L << pow)) != 0) bits[pow] = true;   // 해당 위치의 bit가 1이라면, true
            pow++;
        }

        return pow - 1; // bit = 1 인 최대 자리의 index를 return    ex) 15 = 1111(2) -> 3 return
    }

    // {{1,1},{1,0}} = M일 때, M^(2^max) 값까지 구해서 memo배열에 저장
    static void calMatrix(int powMax, long mod) {
        memo = new long[powMax + 1][2][2];
        memo[0] = new long[][]{{1, 1}, {1, 0}};

        for (int p = 1; p <= powMax; p++) {
            memo[p] = multiply(memo[p - 1], memo[p - 1], mod);
        }
    }

    // 행렬 곱
    static long[][] multiply(long[][] a, long[][] b, long mod) {
        long[][] result = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                    result[i][j] %= mod; // 모듈러 연산 처리
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long mod = 1_000_000L;

        if(n == 0){
            System.out.println(0);
            System.exit(0);
        }

        int powMax = checkBit(n);
        calMatrix(powMax, mod);

        System.out.println(fibonacci(n - 1, mod, powMax));
    }
}
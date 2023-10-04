import java.io.IOException;
import java.util.Arrays;

class Main {
    /*  그리디
        - 가장 큰 B의 값과 가장 작은 A의 값이 만나게
     * */

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] A = new int[N];
        int[] B = new int[N];
        int S = 0;

        for(int i=0;i<N;i++) A[i] = readInt();
        Arrays.sort(A);
        for(int i=0;i<N;i++) B[i] = readInt();
        Arrays.sort(B);

        for(int i=0;i<N;i++){
            S += A[i] * B[N-i-1 ];
        }

        System.out.println(S);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
import java.io.*;
import java.util.*;

class Main {
    /*  백트래킹
     */
    static int[] num;
    static int[] numSeq;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solution(int N, int M) throws IOException {
        printSeq(N, M, 0, 0);
    }

    // 백트래킹으로 프린트하는 메서드
    static void printSeq(int N, int M, int depth, int index) throws IOException {
        // M개만큼 출력할 수열이 모이면, Arraylist를 사용해 출력
        if (depth >= M) {
            for (int seq : numSeq) sb.append(seq).append(" ");
            sb.append("\n");
            return;
        }
        // 비내림차순을 유지하기 위해, 이전 index를 받아옴
        for (int i = index; i < N; i++) {
            numSeq[depth] = num[i];
            // 수열의 다음 숫자 재귀로 정하기
            printSeq(N, M, depth + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        num = new int[N];
        numSeq = new int[M];

        for (int i = 0; i < N; i++) num[i] = readInt();
        Arrays.sort(num);

        solution(N, M);
        bw.write(sb.toString());
        bw.close();
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
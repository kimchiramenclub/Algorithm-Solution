import java.io.IOException;
import java.util.*;

class Main {
    /*  정렬
     * */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int[][] scores = new int[9][2];
        int totalScore = 0;

        for (int i = 1; i <= 8; i++) {
            scores[i][0] = readInt();
            scores[i][1] = i;
        }

        // 점수 하향식 정렬
        Arrays.sort(scores, Comparator.comparingInt((int[] o) -> o[0]).reversed());
        // Top 5개의 점수를 문제 번호 증가 순으로 재정렬
        Arrays.sort(scores, 0, 5, Comparator.comparingInt((int[] o) -> o[1]));
        for (int i = 0; i < 5; i++) {
            totalScore += scores[i][0];
            sb.append(scores[i][1]).append(" ");
        }

        System.out.print(totalScore+"\n"+sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

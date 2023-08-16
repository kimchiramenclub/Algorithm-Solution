import java.io.IOException;

public class Main {
    /*  브루트포스
        - 보드를 순회하면서, 보드의 각 위치에서 체스판을 구현하기 위해서는 몇개의 변화가 필요한지 체크
        - WBWB  ...
          BWBW
          WBWB
          ...
               이런 패턴의 보드가 끝없이 이어진다고 가정하고, 누적합 sumBW (BWBW 패턴)  sumWB( WBWB 패턴)을 만듬.
          sum[i][j] = sum[i-1][j] (세로 누적합, i>0일때)
                        + sum[i][j-1] (가로 누적합, j>0일때)
                        - sum[i-1][j-1](세로 누적합과 가로 누적합 합의 겹치는 부분,  i>0 && j>0 일 때)

          체스 영역을 정할 때, 체스판을 어디에 두냐에 따라서
          칠해야 할 칸의 수 = sum(i,j)(체스판 우하단 위치)
                            - sum(i-size, j) (체스판을 아래쪽에 두면서 상단의 비어있는 부분. 누적합에서 빼줘야함)
                            - sum(i, j-size) ((체스판을 오른쪽에 두면서 왼쪽의 비어있는 부분. 누적합에서 빼줘야함)
                            + sum(i-size, j-size) (중복으로 빼준 값)
          이런 보정도 추가로 필요함.

          2개의 누적합 배열 값 중 더 작은 값을 사용
     * */

    static int solution(int N, int M, int size, boolean[][] board) throws IOException {
        int answer = Integer.MAX_VALUE;

        boolean[] WB = {true, false}; // WBWBWBWB.... 패턴

        int[][] sumWB = new int[N][M]; // WB패턴과 일치하지 않아서, 다시 칠할 정사각형 갯수 누적합
        int[][] sumBW = new int[N][M]; // BW패턴과 일치하지 않아서, 다시 칠할 정사각형 갯수 누적합

        int idx = 0; // 해당 칸의 패턴을 정해주는 index

        for (int i = 0; i < N; i++) {
            idx = i % 2; // 세로 줄 패턴
            for (int j = 0; j < M; j++) {
                // 현재 위치의 값 업데이트
                if (board[i][j] == WB[idx]) sumBW[i][j]++;
                else sumWB[i][j]++;
                // 이전 행의 누적합을 더하기
                if (i > 0) {
                    sumBW[i][j] += sumBW[i - 1][j];
                    sumWB[i][j] += sumWB[i - 1][j];
                }
                // 이전 열의 누적합을 더하기
                if(j > 0) {
                    sumBW[i][j] += sumBW[i][j - 1];
                    sumWB[i][j] += sumWB[i][j - 1];
                }
                // 이전 행과 이전 열의 교차점의 누적합을 빼기 (중복 제거)
                if(i > 0 && j > 0){
                    sumBW[i][j] -= sumBW[i-1][j-1];
                    sumWB[i][j] -= sumWB[i-1][j-1];
                }
                idx ^= 1; // XOR 연산으로 패턴 변경. 0이면 1,  1이면 0으로 변환
            }
        }

        int totalWB = 0; // WB 패턴일 때 칠해야 할 칸의 수
        int totalBW = 0; // BW 패턴일 때 칠해야 할 칸의 수

        // 체스판 구역을 정하기
        for (int i = size -1; i < N; i++) {
            for (int j = size - 1; j < M; j++) {
                totalWB = sumWB[i][j];
                totalBW = sumBW[i][j];
                // 체스판이 아래쪽으로 치우쳤다면
                if(i > size -1) {
                    // 체스판에 포함되지 않은 상단 줄은 빼줌
                    totalWB -= sumWB[i-size][j];
                    totalBW -= sumBW[i-size][j];
                }
                // 체스판이 오른쪽으로 치우쳤다면
                if(j > size - 1){
                    // 체스판에 포함되지 않은 왼쪽 줄은 빼줌
                    totalWB -= sumWB[i][j-size];
                    totalBW -= sumBW[i][j-size];
                }
                // 누적합을 뺄 때 중복 구간은 다시 +
                if(i > size -1 && j > size - 1){
                    totalWB += sumWB[i-size][j-size];
                    totalBW += sumBW[i-size][j-size];
                }
                // 체스판의 2 패턴(BW, WB) 중 최소로 칠해야 하는 패턴
                answer = Math.min(answer, Math.min(totalWB, totalBW));
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        int N = readInt();
        int M = readInt();
        int K = readInt();
        boolean[][] board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) board[i][j] = readChar();
            readChar(); // 줄바꿈 문자 넘기기
        }

        System.out.println(solution(N, M, K, board) + "\n");
    }

    // W면 true, B면 false return
    static boolean readChar() throws IOException {
        return System.in.read() == 'W';
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
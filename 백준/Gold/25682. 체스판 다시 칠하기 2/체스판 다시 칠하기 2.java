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


          수정 :
          - System.in.read로 char를 받던걸 BufferedReader로 받고, charAt으로 조회하도록 변경
            입력 문자열이 길 때는 System.in.read는 스트림에서 한 바이트씩 읽음
            BufferedReader는 버퍼로 받아놓고 버퍼에서 데이터를 가져오므로 시스템 호출이 적음.
          - board를 받을 때부터 WB 패턴과 비교해서, 칠해야 하는 칸들을 1, 아닌 칸들을 0으로
          - board를 재활용해서 누적합 배열로
     * */

    static int solution(int N, int M, int size, int[][] board) throws IOException {
        int answer = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(i>0) board[i][j] += board[i-1][j];
                if(j>0) board[i][j] += board[i][j-1];
                if(i>0 && j > 0) board[i][j] -= board[i-1][j-1];
            }
        }

        int total;
        // 체스판 구역을 정하기
        for (int i = size -1; i < N; i++) {
            for (int j = size - 1; j < M; j++) {
                total = board[i][j];
                // 체스판이 아래쪽으로 치우쳤다면 체스판에 포함되지 않은 상단 줄은 빼줌
                if(i > size -1) total -= board[i-size][j];
                // 체스판이 오른쪽으로 치우쳤다면 체스판에 포함되지 않은 왼쪽 줄은 빼줌
                if(j > size -1) total -= board[i][j-size];
                // 누적합을 뺄 때 중복 구간은 다시 +
                if(i > size -1 && j > size - 1) total += board[i-size][j-size];
                // 체스판의 2 패턴(BW, WB) 중 최소로 칠해야 하는 패턴
                answer = Math.min(answer, reversePattern(size, total));
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        int N = readInt();  int M = readInt(); int K = readInt();
        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++){
                if((i+j)%2 == 0) board[i][j] = readChar() ? 0 : 1;
                else board[i][j] = readChar() ? 1 : 0;
            }
            readChar();
        }
        System.out.println(solution(N, M, K, board) + "\n");
    }

    // 최대의 칸을 칠해야 하는 최악의 케이스랑 비교해서
    // 만약 그보다 더 칠해야한다면, 패턴을 바꿔서 덜 칠하게 하기
    static int reversePattern(int K, int total){
        int mid = K * K / 2;
        if(total > mid) return K*K - total;
        else return total;
    }

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
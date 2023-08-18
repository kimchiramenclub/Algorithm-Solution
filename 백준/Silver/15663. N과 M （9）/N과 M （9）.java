import java.io.*;
import java.util.*;

class Main {
    /*  백트래킹
        - 중복되는 숫자를 배열에서 바로 배제하는 게 아님. 수열이 겹치지 않게 하는 것
        - prevNum 배열을 만들어서 수열의 각 자리마다 이전에 사용된 숫자 저장.
          수열의 각 자리는 뒷자리부터 차례대로 다음 숫자로 변해가므로, 그 전 자리의 prevNum은 활용 가능
          ex) 1 1 2 2 3 3 4 4
          1 1 2 2 -> 1 1 2 3 - (3은 사용됐으므로 4로 pass) 1 1 2 4 - (3번째 자리인 2를 바꿀때도, 2는 사용됐으므로 pass) -> 1 1 3 2

          int prev = -1 을 각 재귀마다 넣는 것도 가능.
          그럼 각 cycle마다 unique한 prev int가 생성되고, 각 자릿수의 이전 숫자를 저장함.
          가시성이 안 좋아서 prevNum 배열을 사용


          수정 :
          - prevNum 배열을 만들면, 각 자리에 저장한 값이 계속 유지돼서 에러가 생김!
          ex) 1 9 9 -> 919 하려 하면, index 2에 9가 저장되어 있어서 스킵됨.
          for문 사이클이 끝나서 새로운 for문 시작할 때, 초기화 하는 코드 넣음.
     */
    static int[] num; // 입력 숫자 배열
    static int[] numSeq; // 수열
    static int[] prevNum; // 각 자리마다 사용된 숫자 저장
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solution(int N, int M) throws IOException {
        printSeq(N, M, 0);
    }

    // 백트래킹으로 프린트하는 메서드
    static void printSeq(int N, int M, int depth) throws IOException {
        // M개만큼 출력할 수열이 모이면, Array를 사용해 출력
        if (depth >= M) {
            for (int seq : numSeq) sb.append(seq).append(" ");
            sb.append("\n");
            return;
        }

        // for문 사이클이 한번 다 돌았으므로, 아예 다른 수열임. 그러므로 초기화
        prevNum[depth] = 0; // int prev = 0;
        
        for (int i = 0; i < N; i++) {
            // 수열에 사용한 숫자는 pass
            // 각 자리마다
            if (!visited[i] && prevNum[depth] != num[i]) { // prev != num[i]
                // 수열 add, 방문처리
                numSeq[depth] = num[i];
                visited[i] = true;
                // 수열의 다음 숫자 재귀로 정하기
                printSeq(N, M, depth + 1);
                visited[i] = false;

                prevNum[depth] = num[i]; // prev = num[i]
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        num = new int[N];
        visited = new boolean[N];
        numSeq = new int[M];
        prevNum = new int[M];

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
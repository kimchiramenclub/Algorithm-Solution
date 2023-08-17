import java.io.*;
import java.util.*;

class Main {
    /*  백트래킹
        */
    static int[] num;
    static boolean[] visited;
    static ArrayList<Integer> numSeq = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solution(int N, int M) throws IOException {
         printSeq(N, M, 0);
    }

    // 백트래킹으로 프린트하는 메서드
    static void printSeq(int N, int M, int depth) throws IOException{
        // M개만큼 출력할 수열이 모이면, Arraylist를 사용해 출력
        if(depth >= M){
           for(int num : numSeq) bw.write(num+" ");
           bw.newLine();
           return;
        }

        for(int i=0;i<N;i++){
            // 수열에 사용한 숫자는 pass
            if(!visited[i]){
                // 방문처리 + 수열 add
                visited[i] = true;
                numSeq.add(num[i]);
                // 수열의 다음 숫자 재귀로 정하기
                printSeq(N, M, depth+1);
                // 백트래킹해서 돌아오면, 수열 숫자 제거 후 새로운 숫자 대입
                visited[i] = false;
                numSeq.remove(numSeq.size()-1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        num = new int[N];
        visited = new boolean[N];

        for(int i=0;i<N;i++) num[i] = readInt();
        Arrays.sort(num);

        solution(N, M);
        bw.flush();
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
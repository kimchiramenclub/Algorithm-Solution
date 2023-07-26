import java.io.*;
import java.util.*;

class Main {
    /*  그래프 탐색 - BFS
        - D : 2n % 10000
        - S : n-1 (if n == 0, 9999)
        - L : d2 d3 d4 d1    ex) 1000 -> 0100 -> 100
        - R : d4 d1 d2 d3
        A -> B로 바꾸는 최소 명령어

        - 몇 회차의 명령인지 큐에 저장 -> 필요없음. 큐 특성상 어차피 순서대로 진행되고 결국 회차가 가장 짧은게 나옴
        - 명령어 나열을 어떻게 기억할지? -> byte 배열 큐를 사용해서 저장하기.
        - Q<Integer> (n값) , Q<byte[]> (명령어)
        - 큐를 모든 케이스마다 새로 선언하면 memory 문제가 생기므로, clear해서 비우고 재사용

        - d1, d2, d3, d4가 순서는 달라도 A,B에 공통으로 있다면 1,2를 배제하도록 최적화도 가능할듯? (나중에)

    */
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited; // 방문 체크 배열
    static Queue<Integer> nValue = new ArrayDeque<>(); // 명령어에 따라 변한 n 값을 담는 큐
    static Queue<byte[]> Command = new ArrayDeque<>(); // 명령어들을 DSLR -> 0123 의 값으로 저장하는 큐

    static void solution(int T, int[][] testCase) throws IOException {
        for(int[] C : testCase) bfs(C);
    }

    static void bfs(int[] C) throws IOException {
        visited = new boolean[10000]; // 방문 배열 초기화
        visited[C[0]] = true; // 초기 방문 처리
        int ans = C[1]; // 최종 값 저장
        int N = 0; // 변환된 n 값

        nValue.offer(C[0]);             // n 초기값 대입
        Command.offer(new byte[]{});    // 빈 명령어 배열 대입

        while(!nValue.isEmpty()){
            int n = nValue.poll();
            byte[] cmd = Command.poll();

            // 탈출 조건
            if(n == ans){
                printCmd(cmd); // 명령어들을 출력하는 메서드
                nValue.clear(); // 다음 케이스를 위해 큐 비우기
                Command.clear(); // 다음 케이스를 위해 큐 비우기
                return;
            }

            for(byte i=1;i<=4;i++){
                switch (i){
                    case (byte)1 : N = doubleAndModule(n); break;
                    case (byte)2 : N = minusOne(n); break;
                    case (byte)3 : N = moveLeft(n); break;
                    case (byte)4 : N = moveRight(n); break;
                }

                if(!visited[N]){
                    visited[N] = true; // 방문 처리

                    nValue.offer(N); // 변환된 n 값 대입

                    int size = cmd.length;
                    byte[] nCmd = new byte[size+1];
                    if(size > 0) System.arraycopy(cmd, 0, nCmd, 0, cmd.length);
                    nCmd[size] = i;
                    Command.offer(nCmd); // 명령어 배열 대입
                }
            }
        }
    }

    static int doubleAndModule(int N){
        return N * 2 % 10000;
    }
    static int minusOne(int N){
        return (N > 0) ? N-1 : 9999;
    }
    static int moveLeft(int N){
        // 1234 -> 2341  | FirstDigit = n / 1000   | OtherDigits = n % 1000
        return (N % 1000) * 10 + (N / 1000);
    }
    static int moveRight(int N){
        // 1234 -> 4123  |   LastDigit = n % 10   |   OtherDigits = n / 10
        return (N % 10) * 1000 + (N / 10);
    }
    static void printCmd(byte[] cmd) throws IOException {
        for(byte c : cmd){
            switch(c){
                case (byte)1 : bw.write('D'); break;
                case (byte)2 : bw.write('S'); break;
                case (byte)3 : bw.write('L'); break;
                case (byte)4 : bw.write('R'); break;
            }
        }
        bw.write("\n");
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[][] testCase = new int[T][2];

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            testCase[i][0] = Integer.parseInt(st.nextToken());
            testCase[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        solution(T, testCase);
        bw.flush();
        bw.close();
    }
}
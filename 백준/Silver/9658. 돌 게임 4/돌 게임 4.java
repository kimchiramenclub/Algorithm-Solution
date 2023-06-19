import java.io.*;

public class Main {
    /* 돌 게임 4 : 돌 게임 3의 반대
    다음 턴에 상대를 '지는 턴'으로 보낼 수 있느냐가 중요!
    1 : X
    2 : O
    3 : X
    4 -> 1 : O
    5 -> 1 : O
    6 -> 2 : 0
    7 -> 3(4) : O
    8 : X (4,6,7 모두 지는 턴이므로)
    9 -> 8(1) : O
    10 : X (6,7,9 모두 지는 턴)
    ....
    1 + 2 + 5 +2 + 5  -> +2 +5 +2 +5   (N-1일 때)
    a1 = 7n+2, 7n (시작 시 필패 갯수)
     */

    public static String solution(long N) {
        N--;
        if (N == 0) {return "CY";} // N == 1 일때의 예외 값
        else if((N) % 7 == 2 || N % 7 == 0) {return "CY";}
        else {return "SK";}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());

        bw.write(solution(N));

//        // 메모리 체크
//        System.gc();
//        double memoryUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024.0 * 1024.0);
//        String formattedMemoryUsage = String.format("%.4f", memoryUsage);
//        bw.write("\nMemory Usage: " + formattedMemoryUsage + " MB");

        bw.flush();
        bw.close();
    }
}
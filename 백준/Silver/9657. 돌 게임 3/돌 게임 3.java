import java.io.*;

public class Main {
    /* 돌 게임 3
    다음 턴에 상대를 '지는 턴'으로 보낼 수 있느냐가 중요!
    1 : O
    2 : X
    3 : O
    4 : O
    5 : X (4, 3, 1 모두 상대가 이기는 턴)
    6 -> 2 : 0
    7 : X (6, 4, 3 모두 상대가 이기는 턴이므로, 나에게는 지는 턴)
    ....
    +2 +5 +2 +5
    a1 = 7n+2, 7n (시작 시 필패 갯수)
     */

    public static String solution(long N) {
        if(N % 7 == 2 || N % 7 == 0) {return "CY";}
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
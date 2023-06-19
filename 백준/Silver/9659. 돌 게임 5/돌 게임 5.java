import java.io.*;

public class Main {
    /* 돌 게임 3 : 9655번 + 몇 번째 턴에 끝나냐가 중요.
    베스킨 라빈스 이론

    돌 게임 5 : 9655번에서 long으로 바뀐게 끝


     */

    public static String solution(long N) {
        if(N % 2 == 1) {return "SK";}
        else {return "CY";}
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

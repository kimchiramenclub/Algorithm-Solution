import java.io.*;

public class Main {
    /* 돌 게임 : 베스킨라빈스 31 해결법과 같기도 하지만

    다른 해법 : 1,3 , 즉 홀수 개의 돌만 가져갈 수 있으므로,
    자기 턴에 돌을 '짝수'개로 만들 수 있는 플레이어가 승리한다. (ex) 1 -> 0 ,  3 -> 0)
    즉, 자기 턴에 돌이 '홀수' 개인 플레이어가 승리한다.

     */

    public static String solution(int N) {
        if(N % 2 == 1) {return "SK";}
        else {return "CY";}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

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
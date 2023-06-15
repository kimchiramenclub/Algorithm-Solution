import java.io.*;

public class Main {
    /* 돌 게임 2 : 9655번과 풀이가 같음.

    다른 해법 : 1,3 , 즉 홀수 개의 돌만 가져갈 수 있으므로,
    자기 턴에 돌을 '홀수'개로 만들 수 있는 플레이어가 승리한다. (ex) 2 -> 1 ,  4 -> 1)
    즉, 자기 턴에 돌이 '짝수' 개인 플레이어가 승리한다.

     */

    public static String solution(int N) {
        if(N % 2 == 0) {return "SK";}
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
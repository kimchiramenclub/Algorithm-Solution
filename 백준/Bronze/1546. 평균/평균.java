import java.io.*;
import java.util.*;

public class Main {
    /*  수학
        - X/M*100
        - AVG : SUM / N
        - new AVG : SUM * 100 / MN
    * */
    
    public static float solution(int N, int M, int sum){
        return (float)(sum * 100) / (float)(M*N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = 0;
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int score = Integer.parseInt(st.nextToken());
            M = Math.max(M, score);
            sum += score;
        }

        System.out.println(solution(N, M, sum));
    }
}
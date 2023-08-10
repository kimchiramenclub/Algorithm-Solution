import java.io.*;
import java.util.*;

class Main {
    /*  중국인의 나머지 정리
        * 참고 : https://casterian.net/algo/crt.html
        - x == 3 mod 10
          x == 9 mod 12
          x = 10n+3
          10n+3 == 9 (mod 12)   => 10n == 6 (mod 12)


    */
    
    static int solution(int M, int N, int x, int y){
        int answer = -1;
        int gcd = gcd(M,N);
        if(Math.abs(x-y) % gcd != 0) return answer;

        int maxYear = M * N / gcd;
        for(int k = x; k<= maxYear; k += M){
            if((k-1)%N+1 == y){
                answer = k;
                break;
            }
        }
        return answer;
    }

    static int gcd(int M, int N){
        return (N == 0) ? M : gcd(N, M%N);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            bw.write(solution(M, N, x, y)+"\n");
        }

        bw.flush();
    }
}
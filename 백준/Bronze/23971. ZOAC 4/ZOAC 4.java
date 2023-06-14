import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /* '가로수 놓기'와 비슷한 문제
    *  N+1, M+1 값의 뺼셈 반복 => 나누기
    *  ex) H=9, N=2 --> OXX OXX OXX 3자리  9/3 = 3  | OXX OXX OX  8/3 = 2, but 3
    *  구간 나누기로 볼 수 있음. '구간 갯수'
    *  무조건 구간 첫 자리에 사람이 들어가므로, 나머지가 있으면 +
    *  1. H / (N+1), W / (M+1)
    *  2. 각 값에 Math.ceil
    *  */

    public static int solution(int[] numbers){
        int answer = 0;
        answer = (int) (Math.ceil((double)(numbers[0])/(numbers[2]+1)) // (H) / (N+1)
                        * Math.ceil((double)(numbers[1])/(numbers[3]+1))); // (W) / (M+1)
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[4];

        int temp = 0;
        while(st.hasMoreTokens()){
            numbers[temp++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(numbers));
    }
}
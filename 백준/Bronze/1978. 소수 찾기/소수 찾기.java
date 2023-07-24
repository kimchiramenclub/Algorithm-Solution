import java.io.*;
import java.util.*;

public class Main {
    /*  소수 판정
        - 제곱근까지로 한정
        - 에라토스테네스의 체
    *
    * */

    public static int notPrime = 0;

    public static int solution(int[] numbers, int numMax){
        int sqrt = (int)Math.sqrt(numMax);

        for(int i=2;i<=sqrt;i++){
            for(int j=0;j<numbers.length;j++){
                if(numbers[j] != 0 && numbers[j] % i == 0 && numbers[j] / i != 1){
                    numbers[j] = 0;
                    notPrime++;
                }
            }
        }

        return numbers.length - notPrime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int numMax = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
            numMax = Math.max(numMax, numbers[i]);
            if(numbers[i] == 1){
                numbers[i] = 0;
                notPrime++;
            }
        }

        System.out.println(solution(numbers, numMax));
    }
}
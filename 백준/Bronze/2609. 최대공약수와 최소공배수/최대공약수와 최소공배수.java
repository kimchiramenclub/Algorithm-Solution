import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /* 유클리드 호제법
       ex) 60, 35   60 / 35 = 1 ... 25
                    35 / 25 = 1 ... 10
                    25 / 10 = 2 ... 5
                    10 / 5  = 2 ... 0
            60, 35의 최대공약수 = 5
    */

    public static int[] solution(int n1, int n2) {
        int tmp = 0;
        int[] answer = new int[2];
        int num1 = n1;
        int num2 = n2;

       do{
            tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        } while(tmp != 0);

        answer[0] = num1;
        answer[1] = n1 * n2 / answer[0];
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        // 출력부
        int[] answer = solution(Math.max(n1, n2), Math.min(n1, n2));
        System.out.println(answer[0]);
        System.out.print(answer[1]);
    }
}

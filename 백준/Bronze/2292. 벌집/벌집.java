import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 1 -> 6 -> 12 -> 18 이렇게 6의 배수로 늘어남
    // 방향을 정하면 항상 최단거리가 가능. 그러므로 어느 계층인지가 중요
    // N이 어느 계층인지 가장 시행횟수가 적게 찾는 방법?
    // 1 -> n+1 -> 3n+1 -> 6n+1 -> 10n+1  -->  M*(M+1)*6/2 + 1  >=< N (M : 계층)
    // M >=< -0.5 + root(1+ 4/3(N-1)) : 올림, 시작위치를 0계층으로 했으므로, 감안해서 + 1

    public static int solution(int N){
        double answer = 0;
        answer = Math.ceil(-0.5 + Math.sqrt(1 + (double)4/3*(N-1))/2);

        return (int) answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N)+1);
    }
}
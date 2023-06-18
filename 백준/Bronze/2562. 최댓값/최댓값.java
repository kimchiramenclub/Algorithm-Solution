import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static int[] solution(int[] numbers) {
        int[] answer = new int[2];
        for(int i=0;i<numbers.length; i++){
            if(answer[0] < numbers[i]) {
                answer[0] = numbers[i];
                answer[1] = i+1; // 인덱스와 실제 순서 매칭
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[9];
        String input;
        int[] answer = new int[2];

        for(int i=0;i<9;i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        answer = solution(numbers);
        for(int value : answer){
            System.out.println(value);
        }
    }
}
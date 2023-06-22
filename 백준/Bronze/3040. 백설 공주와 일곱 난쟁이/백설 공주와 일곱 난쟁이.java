import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    /* 브루트포스 : 완전탐색
       모든 경우의 수를 시행하면서 답을 탐색

       - 모자 전체 합에서 2개를 뺐을 때 100이 되는 경우를 찾기
       - 이중 for문
    */

    public static int[] solution(int[] hatNum) {
        // 배열 합 Stream
        int hatSum = Arrays.stream(hatNum).sum();

        Loop : for(int i=0;i<8;i++){
            for(int j=i+1;j<9;j++){
                if(hatSum - hatNum[i] - hatNum[j] == 100) {
                    hatNum[i] = hatNum[j] = 0; // 답이 아님을 0으로 표시
                    break Loop;
                }
            }
        }
        // 0이 아닌 값만 answer 배열로 복사하는 stream 메서드
        int[] answer = IntStream.of(hatNum).filter(n -> n != 0).toArray();

        return answer;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] hatNum = new int[9];
        int[] answer;

        // 입력부
        for(int i=0;i<9;i++){hatNum[i] = Integer.parseInt(br.readLine());}
        answer = solution(hatNum);

        // 출력부
        // BufferedWriter는 stream 사용 시 IOException을 try catch 구문으로 감싸줘야 해서, 오히려 stream 사용 시 더 길어짐
        // for(int i : answer){
        //    bw.write(i+"\n");
        // }
        Arrays.stream(answer).forEach(i ->{System.out.println(i);});

    }
}
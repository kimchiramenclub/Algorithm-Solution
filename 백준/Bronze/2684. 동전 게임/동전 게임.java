import java.io.*;

class Main {
    // 뒤 : 0,  앞 : 1
    // 이진법을 사용해서 동전수열을 표현. ex) 앞뒤앞 : 2^2 + 2^0 = 5

    static StringBuilder sb = new StringBuilder();
    static void solution(String tc){
        int size = tc.length();
        int[] seqNum = new int[8];

        int value = 0;
        // 최초 수열
        for(int i=0;i<3;i++){
            value <<= 1;
            if(tc.charAt(i) == 'H') value += 1;
        }
        seqNum[value]++;

        // 3자릿수 이진법에서 head를 바꿔가면서 계산
        boolean head;
        for(int i=3;i<size;i++){
            head = tc.charAt(i-3) == 'H';
            
            // 자릿수 옮기기    ex) HTT -> TT?
            // head가 H일 경우, 2^2를 제거
            if(head) value -= 4;
            value <<=1;
            if(tc.charAt(i) == 'H') value += 1;

            seqNum[value]++;
        }

        for(int num : seqNum) sb.append(num).append(" ");
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int P = Integer.parseInt(br.readLine());
        String testCase;

        for(int i=0;i<P;i++){
            testCase = br.readLine();
            solution(testCase);
        }

        System.out.println(sb);
    }
}
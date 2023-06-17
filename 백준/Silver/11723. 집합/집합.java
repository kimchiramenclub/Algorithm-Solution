import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // Queue 등을 써도 되겠지만, 20크기의 boolean[] 으로 해보기
    // solution에서 check 결과는 queue에 넣음
    // 명령 수행 시 "check 20" 이런 식으로 명령, 명령 값이 반복되므로,
    // cmd, idx를 사용해서 코드를 줄임.

    /* 문제: 사용 메모리 양이 6MB를 넘음.
        1. Queue를 사용해서 메서드끼리 주고 받는게 너무 낭비가 큼.
        2. String[] message를 주고 받는게 너무 낭비가 큼
        3. 가독성

        해결 :
        1-1. StringBuilder로 변환   -> .toString() 변환 과정에서 메모리 많이 소요.
        1-2. BufferedWriter로 변환
        2. 프로그래머스 방식 포기
        3. check, toggle이 삼항연산자 사용하게 변환
     */

//    public static void solution() {
//
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;
        String cmd = null;
        int idx = 0;
        int lines = Integer.parseInt(br.readLine());
        boolean[] flag = new boolean[20];


        for(int i=0; i<lines; i++){
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            // all, empty의 경우 다음 토큰이 없으므로 if문으로 체크
            if(st.hasMoreTokens()) {idx = Integer.parseInt(st.nextToken()) - 1;}

            // 명령문 수행
            if(cmd.equals("add")) { flag[idx] = true;}
            else if(cmd.equals("remove")) { flag[idx] = false;}
            else if(cmd.equals("check"))  {
                bw.write(flag[idx] ? "1\n" : "0\n");
            }
            else if(cmd.equals("toggle")) {
                flag[idx] = !flag[idx];
            }
            else if(cmd.equals("all"))   {Arrays.fill(flag, true);}
            else if(cmd.equals("empty")) {Arrays.fill(flag, false);}
        }


//        // 메모리 체크
//        long memoryUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
//        bw.write("Memory Usage: " + memoryUsage + " MB");

        bw.flush();
        bw.close();
    }
}

import java.io.*;
import java.util.*;

class Main {
    /*  파싱, 덱
        - R : 나올 때마다 덱에서 offerFirst, offerLast를 바꾸기. 출력도
    */

    static Deque<Integer> deque = new ArrayDeque<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solution(String opr, int size, int[] array) throws IOException {
        deque.clear(); // 덱 초기화
        boolean isReversed = false; // 배열 reverse 여부 체크

        for(int i=0;i<size;i++) deque.offer(array[i]); // 배열 값 대입
        try {
            for (int i = 0; i < opr.length(); i++) {
                if (opr.charAt(i) == 'R') isReversed = !isReversed; // R 명령을 만날 때마다 reverse
                else if (isReversed) deque.removeLast(); // 뒤집혔다면, 뒤집혔다고 가정하고 맨 뒤의 요소 pop
                else deque.removeFirst();
            }

            // 정수 문자열 프린트
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while(!deque.isEmpty()){
                if(!isReversed) sb.append(deque.poll()).append(",");
                else sb.append(deque.pollLast()).append(",");
            }
            if(sb.length() > 1) sb.setLength(sb.length()-1);  // 빈 배열일 때 [] 를 출력하기 위해
            sb.append("]");

            bw.write(sb.toString()); // 배열 출력
            bw.newLine();

        } catch(NoSuchElementException e1){ // 만약 배열이 비었는데 pop 하려했다면, error 출력
           bw.write("error");
           bw.newLine();
        }



    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            String opr = br.readLine();
            int size = Integer.parseInt(br.readLine());

            int[] array = new int[size];
            st = new StringTokenizer(br.readLine(), "[,]");
            for(int j=0;j<size;j++) array[j] = Integer.parseInt(st.nextToken());
            solution(opr, size, array);
        }

        bw.flush();
    }

}

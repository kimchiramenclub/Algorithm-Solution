import java.io.*;
import java.util.*;

public class Main {
    /*  최대 힙
        - Natural이 asceinding인 priority queue에 Collections.reverseOrder로,
        간단한 Integer의 경우 maxHeap으로 변환
        - PriorityQueue 쓰지 않고 일반 큐로 구현해서 해보기
    * */

    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public static void solution(int N, int[] cal) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; i++) {
            if (cal[i] == 0) {
                if (maxHeap.isEmpty()) bw.write("0\n");
                else bw.write(maxHeap.poll()+"\n");
            } else {
                maxHeap.offer(cal[i]);
            }
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cal = new int[N];
        for(int i=0;i<N;i++){
            cal[i] = Integer.parseInt(br.readLine());
        }

        solution(N, cal);
    }
}
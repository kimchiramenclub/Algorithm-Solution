import java.io.*;
import java.util.PriorityQueue;

public class Main {
    /*  최소 힙
    * */

    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void solution(int N, int[] cal) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; i++) {
            if (cal[i] == 0) {
                if (minHeap.isEmpty()) bw.write("0\n");
                else bw.write(minHeap.poll()+"\n");
            } else {
                minHeap.offer(cal[i]);
            }
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] cal = new int[N];
        for(int i=0;i<N;i++){
            cal[i] = Integer.parseInt(br.readLine());
        }

        solution(N, cal);
    }
}
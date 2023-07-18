import java.io.*;
import java.util.PriorityQueue;

public class Main {
    /**/

    public static int[] solution(int N, int[] cal){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] result = new int[N];
        int index = 0;

        for (int i = 0; i < N; i++) {
            if (cal[i] == 0) {
                if (minHeap.isEmpty()) {
                    result[index++] = 0;
                } else {
                    result[index++] = minHeap.poll();
                }
            } else {
                minHeap.offer(cal[i]);
            }
        }

        int[] output = new int[index];
        for (int i = 0; i < index; i++) {
            output[i] = result[i];
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] cal = new int[N];
        for(int i=0;i<N;i++){
            cal[i] = Integer.parseInt(br.readLine());
        }

        for(int ans : solution(N, cal)) bw.write(ans+"\n");
        bw.flush();
        bw.close();
    }
}
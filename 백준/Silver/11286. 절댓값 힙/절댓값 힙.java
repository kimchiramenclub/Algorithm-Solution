import java.io.*;
import java.util.PriorityQueue;

public class Main {
    /* 절댓갑 힙
     */


    public static void solution(int N, int[] tc) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 람다식으로 절댓값 힙 조건
        PriorityQueue<Integer> absHeap = new PriorityQueue<>((a,b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if(absA == absB) return Integer.compare(a, b);
            else return Integer.compare(absA, absB);
        });

        for(int i=0;i<N;i++){
            if(tc[i] == 0){
                if(absHeap.isEmpty()) bw.write("0\n");
                else bw.write(absHeap.poll()+"\n");
            } else absHeap.offer(tc[i]);
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] testCase = new int[N];

        for(int i=0;i<N;i++) testCase[i] = Integer.parseInt(br.readLine());
        solution(N, testCase);
    }
}
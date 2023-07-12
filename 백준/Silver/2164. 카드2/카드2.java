import java.io.*;
import java.util.*;

public class Main {
    /* 큐
     */

    public static int solution(int N) {
        Queue<Integer> cards = new LinkedList<>();

        if(N==1) return 1;
        for(int i=1;i<=N;i++) cards.offer(i);

        while(cards.size()> 1){
            cards.poll();
            cards.offer(cards.poll());
        }
        return cards.poll();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }
}
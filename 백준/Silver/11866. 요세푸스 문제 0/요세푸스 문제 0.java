import java.io.*;
import java.util.*;

class Main {
    /*  큐
        - 큐에 순서대로 숫자들 넣음
        - K-1만큼 offer(poll)을 이용해서 앞의 숫자들을 뒤로 보내면서 순회
          K번 째에 poll해서 프린트
     */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int K = readInt();

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=N;i++) queue.add(i);

        sb.append("<");
        while(!queue.isEmpty()){
            for(int i=0;i<K-1;i++) queue.offer(queue.poll());
            sb.append(queue.poll()).append(", ");
        }
        sb.setLength(sb.length()-2);
        sb.append(">");

        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
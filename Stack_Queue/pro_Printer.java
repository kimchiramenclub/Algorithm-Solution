package Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


public class pro_Printer {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] priorities = new int[4];
        int location;
        int answer = 0;
        for (int i = 0; i < priorities.length; i++) {
            priorities[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        location = Integer.parseInt(st.nextToken());

        // location을 표시할 d_loc, 순서를 표시할 d_pri
        Deque<Integer> d_loc = new ArrayDeque<Integer>();
        Deque<Integer> d_pri = new ArrayDeque<Integer>();

        // priorities 배열과 순서를 두 덱에 입력.
        for (int i = 0; i < priorities.length; i++) {
            d_loc.add(i);
            d_pri.add(priorities[i]);
        }

        // priorities 배열로 print 우선순위를 체크 위해 정렬.
        Arrays.sort(priorities);

        while (true) {
            // priorities 역순 조회
            if (d_pri.peek() < priorities[priorities.length - answer - 1]) {
                // 정렬된 priorities 배열 index를 조회회가며 가장 우선인 print 나올 때까지 뒤로 밀기.
                d_pri.add(d_pri.poll());
                d_loc.add(d_loc.poll());
            } else {
                // poll하기 전 location = d_loc.peek() 이면 다음 차례에 뽑히므로, return answer + 1
                if (d_loc.peek() == location) {
                    answer += 1;
                    break;
                }
                // 큰 숫자가 맨 앞에 오면 poll. poll하면서 answer ++
                d_pri.poll();
                d_loc.poll();
                answer++;
            }
        }

        System.out.println(answer);


    }
}

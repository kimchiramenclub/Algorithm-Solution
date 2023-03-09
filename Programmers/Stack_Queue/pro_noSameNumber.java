package Programmers.Stack_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class pro_noSameNumber {
    public int[] solution(int []arr) {
        int[] answer;

        // 큐 인스턴스. 지속적인 수정/삭제 가 없으므로 ArrayDeque가 메모리 상으로 더 유리.
        Deque<Integer> deque = new ArrayDeque<>();



        // arr 배열을 큐에 넣으면서, 직전 같은 값이 있는 지( 체크 후, 없으면 pass
        for(int i=0;i<arr.length;i++) {
            if(i > 0 && arr[i] == deque.peekLast()) {
                continue;
            }
            deque.addLast(arr[i]);
        }
        // 큐를 answer 배열에 담음.
        answer = new int[deque.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = deque.poll();
        }


        return answer;
    }


    // 테스트케이스
    /*public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] arr = new int[7];
        for(int i=0;i<arr.length;i++) {
           arr[i] = sc.nextInt();
        }


        // 큐 인스턴스
        Deque<Integer> deque = new ArrayDeque<>();

        // arr 배열을 큐에 넣으면서, 직전 같은 값이 있는 지( 체크 후, 없으면 pass
        for(int i=0;i<arr.length;i++) {
            if(i > 0 && arr[i] == deque.peekLast()) {
                continue;
            }
            deque.addLast(arr[i]);
        }
        // 큐를 answer 배열에 담음.

        int[] answer = new int[deque.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = deque.poll();
            System.out.print(answer[i]+" ");
        }

    }*/
}

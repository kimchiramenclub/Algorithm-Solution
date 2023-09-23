import java.io.IOException;

class Main {
    /*  큐
        - Circular LinkedList 만들어서 구현.
     * */


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();

        // 순환 LinkedList
        Node head = new Node(1, null);  // 카드 1을 head로, list 시작.
        Node tail = head;   // 현재까지는 head = tail    ex) 1 -> 1
        for (int i = 2; i <= N; i++) {
            tail.next = new Node(i, null);  // head를 next node와 연결      1(h,t) -> 2
            tail = tail.next;   // tail이 next node로 옮겨감.    1(h) -> 2(t)
        }
        tail.next = head;   // tail을 head와 연결        7(t) -> 1(h)

        // 카드가 하나 남을 때까지 pop
        while(head != tail){
            sb.append(head.idx).append(" ");
            // pop
            head = head.next;   // head를 다음 카드로 이동
            tail.next = head;   // tail도 다음 카드를 가리키게 해서, 이전의 head 카드 제거
            // pop & offerLast
            tail = tail.next;   // tail을 다음 카드로 이동
            head = head.next;   // head도 다음 카드로 이동해서, 이전의 head를 tail로 이동
        }
        // 마지막 카드 append
        sb.append(head.idx);
        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static class Node {
        int idx;
        Node next;

        Node(int idx, Node next) {
            this.idx = idx;
            this.next = next;
        }
    }
}
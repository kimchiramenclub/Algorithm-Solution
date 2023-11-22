import java.io.*;
import java.util.*;

public class Main {
    /*  스택
        - 피연산자(숫자)는 연산자를 제외하면 순서가 바뀌지 않으므로, 바로 print
        - 연산자들은 스택에 담아서 보관
        - 연산자 우선순위 1. '*' '/'   2. '+' '-'
        (※ 괄호의 경우에는 우선순위랑 별개로 기능. '('는 무조건 스택에 넣지만, 우선순위는 마지막으로. ')'는 '('가 나올 때까지 pop
        - 연산자를 만날 때마다 매번 스택 맨 위와 비교해서, 더 높은 우선순위를 가질 경우 push.
          아닐 경우, 스택에서 더 낮은 우선순위의 연산자를 만날 때까지 pop

     * */

    static Deque<Character> oprs;
    static StringBuilder sb = new StringBuilder();

    static void postfix(String N) {
        // 연산자 우선순위 판별을 위한 Map
        HashMap<Character, Integer> order = new HashMap<>(5);
        order.put('(',0);
        order.put('+',1);
        order.put('-',1);
        order.put('*',2);
        order.put('/',2);

        for (int i = 0; i < N.length(); i++){
            char C = N.charAt(i);

            // 피연산자의 경우, 바로 출력
            if(C >= 'A' && C <= 'Z') sb.append(C);
            // '('의 경우, 무조건 push. 하지만 우선순위 비교에서는 밀림
            else if(C == '(') oprs.push(C);
            // ')'의 경우, '('를 만날 때까지 연산자들을 출력
            else if(C == ')'){
                while(oprs.peek() != '(') sb.append(oprs.removeFirst());
                oprs.removeFirst();
            }
            else{
                // 스택이 비어있지 않고, 지금 만난 연산자 > 스택 연산자 일때까지 출력하며 pop
                while(!oprs.isEmpty() && order.get(C) <= order.get(oprs.peek())){
                    sb.append(oprs.removeFirst());
                }
                oprs.push(C);
            }
        }
        // 남아있는 연산자 pop
        while(!oprs.isEmpty()) sb.append(oprs.removeFirst());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        oprs = new ArrayDeque<>(N.length() / 2 - 1);

        postfix(N);
        System.out.println(sb);
    }
}
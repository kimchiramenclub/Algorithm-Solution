package Programmers.Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class pro_StockPrice {
    public static void main(String[] args) throws IOException, EmptyStackException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] prices = new int[6]; // 테스트 케이스 길이
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        // 1 7 3 4 2 5    5 1 2 1 1 0

        //주식 스택
        Stack<Integer> Stock = new Stack<>();
        // 주식의 원래 위치를 저장하는 스택
        Stack<Integer> Stock_Loc = new Stack<>();
        int[] Stock_rise = new int[prices.length];
        int tmp;

        // 첫 주식 집어넣음
        Stock.push(prices[0]);
        Stock_Loc.push(0);


        for (int i = 1; i < prices.length; i++) {

            // 하락장이면, 하락장인 애들 다 빼면서 버틴 턴만큼 Stock_rise에 부여.
            // 버틴 턴 :  i - 원래 location
            // empty 전까지만 뺴야함.
                while (Stock.peek() > prices[i]) {
                    Stock.pop();
                    tmp = Stock_Loc.peek();
                    Stock_rise[tmp] = i - Stock_Loc.pop();
                    // 다 빼서 peek할게 없고, 에러가 생기는 걸 피하기 위해
                    if(Stock.empty()) break;
                }


            // 하락장인 애들 빼고나서 push
            Stock.push(prices[i]);
            Stock_Loc.push(i);
        }

        //끝까지 상승장이던 애들 pop
        while (!Stock.empty()) {
            Stock.pop();
            tmp = Stock_Loc.peek();
            Stock_rise[tmp] = prices.length - Stock_Loc.pop() - 1;
        }

        for (int i : Stock_rise) {
            System.out.print(i + " ");
        }

        // 아이디어 1
        // 각 주식이 받을 수 있는 최대의 +의 배열을 만들고, ex) 4 3 2 1 0
        // 상승장인 주식은 계속 스택에 넣어두다가, 하락장이 오는 순간 빼서 그 주식의 인덱스를 체크하고,
        // 밖에 있는 동안 그 주식은 - 1씩
        // 하락이 오면 스택에서 빼버림. 스택에서 빼면서 그 스택의 위치 인덱스를 참조해서, 그만큼 더해줌.
        // 스택 [3,0], [2,1]


    }
}

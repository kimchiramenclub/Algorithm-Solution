package Stack_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class pro_CorrectBracket {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        boolean answer = false;

        Deque<Boolean> deque = new ArrayDeque<>();

        // 덱에 하나씩 넣으면서 만약 중간에 한번이라도 poll할때 null이면 false, String 맨마지막에 isEmpty면 true
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                deque.add(true);
            } else {
                if(deque.peek() == null) {
                    answer = false;
                } else { deque.poll(); }
            }
        }
        if(deque.isEmpty()) {answer = true;}

        System.out.println(answer);

        // 제일 쉬운 풀이 :  boolean 100,000 만들어서 전부 false여만 통과하게 한다던가?


    }
}


// 프로그래머스 코드
/*class Solution {
    boolean solution(String s) {

        Deque<Boolean> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                deque.add(true);
            } else {
                if(deque.peek() == null) {
                    return false;
                } else { deque.poll(); }
            }
        }

        if(deque.isEmpty()) {return true;}
        return false;
    }
}*/

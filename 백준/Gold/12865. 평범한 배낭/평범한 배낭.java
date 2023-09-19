import java.io.IOException;
import java.util.*;

class Main {
    /*  배낭 문제
        DP 혹은 백트래킹

        - A, B, C, D, ... 이렇게 물건 순서대로 가면서 무게 경우의 수를 축적.
          ex) A : 0, 6(A무게) , B : 0, 10(A+B), 4(B무게)
        - 같은 무게일 경우, 최대 가치만 저장.
        - 초기 capacity = K 인 HashMap을 이용해서 저장.

        문제 : Hashmap에서 ConcurrentModificationException(for문 돌면서 바로바로 수정) 하는 error 생김.
        수정 : temporary Hashmap을 선언해가면서 사용
      
     */

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();

        HashMap<Integer, Integer> cases = new HashMap<>();
        cases.put(0,0); // 초기값 대입

        for(int i=0;i<N;i++){
            int W = readInt();  // 물품 무게
            int V = readInt();  // 물품 가치

            // 경우의 수 변화를 담기 위한 임시 map
            HashMap<Integer, Integer> tmpCases = new HashMap<>(cases.size(), 1);

            for(Map.Entry<Integer, Integer> entry : cases.entrySet()){
                int weight = entry.getKey() + W;
                int value = entry.getValue() + V;

                // 1. 한계무계를 넘지 않아야 하고
                // 2. 해당 무게의 case가 없거나     (true일 경우, 바로 통과하므로 get메서드의 null 신경쓰지 않아도 됨)
                // 3. case가 있다면 무게 값이 업데이트 가능하거나
                if(weight <= K && (!cases.containsKey(weight) || value > cases.get(weight))){
                   tmpCases.put(weight, value);
                }
            }
            // 경우의 수 변화 반영
            cases.putAll(tmpCases);
        }

        // Map을 iterate하면서 최대 가치 합 구함
        int maxValue = 0;
        for(int value : cases.values()) maxValue = Math.max(maxValue, value);
        System.out.println(maxValue);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
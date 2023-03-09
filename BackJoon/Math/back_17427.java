package BackJoon.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class back_17427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long multSum = 0; // j개의 배수들이 공유하는 i의 합. 즉, i * j -> multSum에 계속 더하기

        for (int i = 1; i <= N; i++) {
            multSum += (long) N / i * i;
        }
        System.out.println(multSum);

    }
//	1~n에 대해 각각 약수의 배열을 구해야함. -> 약수 배열 구하기 최소화하는 게 필요! f(y) - 루트 값까지만 하게
//  n의 약수의 합, 그리고 그 약수 배열들의 합 g(x)

// 코드 줄이기 : 겹치는 값은 저장해두고 써도 되지 않을까? ex) 2(1,2) -> 4(4만 count해서 더하기)
// n의 배수인 숫자들은 다 n을 나중에 더해야하므로, n의 배수의 갯수를 구해 배열에 넣어주면 쉽게 풀 수 있음!
}

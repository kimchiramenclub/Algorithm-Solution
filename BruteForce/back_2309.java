package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Brute Force 알고리즘 이용

public class back_2309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] N = new int[9]; // 난쟁이 키 받는 배열
        int totalSum = 0;

        for(int i=0;i<9;i++) {
            N[i] = Integer.parseInt(br.readLine());
            totalSum += N[i]; // 9명의 난쟁이 키 총합
        }
        Arrays.sort(N); //정렬

        // 안 더할 2개를 고르기   i: 0~7, j:i+1~8
       Loop: for(int i=0;i<8;i++) {
            for(int j=i+1;j<9;j++) {
                if(totalSum - N[i] - N[j] == 100) { // 전체 합에서 두 난쟁이의 키를 빼서 100과 매치하는 지 확인
                    N[i]= N[j] = 0;
                    break Loop;
                }
            }
        }
        // 두 난쟁이의 키만 빼고 출력
        for(int i=0;i<9;i++) {
            if(N[i] != 0) {
                System.out.println(N[i]);
            }
        }

    }
}

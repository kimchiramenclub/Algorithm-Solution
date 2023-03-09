package BackJoon.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back_1037 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] div = new int[num];
		int N;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// num개의 약수 넣기
		for (int i = 0; i < num; i++) { div[i] = Integer.parseInt(st.nextToken()); }
		
		// 약수 배열 sort
		Arrays.sort(div);
		
		// 약수 곱을 통해 N 구하기
		N = div[0] * div[div.length-1];
		System.out.println(N);	
	}

}

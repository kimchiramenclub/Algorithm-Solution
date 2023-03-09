package BackJoon.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class back_4375 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n;
		int result;
		int count;
		// StringTokenizer는 여러 입력이 공백으로 같은 라인에 들어올 때 사용하므로, 한 줄 마다 받는데는 필요 없음.

		String inputCheck = ""; // input이 있는지 없는지 체크하는 용도
		// 1. 여러 개의 n 입력
		while ((inputCheck = br.readLine()) != null) {
			n = Integer.parseInt(inputCheck);
			result = 1; // n으로 나누어지는 지 체크
			count = 1; // 1로 이루어진 숫자의 자릿수

			// 2. result가 n으로 나눠질 때까지 자릿수를 올려가는 반복문 + 1의 갯수 count
			if (n != 1) {
				while (result % n != 0) {
					result = result * 10 + 1; 
					result %= n;
					count++;
				}
			}
			bw.write(count+"\n");
			bw.flush();
		}
		bw.close();
	}

}

// 풀면서 겪은 문제 : 
// 1. 자릿수가 int의 범위를 초과하면서 문제가 생김. long으로 바꿈
// 2. 시간초과. sysout을 써선가 해서 bufferedwriter로 바꿔줌. while문 돌 때마다 flush를 해줘야만 출력이 되길래, flush함.

// 3. 매번 n의 배수를 구하려고 result의 자릿수를 높여가다보니 길이가 너무 길어져서 시간초과가 뜸.
// 		나머지의 원리를 이용!	


package BackJoon.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class back_10430 {
	 static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        bw.write((A+B)%C+"\n");
        bw.write(((A%C) + (B%C))%C+"\n");
        
        // %C로 나머지를 여러 번 구하는 것은 사실 달라지는 것이 없음. A%C%C == A%C
        // 나머지를 구하기 전, 덧셈을 해도 같은 결과가 나온다.
        
        bw.write((A*B)%C+"\n");
        bw.write(((A%C) * (B%C))%C+"\n");
        
        // 곱셈도 곱셈 전, 곱셈 후 나머지를 구해도 같은 결과
        
        // 덧셈, 곱셈은 나머지에 대해서 열려있다.
        bw.flush();
        bw.close();
        
	}

}

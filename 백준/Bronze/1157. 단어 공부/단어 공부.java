import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 한번이라도 같은 값을 만나면 ?을 기억, 그리고 최고값이 갱신이 안되면 ?이 출력되게


    public static char solution(String word) {
        int[] alpha = new int[26];
        int temp = 0;
        char maxAlpha = '\u0000';

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) >= 'a') { alpha[word.charAt(i)-97]++;}
            else { alpha[word.charAt(i)-65]++;}
        }
        for(int i=0;i<26;i++){
            if(temp == alpha[i]) {maxAlpha = 63;}
            else if(temp < alpha[i]) {
                temp = alpha[i];
                maxAlpha = (char) (65+i);
            }
        }
        return maxAlpha;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        System.out.println(solution(word));
    }
}
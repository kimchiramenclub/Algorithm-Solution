import java.io.*;
import java.util.*;

class Main {
    /*
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] alphabet = new boolean[27];       // 그룹단어 판별용 array. 'a'~'z' -> 1~26의 idx에 해당됨
        int count = 0;  // 그룹단어 갯수

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int lastIdx = 0;    // 이전 char의 idx 기억용
            boolean notGroup = false;   // true면 group단어가 아님

            // 그룹단어 판정
            for (int j = 0; j < word.length(); j++) {
                int idx = word.charAt(j) & 31;  // 'a'~'z' & 15의 연산 결과 : 1 ~ 26
                if(!alphabet[idx]) alphabet[idx] = true;    // 문자가 처음 나타났다면
                else if(alphabet[idx] && idx == lastIdx) continue;  // 연속해서 나타난 문자라면
                else{   // 문자가 연속되지 않게 나타났다면, 그룹단어가 아니므로 break
                    notGroup = true;
                    break;
                }
                lastIdx = idx;
            }

            if(!notGroup) count++;
            Arrays.fill(alphabet, false);   
        }
        // 그룹단어 갯수 출력
        System.out.println(count);
    }
}
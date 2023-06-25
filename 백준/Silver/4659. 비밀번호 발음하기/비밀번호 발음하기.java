import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /* 문자열 문제
        - ArrayList 사용
        - charAt()으로 이동

        1. 모음 포함 조건
        2. 모음, 자음 연속 3개 조건
        3. 같은 글자 조건(ee, oo 제외)
     */

    public static List<String> testCase = new ArrayList<>();
    public static void solution() {
        boolean vowel; // 모음 포함 조건
        byte combo; // 모음, 자음 3개 연속 조건
        char[] prev = new char[2]; // 글자 중복 조건.   0 : 글자   1 : v(모음), c(자음)
        char next; // 현재 글자

        for(int i=0;i<testCase.size();i++){
            // 초기화
            vowel = false;
            combo = 1;
            String tmp = testCase.get(i);

            // 이전 char 저장
            prev[0] = tmp.charAt(0);
            if(prev[0] == 'a' ||prev[0] == 'e' ||prev[0] == 'i' ||prev[0] == 'o' ||prev[0] == 'u'){
                vowel = true;
                prev[1] = 'v';
            } else{prev[1] = 'c';}

            for(int j=1;j<tmp.length();j++){
                next = tmp.charAt(j);
                // 모음
                if(next == 'a' ||next == 'e' ||next == 'i' ||next == 'o' ||next == 'u'){
                    // 1. 모음 포함 체크
                    vowel = true;
                    // 2-1. 모음 연속 체크
                    if(prev[1] == 'v'){combo++;}
                    else{combo = 1;}
                    // 모음 정보 저장
                    prev[1] = 'v';
                }
                // 자음
                else{
                    // 2-2. 자음 연속 체크
                    if(prev[1] == 'c'){combo++;}
                    else{combo = 1;}
                    // 자음 정보 저장
                    prev[1] = 'c';
                }
                // 2,3 조건
                if(     (combo >= 3) ||
                        (next == prev[0] && next != 'e' && next != 'o')) {
                  testCase.set(i, "<"+tmp+"> is not acceptable.");
                  break;
                }
                // 글자 정보 저장
                prev[0] = next;
            }
            // 1 조건
            if(!vowel){testCase.set(i, "<"+tmp+"> is not acceptable.");}
            else{
                String temp = testCase.get(i);
                if(temp.charAt(temp.length()-1) != '.') {testCase.set(i, "<"+tmp+"> is acceptable.");}
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String tmp;

        while(true){
            tmp = br.readLine();
            if(tmp.equals("end")) break;
            testCase.add(tmp);
        }

        solution();
        for(String i : testCase){
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();
    }
}
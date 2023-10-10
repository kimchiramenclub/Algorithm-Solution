import java.io.*;

class Main {
    /*
     * */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        boolean[] alphabet = new boolean[27];       // 그룹단어 판별용 array. 'a'~'z' -> 1~26의 idx에 해당됨
        int count = 0;  // 그룹단어 갯수

        while (N-- > 0) if (check()) count++;
        System.out.println(count);  // 그룹단어 갯수 출력
    }

    // 그룹단어 판정
    static boolean check() throws IOException {
        String word = br.readLine();
        boolean[] chars = new boolean[27];  // 그룹단어 판별용 array. 'a'~'z' -> 1~26의 idx에 해당됨
        int prev = 0; // 이전 char check용

        for (int i = 0; i < word.length(); i++) {
            if (prev != word.charAt(i)) { // 연속되지 않은 문자
                if (chars[word.charAt(i) & 31]) return false;    // 이전에 발견한 문자라면, 그룹단어가 아니므로 break
                else {   // 문자가 처음 나타났다면
                    chars[word.charAt(i) & 31] = true;
                    prev = word.charAt(i);
                }
            }
        }
        return true;
    }
}

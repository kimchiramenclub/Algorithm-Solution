import java.io.*;
import java.util.*;

public class Main {
    /*  문자열 정렬
        - compare 재정의해서 정렬
        - 출력하면서 이전과 중복이면 건너뛰도록
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i=0;i<N;i++) words[i] = br.readLine();
        br.close();

        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                int cmp = o1.length()-o2.length();
                if(cmp == 0) cmp = o1.compareTo(o2);
                return cmp;
            }
        });

        // String을 null로 초기화 할 때, nullpointer Exception 피하기 위해
        // index 0 값 넣고 시작도 가능
        String tmp = "Not answer";
        for(String word : words){
            if(tmp.equals(word)) continue;
            tmp = word;
            sb.append(word).append("\n");
        }
        bw.write(sb.toString());    bw.flush();     bw.close();
    }
}

import java.io.*;
import java.util.*;

class Main {
    /*  조합론, 해시
        - 의상 종류별 해시맵 만들어서, key를 부위로 하기
        - 해시맵에 value를 Integer로 해서 갱신해가기

        각 부위별 n1,n2,n3 라고 하면,
        경우의 수 = (n1+1)*(n2+1)*(n3+1) -1     // 각 부위별로 (의상 수 + 그 부위 안 입을 때)* ...  -1  (알몸은 안되므로)

    */

    static int solution(String[][] tc) {
        HashMap<String, Integer> clothes = new HashMap<>();

        for(int i=0;i<tc.length;i++){
            // 의상 종류 저장
            if(!clothes.containsKey(tc[i][1])) clothes.put(tc[i][1], 1);
           // 같은 종류 의상이 더 있으면, 갯수 갱신
            else{
                int tmp = clothes.get(tc[i][1]);
                clothes.put(tc[i][1], tmp+1);
            }
        }

        int result = 1;
        // 경우의 수 구하기
        for(int count : clothes.values()) result *= (count+1);
        return result - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int i =0;i<T;i++){
            int N = Integer.parseInt(br.readLine());

            String[][] testCase = new String[N][2];
            for(int j=0;j<N;j++) {
                st = new StringTokenizer(br.readLine());
                testCase[j][0] = st.nextToken();
                testCase[j][1] = st.nextToken();
            }
            bw.write(solution(testCase)+"\n");
        }
        bw.flush();
    }
}

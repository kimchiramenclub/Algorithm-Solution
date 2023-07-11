import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*  이분탐색
        - 이진탐색 구현

        수정 :
        - 이진탐색 메서드 자체가 답을 return 하게 할려했는데, 기본 return 문이 필요해서 맨 마지막에 return null;
          이렇게 했더니 모든 답이 null이 되버림
        해결 :
        - 재귀에 들어갈 때 그 재귀에 return을 걸어버려서 결과를 찾으면 바로 return 하게 함.


     */
    public static String[] powerName;
    public static int[] powerValue;

    public static String solution(int title, int N){
        return binarySearch(title, 0, N-1);
    }

    public static String binarySearch(int title, int low, int high){
        // 값이 범위이기 때문에, 범위가 둘 중 하나로 줄어들었을 때 탈출문 발동
        if(high-low <= 1){
            if(title <= powerValue[low]) return powerName[low];
            else return powerName[high];
        }

        int mid = (low+high)/2;
        if(title <= powerValue[mid]) return binarySearch(title, low, mid);
        else if(title > powerValue[mid]) return binarySearch(title, mid+1, high);
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        powerName = new String[N];
        powerValue = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            powerName[i] = st.nextToken();
            powerValue[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++){
            int title = Integer.parseInt(br.readLine());
            bw.write(solution(title, N)+"\n");
        }

        bw.flush();
        bw.close();
    }
}

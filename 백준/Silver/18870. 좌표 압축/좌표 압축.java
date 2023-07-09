import java.io.*;
import java.util.*;

public class Main {

    /*  정렬
        - 압축 전 자신의 index를 기억시켜야 함.
        - 별도의 array를 만들 필요
     */

    public static int[] coords;

    public static void solution(int N) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] sorted = new int[N][2];
        for(int i=0;i<N;i++){
            sorted[i][0] = coords[i];
            sorted[i][1] = i; // 좌표의 index 저장
        }
        //index 정보를 담은 Array 정렬
        Arrays.sort(sorted, Comparator.comparingInt(a -> a[0]));

        // 맨 처음 index =0, max 값이 sorted[i][0]과 같아지도록 
        int index = -1;
        int max = Integer.MIN_VALUE;
        // 정렬한 array의 압축값을 원래의 array의 해당 index에 대입
        for(int[] X : sorted){
            if(X[0] > max){
                index++;
                max = X[0];

            }
            coords[X[1]] = index;
        }

        for(int i : coords){
            bw.write(i+" ");
        }
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        coords = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            coords[i] = Integer.parseInt(st.nextToken());
        }

        solution(N);

    }
}
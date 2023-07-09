import java.io.*;
import java.util.*;

public class Main {

    /* 그리디
        - Local의 최적 판단이 전체적으로 최적인 판단으로 이어짐
        - 가장 회의시간이 짧은 순?
        - 시작시간은 절대 우선이 아님. 0-24 같은 경우 회의 한번에 끝
        - 끝나는 시간 우선?
        - 1. 끝나는 시간 우선 -> 회의시간 짧기 우선

        문제 :
        - 시작시간, 끝시간이 같을 수도 있음.
        - 1-3 , 3-3 같은 경우, 회의시간 짧은 순 하면 3-3이 앞에 와서 에러.
     */

    public static long solution(int N, long[][] session) {
        long count = 0;
        long time = 0;

        // 1. 끝나는 시간 우선, 2. 회의 시간 짧기 우선
        Arrays.sort(session, Comparator.comparingLong((long[] a) -> a[1]).thenComparingLong(a -> a[0]));

//        System.out.println();
//        for(long[] i : session){
//            for(long j : i) System.out.print(j+" ");
//            System.out.println();
//        }
        for (int i = 0; i < N; i++) {
            if (session[i][0] >= time) {
                count++;
                time = session[i][1];
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] session = new long[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            session[i][0] = Long.parseLong(st.nextToken());
            session[i][1] = Long.parseLong(st.nextToken());
        }

        System.out.println(solution(N, session));
    }
}
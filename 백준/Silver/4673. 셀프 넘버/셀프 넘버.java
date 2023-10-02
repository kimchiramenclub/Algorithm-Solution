import java.io.*;

class Main {
    /*  브루트포스
     * */

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = 10000;
        boolean[] notSelfNums = new boolean[N+1];

        for(int i=1;i<=N;i++){
            int tmp = i;
            int num = tmp;
            // d(n) 계산
            do num += (tmp % 10);
            while((tmp /= 10) != 0);
            
            if(num <= N) notSelfNums[num] = true; // d(n) <= 10000이면, selfNum이 아니므로 true
            if(!notSelfNums[i]) sb.append(i).append('\n');  // 현재 i가 selfNum이면 출력
        }
        System.out.println(sb);
    }
}

import java.io.*;

class Main {
    /*  문자열
        - I가 나올때마다 체크하는 경우 시간복잡도가 O(MN)이 되므로, 비효율적
        - 전체 문자열에서 IOIO.....I 패턴이 얼마나 길게 이어지는 지 체크하고, 그 패턴 안에서 P가 몇개 나올 수 있는 지 바로 계산
        - IOIO....I 에서,      P 포함 갯수 = (연속적인 I 갯수)-N
    */

    static long solution(int N, int M, String S){
        long sum = 0;

        // 마지막 2개는 OI가 아니라면 체크 필요가 없으므로 M-2까지
        for(int i=0;i<M-2;i++){
            if(S.charAt(i) == 'I'){
                int len = 1;
                while(i+2 < M && S.charAt(i+1) == 'O' && S.charAt(i+2) == 'I'){
                    i+=2;
                    len++;
                }
                // 문자열 P가 포함될 수 있으면, 그 갯수를 구함.  len 2 : IOI   N 1 : IOI
                if(len > N) sum += (len-N);
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        System.out.println(solution(N, M, S));
    }
}
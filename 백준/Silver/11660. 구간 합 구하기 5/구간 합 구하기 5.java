import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    /*  누적합
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();


        // int N = readInt();
        int N = Integer.parseInt(st.nextToken());
//        int M = readInt();
        int M = Integer.parseInt(st.nextToken());

        int[][] sum = new int[N+1][N+1]; // 좌표가 (1,1)부터 시작이므로

        // 입력문을 통해 누적합 생성
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
//                sum[i][j] = readInt();
                sum[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }

        // 누적합을 이용해 구간합 계산
        int x1, y1, x2, y2;
        int areaSum = 0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
//            x1 = readInt();
            x1 = Integer.parseInt(st.nextToken());
//            y1 = readInt();
            y1 = Integer.parseInt(st.nextToken());
//            x2 = readInt();
            x2 = Integer.parseInt(st.nextToken());
//            y2 = readInt();
            y2 = Integer.parseInt(st.nextToken());
//            sb.append("x1 :"+x1+" y1 : "+y1+" x2 :"+x2+" y2 :"+y2).append("\n");
            // sum 배열의 비어있는 0행, 0열은 0으로 초기화 되어 있어서, 자동으로 s[0]-1, s[1]-1 등이 0으로 알아서 보정됨.
            // ex) (1,1) (1,1) -> sum(1,1) - sum(1,0) - sum(0,1) + sum(0,0)  = sum(1,1)
            areaSum = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];

            sb.append(areaSum).append("\n");
        }
        System.out.print(sb);

    }

//    // 입력에서 숫자를 읽는 메서드
//    static int readInt() throws IOException{
//        int c, n = 0;
//        while((c = System.in.read()) > 32) n = (n << 3) + (n << 2) + (c & 15);
//        // & 연산 : bit를 비교해서 둘다 1일 경우에만 1 반환 (둘 다 0일 경우 0)
//        // 15 -> 0000 1111 이라서, '0'~'9' -> 0011 0000 ~ 0011 1001  -- & -- > 0000 0000 ~ 0000 1001 -> 0 ~ 9
//        // 뒤의 4자리 중 1인 숫자만 남기므로, 결국 숫자 0~9로 변환
//        return n;
//    }
}